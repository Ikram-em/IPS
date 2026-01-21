package giis.demo.tkrun.employee;

public class PositionDTO {
	private String nombre;
	private String tipo;
	private Integer id_rol;
	private int id_posicion;
	public int getId_posicion() {
		return id_posicion;
	}
	public void setId_posicion(int id_posicion) {
		this.id_posicion = id_posicion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getId_rol() {
		return id_rol;
	}
	public void setId_rol(Integer id_rol) {
		this.id_rol = id_rol;
	}
	
	public String toString() {
		return this.nombre;
	}
}
