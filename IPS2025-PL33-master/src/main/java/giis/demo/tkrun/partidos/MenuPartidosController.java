package giis.demo.tkrun.partidos;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.partidos.MenuPartidosView;
import giis.demo.ui.partidos.PartidoView;
import giis.demo.ui.partidos.PartidosView;

public class MenuPartidosController extends AbstractController {
	private MenuPartidosView menuPartidosView;
	
	public MenuPartidosController(MenuPartidosView menuPartidos, AuditService audit) {
		super(audit);
		this.menuPartidosView = menuPartidos;
		addActionListeners();
		this.menuPartidosView.setVisible(true);
	}
	

	private void addActionListeners() {
		
		addLoggedAction(menuPartidosView.getBtnVerPartidos(), "Ver partidos", () -> new PartidosController(new PartidosView(), audit));
		
		addLoggedAction(menuPartidosView.getBtnGestionPartidos(), "Añadir partidos",
				() -> new PartidoController(new PartidoView(), audit));

	}
}
