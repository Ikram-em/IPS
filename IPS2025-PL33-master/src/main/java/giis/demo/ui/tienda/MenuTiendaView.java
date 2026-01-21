package giis.demo.ui.tienda;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuTiendaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lbTitulo;
	private JButton btnSalir;
	private JButton btnComprarAbono;
	private JButton btnAccederTienda;
	private JButton btnComprarEntradas;


	public MenuTiendaView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.CENTER);
		
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPanel_1(), BorderLayout.NORTH);
			panel.add(getPanel_2(), BorderLayout.SOUTH);
			panel.add(getPanel_3(), BorderLayout.CENTER);
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			panel_1.add(getLbTitulo());
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(Color.WHITE);
			panel_2.add(getBtnSalir());
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(Color.WHITE);
			panel_3.setLayout(new GridLayout(3, 0, 0, 0));
			panel_3.add(getBtnAccederTienda());
			panel_3.add(getBtnComprarEntradas());
		}
		return panel_3;
	}
	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Menú de tienda");
			lbTitulo.setFont(new Font("Calibri", Font.BOLD, 25));
		}
		return lbTitulo;
	}

	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					salir();
				}
			});
			btnSalir.setBackground(new Color(176, 196, 222));
			btnSalir.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnSalir;
	}

	private void salir() {
		this.dispose();
	}

	public JButton getBtnComprarAbono() {
		if (btnComprarAbono == null) {
			btnComprarAbono = new JButton("Comprar abono de temporada");
			btnComprarAbono.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnComprarAbono;
	}
	public JButton getBtnAccederTienda() {
		if (btnAccederTienda == null) {
			btnAccederTienda = new JButton("Acceder a la tienda de productos");
			btnAccederTienda.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnAccederTienda;
	}
	public JButton getBtnComprarEntradas() {
		if (btnComprarEntradas == null) {
			btnComprarEntradas = new JButton("Comprar entradas");
			btnComprarEntradas.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnComprarEntradas;
	}
}
