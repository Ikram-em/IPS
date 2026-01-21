package giis.demo.tkrun.abonos;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.suplemento.SuplementoController;
import giis.demo.tkrun.ui.suplemento.SuplementoPartidoView;
import giis.demo.ui.abonos.AbonoTemporadaView;
import giis.demo.ui.abonos.MenuAbonosView;

public class MenuAbonosController extends AbstractController {
	private MenuAbonosView menuAbonosView;
	private AuditService audit;
	
	public MenuAbonosController(MenuAbonosView menuAbonosView, AuditService audit) {
		super(audit);
		this.menuAbonosView = menuAbonosView;
		this.audit = audit;
		addActionListeners();
		this.menuAbonosView.setVisible(true);
	}
	
	private void addActionListeners() {

		addLoggedAction(menuAbonosView.getBtnAbonoTemporada(), "Comprar abono de temporada",
				() -> new AbonoTemporadaController(new AbonoTemporadaView(), audit));

		addLoggedAction(menuAbonosView.getBtnSuplementos(), "Pagar suplemento partido",
				() -> new SuplementoController(new SuplementoPartidoView(), audit));

	}
}
