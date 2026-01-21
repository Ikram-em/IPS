package giis.demo.tkrun.employee.schedule;

public class ScheduleWeeklyViewDTO {
	
	public int id_horario;
	public int id_empleado;
	public String dia;
	public String hora_inicio; 
	public String hora_fin;  
    
    
    
	public void setId_horario(int idHorario) {
	    this.id_horario = idHorario;
	}

	public void setId_empleado(int idEmpleado) {
	    this.id_empleado = idEmpleado;
	}

	public void setDia(String dia) {
	    this.dia = dia;
	}

	public void setHora_inicio(String horaInicio) {
	    this.hora_inicio = horaInicio;
	}

	public void setHora_fin(String horaFin) {
	    this.hora_fin = horaFin;
	}

}
