package giis.demo.ui.acciones.campana;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GestionarCampanaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnGeneral;
	private JPanel pnVistaGeneralTitulo;
	private JPanel pnVistaGeneralOpciones;
	private JPanel pnVistaGeneralSalir;
	private JLabel lbGestionarCampanaGeneral;
	private JButton btnSalirGeneral;
	private JPanel pnGestionarCampanaGeneral;
	private JPanel pnCrearCampanaGeneral;
	private JButton btnCrearCampana;
	private JButton btnGestionarCampana;


	/**
	 * Create the frame.
	 */
	public GestionarCampanaView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 647, 373);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPnGeneral(), "pnGeneral");

	}
	
	public CardLayout getCardLayout() {
	    return (CardLayout) contentPane.getLayout();
	}
	
	public JPanel getMainPanel() {
	    return contentPane;
	}


	private JPanel getPnGeneral() {
		if (pnGeneral == null) {
			pnGeneral = new JPanel();
			pnGeneral.setBackground(Color.WHITE);
			pnGeneral.setLayout(new BorderLayout(0, 0));
			pnGeneral.add(getPnVistaGeneralTitulo(), BorderLayout.NORTH);
			pnGeneral.add(getPnVistaGeneralOpciones(), BorderLayout.CENTER);
			pnGeneral.add(getPnVistaGeneralSalir(), BorderLayout.SOUTH);
		}
		return pnGeneral;
	}
	private JPanel getPnVistaGeneralTitulo() {
		if (pnVistaGeneralTitulo == null) {
			pnVistaGeneralTitulo = new JPanel();
			pnVistaGeneralTitulo.setBackground(Color.WHITE);
			pnVistaGeneralTitulo.add(getLbGestionarCampanaGeneral());
		}
		return pnVistaGeneralTitulo;
	}
	private JPanel getPnVistaGeneralOpciones() {
		if (pnVistaGeneralOpciones == null) {
			pnVistaGeneralOpciones = new JPanel();
			pnVistaGeneralOpciones.setBackground(Color.WHITE);
			pnVistaGeneralOpciones.setLayout(new GridLayout(2, 0, 0, 0));
			pnVistaGeneralOpciones.add(getPnCrearCampanaGeneral());
			pnVistaGeneralOpciones.add(getPnGestionarCampanaGeneral());
		}
		return pnVistaGeneralOpciones;
	}
	private JPanel getPnVistaGeneralSalir() {
		if (pnVistaGeneralSalir == null) {
			pnVistaGeneralSalir = new JPanel();
			pnVistaGeneralSalir.setBackground(Color.WHITE);
			pnVistaGeneralSalir.add(getBtnSalirGeneral());
		}
		return pnVistaGeneralSalir;
	}
	private JLabel getLbGestionarCampanaGeneral() {
		if (lbGestionarCampanaGeneral == null) {
			lbGestionarCampanaGeneral = new JLabel("Gestionar Campaña");
			lbGestionarCampanaGeneral.setFont(new Font("Calibri", Font.BOLD, 25));
		}
		return lbGestionarCampanaGeneral;
	}
	public JButton getBtnSalirGeneral() {
		if (btnSalirGeneral == null) {
			btnSalirGeneral = new JButton("Salir");
			btnSalirGeneral.setBackground(new Color(176, 196, 222));
			btnSalirGeneral.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnSalirGeneral;
	}
	private JPanel getPnGestionarCampanaGeneral() {
		if (pnGestionarCampanaGeneral == null) {
			pnGestionarCampanaGeneral = new JPanel();
			pnGestionarCampanaGeneral.setBackground(new Color(255, 255, 255));
			pnGestionarCampanaGeneral.setLayout(new BorderLayout(0, 0));
			pnGestionarCampanaGeneral.add(getBtnGestionarCampana());
		}
		return pnGestionarCampanaGeneral;
	}
	private JPanel getPnCrearCampanaGeneral() {
		if (pnCrearCampanaGeneral == null) {
			pnCrearCampanaGeneral = new JPanel();
			pnCrearCampanaGeneral.setBackground(new Color(255, 255, 255));
			pnCrearCampanaGeneral.setLayout(new BorderLayout(0, 0));
			pnCrearCampanaGeneral.add(getBtnCrearCampana());
		}
		return pnCrearCampanaGeneral;
	}
	public JButton getBtnCrearCampana() {
		if (btnCrearCampana == null) {
			btnCrearCampana = new JButton("Crear nueva Campaña");
			btnCrearCampana.setEnabled(false);
			btnCrearCampana.setBackground(new Color(255, 255, 255));
			btnCrearCampana.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnCrearCampana;
	}
	public JButton getBtnGestionarCampana() {
		if (btnGestionarCampana == null) {
			btnGestionarCampana = new JButton("Gestionar Campaña");
			btnGestionarCampana.setEnabled(false);
			btnGestionarCampana.setBackground(new Color(255, 255, 255));
			btnGestionarCampana.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnGestionarCampana;
	}
}
