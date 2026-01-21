package giis.demo.tkrun.employee.schedule;


public class ScheduleResolvedViewDTO {
	    
	    public int id_empleado; 
	    public String dia_semana;   
	    public String hora_inicio; 
	    public String hora_fin;
	    public String origen;


	    public void setId_empleado(int id_empleado) {
	        this.id_empleado = id_empleado;
	    }


	    public void setDia_semana(String dia_semana) {
	        this.dia_semana = dia_semana;
	    }

	    public void setHora_inicio(String hora_inicio) {
	        this.hora_inicio = hora_inicio;
	    }

	    public void setHora_fin(String hora_fin) {
	        this.hora_fin = hora_fin;
	    }

	    public void setOrigen(String origen) {
	        this.origen = origen;
	    }
	
	   
}
