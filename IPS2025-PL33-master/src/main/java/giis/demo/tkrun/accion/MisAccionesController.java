package giis.demo.tkrun.accion;

import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.accion.compra.CompraAccionesController;
import giis.demo.tkrun.accion.venta.VentaAccionesController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.login.ShareholderSession;
import giis.demo.ui.acciones.misacciones.GestionMisAcciones;
import giis.demo.ui.acciones.misacciones.compra.ComprarAccionesAjenas;
import giis.demo.ui.acciones.misacciones.venta.VentaAccionesPropias;

public class MisAccionesController extends AbstractController {

	private GestionMisAcciones view;

	public MisAccionesController(GestionMisAcciones view, AuditService audit) {
		super(audit);
		this.view = view;

		initView();
	}

	private void initView() {
		view.setVisible(true);
		addActionListeners();
	}

	private void addActionListeners() {

		addLoggedAction(view.getBtVenta(), "Poner en Venta acciones", () -> {
			accederMenuVenta();
		});

		addLoggedAction(view.getBtComprar(), "Comprar Acciones en Venta", () -> {
			accederMenuCompra();
		});

	}

	private void accederMenuVenta() {
		ShareholderSession session = ShareholderSession.get();
		int idAccionista = session.getId();
		new VentaAccionesController(new VentaAccionesPropias(), idAccionista, audit);
	}

	private void accederMenuCompra() {
		ShareholderSession session = ShareholderSession.get();
		int idAccionista = session.getId();
		new CompraAccionesController(new ComprarAccionesAjenas(), idAccionista, audit);
	}

}
