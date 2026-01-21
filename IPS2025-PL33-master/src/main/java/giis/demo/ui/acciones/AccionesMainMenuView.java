package giis.demo.ui.acciones;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AccionesMainMenuView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnTitulo;
	private JPanel pnOpciones;
	private JPanel pnSalir;
	private JLabel lbTituloAcciones;
	private JButton btnSalir;
	private JPanel pnGestionarCampanas;
	private JButton btnGestionarCampana;

	public AccionesMainMenuView() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnTitulo(), BorderLayout.NORTH);
		contentPane.add(getPnOpciones(), BorderLayout.CENTER);
		contentPane.add(getPnSalir(), BorderLayout.SOUTH);

	}

	private JPanel getPnTitulo() {
		if (pnTitulo == null) {
			pnTitulo = new JPanel();
			pnTitulo.setBackground(Color.WHITE);
			pnTitulo.add(getLbTituloAcciones());
		}
		return pnTitulo;
	}
	private JPanel getPnOpciones() {
		if (pnOpciones == null) {
			pnOpciones = new JPanel();
			pnOpciones.setBackground(Color.WHITE);
			pnOpciones.setLayout(new GridLayout(1, 0, 0, 0));
			pnOpciones.add(getPnGestionarCampanas());
		}
		return pnOpciones;
	}
	private JPanel getPnSalir() {
		if (pnSalir == null) {
			pnSalir = new JPanel();
			pnSalir.setBackground(Color.WHITE);
			pnSalir.add(getBtnSalir());
		}
		return pnSalir;
	}
	private JLabel getLbTituloAcciones() {
		if (lbTituloAcciones == null) {
			lbTituloAcciones = new JLabel("Menú de acciones");
			lbTituloAcciones.setFont(new Font("Calibri", Font.BOLD, 25));
		}
		return lbTituloAcciones;
	}
	public JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnSalir.setBackground(new Color(176, 196, 222));
		}
		return btnSalir;
	}
	
	private JPanel getPnGestionarCampanas() {
		if (pnGestionarCampanas == null) {
			pnGestionarCampanas = new JPanel();
			pnGestionarCampanas.setBackground(new Color(255, 255, 255));
			pnGestionarCampanas.setLayout(new BorderLayout(0, 0));
			pnGestionarCampanas.add(getBtnGestionarCampana());
		}
		return pnGestionarCampanas;
	}
	
	public JButton getBtnGestionarCampana() {
		if (btnGestionarCampana == null) {
			btnGestionarCampana = new JButton("Gestionar campañas");
			btnGestionarCampana.setBackground(new Color(255, 255, 255));
			btnGestionarCampana.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnGestionarCampana;
	}
}
