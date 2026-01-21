package giis.demo.tkrun.partidos.entradas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import giis.demo.util.Database;

public class EntradasModel {
	private Database database = new Database();
	
	private final static String SQL_FIND_TRIBUNES = "SELECT * FROM Tribune";
	private final static String SQL_FIND_SECTIONS = "SELECT * FROM Section";
    
	private final static String SQL_CREATE_SEATS_SELECTED = "INSERT INTO AsientoReservado (id_partido, id_tribune, id_section, fila, cantidad_asientos, asiento_inicial, id_purchase) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private final static String SQL_CREATE_PURCHASE = "INSERT INTO Purchase (id_user, fecha, precio, tipo) "
			+ "VALUES (?, datetime('now'), ?, ?)";
	
	private final static String SQL_GET_TICKET_PRICE_BY_SECTION = "SELECT pe.precio FROM PrecioEntradas pe " +
			"WHERE pe.id_partido = ? AND pe.id_tribune = ? AND id_section = ? " +
			"LIMIT 1";
	
	private final static String SQL_CREATE_MATCH_TICKET_PRICES = "INSERT INTO PrecioEntradas (id_partido, id_tribune, id_section, precio) VALUES (?, ?, ?, ?)";
	
	public List<ZoneDTO> getTribunes() {
		return database.executeQueryPojo(ZoneDTO.class, SQL_FIND_TRIBUNES);
	}
	
	public List<ZoneDTO> getSections() {
		return database.executeQueryPojo(ZoneDTO.class, SQL_FIND_SECTIONS);
	}
	
	/**
     * Busca una fila con asientos consecutivos libres
     */
    public FilaDisponibleDTO getAvailableRow(int idPartido, ZoneDTO tribuna, ZoneDTO seccion, int asientosPedidos) {
        Map<Integer, Boolean> ocupados = getSeatsOccupiedCombined(tribuna, seccion, idPartido);

        for (int fila = 1; fila <= 10; fila++) {
            for (int asientoInicial = 1; asientoInicial + asientosPedidos - 1 <= 15; asientoInicial++) {
                boolean bloqueLibre = true;
                for (int a = asientoInicial; a < asientoInicial + asientosPedidos; a++) {
                    if (ocupados.getOrDefault((fila - 1) * 15 + a, false)) {
                        bloqueLibre = false;
                        asientoInicial = a; // saltar al asiento siguiente
                        break;
                    }
                }
                if (bloqueLibre) {
                    return new FilaDisponibleDTO(fila, asientoInicial - 1);
                }
            }
        }
        return null; // No hay bloque disponible
    }
	
	public double saveSeats(int idPartido, int idTribuna, int idSeccion, int fila, int asientosPedidos, int asientoInicial, int userId) {
        List<PrecioEntradaDTO> precioEntradasList = database.executeQueryPojo(PrecioEntradaDTO.class,
                SQL_GET_TICKET_PRICE_BY_SECTION, idPartido, idTribuna, idSeccion);
        double precioEntradas = precioEntradasList.get(0).getPrecio();
        Number idPurchase = database.executeInsertAndReturnId(SQL_CREATE_PURCHASE, userId,
                asientosPedidos * precioEntradas, "Entradas");
        database.executeUpdate(SQL_CREATE_SEATS_SELECTED, idPartido, idTribuna, idSeccion, fila,
                asientosPedidos, asientoInicial, idPurchase);
        return asientosPedidos * precioEntradas;
    }

    /**
     * Obtiene los asientos ocupados combinando AsientoReservado y PurchaseAbono
     */
    public Map<Integer, Boolean> getSeatsOccupiedCombined(ZoneDTO tribuna, ZoneDTO seccion, int idPartido) {
        Map<Integer, Boolean> ocupados = new HashMap<>();
        String sql = "SELECT fila, asiento_inicial AS asiento, cantidad_asientos FROM AsientoReservado " +
                     "WHERE id_partido = ? AND id_tribune = ? AND id_section = ? " +
                     "UNION ALL " +
                     "SELECT fila, idAsiento AS asiento, 1 AS cantidad_asientos FROM PurchaseAbono " +
                     "WHERE tribuna = ? AND seccion = ?";

        List<Object[]> rows = database.executeQueryArray(sql,
                idPartido, tribuna.getId(), seccion.getId(),
                tribuna.getNombre(), seccion.getNombre());

        for (Object[] row : rows) {
            int fila = Integer.parseInt(row[0].toString());
            int asientoInicial = Integer.parseInt(row[1].toString());
            int cantidad = Integer.parseInt(row[2].toString());
            for (int i = 0; i < cantidad; i++) {
                ocupados.put((fila - 1) * 15 + asientoInicial + i, true);
            }
        }
        return ocupados;
    }
	
	public void saveTicketPrices(int idPartido, Map<Integer, Map<Integer, Double>> priceByTribuneAndSection) {
		List<Object[]> dbPrices = new ArrayList<>();
		for (Map.Entry<Integer, Map<Integer, Double>> tribunePrices : priceByTribuneAndSection.entrySet()) {
			int tribuneId = tribunePrices.getKey();
			for (Map.Entry<Integer, Double> sectionPrice : tribunePrices.getValue().entrySet()) {
				dbPrices.add(new Object[] {
						idPartido,
						tribuneId,
						sectionPrice.getKey(),
						sectionPrice.getValue()
				});
			}
		}
			
		database.executeBatchInsert(SQL_CREATE_MATCH_TICKET_PRICES, dbPrices);
	}
}

