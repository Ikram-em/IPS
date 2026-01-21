package giis.demo.tkrun.partidos;

import java.time.LocalTime;

public class PartidoDTO {
	private int id_partido;
	private String fecha;
	private LocalTime hora_inicio;
	private LocalTime hora_fin;
	private int id_instalacion;
	private String nombreEquipoInterno;
	private String nombreEquipoExterno;
	private String nombre_instalacion;
	private boolean priced;
	private int id_equipoInterno;
	private double precioSuplemento;

	public int getId_partido() {
		return id_partido;
	}

	public void setId_partido(int id_partido) {
		this.id_partido = id_partido;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(LocalTime hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public LocalTime getHora_fin() {
		return hora_fin;
	}

	public void setHora_fin(LocalTime hora_fin) {
		this.hora_fin = hora_fin;
	}

	public int getId_instalacion() {
		return id_instalacion;
	}

	public void setId_instalacion(int id_instalacion) {
		this.id_instalacion = id_instalacion;
	}

	public String getNombreEquipoInterno() {
		return nombreEquipoInterno;
	}

	public void setNombreEquipoInterno(String nombreEquipoInterno) {
		this.nombreEquipoInterno = nombreEquipoInterno;
	}

	public String getNombreEquipoExterno() {
		return nombreEquipoExterno;
	}

	public void setNombreEquipoExterno(String nombreEquipoExterno) {
		this.nombreEquipoExterno = nombreEquipoExterno;
	}

	public String getNombre_instalacion() {
		return nombre_instalacion;
	}

	public void setNombre_instalacion(String nombre_instalacion) {
		this.nombre_instalacion = nombre_instalacion;
	}

	public boolean isPriced() {
		return priced;
	}

	public void setPriced(boolean priced) {
		this.priced = priced;
	}

	public String toString() {
		return nombreEquipoInterno + " vs " + nombreEquipoExterno + " (" + fecha + ")";
	}

	public void setId_EquipoInterno(int id_equipoInterno) {
		this.id_equipoInterno = id_equipoInterno;
	}
	
	public int getIdEquipoInterno() {
		return id_equipoInterno;
	}

	public double getPrecioSuplemento() {
        return precioSuplemento;
    }

    public void setPrecioSuplemento(double precioSuplemento) {
        this.precioSuplemento = precioSuplemento;
    }
}
