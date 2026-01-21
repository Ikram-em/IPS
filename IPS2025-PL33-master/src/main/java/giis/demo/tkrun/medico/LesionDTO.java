package giis.demo.tkrun.medico;

public class LesionDTO {

	private Integer id_lesion;
	private Integer id_jugador;
	private Integer id_partido;
	private Integer id_entreno;
	private String prioridad;
	private String diagnostico;
	private Integer alta_medica; // 0 o 1

	public Integer getId_lesion() {
		return id_lesion;
	}

	public void setId_lesion(Integer id_lesion) {
		this.id_lesion = id_lesion;
	}

	public Integer getId_jugador() {
		return id_jugador;
	}

	public void setId_jugador(Integer id_jugador) {
		this.id_jugador = id_jugador;
	}

	public Integer getId_partido() {
		return id_partido;
	}

	public void setId_partido(Integer id_partido) {
		this.id_partido = id_partido;
	}

	public Integer getId_entreno() {
		return id_entreno;
	}

	public void setId_entreno(Integer id_entreno) {
		this.id_entreno = id_entreno;
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

	public Integer getAlta_medica() {
		return alta_medica;
	}

	public void setAlta_medica(Integer alta_medica) {
		this.alta_medica = alta_medica;
	}

}
