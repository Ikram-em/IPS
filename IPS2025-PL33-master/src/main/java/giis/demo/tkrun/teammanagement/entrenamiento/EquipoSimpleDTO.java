package giis.demo.tkrun.teammanagement.entrenamiento;

public class EquipoSimpleDTO {
	private String nombre;
	private int id_equipo;

	public int getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(int id_equipo) {
		this.id_equipo = id_equipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String toString() {
		return this.nombre;
	}
}
