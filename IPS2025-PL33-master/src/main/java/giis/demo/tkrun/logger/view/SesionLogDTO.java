package giis.demo.tkrun.logger.view;

public class SesionLogDTO {

	public int session_id;
	public int user_id;
	public String username;
	public String fecha_inicio;

	public int getSession_id() {
		return session_id;
	}

	public void setSession_id(int session_id) {
		this.session_id = session_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	@Override
	public String toString() {
		return "[" + fecha_inicio + "] " + username;
	}

}
