package giis.demo.tkrun.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import giis.demo.tkrun.accion.AccionistaDAO;
import giis.demo.tkrun.accion.AccionistaUserDTO;
import giis.demo.tkrun.accion.compra.ShareholderMenuController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.logger.CompositeLogger;
import giis.demo.tkrun.logger.DbLogger;
import giis.demo.tkrun.logger.FileLogger;
import giis.demo.tkrun.logger.ILogger;
import giis.demo.tkrun.logger.LoggerModel;
import giis.demo.ui.accionista.ShareholderMenuView;
import giis.demo.ui.login.LoginView;

public class LoginShareholderController {
	private LoginView loginShareholderView;
	private AccionistaDAO accionistaDAO;
	
	public LoginShareholderController(LoginView loginShareholderView) {
		this.loginShareholderView = loginShareholderView;
		addActionListeners();
		this.accionistaDAO = new AccionistaDAO();
		this.loginShareholderView.setVisible(true);
	}
	
	private void addActionListeners() {
		this.loginShareholderView.getLoginBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
	}
	
	private void login() {
		String username = this.loginShareholderView.getLoginForm().getUsernameTx().getText();
		String password = this.loginShareholderView.getLoginForm().getPasswordTx().getText();
		
		AccionistaUserDTO shareholderWithCredentials = accionistaDAO.getShareholderWithCredentials(username, password);
		if (shareholderWithCredentials == null) {
			JOptionPane.showMessageDialog(loginShareholderView, "Las credenciales son incorrectas.");
			return;
		}
		
		ShareholderSession.get().login(shareholderWithCredentials.getId_accionista());
		loginShareholderView.dispose();
		
		// Inicializar el logger
		String nombreFichero = "audit_shareholder" + shareholderWithCredentials.getId_accionista() + "_"
				+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".log";
		LoggerModel logginModel = new LoggerModel();
		ILogger logger = new CompositeLogger(new DbLogger(logginModel), new FileLogger(nombreFichero));

		AuditService audit = new AuditService(logger, logginModel);

		int sessionId = audit.startSession(shareholderWithCredentials.getId_accionista());

		// Registrar evento de login
		audit.log("LOGIN", "Inicio de sesión accionista con id =" + shareholderWithCredentials.getId_accionista());
				
		new ShareholderMenuController(new ShareholderMenuView(), audit);
	}
}
