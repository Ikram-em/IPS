package giis.demo.tkrun.validator;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import giis.demo.tkrun.employee.schedule.ScheduleResolvedViewDTO;
import giis.demo.ui.employee.schedule.ScheduleMenuView;

public class ScheduleValidator {
	
	private static final double MAX_DIA = 8.0;
    private static final double MAX_SEMANA = 40.0;
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");

    private ScheduleMenuView view;

    public ScheduleValidator(ScheduleMenuView view) {
        this.view = view;
    }

    public boolean validarHorasSemana() {
	    DefaultTableModel model = (DefaultTableModel) view.getPnSemanal().getTableSemanal().getModel();

        double totalSemanal = 0;

        for (int row = 0; row < model.getRowCount(); row++) {
        	String dia = (String) model.getValueAt(row, 0);
	        String hi = (String) model.getValueAt(row, 1);
	        String hf = (String) model.getValueAt(row, 2);

	        boolean hiVacio = hi == null || hi.isEmpty();
	        boolean hfVacio = hf == null || hf.isEmpty();

	        // Si ambas vacías, ignorar
	        if (hiVacio && hfVacio) continue;

	        // Validar presencia simultánea
	        if (hiVacio || hfVacio) {
	            JOptionPane.showMessageDialog(view,
	                    "En el día " + dia + " debe existir hora inicio y hora fin si se especifica alguna");
	            return false;
	        }
	        
	        // Parsear y validar orden de horas
	        LocalTime inicio = LocalTime.parse(hi, fmt);
	        LocalTime fin = LocalTime.parse(hf, fmt);
	        
	        if (!fin.isAfter(inicio)) {
	            JOptionPane.showMessageDialog(view,
	                    "En el día " + dia + " la hora de fin debe ser posterior a la hora de inicio");
	            return false;
	        }

	        double horasDia = Duration.between(inicio, fin).toMinutes() / 60.0;

	        if (horasDia > MAX_DIA) {
	            JOptionPane.showMessageDialog(view, 
	                "El día " + model.getValueAt(row, 0) + " supera las 8 horas (" + horasDia + "h)");
	            return false;
	        }

	        totalSemanal += horasDia;
	    }
        
        

	    if (totalSemanal > MAX_SEMANA) {
	        JOptionPane.showMessageDialog(view, 
	            "El total semanal supera las 40 horas (" + totalSemanal + "h)");
	        return false;
	    }

	    return true;
    }

    public boolean validarHorarioEspecifico(List<ScheduleResolvedViewDTO> horarioResuelto, String hi, String hf, String diaEspecifico, Date selectedDate) {
    	    	
    	// Comprobar que la fecha no sea anterior a hoy
    	Date hoy = new Date();

    	if (selectedDate.before(hoy)) {
    	    JOptionPane.showMessageDialog(view,
    	        "No se puede añadir un horario en un día anterior a hoy");
    	    return false;
    	}
    	
    	// Validar que la hora de fin sea posterior a la hora de inicio
        if (hi != null && hf != null && !hi.isEmpty() && !hf.isEmpty()) {
            LocalTime inicio = LocalTime.parse(hi, fmt);
            LocalTime fin = LocalTime.parse(hf, fmt);

            if (!fin.isAfter(inicio)) {
                JOptionPane.showMessageDialog(view,
                    "La hora de fin debe ser posterior a la hora de inicio");
                return false;
            }
        }
    	
    	if (hi == null || hf == null || hi.isEmpty() || hf.isEmpty()) return true;

        // Comprobación horas dia especifico
        double horasDia = calcularHoras(hi, hf);
        if (horasDia > MAX_DIA) {
            JOptionPane.showMessageDialog(view,
                "El día " + diaEspecifico + " supera las 8 horas (" + horasDia + "h)");
            return false;
        }

        // Calcular total semanal teniendo en cuenta el nuevo horario especifico.
        double totalSemanal = 0;
        boolean diaExiste = false;
        
        for (ScheduleResolvedViewDTO s : horarioResuelto) {
            String dia = s.dia_semana;
            String hinicial = s.hora_inicio;
            String hfin = s.hora_fin;

            if (dia == null) continue;

            // Si es el día que se está modificando, usar las nuevas horas
            if (dia.equalsIgnoreCase(diaEspecifico)) {
                hinicial = hi;
                hfin = hf;
                diaExiste = true;
            }

            if (hinicial == null || hfin == null || hinicial.isEmpty() || hfin.isEmpty())
                continue;

            double horas = calcularHoras(hinicial, hfin);

            if (horas > MAX_DIA) {
                JOptionPane.showMessageDialog(view,
                    "El día " + dia + " supera las 8 horas (" + horas + "h)");
                return false;
            }

            totalSemanal += horas;
        }

        // Si el día no existe aún en el horario, sumarlo igualmente
        if (!diaExiste) {
            totalSemanal += horasDia;
        }

        if (totalSemanal > MAX_SEMANA) {
            JOptionPane.showMessageDialog(view,
                "El total semanal supera las 40 horas (" + totalSemanal + "h)");
            return false;
        }

        return true;
    }

    private double calcularHoras(String hi, String hf) {
        LocalTime inicio = LocalTime.parse(hi, fmt);
        LocalTime fin = LocalTime.parse(hf, fmt);
        return Duration.between(inicio, fin).toMinutes() / 60.0;
    }

}
