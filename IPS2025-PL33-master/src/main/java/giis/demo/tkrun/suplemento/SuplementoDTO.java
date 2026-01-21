package giis.demo.tkrun.suplemento;

import java.util.Date;

public class SuplementoDTO {
	private int idAbono; 
	private int idPartido; 
	private double precio;
	private Date fechaCompra;

	// Getters y setters
	public int getIdAbono() {
		return idAbono;
	}

	public void setIdAbono(int idAbono) {
		this.idAbono = idAbono;
	}

	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
}
