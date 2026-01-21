package giis.demo.tkrun.estadisticas;

import java.util.Date;

/**
 * Modelo de negocio para representar un gasto del club.
 * Contiene:
 * - Tipo (por ejemplo: COMPRA_STOCK, SUELDOS, MANTENIMIENTO)
 * - Concepto (descripción del gasto)
 * - Fecha y hora del gasto
 * - Importe total
 */
public class Gasto {
    private String tipo;
    private String concepto;
    private Date fechaHora;
    private double total;

    public Gasto(String tipo, String concepto, Date fechaHora, double total) {
        this.tipo = tipo;
        this.concepto = concepto;
        this.fechaHora = fechaHora;
        this.total = total;
    }

    // Getters
    public String getTipo() {
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

    // Setter fecha en caso de necesitar actualizarla
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
}
