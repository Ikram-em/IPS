package giis.demo.tkrun.teammanagement;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.teammanagment.TeamCreationView;

public class TeamCreationController extends AbstractController {

	private TeamCreationView view;

	public TeamCreationController(TeamCreationView view, AuditService audit) {
		super(audit);
		this.view = view;
		initView();
		addActionListener();
	}

	private void initView() {
		this.view.setVisible(true);
	}

	private void addActionListener() {
		addLoggedAction(view.getBtAgregarEquipo(), "Guardar Nuevo Equipo", () -> {
			Integer id = view.crearEquipo();

			if (id != null) {
				audit.log("INFO", "Equipo creado con id=" + id);
			}
		});
	}

}
