package giis.demo.ui.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private LoginForm loginForm;
	private JButton loginBtn;

	public LoginView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnTitle(), BorderLayout.NORTH);
		contentPane.add(getLoginForm(), BorderLayout.CENTER);
		contentPane.add(getBottomPn(), BorderLayout.SOUTH);
	}
	
	public LoginForm getLoginForm() {
		if (loginForm == null) {
			loginForm = new LoginForm();
		}
		return loginForm;
	}

	private JPanel getPnTitle() {
		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnTitle.setBackground(Color.WHITE);
		pnTitle.add(createLabel("Login", Font.BOLD, 20));
		return pnTitle;
	}
	
	private JPanel getBottomPn() {
		JPanel bottomPn = new JPanel();
		bottomPn.setLayout(new FlowLayout(FlowLayout.CENTER));
		bottomPn.add(getLoginBtn());
		return bottomPn;
	}
	
	public JButton getLoginBtn() {
		if (loginBtn == null) {
			loginBtn = new JButton("Log in");
		}
		return loginBtn;
	}
	
	private JLabel createLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, size));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(4,0,0,0));
        return label;
    }
}
