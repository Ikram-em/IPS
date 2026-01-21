package giis.demo.tkrun.accion.campana;

public class CampanaDTO {
	
	private int id_campana;
	private int fase;
	private String estado;
	private int n_accionesDisponibles;
	private int n_accionesVendidas;
	private String fecha_creacion;
	
	
	public int getId_campana() {
		return id_campana;
	}
	public void setId_campana(int id_campana) {
		this.id_campana = id_campana;
	}
	public int getFase() {
		return fase;
	}
	public void setFase(int fase) {
		this.fase = fase;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getN_accionesDisponibles() {
		return n_accionesDisponibles;
	}
	public void setN_accionesDisponibles(int n_accionesDisponibles) {
		this.n_accionesDisponibles = n_accionesDisponibles;
	}
	public int getN_accionesVendidas() {
		return n_accionesVendidas;
	}
	public void setN_accionesVendidas(int n_accionesVendidas) {
		this.n_accionesVendidas = n_accionesVendidas;
	}
	public String getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	
	public String toString() {
		
		return String.valueOf(id_campana) + " (" + fecha_creacion + ")";
	}

}
