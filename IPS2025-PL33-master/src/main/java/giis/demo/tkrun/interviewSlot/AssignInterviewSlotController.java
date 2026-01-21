package giis.demo.tkrun.interviewSlot;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.interviewSlot.AssignInterviewSlotView;

public class AssignInterviewSlotController extends AbstractController {

	private AssignInterviewSlotView view;

	public AssignInterviewSlotController(AssignInterviewSlotView view, AuditService audit) {
		super(audit);
		this.view = view;
		initView();
	}

	private void initView() {
		this.view.setVisible(true);
		addActionListener();
	}

	private void addActionListener() {
		addLoggedAction(view.getBtGuardarCambios(), "Asignar Entrevista a Franja", () -> {
			Integer id = view.guardarCambios();

			if (id != null) {
				audit.log("INFO", "Asignada entrevista a la franja con id=" + id);
			}
		});
	}

}
