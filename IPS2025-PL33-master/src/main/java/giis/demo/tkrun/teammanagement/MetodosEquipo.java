package giis.demo.tkrun.teammanagement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.teammanagement.entrenamiento.EquipoSimpleDTO;
import giis.demo.util.Database;

/**
 * Clase que gestiona las operaciones relacionadas con los equipos:
 * creación, asociación de jugadores, etc.
 */
public class MetodosEquipo {

    private Database db;

    private static final String SQL_INSERT_EQUIPO = 
        "INSERT INTO Equipo (nombre, id_primer_entrenador, id_segundo_entrenador, tipo_equipo, categoria_equipo) " +
        "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_INSERT_EQUIPO_JUGADOR = 
        "INSERT INTO Plantilla (id_equipo, id_jugador) VALUES (?, ?)";

    private static final String SQL_GET_ALL_CREATED_TEAMS = "SELECT * FROM Equipo";
    
    private static final String SQL_GET_TEAMS_BY_TRAINER = "SELECT e.nombre, e.id_equipo FROM Equipo e " + 
    		"JOIN EmpleadoDeportivo ed ON ed.id_empleado = e.id_primer_entrenador " +
    		"JOIN User u ON u.id_empleado = ed.id_empleado " +
    		"WHERE u.id_user = ? " +
    		"UNION SELECT e.nombre, e.id_equipo FROM Equipo e " + 
    		"JOIN EmpleadoDeportivo ed ON ed.id_empleado = e.id_segundo_entrenador " +
    		"JOIN User u ON u.id_empleado = ed.id_empleado " +
    		"WHERE u.id_user = ? ";
    
    private static final String SQL_GET_ID_BY_NAME = 
            "SELECT id_equipo FROM EQUIPO Where nombre = ?";

    public MetodosEquipo() {
        db = new Database();
    }

    /**
     * Crea un nuevo equipo en la base de datos y devuelve su ID generado.
     */
    public int crearEquipo(String nombreEquipo, int idPrimerEntrenador, int idSegundoEntrenador,
                           String tipoEquipo, String categoria) {
        int idGenerado = -1;

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT_EQUIPO, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, nombreEquipo);
            ps.setInt(2, idPrimerEntrenador);
            ps.setInt(3, idSegundoEntrenador);
            ps.setString(4, tipoEquipo);

            if (categoria != null && !categoria.isEmpty()) {
                ps.setString(5, categoria.toLowerCase());
            } else {
                ps.setNull(5, Types.VARCHAR);
            }

            ps.executeUpdate();

            // Recuperar el ID autogenerado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                }
            }


        } catch (SQLException e) {
            System.err.println("Error al crear el equipo: " + e.getMessage());
        }

        return idGenerado;
    }

    /**
     * Asocia jugadores a un equipo (sin uso de batch).
     */
    public void agregarJugadoresAlEquipo(int idEquipo, List<PersonDTO> jugadores) {
        if (jugadores == null || jugadores.isEmpty())
            return;

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT_EQUIPO_JUGADOR)) {

            for (PersonDTO jugador : jugadores) {
                ps.setInt(1, idEquipo);
                ps.setInt(2, jugador.getId());
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            System.err.println("Error al agregar jugadores al equipo: " + e.getMessage());
        }
    }

    /**
     * Devuelve todos los equipos creados.
     */
    public List<EquipoDTO> getAllCreatedTeams() {
        List<EquipoDTO> equipos = new ArrayList<>();

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL_CREATED_TEAMS);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                EquipoDTO equipo = new EquipoDTO(
                        rs.getInt("id_equipo"),
                        rs.getString("nombre"),
                        rs.getInt("id_primer_entrenador"),
                        rs.getInt("id_segundo_entrenador"),
                        convertTypeToEnum(rs.getString("tipo_equipo")),
                        convertCategoryToEnum(rs.getString("categoria_equipo"))
                );
                equipos.add(equipo);
            }

        } catch (SQLException e) {
            System.err.println("Error al seleccionar equipos: " + e.getMessage());
        }

        return equipos;
    }

    private FormationTeamCategory convertCategoryToEnum(String category) {
        if (category != null) {
            return FormationTeamCategory.valueOf(category.toUpperCase());
        } else {
            return null;
        }
    }

    private TeamType convertTypeToEnum(String type) {
        return TeamType.valueOf(type.toUpperCase());
    }

    /**
     * Actualiza la plantilla de un equipo (agrega y elimina jugadores según corresponda).
     */
    public void actualizarJugadoresDeEquipo(int equipoId, List<PersonDTO> originales, List<PersonDTO> actuales) {
        // Jugadores eliminados
        List<PersonDTO> eliminados = new ArrayList<>();
        for (PersonDTO jugador : originales) {
            if (!actuales.contains(jugador)) {
                eliminados.add(jugador);
            }
        }

        // Jugadores agregados
        List<PersonDTO> agregados = new ArrayList<>();
        for (PersonDTO jugador : actuales) {
            if (!originales.contains(jugador)) {
                agregados.add(jugador);
            }
        }

        // Ejecutar eliminaciones
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "DELETE FROM Plantilla WHERE id_equipo = ? AND id_jugador = ?")) {

            for (PersonDTO jugador : eliminados) {
                ps.setInt(1, equipoId);
                ps.setInt(2, jugador.getId());
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar jugadores: " + e.getMessage());
        }

        // Ejecutar agregaciones
        agregarJugadoresAlEquipo(equipoId, agregados);
    }
    
    public void actualizarEntrenadoresDeEquipo(int idEquipo, int idPrimerEntrenador, int idSegundoEntrenador) {
        String sql = "UPDATE Equipo SET id_primer_entrenador = ?, id_segundo_entrenador = ? WHERE id_equipo = ?";
        try (Connection conn = new Database().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPrimerEntrenador);

            pstmt.setInt(2, idSegundoEntrenador);

            pstmt.setInt(3, idEquipo);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar entrenadores del equipo: " + e.getMessage());
        }
    }
    
    /**
     * Devuelve la lista de entrenadores que tienen asignado al menos un equipo.
     */
    public List<String> getEntrenadoresConEquipo() {
        List<String> entrenadores = new ArrayList<>();

        String sql = "SELECT DISTINCT e.id_empleado, e.nombre, e.apellido " +
        	"FROM EmpleadoDeportivo ed JOIN Empleado e ON e.id_empleado = ed.id_empleado JOIN Posicion p ON ed.id_posicion = p.posicion" + 
        	"WHERE p.nombre = 'Entrenador' " +
            "AND e.id_empleado IN ( " +
                  "SELECT id_primer_entrenador FROM Equipo " +
                  "UNION " +
                  "SELECT id_segundo_entrenador FROM Equipo)";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

        	while (rs.next()) {
                entrenadores.add(rs.getString("id_empleado") + " - " + rs.getString("nombre") + " " + rs.getString("apellido"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener entrenadores con equipo: " + e.getMessage());
        }

        return entrenadores;
    }
    
    public List<PersonDTO> getJugadoresPorEntrenador(int idEntrenador) {
        List<PersonDTO> jugadores = new ArrayList<>();
        String sql = "SELECT e.id_empleado, e.dni, e.nombre, e.apellido, e.fecha_nacimiento, e.telefono, e.salario_anual "+
            "FROM Equipo eq " +
            "JOIN Plantilla p ON eq.id_equipo = p.id_equipo " +
            "JOIN Empleado e ON e.id_empleado = p.id_jugador " +
            "WHERE eq.id_primer_entrenador = ? OR eq.id_segundo_entrenador = ? ";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEntrenador);
            ps.setInt(2, idEntrenador);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                jugadores.add(new PersonDTO(
                        rs.getInt("id_empleado"),
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        Date.valueOf(rs.getString("fecha_nacimiento")),
                        rs.getInt("telefono"),
                        rs.getDouble("salario_anual")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jugadores;
    }

    public List<EquipoSimpleDTO> getTeamNames() {
    	return db.executeQueryPojo(EquipoSimpleDTO.class, SQL_GET_ALL_CREATED_TEAMS);
    }

    public List<EquipoSimpleDTO> getTeamNamesByTrainer(int trainerUserId) {
    	return db.executeQueryPojo(EquipoSimpleDTO.class, SQL_GET_TEAMS_BY_TRAINER, trainerUserId, trainerUserId);
    }
    
    public int getTeamIdByName(String name) {

        try (Connection conn = db.getConnection();
        		PreparedStatement ps = conn.prepareStatement(SQL_GET_ID_BY_NAME)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("id_equipo");
            }

        } catch (SQLException e) {
            System.err.println("Error al seleccionar equipos: " + e.getMessage());
        }

        return -1;
    }

}
