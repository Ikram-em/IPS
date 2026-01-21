package giis.demo.ui.accionista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ShareholderMenuView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnMisAcciones;
	private JButton btnComprarAcciones;
	private JButton btnLogout;

	public ShareholderMenuView() {
		setTitle("Menu accionista");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 482, 205);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(getBtnMisAcciones());
		contentPane.add(getBtnComprarAcciones());
		contentPane.add(getSpacing());
		contentPane.add(getLogoutBtn());
	}
	
	public JButton getBtnMisAcciones() {
		if (btnMisAcciones == null) {
			btnMisAcciones = new JButton("Mis acciones");
			btnMisAcciones.setBackground(new Color(255, 255, 255));
			btnMisAcciones.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnMisAcciones;
	}
	
	public JButton getBtnComprarAcciones() { 
		if (btnComprarAcciones == null) {
			btnComprarAcciones = new JButton("Comprar acciones");
			btnComprarAcciones.setBackground(new Color(255, 255, 255));
			btnComprarAcciones.setFont(new Font("Calibri", Font.PLAIN, 15)); 
		} 
		return btnComprarAcciones; 
	}

	public JButton getLogoutBtn() {
		if (btnLogout == null) {
			btnLogout = new JButton("Cerrar sesión");
			btnLogout.setFont(new Font("Calibri", Font.BOLD, 15));
			btnLogout.setBackground(new Color(255, 255, 255));
			btnLogout.setPreferredSize(new Dimension(300, 30));
		}
		return btnLogout;
	}
	
	private JPanel getSpacing() {
    	JPanel spacing = new JPanel();
    	spacing.setPreferredSize(new Dimension(300, 8));
    	spacing.setMaximumSize(new Dimension(300, 8));
    	spacing.setBackground(null);
    	return spacing;
    }
}
