package giis.demo.tkrun.jardineria;

import java.security.Timestamp;

public class LaborJardineriaDTO {

	private int id;
	private int id_empleado;
	private Timestamp fecha;
	private Timestamp hora_inicio;
	private Timestamp hora_fin;
	private int id_instalacion;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(int id_empleado) {
		this.id_empleado = id_empleado;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Timestamp getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(Timestamp hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public Timestamp getHora_fin() {
		return hora_fin;
	}

	public void setHora_fin(Timestamp hora_fin) {
		this.hora_fin = hora_fin;
	}

	public int getId_instalacion() {
		return id_instalacion;
	}

	public void setId_instalacion(int id_instalacion) {
		this.id_instalacion = id_instalacion;
	}
	
}
