
package giis.demo.tkrun.partidos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database;

public class PartidoDAO {

    private Database db = new Database();
    
	private static final String SQL_FIND_PARTIDO_BY_ID = "SELECT p.id_partido AS idPartido, p.fecha, p.hora_inicio AS horaInicio, p.hora_fin AS horaFin, "
			+ "ei.nombre AS equipoLocal, " + "ee.nombre AS equipoVisitante, " + "ins.nombre AS nombre_instalacion, "
			+ "p.id_instalacion, " + "p.tieneSuplemento, " + "p.precioSuplemento " + "FROM Partido p "
			+ "LEFT JOIN Equipo ei ON p.id_equipo = ei.id_equipo "
			+ "LEFT JOIN EquipoExterno ee ON p.id_equipo_externo = ee.id_equipo "
			+ "LEFT JOIN Instalacion ins ON p.id_instalacion = ins.id " + "WHERE p.id_partido = ?";
    

    /**
     * Inserta un nuevo partido en la base de datos.
     */
	public Integer insertarPartido(Date fecha, LocalTime horaInicio, LocalTime horaFin,
            String nombreLocal, String nombreVisitante, int id_instalacion,
            boolean tieneSuplemento, double precioSuplemento) {

        int idLocal = obtenerIdEquipoLocal(nombreLocal);
        int idVisitante = obtenerIdEquipoExterno(nombreVisitante);

        String sql = "INSERT INTO Partido (fecha, hora_inicio, hora_fin, id_equipo, id_equipo_externo, id_instalacion, tieneSuplemento, precioSuplemento) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Number idGenerado = db.executeInsertAndReturnId(sql,
            fecha.toString(),
            horaInicio.toString(),
            horaFin.toString(),
            idLocal,
            idVisitante,
            id_instalacion,
            tieneSuplemento ? 1 : 0,
            precioSuplemento
        );

        return idGenerado.intValue();
    }



    public List<Partido> obtenerTodos() {
    	String sql =
    		    "SELECT p.id_partido AS idPartido, " +
    		    "e.nombre AS equipoLocal, " +
    		    "ex.nombre AS equipoVisitante, " +
    		    "p.fecha, " +
    		    "p.hora_inicio AS horaInicio, " +
    		    "p.hora_fin AS horaFin, " +
    		    "p.tieneSuplemento AS tieneSuplemento, " +
    		    "p.precioSuplemento AS precioSuplemento, " +
    		    "p.id_instalacion AS id_instalacion, " +
    		    "i.nombre AS nombre_instalacion " +
    		    "FROM Partido p " +
    		    "JOIN Equipo e ON p.id_equipo = e.id_equipo " +
    		    "JOIN EquipoExterno ex ON p.id_equipo_externo = ex.id_equipo " +
    		    "JOIN Instalacion i ON p.id_instalacion = i.id";


        return db.executeQueryPojo(Partido.class, sql);
    }

    public List<String> obtenerEquiposLocales() {
        String sql = "SELECT nombre FROM Equipo ORDER BY nombre";
        List<Object[]> filas = db.executeQueryArray(sql);
        List<String> nombres = new ArrayList<>();

        for (Object[] fila : filas) {
            nombres.add((String) fila[0]);
        }
        return nombres;
    }

    public List<String> obtenerEquiposExternos() {
        String sql = "SELECT nombre FROM EquipoExterno ORDER BY nombre";
        List<Object[]> filas = db.executeQueryArray(sql);
        List<String> nombres = new ArrayList<>();

        for (Object[] fila : filas) {
            nombres.add((String) fila[0]);
        }
        return nombres;
    }

    /**
     * Devuelve el id de un equipo local por nombre.
     */
    private int obtenerIdEquipoLocal(String nombre) {
        String sql = "SELECT id_equipo FROM Equipo WHERE nombre = ?";
        List<Object[]> res = db.executeQueryArray(sql, nombre);
        if (res.isEmpty()) throw new RuntimeException("Equipo local no encontrado: " + nombre);
        return ((Number) res.get(0)[0]).intValue();
    }

    private int obtenerIdEquipoExterno(String nombre) {
        String sql = "SELECT id_equipo FROM EquipoExterno WHERE nombre = ?";
        List<Object[]> res = db.executeQueryArray(sql, nombre);
        if (!res.isEmpty()) {
            return ((Number) res.get(0)[0]).intValue();
        }
        String insertSql = "INSERT INTO EquipoExterno (nombre) VALUES (?)";
        db.executeUpdate(insertSql, nombre);
        res = db.executeQueryArray(sql, nombre);
        return ((Number) res.get(0)[0]).intValue();
    }



	public Partido getPartido(Integer id_partido) {
		List<Partido> lista = db.executeQueryPojo(Partido.class, SQL_FIND_PARTIDO_BY_ID, id_partido);

	    if (lista.isEmpty()) {
	        return null;
	    }

	    return lista.get(0);
	}
	
	public void actualizarMarcador(int idPartido, int golesLocal, int golesVisitante) {
        String sql = "UPDATE Partido SET goles_local = ?, goles_visitante = ? WHERE id_partido = ?";
        try (Connection conn = db.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
               ps.setInt(1, golesLocal);
               ps.setInt(2, golesVisitante);
               ps.setInt(3, idPartido);
               ps.executeUpdate();
           } catch (SQLException e) {
               e.printStackTrace();
           }
    }
	
	/**
	 * Devuelve los goles local y visitante del partido indicado.
	 * No modifica ninguna parte existente de la aplicación.
	 */
	public int[] getGolesPartido(int idPartido) {
		String SQL_GET_GOLES_PARTIDO =
			    "SELECT goles_local, goles_visitante FROM Partido WHERE id_partido = ?";

	    List<Object[]> lista = db.executeQueryArray(SQL_GET_GOLES_PARTIDO, idPartido);

	    int golesLocal = 0;
	    int golesVisitante = 0;

	    if (lista != null && !lista.isEmpty()) {
	        Object[] fila = lista.get(0);
	        if (fila[0] != null) golesLocal = ((Number) fila[0]).intValue();
	        if (fila[1] != null) golesVisitante = ((Number) fila[1]).intValue();
	    }

	    return new int[] { golesLocal, golesVisitante };
	}


}



