package giis.demo.tkrun.interviewSlot;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.interviewSlot.InterviewSlotView;

public class InterviewSlotController extends AbstractController {

	private InterviewSlotView view;

	public InterviewSlotController(InterviewSlotView view, AuditService audit) {
		super(audit);
		this.view = view;
		initView();
	}

	private void initView() {
		this.view.setVisible(true);
		addActionListeners();
	}

	private void addActionListeners() {
		addLoggedAction(view.getBtGuardarCambios(), "Guardar nueva Franja", () -> {
			Integer id = view.guardarFranja();

			if (id != null) {
				audit.log("INFO", "Franja añadida con id=" + id);
			}
		});
	}

}
