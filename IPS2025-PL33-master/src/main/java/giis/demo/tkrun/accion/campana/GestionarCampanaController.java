package giis.demo.tkrun.accion.campana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.acciones.campana.CrearCampanaOptionPanel;
import giis.demo.ui.acciones.campana.GestionarCampanaView;
import giis.demo.ui.acciones.campana.GestionarFasesView;

public class GestionarCampanaController extends AbstractController {
	
	private GestionarCampanaView view;
	private CampanaModel cM = new CampanaModel();
	private boolean isActiva;

	public GestionarCampanaController(GestionarCampanaView gestionarCampanaView, AuditService audit) {
		super(audit);
		this.view = gestionarCampanaView;
		initView();
	}
	
	private void initView() {
		this.view.setVisible(true);
		this.isActiva = cM.getEstadoCampana();
		gestionarBotones();
		addActionListeners();
	}
	
	private void gestionarBotones() {
		if (!isActiva) {
			view.getBtnCrearCampana().setEnabled(true);
			view.getBtnGestionarCampana().setEnabled(false);
		}
		else {
			view.getBtnCrearCampana().setEnabled(false);
			view.getBtnGestionarCampana().setEnabled(true);
		}
	}

	private void addActionListeners() {

		addLoggedAction(view.getBtnCrearCampana(), "Creación nueva Campaña de Acciones",
				() -> new JOptionPnController(new CrearCampanaOptionPanel(), view, audit));

		addLoggedAction(view.getBtnGestionarCampana(), "Gestión Fase Campaña de Acciones",
				() -> new GestionarFasesController(view, new GestionarFasesView(), audit));


		view.getBtnSalirGeneral().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.dispose();
			}
		});
	}



}
