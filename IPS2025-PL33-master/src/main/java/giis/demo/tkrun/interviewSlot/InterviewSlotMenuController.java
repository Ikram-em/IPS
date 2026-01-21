package giis.demo.tkrun.interviewSlot;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.interviewSlot.AssignInterviewSlotView;
import giis.demo.ui.interviewSlot.InterviewSlotMenu;
import giis.demo.ui.interviewSlot.InterviewSlotView;

public class InterviewSlotMenuController extends AbstractController {

	private InterviewSlotMenu view;

	public InterviewSlotMenuController(InterviewSlotMenu view, AuditService audit) {
		super(audit);
		this.view = view;
		initView();
		addActionListeners();
	}

	private void initView() {
		this.view.setVisible(true);
	}

	private void addActionListeners() {
		addLoggedAction(view.getBtgestionFranjas(), "Gestionar franjas",
				() -> new InterviewSlotController(new InterviewSlotView(), audit));
		addLoggedAction(view.getBtAsignarEntrevista(), "Asignar entrevista",
				() -> new AssignInterviewSlotController(new AssignInterviewSlotView(), audit));
	}

}
