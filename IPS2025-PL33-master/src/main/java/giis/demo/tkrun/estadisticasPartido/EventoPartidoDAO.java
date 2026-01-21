package giis.demo.tkrun.estadisticasPartido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import giis.demo.util.Database;
import giis.demo.util.Util;

public class EventoPartidoDAO {

    private final Database db;

    public EventoPartidoDAO() {
        this.db = new Database();
    }

    // -------------------------
    // DTOs
    // -------------------------
    public static class EventoPartidoDTO {
        public int idEvento;
        public String descripcion;

        public EventoPartidoDTO(int idEvento, String descripcion) {
            this.idEvento = idEvento;
            this.descripcion = descripcion;
        }

        @Override
        public String toString() {
            return descripcion;
        }
    }

    public static class StatsJugadorDTO {
        public int idJugador;
        public String nombreJugador;
        public int goles;
        public int amarillas;
        public int rojas;
        public int lesiones;

        public StatsJugadorDTO(int idJugador, String nombreJugador, int goles, int amarillas, int rojas, int lesiones) {
            this.idJugador = idJugador;
            this.nombreJugador = nombreJugador;
            this.goles = goles;
            this.amarillas = amarillas;
            this.rojas = rojas;
            this.lesiones = lesiones;
        }
    }

    public static class StatsTemporadaDTO {
        public String temporada;
        public int goles;
        public int amarillas;
        public int rojas;
        public int lesiones;

        public StatsTemporadaDTO(String temporada, int goles, int amarillas, int rojas, int lesiones) {
            this.temporada = temporada;
            this.goles = goles;
            this.amarillas = amarillas;
            this.rojas = rojas;
            this.lesiones = lesiones;
        }
    }

    // -------------------------
    // Helpers
    // -------------------------
    private String seasonFromDate(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        if (month >= 8) return String.format("%d-%02d", year, (year + 1) % 100);
        else return String.format("%d-%02d", year - 1, year % 100);
    }

    // -------------------------
    // Insertar evento
    // -------------------------
    public void insertarEvento(int idPartido, int idJugador, String tipo) {
        String sql = "INSERT INTO EventoPartido (id_partido, id_jugador, tipo) VALUES (?, ?, ?)";
        try (Connection cn = db.getConnection(); PreparedStatement st = cn.prepareStatement(sql)) {
            st.setInt(1, idPartido);
            st.setInt(2, idJugador);
            st.setString(3, tipo);
            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // -------------------------
    // Conteos totales por jugador
    // -------------------------
    public int countGolesJugador(int idJugador) { return countEventoJugador(idJugador, "Gol"); }
    public int countAmarillasJugador(int idJugador) { return countEventoJugador(idJugador, "TarjetaAmarilla"); }
    public int countRojasJugador(int idJugador) { return countEventoJugador(idJugador, "TarjetaRoja"); }
    public int countLesionesJugador(int idJugador) { return countLesionJugador(idJugador); }

    private int countEventoJugador(int idJugador, String tipo) {
        String sql = "SELECT COUNT(*) FROM EventoPartido WHERE id_jugador=? AND tipo=?";
        try (Connection cn = db.getConnection(); PreparedStatement st = cn.prepareStatement(sql)) {
            st.setInt(1, idJugador);
            st.setString(2, tipo);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return 0;
    }

    private int countLesionJugador(int idJugador) {
        String sql = "SELECT COUNT(*) FROM Lesion WHERE id_jugador=?";
        try (Connection cn = db.getConnection(); PreparedStatement st = cn.prepareStatement(sql)) {
            st.setInt(1, idJugador);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return 0;
    }

    // -------------------------
    // Eventos por partido
    // -------------------------
    public List<EventoPartidoDTO> getGolesPartidoConId(int idPartido) {
        return getEventosPartido(idPartido, "Gol");
    }

    public List<EventoPartidoDTO> getTarjetasPartidoConId(int idPartido) {
        return getEventosPartido(idPartido, "TarjetaAmarilla", "TarjetaRoja");
    }

    public List<EventoPartidoDTO> getLesionesPartidoConId(int idPartido) {
    	List<EventoPartidoDTO> lista = new ArrayList<>();
        
        // Consulta SQL para obtener las lesiones asociadas a un partido, 
        // incluyendo el nombre del jugador y el diagnóstico.
        String sqlLesiones = 
              "SELECT l.id_lesion, e.nombre || ' ' || e.apellido || ' - Lesión: ' || l.diagnostico AS descripcion "
            + "FROM Lesion l "
            + "JOIN Empleado e ON l.id_jugador = e.id_empleado "
            + "WHERE l.id_partido = ?";

        try (Connection cn = db.getConnection(); 
             PreparedStatement st = cn.prepareStatement(sqlLesiones)) {
            
            st.setInt(1, idPartido);
            
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    // Usamos id_lesion como el id_evento y el diagnóstico en la descripción
                    lista.add(new EventoPartidoDTO(
                        rs.getInt("id_lesion"), 
                        rs.getString("descripcion")
                    ));
                }
            }
            
        } catch (SQLException ex) { 
            ex.printStackTrace(); 
        }

        return lista;
    }

    private List<EventoPartidoDTO> getEventosPartido(int idPartido, String... tipos) {
        List<EventoPartidoDTO> lista = new ArrayList<>();
        if (tipos == null || tipos.length == 0) return lista;

        StringBuilder sb = new StringBuilder(
            "SELECT ep.id_evento, e.nombre || ' ' || e.apellido || ' - ' || ep.tipo AS descripcion "
          + "FROM EventoPartido ep "
          + "JOIN EmpleadoDeportivo d ON ep.id_jugador=d.id_empleado "
          + "JOIN Empleado e ON d.id_empleado=e.id_empleado "
          + "WHERE ep.id_partido=? AND ep.tipo IN (");

        for (int i = 0; i < tipos.length; i++) {
            sb.append("?");
            if (i < tipos.length - 1) sb.append(",");
        }
        sb.append(")");

        try (Connection cn = db.getConnection(); PreparedStatement st = cn.prepareStatement(sb.toString())) {
            st.setInt(1, idPartido);
            for (int i = 0; i < tipos.length; i++) st.setString(i + 2, tipos[i]);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    lista.add(new EventoPartidoDTO(rs.getInt("id_evento"), rs.getString("descripcion")));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }

        return lista;
    }

    // -------------------------
    // Stats por temporada
    // -------------------------
    public List<StatsTemporadaDTO> getStatsJugadorPorTemporada(int idJugador) {
        Map<String, int[]> map = new TreeMap<>();

        // ----------------------------
        // Eventos Gol, Amarilla, Roja
        // ----------------------------
        String sqlEventos = "SELECT ep.tipo, p.fecha FROM EventoPartido ep JOIN Partido p ON ep.id_partido=p.id_partido WHERE ep.id_jugador=?";
        try (Connection cn = db.getConnection(); PreparedStatement st = cn.prepareStatement(sqlEventos)) {
            st.setInt(1, idJugador);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    String tipo = rs.getString("tipo");
                    String fechaStr = rs.getString("fecha"); // leer como String
                    if (fechaStr == null || fechaStr.trim().isEmpty()) continue;
                    
                    LocalDate fecha = Util.parseIsoDate(fechaStr).toInstant()
                            .atZone(java.time.ZoneId.systemDefault())
                            .toLocalDate();
                    
                    String season = seasonFromDate(fecha);
                    int[] counters = map.computeIfAbsent(season, k -> new int[4]);
                    switch (tipo) {
                        case "Gol": counters[0]++; break;
                        case "TarjetaAmarilla": counters[1]++; break;
                        case "TarjetaRoja": counters[2]++; break;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // ----------------------------
        // Lesiones
        // ----------------------------
        String sqlLesiones = "SELECT fecha_lesion FROM Lesion WHERE id_jugador=?";
        try (Connection cn = db.getConnection(); PreparedStatement st = cn.prepareStatement(sqlLesiones)) {
            st.setInt(1, idJugador);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    String fechaStr = rs.getString("fecha_lesion");
                    if (fechaStr == null || fechaStr.trim().isEmpty()) continue;

                    LocalDate fecha = Util.parseIsoDate(fechaStr).toInstant()
                            .atZone(java.time.ZoneId.systemDefault())
                            .toLocalDate();

                    String season = seasonFromDate(fecha);
                    int[] counters = map.computeIfAbsent(season, k -> new int[4]);
                    counters[3]++; // Lesiones
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // ----------------------------
        // Convertir a lista final
        // ----------------------------
        List<StatsTemporadaDTO> result = new ArrayList<>();
        for (Map.Entry<String, int[]> e : map.entrySet()) {
            int[] c = e.getValue();
            result.add(new StatsTemporadaDTO(e.getKey(), c[0], c[1], c[2], c[3]));
        }

        return result;
    }


    // -------------------------
    // Stats comparativa
    // -------------------------
    public List<StatsJugadorDTO> getStatsComparativa(List<Integer> idsJugadores) {
        List<StatsJugadorDTO> lista = new ArrayList<>();
        if (idsJugadores == null || idsJugadores.isEmpty()) return lista;

        try (Connection cn = db.getConnection()) {
            for (int id : idsJugadores) {
                int goles = countGolesJugador(id);
                int amar = countAmarillasJugador(id);
                int rojas = countRojasJugador(id);
                int lesiones = countLesionesJugador(id);

                String nombre = getNombreJugadorById(cn, id);
                lista.add(new StatsJugadorDTO(id, nombre, goles, amar, rojas, lesiones));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }

        return lista;
    }

    private String getNombreJugadorById(Connection cn, int idJugador) {
        String sql = "SELECT nombre, apellido FROM Empleado WHERE id_empleado=?";
        try (PreparedStatement st = cn.prepareStatement(sql)) {
            st.setInt(1, idJugador);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) return rs.getString("nombre") + " " + rs.getString("apellido");
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return "Jugador " + idJugador;
    }

    // -------------------------
    // Conteos por partido
    // -------------------------
    public int countAmarillas(int idPartido, int idJugador) {
        String sql = "SELECT COUNT(*) FROM EventoPartido WHERE id_partido=? AND id_jugador=? AND tipo='TarjetaAmarilla'";
        try (Connection cn = db.getConnection(); PreparedStatement st = cn.prepareStatement(sql)) {
            st.setInt(1, idPartido);
            st.setInt(2, idJugador);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return 0;
    }

    public int countRojas(int idPartido, int idJugador) {
        String sql = "SELECT COUNT(*) FROM EventoPartido WHERE id_partido=? AND id_jugador=? AND tipo='TarjetaRoja'";
        try (Connection cn = db.getConnection(); PreparedStatement st = cn.prepareStatement(sql)) {
            st.setInt(1, idPartido);
            st.setInt(2, idJugador);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return 0;
    }
    
    public void eliminarEvento(int idEvento) {
        String sql = "DELETE FROM EventoPartido WHERE id_evento=?";
        try (Connection cn = db.getConnection(); PreparedStatement st = cn.prepareStatement(sql)) {
            st.setInt(1, idEvento);
            st.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
