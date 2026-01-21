package giis.demo.tkrun.teammanagement;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.interviewSlot.InterviewSlotMenuController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.teammanagement.entrenamiento.TrainingCreationController;
import giis.demo.ui.interviewSlot.InterviewSlotMenu;
import giis.demo.ui.teammanagment.ExistingTeamManagement;
import giis.demo.ui.teammanagment.TeamCreationView;
import giis.demo.ui.teammanagment.TeamManagementView;
import giis.demo.ui.teammanagment.entrenamiento.TrainingCreationView;

public class TeamManagementController extends AbstractController {
	private TeamManagementView teamManagementView;
	
	public TeamManagementController(TeamManagementView teamManagementView, AuditService audit) {
		super(audit);
		this.teamManagementView = teamManagementView;
		addActionListeners();
		this.teamManagementView.setVisible(true);
	}
	
	private void addActionListeners() {

		addLoggedAction(teamManagementView.getBtCrear(), "Crear Equipo",
				() -> new TeamCreationController(new TeamCreationView(), audit));

		addLoggedAction(teamManagementView.getBtVer(), "Gestionar Equipos",
				() -> new TeamExistingTeamManagerController(new ExistingTeamManagement(), audit));

		addLoggedAction(teamManagementView.getBtnEntrenamientos(), "Crear Entrenamiento",
				() -> new TrainingCreationController(new TrainingCreationView(), audit));

		addLoggedAction(teamManagementView.getBtnGestionarFranjas(), "Gestionar Franjas",
				() -> new InterviewSlotMenuController(new InterviewSlotMenu(), audit));
		
	}
}
