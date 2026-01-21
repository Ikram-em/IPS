package giis.demo.tkrun.teammanagement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database;

public class MetodosJugador {
	
	 private Database db;
	
	 private static final String SQL_GET_ALL_PLAYERS = 
			 "SELECT e.id_empleado, e.dni, e.nombre, e.apellido, e.fecha_nacimiento, e.telefono, e.salario_anual " +
					    "FROM Empleado e " +
					    "JOIN EmpleadoDeportivo ed ON e.id_empleado = ed.id_empleado " +
					    "JOIN Posicion pos ON ed.id_posicion = pos.id_posicion " +
					    "LEFT JOIN Plantilla p ON e.id_empleado = p.id_jugador " +  // solo jugadores con equipo
					    "WHERE pos.nombre = 'Jugador' AND p.id_jugador IS NULL";

	private static final String SQL_GET_BY_ID = "SELECT e.id_empleado, e.dni, e.nombre, e.apellido, e.fecha_nacimiento, e.telefono, e.salario_anual " +
					    "FROM Empleado e " +
					    "JOIN EmpleadoDeportivo ed ON e.id_empleado = ed.id_empleado " +
					    "JOIN Posicion pos ON ed.id_posicion = pos.id_posicion " +
					    "LEFT JOIN Plantilla p ON e.id_empleado = p.id_jugador " + 
					    "WHERE pos.nombre = 'Jugador' AND p.id_jugador = ?";
	
	public MetodosJugador() {
        db = new Database(); // Usa la configuración de conexión de la clase Database
    }
	
	public List<PersonDTO> getAllPlayers() {
		List<PersonDTO> jugadores = new ArrayList<>();
		 try (Connection conn = db.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL_PLAYERS);
	             ResultSet rs = pstmt.executeQuery()) {

	            while (rs.next()) {
	            	PersonDTO jugador = new PersonDTO(
	                    rs.getInt("id_empleado"),
	                    rs.getString("dni"),
	                    rs.getString("nombre"),
	                    rs.getString("apellido"),
	                    Date.valueOf(rs.getString("fecha_nacimiento")),
	                    rs.getInt("telefono"),
	                    rs.getDouble("salario_anual")
	                );
	                jugadores.add(jugador);
	            }

	        } catch (SQLException e) {
	            System.err.println("Error al obtener los jugadores: " + e.getMessage());
	        }

	        return jugadores;
	}
	
	public List<PersonDTO> getPlayersOfTeam(int equipoId) {
	    List<PersonDTO> jugadores = new ArrayList<>();
	    String sql = "SELECT e.id_empleado, e.dni, e.nombre, e.apellido, e.fecha_nacimiento, e.telefono, e.salario_anual " +
	                 "FROM Empleado e " +
	                 "JOIN Plantilla p ON e.id_empleado = p.id_jugador " +
	                 "WHERE p.id_equipo = ?";

	    try (Connection conn = db.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, equipoId);
	        ResultSet rs = ps.executeQuery();
	        while(rs.next()) {
	            PersonDTO jugador = new PersonDTO(
	                rs.getInt("id_empleado"),
	                rs.getString("dni"),
	                rs.getString("nombre"),
	                rs.getString("apellido"),
	                Date.valueOf(rs.getString("fecha_nacimiento")), 
	                rs.getInt("telefono"),
	                rs.getDouble("salario_anual")
	            );
	            jugadores.add(jugador);
	        }
	    } catch(Exception ex) {
	        ex.printStackTrace();
	    }
	    return jugadores;
	}
	
	
	public List<PersonDTO> getJugadoresConFranjas() {
	    List<PersonDTO> jugadores = new ArrayList<>();

	    String sql = "SELECT DISTINCT e.id_empleado, e.dni, e.nombre, e.apellido, e.fecha_nacimiento, e.telefono, e.salario_anual " +
                "FROM Empleado e " +
                "INNER JOIN EmpleadoDeportivo ed ON e.id_empleado = ed.id_empleado " +
                "JOIN Posicion pos ON ed.id_posicion = pos.id_posicion " +
                "INNER JOIN FranjaEntrevista f ON e.id_empleado = f.id_jugador " +
                "WHERE pos.nombre = 'Jugador' " +
                "ORDER BY e.apellido, e.nombre";

	    try (Connection conn = db.getConnection();
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery()) {

	           while (rs.next()) {
	               PersonDTO jugador = new PersonDTO(
	                   rs.getInt("id_empleado"),
	                   rs.getString("dni"),
	                   rs.getString("nombre"),
	                   rs.getString("apellido"),
	                   Date.valueOf(rs.getString("fecha_nacimiento")),
	                   rs.getInt("telefono"),
	                   rs.getDouble("salario_anual")
	               );
	               jugadores.add(jugador);
	           }

	       } catch (SQLException e) {
	           System.err.println("Error al obtener jugadores con franjas: " + e.getMessage());
	       }

	       return jugadores;
	}

	public List<PersonDTO> getJugadoresDeEntrenadorConFranjas(int idEntrenador) {
		List<PersonDTO> jugadores = new ArrayList<>();

	    String sql = "SELECT DISTINCT e.id_empleado, e.dni, e.nombre, e.apellido, e.fecha_nacimiento, e.telefono, e.salario_anual " +
                "FROM Empleado e " +
                "INNER JOIN EmpleadoDeportivo ed ON e.id_empleado = ed.id_empleado " +
                "JOIN Posicion pos ON ed.id_posicion = pos.id_posicion " +
                "INNER JOIN FranjaEntrevista f ON e.id_empleado = f.id_jugador " +
                "JOIN Plantilla pl ON pl.id_jugador = e.id_empleado " +
                "JOIN Equipo eq ON eq.id_equipo = pl.id_equipo " +
                "WHERE pos.nombre = 'Jugador' AND (eq.id_primer_entrenador = ? OR eq.id_segundo_entrenador = ?)" +
                "ORDER BY e.apellido, e.nombre";

	    try (Connection conn = db.getConnection();
	            PreparedStatement ps = conn.prepareStatement(sql)) {
	    		
	    		ps.setInt(1, idEntrenador);
	    		ps.setInt(2, idEntrenador);
	            ResultSet rs = ps.executeQuery();

	           while (rs.next()) {
	               PersonDTO jugador = new PersonDTO(
	                   rs.getInt("id_empleado"),
	                   rs.getString("dni"),
	                   rs.getString("nombre"),
	                   rs.getString("apellido"),
	                   Date.valueOf(rs.getString("fecha_nacimiento")),
	                   rs.getInt("telefono"),
	                   rs.getDouble("salario_anual")
	               );
	               jugadores.add(jugador);
	           }

	       } catch (SQLException e) {
	           System.err.println("Error al obtener jugadores con franjas: " + e.getMessage());
	       }

	       return jugadores;
	}
	
	public List<PersonDTO> getAllPlayersIncludingAssigned() {
	    List<PersonDTO> jugadores = new ArrayList<>();
	    String sql = "SELECT e.id_empleado, e.dni, e.nombre, e.apellido, e.fecha_nacimiento, e.telefono, e.salario_anual " +
	                 "FROM Empleado e " +
	                 "JOIN EmpleadoDeportivo ed ON e.id_empleado = ed.id_empleado " +
	                 "JOIN Posicion pos ON ed.id_posicion = pos.id_posicion " +
	                 "WHERE pos.nombre = 'Jugador' " +
	                 "ORDER BY e.apellido, e.nombre";

	    try (Connection conn = db.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            PersonDTO jugador = new PersonDTO(
	                rs.getInt("id_empleado"),
	                rs.getString("dni"),
	                rs.getString("nombre"),
	                rs.getString("apellido"),
	                Date.valueOf(rs.getString("fecha_nacimiento")),
	                rs.getInt("telefono"),
	                rs.getDouble("salario_anual")
	            );
	            jugadores.add(jugador);
	        }

	    } catch (SQLException e) {
	        System.err.println("Error al obtener todos los jugadores incluyendo los asignados: " + e.getMessage());
	    }

	    return jugadores;
	}

	public PersonDTO getPlayerById(int id) {
		PersonDTO jugador = null;
		 try (Connection conn = db.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(SQL_GET_BY_ID);
	             ResultSet rs = pstmt.executeQuery()) {
	            jugador = new PersonDTO(
	                    rs.getInt("id_empleado"),
	                    rs.getString("dni"),
	                    rs.getString("nombre"),
	                    rs.getString("apellido"),
	                    Date.valueOf(rs.getString("fecha_nacimiento")),
	                    rs.getInt("telefono"),
	                    rs.getDouble("salario_anual"));

	        } catch (SQLException e) {
	            System.err.println("Error al obtener los jugadores: " + e.getMessage());
	        }

	        return jugador;
	}


}
