package giis.demo.tkrun.employee;



public class EmployeeViewDTO {
	
	public int id_empleado;
	public String dni;
	public String nombre;
	public String apellido;
	public String fecha_nacimiento;
	public int telefono;
	public double salario_anual;
	public int id_posicion;
	public String nombre_posicion;
	public String username;
	public Integer id_rol;

	public int getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(int id_empleado) {
		this.id_empleado = id_empleado;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public double getSalario_anual() {
		return salario_anual;
	}

	public void setSalario_anual(double salario_anual) {
		this.salario_anual = salario_anual;
	}

	public int getId_posicion() {
		return id_posicion;
	}

	public void setId_posicion(int id_posicion) {
		this.id_posicion = id_posicion;
	}

	public String getNombre_posicion() {
		return nombre_posicion;
	}

	public void setNombre_posicion(String nombre_posicion) {
		this.nombre_posicion = nombre_posicion;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	@Override
	public String toString() {
		return "[" + id_empleado + "] " + nombre + ", " + apellido;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getId_rol() {
		return id_rol;
	}

	public void setId_rol(Integer id_rol) {
		this.id_rol = id_rol;
	}
	
	

}
