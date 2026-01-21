package giis.demo.tkrun.factura;

public class FacturaDatos {
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	private String fechaFactura;
	private String numeroFactura;
	private String fechaVencimiento;
	private String empresaNombre;
	private String empresaDireccion;
	private String empresaCif;
	private String empresaCpCiudad;
	private String empresaEmail;

	// + getters y setters


	public FacturaDatos(String nombre, String direccion, String telefono, String email) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
	}

	public String getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getEmpresaNombre() {
		return empresaNombre;
	}

	public void setEmpresaNombre(String empresaNombre) {
		this.empresaNombre = empresaNombre;
	}

	public String getEmpresaDireccion() {
		return empresaDireccion;
	}

	public void setEmpresaDireccion(String empresaDireccion) {
		this.empresaDireccion = empresaDireccion;
	}

	public String getEmpresaCif() {
		return empresaCif;
	}

	public void setEmpresaCif(String empresaCif) {
		this.empresaCif = empresaCif;
	}

	public String getEmpresaCpCiudad() {
		return empresaCpCiudad;
	}

	public void setEmpresaCpCiudad(String empresaCpCiudad) {
		this.empresaCpCiudad = empresaCpCiudad;
	}

	public String getEmpresaEmail() {
		return empresaEmail;
	}

	public void setEmpresaEmail(String empresaEmail) {
		this.empresaEmail = empresaEmail;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Getters
	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}
}
