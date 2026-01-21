package giis.demo.tkrun.accion;

public class AccionistaViewDTO {
	private int id_accionista;
	private int id_user;
	private String nombre;
	
	public int getId_accionista() {
		return id_accionista;
	}
	public void setId_accionista(int id_accionista) {
		this.id_accionista = id_accionista;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String toString() {
		if (this.nombre != null) {
			return this.id_user + " - " + this.nombre;			
		}
		return "" + this.id_user;
	}

}
