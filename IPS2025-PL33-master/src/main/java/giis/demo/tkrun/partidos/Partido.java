package giis.demo.tkrun.partidos;

public class Partido {

	private int idPartido;
	private String fecha;
	private String horaInicio;
	private String horaFin;
	private String equipoLocal;
	private String equipoVisitante;
	private String nombre_instalacion;
	private int id_instalacion;
	private boolean tieneSuplemento;
	private double precioSuplemento;

	private int golesLocal;
	private int golesVisitante;

	public String getNombre_instalacion() {
		return nombre_instalacion;
	}

	public void setNombre_instalacion(String nombre_instalacion) {
		this.nombre_instalacion = nombre_instalacion;
	}

	public int getId_instalacion() {
		return id_instalacion;
	}

	public void setId_instalacion(int id_instalacion) {
		this.id_instalacion = id_instalacion;
	}

	public Partido() {
	}

	public Partido(int idPartido, String fecha, String horaInicio, String horaFin, String equipoLocal,
			String equipoVisitante, String instalacion) {
		this.idPartido = idPartido;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.nombre_instalacion = instalacion;
	}

	public Partido(int idPartido, String fecha, String horaInicio, String horaFin, String equipoLocal,
			String equipoVisitante, String instalacion, int golesLocal, int golesVisitante) {
		this.idPartido = idPartido;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.nombre_instalacion = instalacion;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
	}

	// Getters y setters
	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public String getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(String equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public String getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(String equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public String getInstalacion() {
		return nombre_instalacion;
	}

	public void setInstalacion(String instalacion) {
		this.nombre_instalacion = instalacion;
	}

	@Override
	public String toString() {
		return equipoLocal + " vs " + equipoVisitante + " (" + fecha + ")";
	}

	public boolean isTieneSuplemento() {
		return tieneSuplemento;
	}

	public void setTieneSuplemento(boolean tieneSuplemento) {
		this.tieneSuplemento = tieneSuplemento;
	}

	public double getPrecioSuplemento() {
		return precioSuplemento;
	}

	public void setPrecioSuplemento(double precioSuplemento) {
		this.precioSuplemento = precioSuplemento;
	}

	// Getters y setters
	public int getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}

	public int getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(int golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

}
