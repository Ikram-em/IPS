package giis.demo.tkrun.accion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.accion.campana.GestionarCampanaController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.acciones.AccionesMainMenuView;
import giis.demo.ui.acciones.campana.GestionarCampanaView;
import giis.demo.ui.acciones.misacciones.GestionMisAcciones;

public class AccionMainMenuController extends AbstractController {
	
	private AccionesMainMenuView view;

	public AccionMainMenuController(AccionesMainMenuView view, AuditService audit) {
		super(audit);
		this.view = view;
		initView();
	}
	
	private void initView() {
		view.setVisible(true);
		setActionListeners();
	}
	
	private void setActionListeners() {
		view.getBtnSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.dispose();
			}
		});
		
		addLoggedAction(view.getBtnGestionarCampana(), "Gestionar Campaña de Acciones",
				() -> new GestionarCampanaController(new GestionarCampanaView(), audit));
	}

}
