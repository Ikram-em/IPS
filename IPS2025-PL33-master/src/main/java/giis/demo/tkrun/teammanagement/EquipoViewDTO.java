package giis.demo.tkrun.teammanagement;

public class EquipoViewDTO {

	private int id_equipo;
	private String nombre;
	private int id_primer_entrenador;
	private int id_segundo_entrenador;
	private String tipo_equipo;
	private String categoria_equipo;

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

	public int getId_primer_entrenador() {
		return id_primer_entrenador;
	}

	public void setId_primer_entrenador(int id_primer_entrenador) {
		this.id_primer_entrenador = id_primer_entrenador;
	}

	public int getId_segundo_entrenador() {
		return id_segundo_entrenador;
	}

	public void setId_segundo_entrenador(int id_segundo_entrenador) {
		this.id_segundo_entrenador = id_segundo_entrenador;
	}

	public String getTipo_equipo() {
		return tipo_equipo;
	}

	public void setTipo_equipo(String tipo_equipo) {
		this.tipo_equipo = tipo_equipo;
	}

	public String getCategoria_equipo() {
		return categoria_equipo;
	}

	public void setCategoria_equipo(String categoria_equipo) {
		this.categoria_equipo = categoria_equipo;
	}

	@Override
	public String toString() {
		return "[" + id_equipo + "] " + nombre + ", " + tipo_equipo;
	}

}
