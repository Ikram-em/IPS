package giis.demo.tkrun.teammanagement;

import java.sql.Date;

public class PersonDTO {
	private int id;
	private String dni;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private int telefono;
	private double salarioAnual;

    public PersonDTO(int id, String dni, String nombre, String apellido, Date fechaNacimiento, int telefono, double salarioAnual) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.salarioAnual = salarioAnual;
    }

    public int getId() {
		return id;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public int getTelefono() {
		return telefono;
	}

	public double getSalarioAnual() {
		return salarioAnual;
	}

	@Override
    public String toString() {
        return String.format("%s %s",
                nombre, apellido);
    }

}
