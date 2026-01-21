package giis.demo.tkrun.teammanagement.entrenamiento;


public class EntrenamientoDTO {
	private int id_entrenamiento;
	private String fecha;
	private String hora_inicio;
	private String hora_fin;

	public int getId_entrenamiento() {
		return id_entrenamiento;
	}

	public void setId_entrenamiento(int id_entrenamiento) {
		this.id_entrenamiento = id_entrenamiento;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public String getHora_fin() {
		return hora_fin;
	}

	public void setHora_fin(String hora_fin) {
		this.hora_fin = hora_fin;
	}

	@Override
	public String toString() {
		return "[" + id_entrenamiento + "] " + fecha + ", inicio: " + hora_inicio + " fin: " + hora_fin;
	}
	
}
