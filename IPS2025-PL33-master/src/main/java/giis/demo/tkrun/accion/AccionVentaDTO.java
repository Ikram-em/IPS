package giis.demo.tkrun.accion;

public class AccionVentaDTO {
    private int idVenta;
    private int idAccion;
    private String fechaPublicacion;
    private int idVendedor;

    public AccionVentaDTO(int idVenta, int idAccion, String fechaPublicacion, int idVendedor) {
        this.idVenta = idVenta;
        this.idAccion = idAccion;
        this.fechaPublicacion = fechaPublicacion;
        this.idVendedor = idVendedor;
    }

    public int getIdVenta() { return idVenta; }
    public int getIdAccion() { return idAccion; }
    public String getFechaPublicacion() { return fechaPublicacion; }
    public int getIdVendedor() { return idVendedor; }
}