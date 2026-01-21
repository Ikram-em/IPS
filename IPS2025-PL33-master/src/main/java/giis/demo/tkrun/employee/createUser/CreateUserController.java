package giis.demo.tkrun.employee.createUser;

import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.employee.EmployeeViewDTO;
import giis.demo.tkrun.employee.RoleDTO;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.ui.employee.CreateUserView;
import giis.demo.util.SwingUtil;

public class CreateUserController extends AbstractController {
	private CreateUserView createUserView;
	private UserModel uModel;
	private EmployeeViewDTO employeeViewDTO;
	private RoleDTO rol;
	
	public CreateUserController(CreateUserView createUserView, EmployeeViewDTO employee, RoleDTO rol,
			AuditService audit) {
		super(audit);
		this.createUserView = createUserView;
		this.uModel = new UserModel();
		this.employeeViewDTO = employee;
		this.rol = rol;
		addListeners();
		this.createUserView.setVisible(true);
	}
	
	private void addListeners() {
		this.createUserView.getCancelBtn().addActionListener(e -> SwingUtil.exceptionWrapper(() -> {
			this.createUserView.dispose();
		}));
		
		addLoggedAction(createUserView.getCreateBtn(), "Asignar usuario", () -> {
			Integer id = saveUser();

			if (id != null) {
				audit.log("INFO", "Usuario añadido con id=" + id);
			}
		});


	}
	
	private Integer saveUser() {
		String username = createUserView.getLoginForm().getUsernameTx().getText();
		if (username == null || username.isBlank()) {
			JOptionPane.showMessageDialog(createUserView, "El nombre de usuario no puede estar vacío");
			return null;
		}
		String password = createUserView.getLoginForm().getPasswordTx().getText();
		if (password == null || password.isBlank()) {
			JOptionPane.showMessageDialog(createUserView, "La contraseña no puede estar vacía");
			return null;
		}
		if (password.length() < 8) {
			JOptionPane.showMessageDialog(createUserView, "La contraseña debe contener 8 o más caracteres");
			return null;
		}
		boolean repeatedUsername = uModel.getUsernameInUse(username);
		if (repeatedUsername) {
			JOptionPane.showMessageDialog(createUserView, "El nombre de usuario ingresado ya se encuentra en uso");
			return null;
		}
		
		Integer id_user = uModel.createUser(username, password, rol.getId_rol(), employeeViewDTO.getId_empleado());
		createUserView.dispose();
		JOptionPane.showMessageDialog(null, "El usuario fue creado correctamente");

		return id_user;
	}
}
