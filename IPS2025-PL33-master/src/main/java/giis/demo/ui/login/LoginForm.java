package giis.demo.ui.login;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField usernameTx;
	private JPasswordField passwordTx;
	private JButton showPasswordButton;

	public LoginForm() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(20, 50, 20, 50));
		add(getKeyValuePn(createLabel("Usuario: ", Font.BOLD,  15), getUsernameTx()));
		add(getPasswordPn());
	}
	
	private JPanel getPasswordPn() {
		JPanel passwordPn = new JPanel();
		passwordPn.setLayout(new BoxLayout(passwordPn, BoxLayout.X_AXIS));
		passwordPn.add(getKeyValuePn(createLabel("Contraseña: ", Font.BOLD,  15), getPasswordTx()));
		passwordPn.add(getShowPasswordPn());
		
		return passwordPn;
	}
	
	public JTextField getUsernameTx() {
		if (usernameTx == null) {
			usernameTx = new JTextField();
			usernameTx.setBackground(Color.WHITE);
			usernameTx.setFont(new Font("Calibri", Font.PLAIN, 15));
			usernameTx.setColumns(10);
		}
		return usernameTx;
	}
	
	public JPasswordField getPasswordTx() {
		if (passwordTx == null) {
			passwordTx = new JPasswordField();
			passwordTx.setBackground(Color.WHITE);
			passwordTx.setFont(new Font("Calibri", Font.PLAIN, 15));
			passwordTx.setColumns(10);
		}
		return passwordTx;
	}
	
	private JPanel getShowPasswordPn() {
		JPanel showPassPn = new JPanel();
		showPassPn.setBackground(Color.WHITE);
		showPassPn.setPreferredSize(new Dimension(80, 30));
		showPassPn.add(getShowPasswordBtn());
		
		return showPassPn;
	}
	
	public JButton getShowPasswordBtn() {
		if (showPasswordButton == null) {
			showPasswordButton = new JButton("Show");
			showPasswordButton.setBackground(Color.WHITE);
			char defaultEcho = passwordTx.getEchoChar();
			showPasswordButton.addActionListener(e -> {
			    boolean showing = passwordTx.getEchoChar() == 0;

			    if (showing) {
			    	passwordTx.setEchoChar(defaultEcho);
			    	showPasswordButton.setText("Show");
			    } else {
			    	passwordTx.setEchoChar((char)0);
			    	showPasswordButton.setText("Hide");
			    }
			});
		}
		return showPasswordButton;
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
		key.setPreferredSize(new Dimension(90, 30));
		value.setPreferredSize(new Dimension(90, 30));
		keyValuePn.add(key);
		keyValuePn.add(value);
		return keyValuePn;
	}

}
