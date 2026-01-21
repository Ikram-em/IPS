package giis.demo.tkrun.teammanagement;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.teammanagment.ExistingTeamManagement;

public class TeamExistingTeamManagerController extends AbstractController {

	private ExistingTeamManagement view;

	public TeamExistingTeamManagerController(ExistingTeamManagement view, AuditService audit) {
		super(audit);
		this.view = view;
		initView();

	}

	private void initView() {
		this.view.setVisible(true);
		addActionListener();
	}

	private void addActionListener() {
		addLoggedAction(view.getBtGuardarCambios(), "Guardar Modificación Equipo", () -> {
			Integer id = view.guardarCambios();

			if (id != null) {
				audit.log("INFO", "Equipo modificado con id=" + id);
			}
		});
	}

}
