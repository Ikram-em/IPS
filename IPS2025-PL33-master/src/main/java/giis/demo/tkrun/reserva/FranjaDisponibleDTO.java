package giis.demo.tkrun.reserva;

/**
 * Clase FranjaDisponibleDTO
 * 
 * Representa un hueco disponible para reservar (por ejemplo, una cancha o sala).
 * Contiene la hora de inicio, la hora de fin y la duración en minutos.
 * Se usa para enviar esta información desde la lógica de negocio a la interfaz.
 */
public class FranjaDisponibleDTO {
    private String horaInicio;
    private String horaFin;
    private int duracionMinutos;

    public FranjaDisponibleDTO() {
    }

    public FranjaDisponibleDTO(String horaInicio, String horaFin, int duracionMinutos) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.duracionMinutos = duracionMinutos;
    }

    // Getters y Setters
    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }
}
