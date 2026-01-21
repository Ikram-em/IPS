package giis.demo.tkrun.accion.compra;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.accion.AccionDTO;
import giis.demo.util.Database;

public class MetodosCompraAcciones {

    private final Database db;

    public MetodosCompraAcciones() {
        this.db = new Database();
    }

    // 1️⃣ Listar acciones en venta de otros
    private static final String SQL_ACCIONES_EN_VENTA_OTROS =
        "SELECT a.id_accion, a.id_accionista AS id_vendedor, ac.nombre AS nombre_vendedor " +
        "FROM Accion a " +
        "JOIN Accionista ac ON a.id_accionista = ac.id_accionista " +
        "WHERE a.en_venta = 1 AND a.id_accionista <> ?";

    // 2️⃣ Transferir acción
    private static final String SQL_TRANSFERIR_ACCION =
        "UPDATE Accion SET id_accionista = ?, en_venta = 0, fecha_creacion = DATETIME('now') WHERE id_accion = ?";

    /**
     * Devuelve las acciones que están en venta por otros accionistas.
     */
    public List<AccionDTO> listarAccionesEnVentaDeOtros(int idAccionistaActual) {
        List<AccionDTO> lista = new ArrayList<>();

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_ACCIONES_EN_VENTA_OTROS)) {

            stmt.setInt(1, idAccionistaActual);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int idVendedor = rs.getInt("id_vendedor");

                    // Evita el caso en que el accionista vea sus propias acciones (seguridad extra)
                    if (idVendedor == idAccionistaActual)
                        continue;

                    AccionDTO accion = new AccionDTO();
                    accion.setIdAccion(rs.getInt("id_accion"));
                    accion.setIdAccionista(idVendedor);
                    accion.setNombreVendedor(rs.getString("nombre_vendedor"));
                    lista.add(accion);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al listar acciones en venta de otros: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Transfiere la acción de un vendedor a un comprador.
     */
    public void comprarAccion(int idAccion, int idComprador, int idVendedor) {
        try (Connection conn = db.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement transferir = conn.prepareStatement(SQL_TRANSFERIR_ACCION)) {
                transferir.setInt(1, idComprador); // nuevo propietario
                transferir.setInt(2, idAccion);
                transferir.executeUpdate();

                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                System.err.println("Error al comprar acción: " + e.getMessage());
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            System.err.println("Error general al realizar compra: " + e.getMessage());
        }
    }
}
