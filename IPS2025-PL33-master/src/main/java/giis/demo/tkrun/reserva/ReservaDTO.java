package giis.demo.tkrun.reserva;

import java.util.Date;

public class ReservaDTO {
	private String nombreUsuario;
	private String numTarjeta;
	private int id_instalacion;
	private Date fecha;
	private Date horaInicio;
	private Date horaFin;
	private double precioTotal;
	private String nombre_instalacion;

	public ReservaDTO(String nombre, String tarjeta, int id_instalacion, Date fechaBase, Date inicioReserva,
			Date finReserva, double precioTotal, String nombreInstalacion) {
		this.nombreUsuario = nombre;
		this.numTarjeta = tarjeta;
		this.id_instalacion = id_instalacion;
		this.fecha = fechaBase;
		this.horaInicio = inicioReserva;
		this.horaFin = finReserva;
		this.precioTotal = precioTotal;
		this.nombre_instalacion = nombreInstalacion;
	}

	public ReservaDTO() {

	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public int getId_instalacion() {
		return id_instalacion;
	}

	public void setId_instalacion(int id_instalacion) {
		this.id_instalacion = id_instalacion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public String getNombre_instalacion() {
		return nombre_instalacion;
	}

	public void setNombre_instalacion(String nombre_instalacion) {
		this.nombre_instalacion = nombre_instalacion;
	}

	
}