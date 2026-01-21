package giis.demo.tkrun.accion.venta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.accion.AccionDTO;
import giis.demo.util.Database;

public class MetodosVentaAcciones {

    private final Database db;

    public MetodosVentaAcciones() {
        this.db = new Database();
    }

    // SQL base para mapear Acciones con nombre de accionista
    private static final String BASE_SELECT =
        "SELECT a.id_accion, a.id_accionista, a.fecha_creacion, a.id_campana, a.en_venta, ac.nombre AS nombre_accionista " +
        "FROM Accion a " +
        "JOIN Accionista ac ON a.id_accionista = ac.id_accionista ";

    // 1️⃣ Acciones NO en venta
    private static final String SQL_ACCIONES_DE_ACCIONISTA =
        BASE_SELECT + "WHERE a.id_accionista = ? AND a.en_venta = 0";

    // 2️⃣ Acciones EN venta
    private static final String SQL_ACCIONES_EN_VENTA_DE_ACCIONISTA =
        BASE_SELECT + "WHERE a.id_accionista = ? AND a.en_venta = 1";

    // 3️⃣ Poner en venta
    private static final String SQL_PONER_EN_VENTA =
        "UPDATE Accion SET en_venta = 1 WHERE id_accion = ?";

    // 4️⃣ Quitar de venta
    private static final String SQL_QUITAR_VENTA =
        "UPDATE Accion SET en_venta = 0 WHERE id_accion = ?";

    /**
     * Devuelve las acciones del accionista que NO están en venta.
     */
    public List<AccionDTO> mostrarAccionesDeAccionista(int idAccionista) {
        return obtenerAccionesPorEstado(idAccionista, SQL_ACCIONES_DE_ACCIONISTA);
    }

    /**
     * Devuelve las acciones del accionista que SÍ están en venta.
     */
    public List<AccionDTO> mostrarAccionesEnVentaDeAccionista(int idAccionista) {
        return obtenerAccionesPorEstado(idAccionista, SQL_ACCIONES_EN_VENTA_DE_ACCIONISTA);
    }

    /**
     * Método común de obtención de acciones filtradas.
     */
    private List<AccionDTO> obtenerAccionesPorEstado(int idAccionista, String sql) {
        List<AccionDTO> lista = new ArrayList<>();

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAccionista);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    AccionDTO accion = mapearAccionDTO(rs);
                    lista.add(accion);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar acciones: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Pone una acción en venta.
     */
    public void ponerEnVenta(int idAccion) {
        ejecutarCambioEstado(idAccion, SQL_PONER_EN_VENTA, "poner en venta");
    }

    /**
     * Quita una acción de la venta.
     */
    public void quitarVenta(int idAccion) {
        ejecutarCambioEstado(idAccion, SQL_QUITAR_VENTA, "quitar venta");
    }

    /**
     * Método común para ejecutar UPDATEs de cambio de estado.
     */
    private void ejecutarCambioEstado(int idAccion, String sql, String accionTexto) {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAccion);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al " + accionTexto + ": " + e.getMessage());
        }
    }

    /**
     * Mapea un ResultSet a AccionDTO.
     */
    private AccionDTO mapearAccionDTO(ResultSet rs) throws SQLException {
        AccionDTO accion = new AccionDTO();
        accion.setIdAccion(rs.getInt("id_accion"));
        accion.setIdAccionista(rs.getInt("id_accionista"));
        accion.setFechaCreacion(rs.getString("fecha_creacion"));
        accion.setIdCampana(rs.getInt("id_campana"));
        accion.setEnVenta(rs.getInt("en_venta") == 1);
        accion.setNombreVendedor(rs.getString("nombre_accionista"));
        return accion;
    }
}
