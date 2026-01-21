package giis.demo.tkrun.teammanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database;

/**
 * Métodos robustos para la gestión de entrenadores, diseñados para funcionar
 * bien con JComboBox.
 */
public class MetodosEntrenador {

    private final Database db;

    public MetodosEntrenador() {
        this.db = new Database();
    }
    
    public static class EntrenadorDTO {
        private final int id_empleado;
        private final String nombre;
        private final String apellido;

        public EntrenadorDTO(int id, String nombre, String apellido) {
            this.id_empleado = id;
            this.nombre = nombre;
            this.apellido = apellido;
        }

        public int getId_empleado() {
            return id_empleado;
        }

        public String getNombreCompleto() {
            return nombre + " " + apellido;
        }

        @Override
        public String toString() {
            // Formato pensado para el JComboBox
            return id_empleado + " - " + nombre + " " + apellido;
        }
    }

    /**
     * Devuelve una lista de entrenadores sin equipo (para ComboBox).
     */
    public List<EntrenadorDTO> getAllAvailableCoaches() {
        String sql = "SELECT e.id_empleado, e.nombre, e.apellido " +
                "FROM Empleado e " +
                "JOIN EmpleadoDeportivo ed ON e.id_empleado = ed.id_empleado " +
                "JOIN Posicion p ON p.id_posicion = ed.id_posicion " +
                "WHERE p.nombre = 'Entrenador' " +
                  "AND e.id_empleado NOT IN ( " +
                      "SELECT id_primer_entrenador FROM Equipo " +
                      "UNION " +
                      "SELECT id_segundo_entrenador FROM Equipo " +
                  ") " +
                "ORDER BY e.nombre, e.apellido";

        List<EntrenadorDTO> entrenadores = new ArrayList<>();

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                entrenadores.add(new EntrenadorDTO(
                        rs.getInt("id_empleado"),
                        rs.getString("nombre"),
                        rs.getString("apellido")));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener entrenadores disponibles: " + e.getMessage());
        }

        return entrenadores;
    }

    /**
     * Devuelve todos los entrenadores, estén o no asignados.
     */
    public List<EntrenadorDTO> getAllCoaches() {
        String sql = "SELECT e.id_empleado, e.nombre, e.apellido "+
                "FROM Empleado e " +
                "JOIN EmpleadoDeportivo ed ON e.id_empleado = ed.id_empleado " +
                "JOIN Posicion p ON p.id_posicion = ed.id_posicion " +
                "WHERE p.nombre = 'Entrenador' " +
                "ORDER BY e.nombre, e.apellido";

        List<EntrenadorDTO> entrenadores = new ArrayList<>();

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                entrenadores.add(new EntrenadorDTO(
                        rs.getInt("id_empleado"),
                        rs.getString("nombre"),
                        rs.getString("apellido")));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener todos los entrenadores: " + e.getMessage());
        }

        return entrenadores;
    }

    /**
     * Obtiene el ID de un entrenador a partir del texto mostrado en el ComboBox
     * ("123 - Juan Pérez").
     */
    public int getIdFromDisplayName(String displayName) {
        if (displayName == null || !displayName.contains(" - "))
            return -1;

        try {
            return Integer.parseInt(displayName.split(" - ")[0].trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Obtiene el nombre completo del entrenador por su ID.
     */
    public String getNameById(int id) {
        String sql = "SELECT nombre, apellido FROM Empleado WHERE id_empleado = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("nombre") + " " + rs.getString("apellido");
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener nombre del entrenador: " + e.getMessage());
        }

        return null;
    }

    /**
     * Devuelve un texto estándar para mostrar en JComboBox: "ID - Nombre Apellido".
     */
    public String getDisplayName(int id, String nombre, String apellido) {
        return id + " - " + nombre + " " + apellido;
    }
    
    /**
     * Obtiene un entrenador por su ID.
     * @param id ID del entrenador
     * @return EntrenadorDTO si existe, null si no se encuentra
     */
    public EntrenadorDTO getEntrenadorById(int id) {
        String sql = "SELECT e.id_empleado, e.nombre, e.apellido " +
                     "FROM Empleado e " +
                     "JOIN EmpleadoDeportivo ed ON e.id_empleado = ed.id_empleado " +
                     "JOIN Posicion p ON p.id_posicion = ed.id_posicion " +
                     "WHERE p.nombre = 'Entrenador' AND e.id_empleado = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new EntrenadorDTO(
                            rs.getInt("id_empleado"),
                            rs.getString("nombre"),
                            rs.getString("apellido")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener entrenador por ID: " + e.getMessage());
        }

        return null; // no encontrado
    }

    public List<String> getEntrenadoresDeEquiposProfesionales() {
        List<String> entrenadores = new ArrayList<>();

        String sql = 
            "SELECT DISTINCT e.id_empleado, e.nombre, e.apellido " +
            "FROM EmpleadoDeportivo ed " +
            "JOIN Empleado e ON e.id_empleado = ed.id_empleado " +
            "JOIN Posicion p ON ed.id_posicion = p.id_posicion " +
            "JOIN Equipo eq ON (eq.id_primer_entrenador = e.id_empleado OR eq.id_segundo_entrenador = e.id_empleado) " +
            "WHERE p.nombre = 'Entrenador' " +
            "AND LOWER(eq.tipo_equipo) IN ('profesional_primer_equipo', 'profesional_filial')";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                entrenadores.add(
                    rs.getInt("id_empleado") + " - " +
                    rs.getString("nombre") + " " +
                    rs.getString("apellido")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener entrenadores de equipos profesionales: " + e.getMessage());
        }

        return entrenadores;
    }
    
    public List<String> getEntrenadoresDeEquiposProfesionalesConId(int idEntrenador) {
    	List<String> entrenadores = new ArrayList<>();

		String sql = "SELECT e.id_empleado, e.nombre, e.apellido " + "FROM EmpleadoDeportivo ed "
				+ "JOIN Empleado e ON e.id_empleado = ed.id_empleado "
				+ "JOIN Posicion p ON ed.id_posicion = p.id_posicion "
				+ "JOIN Equipo eq ON (eq.id_primer_entrenador = e.id_empleado OR eq.id_segundo_entrenador = e.id_empleado) "
				+ "WHERE p.nombre = 'Entrenador' " + "AND LOWER(eq.tipo_equipo) LIKE 'profesional%' "
				+ 
				"AND e.id_empleado = ?";
		

        try (Connection conn = db.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setInt(1, idEntrenador);
                ResultSet rs = ps.executeQuery();

               while (rs.next()) {
                   entrenadores.add(
                       rs.getInt("id_empleado") + " - " +
                       rs.getString("nombre") + " " +
                       rs.getString("apellido")
                   );

               }

           } catch (SQLException e) {
               System.err.println("Error al obtener entrenadores de equipos profesionales: " + e.getMessage());
           }
        return entrenadores;
    }
}
