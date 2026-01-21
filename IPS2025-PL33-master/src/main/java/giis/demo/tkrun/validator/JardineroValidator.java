package giis.demo.tkrun.validator;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import giis.demo.tkrun.employee.EmployeeViewDTO;
import giis.demo.tkrun.employee.schedule.ScheduleModel;
import giis.demo.tkrun.employee.schedule.ScheduleResolvedViewDTO;
import giis.demo.tkrun.reserva.HorarioOcupadoDTO;
import giis.demo.tkrun.reserva.ReservaDAO;
import giis.demo.tkrun.teammanagement.entrenamiento.EntrenamientoDAO;

public class JardineroValidator {

	private EmployeeViewDTO jardinero;
	private Date date;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private int id_instalacion;
	private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");

	/*******************************************************************************************/
	private ScheduleModel sModel = new ScheduleModel();
	private EntrenamientoDAO eModel = new EntrenamientoDAO();
	private ReservaDAO rModel = new ReservaDAO();

	public JardineroValidator(EmployeeViewDTO jardinero, Date date, String horaInicio, String horaFin,
			int id_instalacion) {
		this.jardinero = jardinero;
		this.date = date;
		this.horaInicio = LocalTime.parse(horaInicio, fmt);
		this.horaFin = LocalTime.parse(horaFin, fmt);
		this.id_instalacion = id_instalacion;
	}

	public boolean validarHorarioLaboral() {
		// Comprobar hora inicio < hora fin
		if (!horaInicio.isBefore(horaFin)) {
			JOptionPane.showMessageDialog(null, "La hora de inicio no puede ser posterior a la hora de fin");
			return false;
		}

		// Obtener dia de la semana de la fecha
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		String[] diasSemana = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int index = (dayOfWeek == Calendar.SUNDAY) ? 6 : dayOfWeek - 2;
		String diaSemana = diasSemana[index];
		
		// Obtener horario semanal resuelto
		List<ScheduleResolvedViewDTO> horarios = sModel.getHorarioResueltoSemana(jardinero.id_empleado, date);
		
		// Buscar horario del dia concreto
		ScheduleResolvedViewDTO horarioDia = horarios.stream().filter(h -> h.dia_semana.equalsIgnoreCase(diaSemana))
				.findFirst().orElse(null);

		// Si no hay horario no se puede añadir horario ese dia
		if (horarioDia == null || horarioDia.hora_inicio == null || horarioDia.hora_fin == null) {
			JOptionPane.showMessageDialog(null, "El trabajador no está disponible en ese horario");
			return false;
		}

		LocalTime horaInicioLaboral = LocalTime.parse(horarioDia.hora_inicio, fmt);
		LocalTime horarioFinLaboral = LocalTime.parse(horarioDia.hora_fin, fmt);

		// Comprobar que las horas pertenecen al horario del trabajador.
		boolean dentro = !horaInicio.isBefore(horaInicioLaboral) && !horaFin.isAfter(horarioFinLaboral);

		if (!dentro) {
			JOptionPane.showMessageDialog(null, "El trabajador no está disponible en ese horario");
			return false;
		}

		return dentro;

	}

	public boolean validarEntrenamientos() {
		// Comprobar hora inicio < hora fin
		if (!horaInicio.isBefore(horaFin)) {
			JOptionPane.showMessageDialog(null, "La hora de inicio no puede ser posterior a la hora de fin");
			return false;
		}

		// Formatear fecha a String yyyy-MM-dd
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fechaStr = sdf.format(date);

		// Convertir horas a String HH:mm
		String horaInicioStr = horaInicio.format(fmt);
		String horaFinStr = horaFin.format(fmt);

		// Comprobar solapamientos
		String mensaje = eModel.hasOverlap(fechaStr, horaInicioStr, horaFinStr, id_instalacion, 0);

		if (mensaje != null) {
			JOptionPane.showMessageDialog(null, "No se puede añadir la tarea porque hay un entrenamiento a esa hora");
			return false;
		}

		return true;

	}

	public boolean validarReservasExternas() {
		// Comprobar hora inicio < hora fin
		if (!horaInicio.isBefore(horaFin)) {
			JOptionPane.showMessageDialog(null, "La hora de inicio no puede ser posterior a la hora de fin");
			return false;
		}

		// Obtener periodos ocupados:
		List<HorarioOcupadoDTO> ocupados = rModel.findHorariosOcupados(id_instalacion, date);

		// Comprobar si se solapa alguna franja con horario seleccionado:
		boolean haySolapamiento = ocupados.stream()
				.anyMatch(h -> !horaFin.isBefore(LocalTime.parse(h.getHora_inicio(), fmt))
						&& !horaInicio.isAfter(LocalTime.parse(h.getHora_fin(), fmt)));
		
		if (haySolapamiento) {
			JOptionPane.showMessageDialog(null, "No se puede crear la tarea porque la instalación está ocupada por una reserva o evento en ese horario");
			return false;
		}

		return true;
	}

}
