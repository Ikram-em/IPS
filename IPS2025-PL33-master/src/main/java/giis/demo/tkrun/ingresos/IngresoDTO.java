package giis.demo.tkrun.ingresos;

public class IngresoDTO {
    private int idOperacion;
    private String tipo;
    private String concepto;
    private String fechaHora;
    private double cuantiaTotal;

    // Getters y setters
    public int getIdOperacion() { return idOperacion; }
    public void setIdOperacion(int idOperacion) { this.idOperacion = idOperacion; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }

    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }

    public double getCuantiaTotal() { return cuantiaTotal; }
    public void setCuantiaTotal(double cuantiaTotal) { this.cuantiaTotal = cuantiaTotal; }
}
