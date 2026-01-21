package giis.demo.ui.abonos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuAbonosView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAbonoTemporada;
	private JButton btnSuplementos;

	public MenuAbonosView() {
		setTitle("Menu Abonos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		contentPane.add(getBtnAbonoTemporada());
		contentPane.add(getBtnSuplementos());
	}
	
	public JButton getBtnAbonoTemporada() {
        if (btnAbonoTemporada == null) {
            btnAbonoTemporada = new JButton("Comprar Abono Temporada");
            btnAbonoTemporada.setFont(new Font("Calibri", Font.PLAIN, 15));
            btnAbonoTemporada.setBackground(Color.WHITE);
        }
        return btnAbonoTemporada;
    }
	
	public JButton getBtnSuplementos() {
        if (btnSuplementos == null) {
            btnSuplementos = new JButton("Suplementos");
            btnSuplementos.setFont(new Font("Calibri", Font.PLAIN, 15));
            btnSuplementos.setBackground(Color.WHITE);
        }
        return btnSuplementos;
    }
}
