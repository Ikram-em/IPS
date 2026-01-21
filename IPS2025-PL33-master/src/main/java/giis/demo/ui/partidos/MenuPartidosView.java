package giis.demo.ui.partidos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuPartidosView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnPartidos;
	private JButton btnGestionPartidos;

	public MenuPartidosView() {
		setTitle("Menu Partidos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));

		contentPane.add(getBtnVerPartidos());
		contentPane.add(getBtnGestionPartidos());
	}
	
	public JButton getBtnVerPartidos() {
		if (btnPartidos == null) {
			btnPartidos = new JButton("Ver partidos");
			btnPartidos.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnPartidos.setBackground(new Color(255, 255, 255));			
		}
		return btnPartidos;
	}

	public JButton getBtnGestionPartidos() {
        if (btnGestionPartidos == null) {
        	btnGestionPartidos= new JButton("Añadir partidos");
        	btnGestionPartidos.setFont(new Font("Calibri", Font.PLAIN, 15));
        	btnGestionPartidos.setBackground(Color.WHITE);
            
        }
        return btnGestionPartidos;
    }
}
