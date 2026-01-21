package giis.demo.ui.employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.employee.EmployeeViewDTO;
import giis.demo.tkrun.employee.RoleDTO;
import giis.demo.ui.login.LoginForm;

public class CreateUserView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private EmployeeViewDTO employee;
	private RoleDTO rol;
	private JButton btnCreate;
	private JButton cancelBtn;
	private LoginForm loginForm;

	public CreateUserView(EmployeeViewDTO employee, RoleDTO rol) {
		this.employee= employee;
		this.rol = rol;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 715, 351);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnTitle(), BorderLayout.NORTH);
		contentPane.add(getCenterPn(), BorderLayout.CENTER);
		contentPane.add(getBottomPn(), BorderLayout.SOUTH);
	}

	private JPanel getPnTitle() {
		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnTitle.setBackground(Color.WHITE);
		pnTitle.add(createLabel("¿Desea crear un usuario para el nuevo empleado?", Font.BOLD, 20));
		return pnTitle;
	}
	
	private JPanel getCenterPn() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setBorder(new EmptyBorder(20, 50, 20, 50));
		centerPanel.add(getEmployeeData());
		centerPanel.add(getLoginForm());
		
		return centerPanel;
	}
	
	private JPanel getEmployeeData() {
		JPanel employeeDataPn = new JPanel();
		employeeDataPn.setLayout(new BoxLayout(employeeDataPn, BoxLayout.Y_AXIS));
		employeeDataPn.add(getKeyValuePn(createLabel("Nombre: ", Font.BOLD,  15), createLabel(employee.getNombre() + " " + employee.getApellido(), Font.PLAIN, 14)));
		employeeDataPn.add(getKeyValuePn(createLabel("Rol: ", Font.BOLD,  15), createLabel(rol.getNombre(), Font.PLAIN, 14)));
		
		return employeeDataPn;
	}
	
	public LoginForm getLoginForm() {
		if (loginForm == null) {
			loginForm = new LoginForm();
		}
		return loginForm;
	}
	
	private JPanel getBottomPn() {
		JPanel bottomPn = new JPanel();
		bottomPn.setLayout(new FlowLayout(FlowLayout.CENTER));
		bottomPn.add(getCancelBtn());
		bottomPn.add(getCreateBtn());
		return bottomPn;
	}
	
	public JButton getCancelBtn() {
		if (cancelBtn == null) {
			cancelBtn = new JButton("No, gracias");
		}
		return cancelBtn;
	}
	
	public JButton getCreateBtn() {
		if (btnCreate == null) {
			btnCreate = new JButton("Crear");
		}
		return btnCreate;
	}
	
	private JLabel createLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, size));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(4,0,0,0));
        return label;
    }
	
	private JPanel getKeyValuePn(Component key, Component value) {
		JPanel keyValuePn = new JPanel();
		keyValuePn.setBackground(Color.WHITE);
		keyValuePn.setLayout(new FlowLayout(FlowLayout.LEFT));
		key.setPreferredSize(new Dimension(100, 30));
		value.setPreferredSize(new Dimension(300, 30));
		keyValuePn.add(key);
		keyValuePn.add(value);
		return keyValuePn;
	}
}
