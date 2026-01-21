package giis.demo.tkrun.accion.campana;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.accion.AccionistaDTO;
import giis.demo.tkrun.accion.AccionistaSimpleDTO;
import giis.demo.util.Database;

public class CampanaModel {
	
	private Database dataBase = new Database();
	
	private final static String FIND_ACTIVE_CAMPAIGNS = "SELECT id_campana, fase, estado, n_accionesDisponibles, n_accionesVendidas, fecha_creacion" + 
			" FROM Campana WHERE estado = 'Activa'";
	private final static String FIND_PHASE_THREE_CAMPAIGNS = "SELECT id_campana FROM Campana WHERE fase = ?";
	private final static String FIND_LAST_CAMPANA = "SELECT id_campana, fase, estado, n_accionesDisponibles, n_accionesVendidas, fecha_creacion FROM Campana ORDER BY id_campana DESC LIMIT 1";
	private final static String FIND_LAST_ACTIVE_CAMPANA = "SELECT id_campana, fase, estado, n_accionesDisponibles, n_accionesVendidas, fecha_creacion FROM Campana WHERE estado = 'Activa' ORDER BY id_campana DESC LIMIT 1";
	private final static String SET_N_ACCIONES_CAMPANA = "INSERT INTO Campana (fase, estado, n_accionesDisponibles, n_accionesVendidas, fecha_creacion) VALUES (?, ?, ?, ?, ?)";
	private final static String UPDATE_FASE_CAMPANA = "UPDATE Campana SET fase = ? WHERE estado = 'Activa'";
	private final static String UPDATE_ESTADO_CAMPANA = "UPDATE Campana SET estado = ? WHERE estado = 'Activa'";
	private final static String FIND_N_ACCIONES_ACCIONISTA = "SELECT COUNT(*) AS num_acciones FROM Accion WHERE id_accionista = ?";
	private final static String BUY_N_ACCIONES_CAMPANA = "UPDATE Campana SET n_accionesVendidas = ? WHERE estado = 'Activa'";
	
	/******************************************************************************************************************************/
	
	private final static String FIND_N_ACCIONES_ANTES = "SELECT COUNT(*) FROM Accion WHERE id_accionista = ? AND fecha_creacion < ?";
	private final static String FIND_N_ACCIONES_EN_CAMPANA = "SELECT COUNT(*) FROM Accion WHERE id_accionista = ? AND id_campana = ?";
	private final static String INSERT_INGRESO = 
		    "INSERT INTO ingresos (tipo, concepto, fecha_hora, total) VALUES (?, ?, DATETIME('now'), ?)";
	private final static String CREATE_SHARES = "INSERT INTO Accion (id_accionista, fecha_creacion, id_campana) VALUES (?, datetime('now'), ?)";
	
	public List<CampanaDTO> getCampanasActivas() {
		return dataBase.executeQueryPojo(CampanaDTO.class, FIND_ACTIVE_CAMPAIGNS);
	}
	
	public List<CampanaDTO> getCampanasEnFaseDada(int fase) {
		return dataBase.executeQueryPojo(CampanaDTO.class, FIND_PHASE_THREE_CAMPAIGNS, fase);
	}
	
	public boolean getEstadoCampana() {
		List<CampanaDTO> campanas = dataBase.executeQueryPojo(CampanaDTO.class, FIND_LAST_CAMPANA);
		if(campanas.size() > 0) {
			CampanaDTO campana = campanas.get(0);
			if (campana.getEstado().equalsIgnoreCase("Activa")) return true;
			else return false;
		}
		else return false;
	}
	
	public int getNAccionesAccionista(int id_accionista) {
		List<Object[]> result = dataBase.executeQueryArray(FIND_N_ACCIONES_ACCIONISTA, id_accionista);

		if (result.isEmpty() || result.get(0).length == 0) {
			return 0;
		}

		return ((Number) result.get(0)[0]).intValue();
	}

	/**
	 * Registra un ingreso asociado a la compra de acciones de la campaña.
	 * @param accionistaId id del accionista que compra
	 * @param nAcciones número de acciones compradas
	 * @param precioUnitario precio de cada acción
	 */
	public void registrarIngresoCompraAcciones(int accionistaId, int nAcciones, double precioUnitario) {
	    double total = nAcciones * precioUnitario;
	    String concepto = "Compra de " + nAcciones + " acciones por accionista ID " + accionistaId;
	    dataBase.executeUpdate(INSERT_INGRESO, "CAMPANA ACCIONISTAS", concepto, total);
	}
	
	public CampanaDTO getCampana() {
		List<CampanaDTO> campanas = dataBase.executeQueryPojo(CampanaDTO.class, FIND_LAST_CAMPANA);
		return campanas.get(0);
	}
	
	public CampanaDTO getActiveCampaign() {
		List<CampanaDTO> campanas = dataBase.executeQueryPojo(CampanaDTO.class, FIND_LAST_ACTIVE_CAMPANA);
		if (campanas.size() == 0) {
			return null;
		}
		return campanas.get(0);
	}

	public Integer setNAccionesCampana(int n) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return dataBase.executeInsertAndReturnId(SET_N_ACCIONES_CAMPANA, 1, "Activa", n, 0,
				sdf.format(new Date(System.currentTimeMillis()))).intValue();
	}

	public void adelantarFaseCampana() {
		CampanaDTO campana = dataBase.executeQueryPojo(CampanaDTO.class, FIND_LAST_CAMPANA).get(0);
		dataBase.executeUpdate(UPDATE_FASE_CAMPANA, campana.getFase() + 1);
	}

	public void finalizarCampana() {
		dataBase.executeUpdate(UPDATE_ESTADO_CAMPANA, "Inactiva");
	}

	public int getNAccionesComprarFase1(int accionista_id, String creacionCampana, int campaignId) {
		List<Object[]> antesCampana = dataBase.executeQueryArray(
				FIND_N_ACCIONES_ANTES, accionista_id,
				creacionCampana);
		int accionesAntes = 0;
		if (!antesCampana.isEmpty() && antesCampana.get(0).length > 0) {
			accionesAntes = ((Number) antesCampana.get(0)[0]).intValue();
		}

		// Conteo de acciones en la campaña actual
		List<Object[]> enCampana = dataBase.executeQueryArray(
				FIND_N_ACCIONES_EN_CAMPANA, accionista_id, campaignId);
		int accionesEnCampana = 0;
		if (!enCampana.isEmpty() && enCampana.get(0).length > 0) {
			accionesEnCampana = ((Number) enCampana.get(0)[0]).intValue();
		}


		return accionesAntes - accionesEnCampana;
	}


	public void comprarAcciones(AccionistaSimpleDTO accionista, int nAccionesAComprar) {
		// Registrar ingreso
		System.out.println("Cant acciones: " + nAccionesAComprar);
		double precioUnitario = 10.0; // o el precio que corresponda según la fase/campaña
		registrarIngresoCompraAcciones(accionista.getId_accionista(), nAccionesAComprar, precioUnitario);
		CampanaDTO campana = getCampana();
		
		List<Object[]> newShares = new ArrayList<>();
		for (int i = 0; i < nAccionesAComprar; i++) {
			newShares.add(new Object[] {
					accionista.getId_accionista(),
					campana.getId_campana()
			});
		}
		
		dataBase.executeBatchInsert(CREATE_SHARES, newShares);
		dataBase.executeUpdate(BUY_N_ACCIONES_CAMPANA, campana.getN_accionesVendidas() + nAccionesAComprar);
	}
}
