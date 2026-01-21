package giis.demo.tkrun.tienda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.partidos.entradas.EntradasController;
import giis.demo.ui.partidos.entradas.EntradasView;
import giis.demo.ui.tienda.MenuTiendaView;
import giis.demo.ui.tienda.TiendaView;

public class MenuTiendaController extends AbstractController {
	
	private MenuTiendaView view;

	public MenuTiendaController(MenuTiendaView view, AuditService audit) {
		super(audit);
		this.view = view;
		initView();
	}
	
	private void initView() {
		view.setVisible(true);
		addActionListeners();
	}
	
	private void addActionListeners() {

		addLoggedAction(view.getBtnAccederTienda(), "Acceso a tienda de productos",
				() -> new TiendaController(new TiendaView(), audit));


		view.getBtnComprarEntradas().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EntradasController(new EntradasView(), audit);
			}
		});

	}

}
