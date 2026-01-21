package giis.demo.tkrun.logger.view;

import java.util.List;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.logger.LoggerModel;
import giis.demo.ui.logger.LoggerDetailsView;
import giis.demo.ui.logger.LoggerView;
import giis.demo.ui.logger.PnIndividualLogger;

public class LoggerViewController extends AbstractController {

	private LoggerView view;
	private LoggerModel model = new LoggerModel();

	public LoggerViewController(LoggerView loggerView, AuditService audit) {
		super(audit);
		this.view = loggerView;
		initView();
	}

	private void initView() {
		view.setVisible(true);
		addListeners();
		cargarLogs();
	}

	private void cargarLogs() {
		view.getPnContenedorLogs().removeAll();
		List<SesionLogDTO> sesiones;

		if (view.getRdBtUsuario().isSelected()) {
			sesiones = model.getAllSessionsByUser();
		} else {
			sesiones = model.getAllSessions();
		}

		for (SesionLogDTO s : sesiones) {
			PnIndividualLogger pnSesion = new PnIndividualLogger();
			String especificador = s.toString();
			pnSesion.getLbNombreSesion().setText(especificador);
			addListener(pnSesion, s);
			view.getPnContenedorLogs().add(pnSesion);
		}

		view.getPnContenedorLogs().revalidate();
		view.getPnContenedorLogs().repaint();
	}

	private void addListeners() {
		view.getRdBtUsuario().addActionListener(e -> {
			cargarLogs();
			audit.log("INFO", "Ordenado por usuario");
		});
		view.getRdBtFecha().addActionListener(e -> {
			cargarLogs();
			audit.log("INFO", "Ordenar por fecha");
		});
	}

	private void addListener(PnIndividualLogger pn, SesionLogDTO sesion) {
		addLoggedAction(pn.getBtnVerLog(),
				"Ver log de la sesión con id=" + sesion.getSession_id() + " para el user con id=" + sesion.getUser_id(),
				() -> {
					new LoggerDetailsController(new LoggerDetailsView(), sesion, audit);
				});

	}

}
