package giis.demo.tkrun.medico;

public class SesionLesionViewDTO {

	private int id_sesion;
	private int id_lesion;
	private String accion;
	private String prioridad;
	private String diagnostico;
	private int alta_medica;
	private String fecha_hora;

	public int getId_lesion() {
		return id_lesion;
	}

	public void setId_lesion(int id_lesion) {
		this.id_lesion = id_lesion;
	}

	public int getId_sesion() {
		return id_sesion;
	}

	public void setId_sesion(int id_sesion) {
		this.id_sesion = id_sesion;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public int getAlta_medica() {
		return alta_medica;
	}

	public void setAlta_medica(int alta_medica) {
		this.alta_medica = alta_medica;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(String fecha_hora) {
		this.fecha_hora = fecha_hora;
	}


}
