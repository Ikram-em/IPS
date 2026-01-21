package giis.demo.tkrun.medico;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.medico.HistorialLesionView;

public class HistorialLesionController extends AbstractController {

	private HistorialLesionView view;
	private int lesionId;
	private LesionesModel model = new LesionesModel();

	public HistorialLesionController(HistorialLesionView historialLesionView, int lesionId, AuditService audit) {
		super(audit);
		this.view = historialLesionView;
		this.lesionId = lesionId;
		initView();
	}

	private void initView() {
		view.setVisible(true);
		cargarHistorial();
	}

	private void cargarHistorial() {
		List<SesionLesionViewDTO> historial = model.getHistorialLesion(lesionId);

		// Obtener el modelo de la tabla
		DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
		tableModel.setRowCount(0); // Limpiar la tabla antes de cargar datos

		// Rellenar la tabla con los datos del historial
		for (SesionLesionViewDTO s : historial) {
			tableModel.addRow(new Object[] { s.getFecha_hora(), // Fecha y hora
					s.getAccion(), // Acción realizada
					s.getPrioridad(), // Prioridad de la lesión
					s.getDiagnostico() // Descripción
			});
		}
	}

}
