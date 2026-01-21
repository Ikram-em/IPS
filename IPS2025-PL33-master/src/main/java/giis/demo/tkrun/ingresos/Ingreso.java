package giis.demo.tkrun.ingresos;

import java.util.Date;

public class Ingreso {
    private TipoIngreso tipo;
    private String concepto;
    private Date fechaHora;
    private double total;

    public Ingreso(TipoIngreso tipo, String concepto, Date fechaHora, double total) {
        this.tipo = tipo;
        this.concepto = concepto;
        this.fechaHora = fechaHora;
        this.total = total;
    }

    // Getters
    public TipoIngreso getTipo() {
        return tipo;
    }

    public String getConcepto() {
        return concepto;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public double getTotal() {
        return total;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
}
