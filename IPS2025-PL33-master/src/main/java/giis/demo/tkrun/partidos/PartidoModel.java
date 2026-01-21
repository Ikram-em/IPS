package giis.demo.tkrun.partidos;

import java.util.List;

import giis.demo.util.Database;

public class PartidoModel {
	private Database database = new Database();
	
	private final static String SQL_FIND_FUTURE_MATCHES = "SELECT p.fecha, p.id_partido, ei.nombre AS nombreEquipoInterno, ee.nombre AS nombreEquipoExterno, EXISTS (SELECT 1 FROM PrecioEntradas pe WHERE pe.id_partido = p.id_partido) AS priced" + 
			" FROM Partido p" +
			" JOIN Equipo ei ON p.id_equipo = ei.id_equipo" +
			" JOIN EquipoExterno ee ON p.id_equipo_externo = ee.id_equipo" +
			" WHERE p.fecha > datetime('now') AND EXISTS (SELECT 1 FROM PrecioEntradas pe WHERE pe.id_partido = p.id_partido)";
	
	private final static String SQL_FIND_MATCHES = "SELECT p.fecha, p.id_partido, ei.nombre AS nombreEquipoInterno, ee.nombre AS nombreEquipoExterno, EXISTS (SELECT 1 FROM PrecioEntradas pe WHERE pe.id_partido = p.id_partido) AS priced" + 
			" FROM Partido p" +
			" JOIN Equipo ei ON p.id_equipo = ei.id_equipo" +
			" JOIN EquipoExterno ee ON p.id_equipo_externo = ee.id_equipo";
	
	public List<PartidoDTO> getPartidosFuturosConPrecio() {
		return database.executeQueryPojo(PartidoDTO.class, SQL_FIND_FUTURE_MATCHES);
	}
	
	public List<PartidoDTO> getPartidos() {
		return database.executeQueryPojo(PartidoDTO.class, SQL_FIND_MATCHES);
	}
}
