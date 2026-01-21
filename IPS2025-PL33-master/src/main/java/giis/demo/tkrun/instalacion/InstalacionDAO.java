package giis.demo.tkrun.instalacion;

import java.util.List;

import giis.demo.util.Database;

public class InstalacionDAO {
	private Database database = new Database();
	
	private final static String SQL_GET_FACILITIES = "SELECT * from Instalacion";
	private final static String GET_INSTALACION_BY_NAME = "SELECT id FROM Instalacion WHERE nombre = ?";
	private final static String SQL_GET_FACILITY_BY_NAME = "SELECT * FROM Instalacion i WHERE i.nombre = ?";
	
	public List<InstalacionDTO> getInstalaciones() {
		return database.executeQueryPojo(InstalacionDTO.class, SQL_GET_FACILITIES);
	}
	
	public int getIdInstalacion(String nombreInstalacion) {
		List<Object[]> filas = database.executeQueryArray(GET_INSTALACION_BY_NAME, nombreInstalacion);
		if (filas != null && !filas.isEmpty()) {
			return ((Number) filas.get(0)[0]).intValue();
		}
		return -1;
	}

	public void guardarLaborJardineria() {

	}

	public InstalacionDTO getFacilityByName(String name) {
		List<InstalacionDTO> instalacionList = database.executeQueryPojo(InstalacionDTO.class, SQL_GET_FACILITY_BY_NAME, name);
		return instalacionList.get(0);
	}
}
