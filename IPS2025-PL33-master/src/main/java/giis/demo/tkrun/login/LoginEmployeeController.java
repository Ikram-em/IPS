package giis.demo.tkrun.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import giis.demo.tkrun.MainMenuController;
import giis.demo.tkrun.employee.createUser.UserDTO;
import giis.demo.tkrun.employee.createUser.UserModel;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.logger.CompositeLogger;
import giis.demo.tkrun.logger.DbLogger;
import giis.demo.tkrun.logger.FileLogger;
import giis.demo.tkrun.logger.ILogger;
import giis.demo.tkrun.logger.LoggerModel;
import giis.demo.ui.MainMenuView;
import giis.demo.ui.login.LoginView;

public class LoginEmployeeController {
	private LoginView loginEmployeeView;
	private UserModel userModel;
	
	public LoginEmployeeController(LoginView loginEmployeeView) {
		this.loginEmployeeView = loginEmployeeView;
		this.userModel = new UserModel();
		addActionListeners();
		this.loginEmployeeView.setVisible(true);
	}
	
	private void addActionListeners() {
		this.loginEmployeeView.getLoginBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
	}
	
	private void login() {
		String username = this.loginEmployeeView.getLoginForm().getUsernameTx().getText();
		String password = this.loginEmployeeView.getLoginForm().getPasswordTx().getText();

		UserDTO userWithCredentials = userModel.getUserWithCredentials(username, password);
		if (userWithCredentials == null) {
			JOptionPane.showMessageDialog(loginEmployeeView, "Las credenciales son incorrectas.");
			return;
		}

		Session.get().login(userWithCredentials.getId_user(), userWithCredentials.getId_rol(),
				userWithCredentials.getNombre_rol(), userWithCredentials.getId_empleado());

		// Inicializar el logger

		String nombreFichero = "audit_user" + userWithCredentials.getId_user() + "_"
				+ new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".log";

		LoggerModel logginModel = new LoggerModel();
		ILogger logger = new CompositeLogger(new DbLogger(logginModel), new FileLogger(nombreFichero));

		AuditService audit = new AuditService(logger, logginModel);

		audit.startSession(userWithCredentials.getId_user());

		// Registrar evento de login
		audit.log("LOGIN", "Inicio de sesión usuarioId=" + userWithCredentials.getId_user());

		loginEmployeeView.dispose();
		new MainMenuController(new MainMenuView(), audit);
	}
}
