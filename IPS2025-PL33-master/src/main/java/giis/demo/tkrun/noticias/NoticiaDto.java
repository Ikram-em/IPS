package giis.demo.tkrun.noticias;

public class NoticiaDto {
    private int id;
    private String titulo;
    private String contenido;
    private String imagen;
    private boolean publicada;
    private String fechaPublicacion;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public boolean isPublicada() { return publicada; }
    public void setPublicada(boolean publicada) { this.publicada = publicada; }

    public String getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(String fechaPublicacion) { 
        this.fechaPublicacion = fechaPublicacion; 
    }
}
