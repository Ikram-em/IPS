package giis.demo.tkrun.partidos.entradas;

public class ZoneDTO {
	private String nombre;
	private int id;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return this.nombre;
	}
}
