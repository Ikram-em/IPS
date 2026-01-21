package giis.demo.tkrun.jardineria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.employee.EmployeeViewDTO;
import giis.demo.tkrun.employee.schedule.ScheduleModel;
import giis.demo.tkrun.employee.schedule.ScheduleResolvedViewDTO;
import giis.demo.tkrun.instalacion.InstalacionDAO;
import giis.demo.tkrun.instalacion.InstalacionDTO;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.validator.JardineroValidator;
import giis.demo.ui.jardineria.PlanificarJardineroView;

public class PlanificarJardineroController extends AbstractController {
	
	private PlanificarJardineroView view;
	private EmployeeViewDTO jardinero;
	private ScheduleModel sModel = new ScheduleModel();
	private JardineriaModel jModel = new JardineriaModel();
	private InstalacionDAO iModel = new InstalacionDAO();
	
	public PlanificarJardineroController(PlanificarJardineroView view, EmployeeViewDTO jardinero, AuditService audit) {
		super(audit);
		this.view = view;
		this.jardinero = jardinero;
		initView();
	}
	
	private void initView() {
		view.setVisible(true);
		addActionListeners();
		setNombre();
		rellenarCboxes();
	}

	private void addActionListeners() {
		view.getDate().getDateEditor().addPropertyChangeListener("date", evt -> {
			Date selectedDate = (Date) evt.getNewValue();

			if (selectedDate != null) {
				mostrarSemana(selectedDate);
			}
		});

		addLoggedAction(view.getBtnGuardar(), "Guardar tarea de jardinería", () -> {
			Integer id = guardarTareaJardineria();

			if (id != null) {
				audit.log("INFO", "Añadida tarea jardinería con id=" + id);
			}
		});

		view.getBtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.dispose();
			}
		});
	}

	private void setNombre() {
		view.getLbTitulo().setText("Tareas de Jardinería: " + jardinero.nombre + " " + jardinero.apellido);
	}

	private void rellenarCboxes() {
		// Crear lista de horas: "" + 00:00, 00:30, 01:00, ... 23:30
		String[] horas = new String[49];
		horas[0] = ""; // valor vacío
		int idx = 1;
		for (int h = 0; h < 24; h++) {
			horas[idx++] = String.format("%02d:00", h);
			horas[idx++] = String.format("%02d:30", h);
		}

		// Combobox para inicio y fin con doble clic para editar
		view.getCbHoraInicio().setModel(new DefaultComboBoxModel<String>(horas));
		view.getCbHoraFin().setModel(new DefaultComboBoxModel<String>(horas));

		// Combobox para instalaciones
		List<InstalacionDTO> instalaciones = iModel.getInstalaciones();
		String[] nombres = instalaciones.stream().map(InstalacionDTO::getNombre).toArray(String[]::new);
		view.getCbInstalacion()
				.setModel(new DefaultComboBoxModel<String>(nombres));
	}

	private void mostrarSemana(Date fechaSeleccionada) {
		// Obtener la semana completa resuelta del empleado
		List<ScheduleResolvedViewDTO> semana = sModel.getHorarioResueltoSemana(jardinero.id_empleado,
				fechaSeleccionada);

		// Crear un modelo de tabla nuevo y solo lectura
		DefaultTableModel model = new DefaultTableModel(new Object[] { "Día", "Inicio", "Fin" }, 0) {
			private static final long serialVersionUID = -5689568089578519941L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // todas las celdas son de solo lectura
			}
		};

		// Rellenar la tabla según la lista
		for (ScheduleResolvedViewDTO horarioDia : semana) {
			model.addRow(new Object[] { horarioDia.dia_semana, horarioDia.hora_inicio, horarioDia.hora_fin });
		}

		// Asignar el modelo a la tabla
		JTable tabla = view.getPnHorario().getTableSemanal();
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
					.filter(d -> d.dia_semana != null && d.dia_semana.equalsIgnoreCase(diaEnTabla)).findFirst()
					.orElse(null);

			if (horarioDia != null) {
				model.setValueAt(horarioDia.hora_inicio, row, 1);
				model.setValueAt(horarioDia.hora_fin, row, 2);
			} else {
				// Si no hay horario para ese día, dejar vacío o null
				model.setValueAt(null, row, 1);
				model.setValueAt(null, row, 2);
			}
		}
	}

	private Integer guardarTareaJardineria() {
		String horaInicio = view.getCbHoraInicio().getSelectedItem().toString();
		String horaFin = view.getCbHoraFin().getSelectedItem().toString();
		int id_instalacion = iModel.getIdInstalacion(view.getCbInstalacion().getSelectedItem().toString());
		Integer id = null;
		JardineroValidator validator = new JardineroValidator(jardinero, view.getDate().getDate(), horaInicio, horaFin,
				id_instalacion);
		if (validator.validarHorarioLaboral() && validator.validarEntrenamientos()
				&& validator.validarReservasExternas()) {
			id = jModel.save(jardinero, view.getDate().getDate(), horaInicio, horaFin, id_instalacion);
			JOptionPane.showMessageDialog(null, "Se ha guardado correctamente la labor de jardinería.");
			view.dispose();
		}
		return id;
	}

}
