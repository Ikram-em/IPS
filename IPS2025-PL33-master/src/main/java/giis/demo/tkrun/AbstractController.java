package giis.demo.tkrun;

import javax.swing.JButton;

import giis.demo.tkrun.logger.AuditService;

public abstract class AbstractController {

	protected AuditService audit;

	public AbstractController(AuditService audit) {
		this.audit = audit;
	}

	/**
	 * Helper para añadir un botón con logging automático
	 * 
	 * @param button       el botón al que añadir listener
	 * @param actionDetail detalle de la acción para el log
	 * @param action       acción a ejecutar al pulsar el botón
	 */
	protected void addLoggedAction(JButton button, String actionDetail, Runnable action) {
		button.addActionListener(e -> {
			audit.log("BOTON_CLICK", actionDetail);
			action.run();
		});
	}

}
