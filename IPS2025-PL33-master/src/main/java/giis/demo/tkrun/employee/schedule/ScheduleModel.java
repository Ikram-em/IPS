package giis.demo.tkrun.employee.schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import giis.demo.util.Database;

public class ScheduleModel {
	
	Database dataBase = new Database();
	
	// HORARIO SEMANAL
	private static final String SEARCH_WEEKLY_SCHEDULE_ID_EMPLOYEE = 
									"SELECT id_horario, id_empleado, dia, hora_inicio, hora_fin FROM horarioSemanal WHERE id_empleado = ?";
	private static final String UPDATE_WEEKLY_SCHEDULE = 
									"UPDATE HorarioSemanal SET hora_inicio = ?, hora_fin = ? WHERE id_horario = ?";
	private static final String INSERT_WEEKLY_SCHEDULE = 
									"INSERT INTO HorarioSemanal (id_empleado, dia, hora_inicio, hora_fin) VALUES (?, ?, ?, ?)";
	
	// HORARIO ESPECIFICO
	private static final String UPDATE_SPECIFIC_SCHEDULE = 
		    						"UPDATE HorarioEspecifico SET hora_inicio = ?, hora_fin = ?, dia_semana = ? WHERE id_empleado = ? AND fecha = ?";
	private static final String INSERT_SPECIFIC_SCHEDULE = 
		    						"INSERT INTO HorarioEspecifico (id_empleado, fecha, dia_semana, hora_inicio, hora_fin) VALUES (?, ?, ?, ?, ?)";
	private static final String SELECT_SPECIFIC_SCHEDULE_BETWEEN_DATES = 
									"SELECT id_empleado, fecha, dia_semana, hora_inicio, hora_fin FROM HorarioEspecifico WHERE id_empleado = ? AND fecha BETWEEN ? AND ?";
	private static final String GET_SPECIFIC_SCHEDULE_ID = "SELECT id_horario FROM HorarioEspecifico WHERE id_empleado = ? AND fecha = ?";
	
	// HORARIO COMPLETO
	private static final String CHECK_SPECIFIC_SCHEDULE_EXISTS = "SELECT COUNT(*) FROM HorarioEspecifico WHERE id_empleado = ? AND fecha = ?";
	

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------//
	
	
	public List<ScheduleWeeklyViewDTO> getEmployeeWeeklySchedule(int id_empleado) {
		return dataBase.executeQueryPojo(ScheduleWeeklyViewDTO.class, SEARCH_WEEKLY_SCHEDULE_ID_EMPLOYEE, id_empleado);
	}

	public Integer actualizarHorario(ScheduleWeeklyViewDTO s) {
		dataBase.executeUpdate(UPDATE_WEEKLY_SCHEDULE, s.hora_inicio, s.hora_fin, s.id_horario);
		return s.id_horario;
	}
	
	public Integer addHorarioSemanal(ScheduleWeeklyViewDTO s) {
		return dataBase
				.executeInsertAndReturnId(INSERT_WEEKLY_SCHEDULE, s.id_empleado, s.dia, s.hora_inicio, s.hora_fin)
				.intValue();
	}
	

	public Integer addHorarioEspecifico(ScheduleSpecificViewDTO s) {
	    // Comprobar si ya existe un horario específico para ese empleado y fecha
		List<Object[]> result = dataBase.executeQueryArray(CHECK_SPECIFIC_SCHEDULE_EXISTS, s.id_empleado, s.fecha);
	    long count = 0;
	    if (!result.isEmpty() && result.get(0)[0] != null)
	        count = ((Number) result.get(0)[0]).longValue();

	    if (count > 0) {
	        // Ya existe → actualizar
			dataBase.executeUpdate(UPDATE_SPECIFIC_SCHEDULE, s.hora_inicio, s.hora_fin, s.dia_semana, s.id_empleado,
					s.fecha);

			// Obtener ID existente
			List<Object[]> idResult = dataBase.executeQueryArray(GET_SPECIFIC_SCHEDULE_ID, s.id_empleado, s.fecha);

			if (!idResult.isEmpty() && idResult.get(0)[0] != null) {
				return ((Number) idResult.get(0)[0]).intValue();
			}

	    } else {
	        // No existe → insertar nuevo
			return dataBase.executeInsertAndReturnId(INSERT_SPECIFIC_SCHEDULE, s.id_empleado, s.fecha, s.dia_semana,
					s.hora_inicio, s.hora_fin).intValue();
	    }
		return null;
	}
	
	
	public ScheduleWeeklyViewDTO getHorarioDia (int id_empleado, String diaSemana) {
		List<ScheduleWeeklyViewDTO> horarios = getEmployeeWeeklySchedule(id_empleado);
		for (ScheduleWeeklyViewDTO s: horarios) {
			if (s.dia.equalsIgnoreCase(diaSemana)) {
				return s;
			}
		}
		return null;
	}
	
	// Obtiene el calendario final de un empleado. Los 7 dias de la semana con horario semanal + especifico.
	public List<ScheduleResolvedViewDTO> getHorarioResueltoSemana(int id_empleado, Date fechaSeleccionada) {
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(fechaSeleccionada);

	    // 👇 Asegurar que la semana empieza en lunes
	    cal.setFirstDayOfWeek(Calendar.MONDAY);

	    // Ir al lunes de esa semana
	    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	    Date lunes = limpiarHora(cal.getTime()); // 00:00

	    // Ir al domingo (fin de semana)
	    cal.add(Calendar.DAY_OF_MONTH, 6);
	    Date domingo = finDia(cal.getTime()); // 23:59:59

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lunesStr = sdf.format(lunes);
		String domingoStr = sdf.format(domingo);

	    // Obtener horarios específicos dentro de la semana
	    List<ScheduleSpecificViewDTO> especificos = dataBase.executeQueryPojo(
				ScheduleSpecificViewDTO.class, SELECT_SPECIFIC_SCHEDULE_BETWEEN_DATES, id_empleado, lunesStr,
				domingoStr
	    );

	    // Obtener horarios semanales base
	    List<ScheduleWeeklyViewDTO> semanal = getEmployeeWeeklySchedule(id_empleado);

	    // Construir resultado día a día
	    String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
	    List<ScheduleResolvedViewDTO> resultado = new ArrayList<>();

	    for (String dia : diasSemana) {
	        ScheduleSpecificViewDTO horarioEspecifico = especificos.stream()
					// .filter(e -> e.dia_semana.equalsIgnoreCase(dia) && esMismaSemana(e.fecha,
					// fechaSeleccionada))
					.filter(e -> e.dia_semana.equalsIgnoreCase(dia))
	            .findFirst()
	            .orElse(null);

	        ScheduleResolvedViewDTO res = new ScheduleResolvedViewDTO();
	        res.dia_semana = dia;
	        res.id_empleado = id_empleado;

	        if (horarioEspecifico != null) {
	            res.hora_inicio = horarioEspecifico.hora_inicio;
	            res.hora_fin = horarioEspecifico.hora_fin;
	            res.origen = "Específico";
	        } else {
	            ScheduleWeeklyViewDTO hs = semanal.stream()
	                .filter(s -> s.dia.equalsIgnoreCase(dia))
	                .findFirst()
	                .orElse(null);

	            if (hs != null) {
	                res.hora_inicio = hs.hora_inicio;
	                res.hora_fin = hs.hora_fin;
	                res.origen = "Semanal";
	            } else {
	                res.hora_inicio = null;
	                res.hora_fin = null;
	                res.origen = "Sin horario";
	            }
	        }

	        resultado.add(res);
	    }

	    return resultado;
	
		
	}
	
	
	// Métodos auxiliares para las fechas -------------------------------------------------------------------------------------------------------------------------------
	private Date limpiarHora(Date fecha) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(fecha);
	    c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
	    return c.getTime();
	}

	private Date finDia(Date fecha) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(fecha);
	    c.set(Calendar.HOUR_OF_DAY, 23);
	    c.set(Calendar.MINUTE, 59);
	    c.set(Calendar.SECOND, 59);
	    c.set(Calendar.MILLISECOND, 999);
	    return c.getTime();
	}



}
