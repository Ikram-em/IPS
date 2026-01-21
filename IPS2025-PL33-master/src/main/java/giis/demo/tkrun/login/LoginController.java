package giis.demo.tkrun.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import giis.demo.tkrun.accion.campana.CampanaDTO;
import giis.demo.tkrun.accion.campana.CampanaModel;
import giis.demo.tkrun.accion.campana.comprar.AddShareholderController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.logger.CompositeLogger;
import giis.demo.tkrun.logger.DbLogger;
import giis.demo.tkrun.logger.FileLogger;
import giis.demo.tkrun.logger.ILogger;
import giis.demo.tkrun.logger.LoggerModel;
import giis.demo.ui.acciones.campana.AddShareholderView;
import giis.demo.ui.login.LoginMenuView;
import giis.demo.ui.login.LoginView;
import giis.demo.ui.noticias.GaleriaNoticias;

public class LoginController {
	private LoginMenuView loginView;
	private CampanaModel campanaModel;
	
	public LoginController(LoginMenuView loginView) {
		this.loginView = loginView;
		this.campanaModel = new CampanaModel();
		loadActionListeners();
		this.loginView.setVisible(true);
	}
	
	public void loadActionListeners() {
		this.loginView.getBtnLoginEmployee().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginEmployeeController(new LoginView());
			}
		});
		this.loginView.getBtnLoginShareholder().addActionListener(e -> {
			new LoginShareholderController(new LoginView());
		});
		this.loginView.getBtnCreateShareholder().addActionListener(e -> {
	        openAddShareholderView();
	    });
		this.loginView.getBtnGaleriaNoticias().addActionListener(e -> {
	        new GaleriaNoticias(); 
	    });
	}
	
	private void openAddShareholderView() {
		List<CampanaDTO> campanasActivas = this.campanaModel.getCampanasEnFaseDada(3);
		
		if (campanasActivas.size() == 0) {
			JOptionPane.showMessageDialog(loginView, "No puede registrarse como accionista en este momento porque no hay ninguna campaña actual que lo permita");
			return;
		}
		
		new AddShareholderController(new AddShareholderView()); 
	}
}
