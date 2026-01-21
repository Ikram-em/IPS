package giis.demo.tkrun.accion.compra;

import java.awt.Window;

import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.accion.MisAccionesController;
import giis.demo.tkrun.accion.campana.comprar.ComprarAccionesController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.login.ShareholderSession;
import giis.demo.ui.StartingView;
import giis.demo.ui.acciones.campana.CompraAccionesCampanaView;
import giis.demo.ui.acciones.misacciones.GestionMisAcciones;
import giis.demo.ui.accionista.ShareholderMenuView;

public class ShareholderMenuController extends AbstractController {
	private ShareholderMenuView shareholderMenuView;
	
	public ShareholderMenuController(ShareholderMenuView shareholderMenuView, AuditService audit) {
		super(audit);
		this.shareholderMenuView = shareholderMenuView;
		addActionListeners();
		this.shareholderMenuView.setVisible(true);
	}
	
	private void addActionListeners() {
		addLoggedAction(shareholderMenuView.getBtnMisAcciones(), "Ver mis acciones",
				() -> new MisAccionesController(new GestionMisAcciones(), audit));
		addLoggedAction(shareholderMenuView.getBtnComprarAcciones(), "Comprar acciones",
			() -> new ComprarAccionesController(new CompraAccionesCampanaView(), audit));
		this.shareholderMenuView.getLogoutBtn().addActionListener(e -> {
			logout();
		});
	}
	
	private void logout() {
		int result = JOptionPane.showConfirmDialog(
		        null,
		        "¿Estás seguro de que quieres cerrar sesión?",
		        "Confirmar cierre de sesión",
		        JOptionPane.OK_CANCEL_OPTION
		);

		if (result == JOptionPane.OK_OPTION) {
			ShareholderSession.get().logout();
			for (Window window : Window.getWindows()) {
				audit.endSession();
			    window.dispose();
			}

			new StartingView().setVisible(true);
		}
	}
}
