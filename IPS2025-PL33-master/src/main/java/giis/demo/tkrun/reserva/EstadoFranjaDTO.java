package giis.demo.tkrun.reserva;


/**
 * Data Transfer Object (DTO) para representar el estado de una franja horaria.
 * 
 * Contiene información sobre:
 * - horaInicio: hora de inicio de la franja
 * - horaFin: hora de finalización de la franja
 * - duracionMinutos: duración en minutos de la franja
 * - estado: estado de la franja (e.g., disponible, ocupada, reservada)
 * Se utiliza para transferir datos entre la lógica de negocio y la interfaz de usuario
 * sin exponer directamente la estructura interna de las clases de reserva.
 */

public class EstadoFranjaDTO {

	private String horaInicio;
	private String horaFin;
	private int duracionMinutos;
	private String estado;

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

	public int getDuracionMinutos() {
		return duracionMinutos;
	}

	public void setDuracionMinutos(int duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}