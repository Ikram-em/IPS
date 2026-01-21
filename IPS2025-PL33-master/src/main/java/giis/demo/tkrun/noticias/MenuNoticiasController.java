package giis.demo.tkrun.noticias;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.noticias.GaleriaNoticias;
import giis.demo.ui.noticias.GestionNoticias;
import giis.demo.ui.noticias.MenuNoticiasView;

public class MenuNoticiasController extends AbstractController {
	
	private MenuNoticiasView view;
	
	public MenuNoticiasController(MenuNoticiasView view, AuditService audit) {
		super(audit);
		this.view = view;
		initView();
	}
	
	private void initView() {
		view.setVisible(true);
		addActionListeners() ;
	}
	
	private void addActionListeners() {

		addLoggedAction(view.getBtnCrearNoticia(), "Gestionar noticias",
				() -> new GestionarNoticiasController(new GestionNoticias(), audit));
		
		addLoggedAction(view.getBtnVerNoticias(), "Ver Noticias", () -> new GaleriaNoticias());

	}

}
