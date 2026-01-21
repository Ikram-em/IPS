package giis.demo.tkrun.interviewSlot;

import java.sql.Date;

public class FranjaEntrevistaDTO {
    private int idFranja;
    private int idJugador;
    private Date fecha;
    private String horaInicio;
    private String horaFin;
    private String medioComunicacion = "";

    public FranjaEntrevistaDTO(int idFranja, int idJugador, Date fecha, String horaInicio, String horaFin) {
        this.idFranja = idFranja;
        this.idJugador = idJugador;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
    
    public FranjaEntrevistaDTO(int idFranja, int idJugador, Date fecha, String horaInicio, String horaFin, String medio) {
        this.idFranja = idFranja;
        this.idJugador = idJugador;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.medioComunicacion = medio;
    }

	public int getIdFranja() {
		return idFranja;
	}


    public String getMedioComunicacion() { return medioComunicacion; }

	public int getIdJugador() {
		return idJugador;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
}
