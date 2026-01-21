package giis.demo.tkrun.employee.schedule;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.employee.EmployeeViewDTO;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.validator.ScheduleValidator;
import giis.demo.ui.employee.schedule.ScheduleMenuView;
import giis.demo.util.SwingUtil;

public class GeneralScheduleController extends AbstractController {
	
	private ScheduleMenuView view;
	private EmployeeViewDTO employee;
	private ScheduleModel sModel;
	private Date selectedDate;
	private String selectedDay;
	

	public GeneralScheduleController(ScheduleMenuView view, EmployeeViewDTO employee, AuditService audit) {
		super(audit);
		this.view = view;
		this.employee = employee;
		sModel = new ScheduleModel();
		
		configureButtonsWeekly();
		configureButtonsSpecific();
		configureButtonsCompleteSchedule();
	}

	// Método para mostrar y editar el horario semanal general.
	public void showWeeklySchedule() {
		configureWeeklyView();
		view.getCardLayout().show(view.getMainPanel(), "pnWeeklySchedule");
	}

	// Método para añadir un horario semanal específico.
	public void showSpecificSchedule() {
		configureSpecificView();
		resetSpecific();
		view.getCardLayout().show(view.getMainPanel(), "pnSpecificSchedule");		
	}
	
	// Método para mostrar calendario.
	public void showShowSchedule() {
		resetAllCalendar();
		configureShowScheduleView();
		
		view.getCardLayout().show(view.getMainPanel(), "pnShowSchedule");		
	}
	
	
	
	private void configureWeeklyView() {
		view.setNameWeeklySchedule(employee.nombre, employee.apellido);
		List<ScheduleWeeklyViewDTO> weekSchedule = sModel.getEmployeeWeeklySchedule(employee.id_empleado);
		configureTableWeekly(weekSchedule);
	}
	
	private void configureSpecificView() {
		view.setTitleSpecificSchedule(employee);
		
	}
	
	private void configureShowScheduleView() {
		view.setTituloCompleto(employee);
	}
	
	private void configureButtonsCompleteSchedule() {
		view.getBtnAtrasCalendarioFinal().addActionListener(e -> SwingUtil.exceptionWrapper(() -> {
			view.getCardLayout().show(view.getMainPanel(), "pnMainMenu");
		}));
		
		view.getCalendarioCompleto().getDateEditor().addPropertyChangeListener("date", evt -> {
			Date fechaSeleccionada = (Date) evt.getNewValue();
			if (fechaSeleccionada != null) {
				mostrarSemana(fechaSeleccionada);
			}
		});
	}
	
	private void mostrarSemana(Date fechaSeleccionada) {
		// Obtener la semana completa resuelta del empleado
	    List<ScheduleResolvedViewDTO> semana = sModel.getHorarioResueltoSemana(employee.id_empleado, fechaSeleccionada);

	    // Crear un modelo de tabla nuevo y solo lectura
	    DefaultTableModel model = new DefaultTableModel(new Object[] {"Día", "Inicio", "Fin"}, 0) {
			private static final long serialVersionUID = -5689568089578519941L;

			@Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // todas las celdas son de solo lectura
	        }
	    };

	    // Rellenar la tabla según la lista
	    for (ScheduleResolvedViewDTO horarioDia : semana) {
	        model.addRow(new Object[] {
	            horarioDia.dia_semana,
	            horarioDia.hora_inicio,
	            horarioDia.hora_fin
	        });
	    }
	    

	    // Asignar el modelo a la tabla
	    JTable tabla = view.getPnHorarioCompleto().getTableSemanal();
	    tabla.setModel(model);

	    // Centrar todas las columnas
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

	    for (int i = 0; i < model.getColumnCount(); i++) {
	        tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	    }

	    // Limpiar solo las horas existentes
	    for (int row = 0; row < model.getRowCount(); row++) {
	        model.setValueAt(null, row, 1);
	        model.setValueAt(null, row, 2);
	    }

	    // Rellenar según el orden de la lista (asumiendo 7 elementos)
	    for (int row = 0; row < model.getRowCount(); row++) {
	        String diaEnTabla = (String) model.getValueAt(row, 0);
	        // Buscar horario para ese día
	        ScheduleResolvedViewDTO horarioDia = semana.stream()
	        	    .filter(d -> d.dia_semana != null && d.dia_semana.equalsIgnoreCase(diaEnTabla))
	        	    .findFirst()
	        	    .orElse(null);

	        if (horarioDia != null) {
	            model.setValueAt(horarioDia.hora_inicio, row, 1);
	            model.setValueAt(horarioDia.hora_fin, row, 2);
	        }
	        else {
	            // Si no hay horario para ese día, dejar vacío o null
	            model.setValueAt(null, row, 1);
	            model.setValueAt(null, row, 2);
	        }
	    }
	}
	

	private void configureButtonsWeekly() {
		
		// Eliminar todos los listeners previos
	    for (ActionListener listener : view.getBtnGuardarHorarioSemanal().getActionListeners()) {
	        view.getBtnGuardarHorarioSemanal().removeActionListener(listener);
	    }

		addLoggedAction(view.getBtnGuardarHorarioSemanal(), "Guardar horario semanal", () -> {
			Integer id = saveWeeklySchedule();

			if (id != null) {
				audit.log("INFO", "Horario semanal añadido/actualizado con id=" + id);
			}
		});
		
		view.getBtnVolverAtras().addActionListener(e -> SwingUtil.exceptionWrapper(() -> {
			view.getCardLayout().show(view.getMainPanel(), "pnMainMenu");
		}));
	}
	
	private void configureButtonsSpecific() {
		
	    // Eliminar todos los listeners previos
	    for (ActionListener listener : view.getBtnGuardarHorarioEspecifico().getActionListeners()) {
	        view.getBtnGuardarHorarioEspecifico().removeActionListener(listener);
	    }
		
		addLoggedAction(view.getBtnGuardarHorarioEspecifico(), "Guardar horario específico", () -> {
			Integer id = saveSpecificSchedule();

			if (id != null) {
				audit.log("INFO", "Horario específico añadido con id=" + id);
			}
		});

		view.getBtnGuardarHorarioEspecifico().setEnabled(false);
		view.getBtnVolverAtrasEspecifico().addActionListener(e -> SwingUtil.exceptionWrapper(() -> {
			view.getCardLayout().show(view.getMainPanel(), "pnMainMenu");
		}));
		view.getDateChooser_1().getDateEditor().addPropertyChangeListener("date", evt -> {
	        this.selectedDate = (Date) evt.getNewValue();

	        if (selectedDate != null) {
	        	habilitarEditor(true);
	        	guardarDiaSeleccionado(selectedDate);

	        } else {
	        	habilitarEditor(false);
	        }
	    });
	}
	
	private void habilitarEditor(Boolean b) {
		view.getPnSemanal1().setVisible(b);
        view.getBtnGuardarHorarioEspecifico().setEnabled(b);
	}
	
	private void guardarDiaSeleccionado(Date selectedDate) {
		// Obtener dia de la seleccion en español
	    @SuppressWarnings("deprecation")
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE", new Locale("es", "ES"));
	    this.selectedDay = sdf.format(selectedDate); // por ejemplo, "miércoles"
	    selectedDay = selectedDay.substring(0,1).toUpperCase() + selectedDay.substring(1).toLowerCase();
	    
	}
	

	private Integer saveWeeklySchedule() {
		// Obtener DTOs
		List<ScheduleWeeklyViewDTO> weekSchedule = sModel.getEmployeeWeeklySchedule(employee.id_empleado);
		ScheduleValidator weeklyValidator = new ScheduleValidator(view);
		
		Integer idH = null;

		if (weeklyValidator.validarHorasSemana()) {
			DefaultTableModel model = (DefaultTableModel) view.getPnSemanal().getTableSemanal().getModel();
			
			// Actualizar DTOs
			for (int i = 0; i < model.getRowCount(); i++) {
				String dia = (String) model.getValueAt(i, 0);
			    String hi = (String) model.getValueAt(i, 1);
			    String hf = (String) model.getValueAt(i, 2);

			    hi = (hi == null || hi.trim().isEmpty()) ? null : hi.trim();
			    hf = (hf == null || hf.trim().isEmpty()) ? null : hf.trim();

			    ScheduleWeeklyViewDTO s = weekSchedule.stream()
			        .filter(w -> w.dia.equalsIgnoreCase(dia))
			        .findFirst()
			        .orElse(null);

			    if (s != null) {
			        // actualizar horario existente
			        s.hora_inicio = hi;
			        s.hora_fin = hf;
					idH = sModel.actualizarHorario(s);
			    } else if (hi != null && hf != null) {
			        // insertar nuevo horario
			        s = new ScheduleWeeklyViewDTO();
			        s.id_empleado = employee.id_empleado;
			        s.dia = dia;
			        s.hora_inicio = hi;
			        s.hora_fin = hf;
					idH = sModel.addHorarioSemanal(s);
			    }
			}

		    JOptionPane.showMessageDialog(view, "Horario guardado correctamente");
			return idH;
		}
		return idH;
		
		
	}
	
	private Integer saveSpecificSchedule() {
		ScheduleValidator scheduleValidator = new ScheduleValidator(view);
		Integer idH = null;
		
		Date datehora_inicio = (Date) view.getPnSemanal1().getSpHoraInicio().getValue();
		Date datehora_fin = (Date) view.getPnSemanal1().getSpHoraFin().getValue();
		
		// Formatear a HH:mm para guardar como String
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    String hora_inicio = sdf.format(datehora_inicio);
	    String hora_fin    = sdf.format(datehora_fin);
		
	    List<ScheduleResolvedViewDTO> horarioResulto = sModel.getHorarioResueltoSemana(employee.id_empleado, selectedDate);
	    
		if (scheduleValidator.validarHorarioEspecifico(horarioResulto, hora_inicio, hora_fin, selectedDay, selectedDate)) {
			hora_inicio = (hora_inicio == null || hora_inicio.equals("---")) ? null : hora_inicio;
			hora_fin = (hora_fin == null || hora_fin.equals("---")) ? null : hora_fin;
			
			ScheduleSpecificViewDTO s = new ScheduleSpecificViewDTO();
			s.id_empleado = employee.id_empleado;
			// s.fecha = new Timestamp(selectedDate.getTime());
			SimpleDateFormat ssdf = new SimpleDateFormat("yyyy-MM-dd");
			s.fecha = ssdf.format(selectedDate);
			s.dia_semana = this.selectedDay;
			s.hora_inicio = hora_inicio;
			s.hora_fin    = hora_fin;
			
			idH = sModel.addHorarioEspecifico(s);
			
			JOptionPane.showMessageDialog(view, "Horario guardado correctamente");
		}
		return idH;
		
	}
	
	
	private void configureTableWeekly(List<ScheduleWeeklyViewDTO> weekSchedule) {
		DefaultTableModel model = (DefaultTableModel) view.getPnSemanal().getTableSemanal().getModel();

		dataTable(weekSchedule, model);
	}

	
	private void dataTable(List<ScheduleWeeklyViewDTO> weekSchedule, DefaultTableModel model) {
		model.setRowCount(0); // Limpiar tabla antes de cargar

	    String[] diasSemana = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };

	    for (String dia : diasSemana) {
	        // Buscar si hay un horario para este día
	        ScheduleWeeklyViewDTO horarioDia = weekSchedule.stream()
	            .filter(s -> dia.equalsIgnoreCase(s.dia)) // Comparación por nombre del día
	            .findFirst()
	            .orElse(null);

	        String hi = (horarioDia != null && horarioDia.hora_inicio != null) ? horarioDia.hora_inicio : "";
	        String hf = (horarioDia != null && horarioDia.hora_fin != null) ? horarioDia.hora_fin : "";

	        model.addRow(new Object[] { dia, hi, hf });
	        
	        if (horarioDia == null) {
	        	horarioDia = new ScheduleWeeklyViewDTO();
	            horarioDia.id_empleado = this.employee.id_empleado;
	            horarioDia.dia = dia;
	            horarioDia.hora_inicio = null;
	            horarioDia.hora_fin = null;
	        }
	        
	    }
	}
	
	
	private void resetSpecific() {
		this.selectedDate = null;
		this.selectedDay = null;
		// Limpia el calendario de la vista específica
	    if (view.getDateChooser_1() != null) {
	        view.getDateChooser_1().setDate(null);
	    }

	    // Oculta los campos de edición
	    view.getPnSemanal1().setVisible(false);
	    view.getBtnGuardarHorarioEspecifico().setEnabled(false);
	}
	
	private void resetAllCalendar() {
		if (view.getCalendarioCompleto() != null) {
	        view.getCalendarioCompleto().setDate(null);
	    }

	    DefaultTableModel model = (DefaultTableModel) view.getPnHorarioCompleto().getTableSemanal().getModel();
	    for (int row = 0; row < model.getRowCount(); row++) {
	        model.setValueAt(null, row, 1);
	        model.setValueAt(null, row, 2);
	    }
	}
	

}
