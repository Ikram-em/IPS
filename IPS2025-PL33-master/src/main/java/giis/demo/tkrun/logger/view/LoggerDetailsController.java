package giis.demo.tkrun.logger.view;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.logger.LoggerModel;
import giis.demo.ui.logger.LoggerDetailsView;

public class LoggerDetailsController extends AbstractController {

	private LoggerDetailsView view;
	private SesionLogDTO sesion;
	private LoggerModel model = new LoggerModel();

	public LoggerDetailsController(LoggerDetailsView loggerDetailsView, SesionLogDTO sesion, AuditService audit) {
		super(audit);
		this.view = loggerDetailsView;
		this.sesion = sesion;
		initView();
	}

	private void initView() {
		view.setVisible(true);
		cargarLog();
	}

	private void cargarLog() {
		DefaultTableModel modelTable = (DefaultTableModel) view.getTable().getModel();
		modelTable.setRowCount(0); // Limpiar tabla
		List<UserLogDTO> logs = model.getLogsSesion(sesion.getSession_id());
		for (UserLogDTO l : logs) {
			modelTable.addRow(new Object[] { l.getFecha_hora(), l.getUser_id(), l.getAccion(), l.getDetalles() });
		}
	}

}
