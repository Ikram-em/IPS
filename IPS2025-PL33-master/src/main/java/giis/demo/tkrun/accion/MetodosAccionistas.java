package giis.demo.tkrun.accion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database;

public class MetodosAccionistas {

    private final Database db;

    public MetodosAccionistas() {
        this.db = new Database();
    }

    private static final String SQL_LISTAR_ACCIONISTAS =
        "SELECT id_accionista, nombre FROM Accionista ORDER BY id_accionista";

    public List<String> listarAccionistas() {
        List<String> lista = new ArrayList<>();

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_LISTAR_ACCIONISTAS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_accionista");
                String nombre = rs.getString("nombre");
                lista.add(id + " - " + nombre);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar accionistas: " + e.getMessage());
        }

        return lista;
    }
}
