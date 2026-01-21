package giis.demo.ui.acciones.campana;

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

import com.toedter.calendar.JDateChooser;

import giis.demo.ui.login.LoginForm;

public class AddShareholderView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel centerPn;
	private JTextField txNombre;
	private JTextField txApellido;
	private JTextField txDni;
	private JDateChooser dtFechaNacimiento;
	private JButton btnCreate;
	private LoginForm loginForm;

	public AddShareholderView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 659, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getTitlePn(), BorderLayout.NORTH);
		contentPane.add(getCenterPn(), BorderLayout.CENTER);
		contentPane.add(getBottomPn(), BorderLayout.SOUTH);
	}

	private JPanel getTitlePn() {
		JPanel title = new JPanel();
		title.setBackground(Color.WHITE);
		title.setLayout(new FlowLayout(FlowLayout.CENTER));
		title.add(createLabel("Nuevo accionista", Font.BOLD, 16));
		return title;
	}
	
	private JPanel getCenterPn() {
		if (centerPn == null) {
			centerPn = new JPanel();
			centerPn.setLayout(new BoxLayout(centerPn, BoxLayout.Y_AXIS));
			centerPn.setBorder(new EmptyBorder(10, 10, 10, 10));
			
			centerPn.add(getShareholderData());
			
			centerPn.add(getLoginForm());
		}
		
		return centerPn;
	}
	
	private JPanel getShareholderData() {
		JPanel pnShareholderData = new JPanel();
		pnShareholderData.setLayout(new BoxLayout(pnShareholderData, BoxLayout.Y_AXIS));
		pnShareholderData.setBackground(Color.WHITE);
		pnShareholderData.setBorder(new EmptyBorder(20, 50, 20, 50));
		pnShareholderData.add(getKeyValuePn(createLabel("Nombre:", Font.BOLD, 15), getTxNombre()));
		pnShareholderData.add(getKeyValuePn(createLabel("Apellido:", Font.BOLD, 15), getTxApellido()));
		pnShareholderData.add(getKeyValuePn(createLabel("DNI:", Font.BOLD, 15), getTxDni()));
		pnShareholderData.add(getKeyValuePn(createLabel("Fecha de nacimiento:", Font.BOLD, 15), getFechaNacimiento()));
		
		return pnShareholderData;
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
		bottomPn.add(getCreateBtn());
		return bottomPn;
	}
	
	public JButton getCreateBtn() {
		if (btnCreate == null) {
			btnCreate = new JButton("Aceptar");
		}
		return btnCreate;
	}
	
	public JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setBackground(Color.WHITE);
			txNombre.setFont(new Font("Calibri", Font.PLAIN, 15));
			txNombre.setColumns(10);
		}
		return txNombre;
	}
	
	public JTextField getTxApellido() {
		if (txApellido == null) {
			txApellido = new JTextField();
			txApellido.setBackground(Color.WHITE);
			txApellido.setFont(new Font("Calibri", Font.PLAIN, 15));
			txApellido.setColumns(10);
		}
		return txApellido;
	}
	
	public JTextField getTxDni() {
		if (txDni == null) {
			txDni = new JTextField();
			txDni.setBackground(Color.WHITE);
			txDni.setFont(new Font("Calibri", Font.PLAIN, 15));
			txDni.setColumns(10);
		}
		return txDni;
	}
	
	public JDateChooser getFechaNacimiento() {
		if (dtFechaNacimiento == null) {
			dtFechaNacimiento = new JDateChooser();
			dtFechaNacimiento.setPreferredSize(new Dimension(100, 25));
		}
		return dtFechaNacimiento;
	}
	
	private JPanel getKeyValuePn(Component key, Component value) {
		JPanel keyValuePn = new JPanel();
		keyValuePn.setLayout(new FlowLayout(FlowLayout.LEFT));
		key.setPreferredSize(new Dimension(100, 30));
		value.setPreferredSize(new Dimension(300, 30));
		keyValuePn.setBackground(Color.WHITE);
		keyValuePn.add(key);
		keyValuePn.add(value);
		return keyValuePn;
	}
	
	private JLabel createLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, size));
        label.setBorder(BorderFactory.createEmptyBorder(8,0,8,0));
        return label;
    }
}
