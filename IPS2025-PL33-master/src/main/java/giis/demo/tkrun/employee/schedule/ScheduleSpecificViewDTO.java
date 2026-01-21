package giis.demo.tkrun.employee.schedule;

public class ScheduleSpecificViewDTO {
	
	public int id_horario;
	public int id_empleado;
	public String fecha;
	public String dia_semana;
	public String hora_inicio; 
	public String hora_fin;
	
	
	public void setId_horario(int idHorario) {
		this.id_horario = idHorario;
	}
	public void setId_empleado(int idEmpleado) {
		this.id_empleado = idEmpleado;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public void setDia_semana(String diaSemana) {
		this.dia_semana = diaSemana;
	}
	public void setHora_inicio(String horaInicio) {
		this.hora_inicio = horaInicio;
	}
	public void setHora_fin(String horaFin) {
		this.hora_fin = horaFin;
	}  
    
    
    
	
	 

}
