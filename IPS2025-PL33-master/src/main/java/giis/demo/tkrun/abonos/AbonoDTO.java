package giis.demo.tkrun.abonos;

public class AbonoDTO {
	private int idAbono;
	private int idCliente;
	private int fila;
	private int asiento;
	private double precioTotal;
	private String nombreCliente;
	private String fechaInicio;
	private String fechaFin;
	private String dniCliente;

	// Nombres de tribuna y sección
	private String tribunaNombre;
	private String seccionNombre;

	// Getters y setters
	public int getIdAbono() {
		return idAbono;
	}

	public void setIdAbono(int idAbono) {
		this.idAbono = idAbono;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getAsiento() {
		return asiento;
	}

	public void setAsiento(int asiento) {
		this.asiento = asiento;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getTribunaNombre() {
		return tribunaNombre;
	}

	public void setTribunaNombre(String tribunaNombre) {
		this.tribunaNombre = tribunaNombre;
	}

	public String getSeccionNombre() {
		return seccionNombre;
	}

	public void setSeccionNombre(String seccionNombre) {
		this.seccionNombre = seccionNombre;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}
}