package giis.demo.tkrun.accion;

public class AccionDTO {
    private int idAccion;
    private int idAccionista;
    private String fechaCreacion;
    private int idCampana;
    private boolean enVenta;

    // Campo auxiliar (no necesariamente persistido en la tabla)
    private String nombreVendedor;

    public AccionDTO() {
    }

    public AccionDTO(int idAccion, String fechaCreacion) {
        this.idAccion = idAccion;
        this.fechaCreacion = fechaCreacion;
    }

    // --- Getters y setters usados en la UI ---
    public int getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(int idAccion) {
        this.idAccion = idAccion;
    }

    public int getIdAccionista() {
        return idAccionista;
    }

    public void setIdAccionista(int idAccionista) {
        this.idAccionista = idAccionista;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getIdCampana() {
        return idCampana;
    }

    public void setIdCampana(int idCampana) {
        this.idCampana = idCampana;
    }

    public boolean isEnVenta() {
        return enVenta;
    }

    public void setEnVenta(boolean enVenta) {
        this.enVenta = enVenta;
    }

    // Campo auxiliar para mostrar el nombre del vendedor en listados
    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    @Override
    public String toString() {
        return "AccionDTO [idAccion=" + idAccion + ", idAccionista=" + idAccionista +
               ", fechaCreacion=" + fechaCreacion + ", idCampana=" + idCampana +
               ", enVenta=" + enVenta + ", nombreVendedor=" + nombreVendedor + "]";
    }
}
