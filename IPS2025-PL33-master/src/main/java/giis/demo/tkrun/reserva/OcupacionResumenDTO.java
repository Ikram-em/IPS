package giis.demo.tkrun.reserva;

import java.util.Date;
import giis.demo.util.Util;

/**
 * DTO para el resumen de ocupación mostrado en la tabla de ReservaView.
 * Representa un periodo de tiempo durante el cual la instalacion está bloqueada
 * (incluye el margen de 90 minutos de limpieza/descanso después del uso por
 * equipos).
 */
public class OcupacionResumenDTO {

	private String inicioOcupado;
	private String finOcupado;
	private String tipo;

	public OcupacionResumenDTO(Date inicio, Date fin, String tipo, boolean esEmpleado) {
		this.inicioOcupado = Util.timeToTimeString(inicio);
		Date finConMargen = esEmpleado ? Util.addMinutes(fin, 90) : fin;
		this.finOcupado = Util.timeToTimeString(finConMargen);
		this.tipo = tipo;
	}

	public String getInicioOcupado() {
		return inicioOcupado;
	}

	public String getFinOcupado() {
		return finOcupado;
	}

	public String getTipo() {
		return tipo;
	}
}