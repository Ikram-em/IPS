package giis.demo.tkrun.partidos.entradas;

import java.util.List;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.partidos.PartidoDTO;
import giis.demo.tkrun.partidos.PartidoModel;
import giis.demo.ui.partidos.entradas.EntradasView;
import giis.demo.ui.partidos.entradas.SeleccionAsientosView;

public class EntradasController extends AbstractController {
	private EntradasView entradasView;
	private PartidoModel partidoModel;
	
	public EntradasController(EntradasView entradasView, AuditService audit) {
		super(audit);
		this.entradasView = entradasView;
		this.partidoModel = new PartidoModel();
		initView();
	}
	
	private void initView() {
		loadPartidos();
		addButtonListeners();
		this.entradasView.setVisible(true);
	}
	
	private void loadPartidos() {
		List<PartidoDTO> partidosFuturos = this.partidoModel.getPartidosFuturosConPrecio();
		for (PartidoDTO partido: partidosFuturos) {
			entradasView.getMatchSelector().addItem(partido);
		}
	}
	
	private void addButtonListeners() {
		entradasView.getBtnComprarEntradas().addActionListener(e -> {
			PartidoDTO partido = (PartidoDTO) entradasView.getMatchSelector().getSelectedItem();
			if (partido != null) {
				// Log manual
				audit.log("BOTON_CLICK", "Acceso a comprar entradas, idPartido=" + partido.getId_partido());

				new SeleccionAsientosController(new SeleccionAsientosView(), partido, audit);
				entradasView.dispose();
			}
		});
	}
}
