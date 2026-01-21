package giis.demo.tkrun.noticias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import giis.demo.util.Database;

public class MetodosNoticias {
	
	private static final String CREAR_NOTICIA =
	        "INSERT INTO noticias (titulo, contenido, imagen, publicada) VALUES (?, ?, ?, ?)";
	
	private static final String SELECT_ALL =
		    "SELECT id, titulo, contenido, imagen, publicada, fecha_publicacion FROM noticias";
	
	private static final String DELETE_NOTICIA =
            "DELETE FROM noticias WHERE id=?";
	
	private static final String UPDATE_NOTICIA =
            "UPDATE noticias SET titulo=?, contenido=?, imagen=?, publicada=? WHERE id=?";
	
	private static final String SELECT_PUBLICADAS =
		    "SELECT id, titulo, contenido, imagen, publicada, fecha_publicacion " +
		    "FROM noticias WHERE publicada = 1 ORDER BY fecha_publicacion DESC";


	
	private final Database db;

    public MetodosNoticias() {
        this.db = new Database();
    }
    
    public void crearNoticia(String titulo, String contenido, boolean publicada, String imagen) {
        try (Connection conn = db.getConnection();
            PreparedStatement ps = conn.prepareStatement(CREAR_NOTICIA)) {

            ps.setString(1, titulo);
            ps.setString(2, contenido);
            ps.setString(3, imagen);     // Puede ser null si no hay imagen
            ps.setBoolean(4, publicada);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al crear la noticia: " + e.getMessage());
        }
    }
    
    public List<NoticiaDto> getNoticias() {
        List<NoticiaDto> lista = new ArrayList<>();

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                NoticiaDto dto = new NoticiaDto();
                dto.setId(rs.getInt("id"));
                dto.setTitulo(rs.getString("titulo"));
                dto.setContenido(rs.getString("contenido"));
                dto.setImagen(rs.getString("imagen"));
                dto.setPublicada(rs.getBoolean("publicada"));
                dto.setFechaPublicacion(rs.getString("fecha_publicacion"));
                lista.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    

    public void eliminarNoticia(int id) {
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_NOTICIA)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar noticia: " + e.getMessage());
        }
    }

    public void actualizarNoticia(NoticiaDto noticia) {
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_NOTICIA)) {

            ps.setString(1, noticia.getTitulo());
            ps.setString(2, noticia.getContenido());
            ps.setString(3, noticia.getImagen());
            ps.setBoolean(4, noticia.isPublicada());
            ps.setInt(5, noticia.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar noticia: " + e.getMessage());
        }
    }
    
    public List<NoticiaDto> getNoticiasPublicadas() {
        List<NoticiaDto> lista = new ArrayList<>();

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_PUBLICADAS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                NoticiaDto dto = new NoticiaDto();
                dto.setId(rs.getInt("id"));
                dto.setTitulo(rs.getString("titulo"));
                dto.setContenido(rs.getString("contenido"));
                dto.setImagen(rs.getString("imagen"));
                dto.setPublicada(rs.getBoolean("publicada"));
                dto.setFechaPublicacion(rs.getString("fecha_publicacion"));
                lista.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }





}
