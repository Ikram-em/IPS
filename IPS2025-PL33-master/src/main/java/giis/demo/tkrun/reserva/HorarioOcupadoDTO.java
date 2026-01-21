package giis.demo.tkrun.reserva;

/**
 * DTO para mapear resultados del DAO (Horarios de reservas o de empleados)
 */
public class HorarioOcupadoDTO {
	private String hora_inicio;
	private String hora_fin;
	private boolean esEmpleado;

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

	public boolean isEsEmpleado() {
		return esEmpleado;
	}

	public void setEsEmpleado(boolean esEmpleado) {
		this.esEmpleado = esEmpleado;
	}

}