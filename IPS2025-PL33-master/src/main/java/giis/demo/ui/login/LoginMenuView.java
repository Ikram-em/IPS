package giis.demo.ui.login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LoginMenuView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton employeeLoginBtn;
	private JButton shareholderLoginBtn;
	private JButton createShareholderBtn;
	private JButton newsPortalBtn;

	public LoginMenuView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(getBtnLoginEmployee());
		contentPane.add(getBtnLoginShareholder());
		contentPane.add(getBtnCreateShareholder());
		contentPane.add(getBtnGaleriaNoticias());
	}

	public JButton getBtnLoginEmployee() {
		if (employeeLoginBtn == null) {
			employeeLoginBtn = new JButton("Login empleados");
			employeeLoginBtn.setFont(new Font("Calibri", Font.PLAIN, 15));
			employeeLoginBtn.setBackground(new Color(255, 255, 255));	
			employeeLoginBtn.setPreferredSize(new Dimension(300, 30));
		}
		return employeeLoginBtn;
	}
	
	public JButton getBtnLoginShareholder() {
		if (shareholderLoginBtn == null) {
			shareholderLoginBtn = new JButton("Login accionistas");
			shareholderLoginBtn.setFont(new Font("Calibri", Font.PLAIN, 15));
			shareholderLoginBtn.setBackground(new Color(255, 255, 255));	
			shareholderLoginBtn.setPreferredSize(new Dimension(300, 30));
		}
		return shareholderLoginBtn;
	}
	
	public JButton getBtnCreateShareholder() {
		if (createShareholderBtn == null) {
			createShareholderBtn = new JButton("Registrarse como accionista");
			createShareholderBtn.setFont(new Font("Calibri", Font.PLAIN, 15));
			createShareholderBtn.setBackground(new Color(255, 255, 255));	
			createShareholderBtn.setPreferredSize(new Dimension(300, 30));
		}
	    return createShareholderBtn;
	}
	
	public JButton getBtnGaleriaNoticias() {
		if (newsPortalBtn == null) {
			newsPortalBtn = new JButton("Noticias");
			newsPortalBtn.setFont(new Font("Calibri", Font.PLAIN, 15));
			newsPortalBtn.setBackground(new Color(255, 255, 255));	
			newsPortalBtn.setPreferredSize(new Dimension(300, 30));
		}
	    return newsPortalBtn;
	}
}
