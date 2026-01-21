package giis.demo.tkrun.logger;

import java.sql.Timestamp;

public class LogViewDTO {

	private int log_id;
	private int session_id;
	private String accion;
	private String detalles;
	private Timestamp fecha_hora;

	public int getLog_id() {
		return log_id;
	}
	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}

	public int getSession_id() {
		return session_id;
	}
	public void setSession_id(int session_id) {
		this.session_id = session_id;
	}

	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public Timestamp getFecha_hora() {
		return fecha_hora;
	}
	public void setFecha_hora(Timestamp fecha_hora) {
		this.fecha_hora = fecha_hora;
	}


}
