package giis.demo.tkrun.partidos;

import java.util.List;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.partidos.precios.PreciosEntradasController;
import giis.demo.ui.partidos.PartidosView;
import giis.demo.ui.partidos.PnMatchDetails;
import giis.demo.ui.partidos.precios.PreciosEntradasView;

public class PartidosController extends AbstractController {
	private PartidosView partidosView;
	private PartidoModel partidoModel;
	
	public PartidosController(PartidosView partidosView, AuditService audit) {
		super(audit);
		this.partidosView = partidosView;
		this.partidoModel = new PartidoModel();
		initView();
	}
	
	private void initView() {
		loadMatches();
		this.partidosView.setVisible(true);
	}
	
	private void loadMatches() {
		List<PartidoDTO> partidos = this.partidoModel.getPartidos();
		this.partidosView.getPnMatchesList().removeAll();
		for (PartidoDTO partido : partidos) {
			PnMatchDetails pnMatch = new PnMatchDetails(partido);
			partidosView.getPnMatchesList().add(pnMatch);
			addMatchActionListeners(pnMatch, partido);
		}
		partidosView.getPnMatchesList().revalidate();
		partidosView.getPnMatchesList().repaint();
	}
	
	private void addMatchActionListeners(PnMatchDetails pnMatch, PartidoDTO partido) {

		addLoggedAction(pnMatch.getBtnGestionarPrecios(), "Gestionar precios partido con id=" + partido.getId_partido(),
				() -> {
					new PreciosEntradasController(new PreciosEntradasView(partido), partido, audit);
					partidosView.dispose();
		});

	}
}
