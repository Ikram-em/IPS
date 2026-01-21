package giis.demo.ui.medico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class PnJugadorMedico extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JSeparator separator;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lbNombre;
	private JPanel panel_5;
	private JButton btnEntrar;
	private JLabel lbPrioridad;
	private JPanel panel_6;
	private JLabel lbAltaMedica;

	/**
	 * Create the panel.
	 */
	public PnJugadorMedico() {
		setLayout(new BorderLayout(0, 0));
		add(getPanel(), BorderLayout.NORTH);
		add(getPanel_1(), BorderLayout.CENTER);
		add(getPanel_2(), BorderLayout.SOUTH);

	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getSeparator());
		}
		return panel;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new GridLayout(2, 0, 0, 0));
			panel_1.add(getPanel_4());
			panel_1.add(getPanel_3());
		}
		return panel_1;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(Color.WHITE);
			panel_2.add(getBtnEntrar());
		}
		return panel_2;
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setForeground(Color.GRAY);
			separator.setBackground(Color.GRAY);
		}
		return separator;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(Color.WHITE);
			panel_3.setLayout(new GridLayout(0, 1, 0, 0));
			panel_3.add(getLbPrioridad());
			panel_3.add(getPanel_6());
		}
		return panel_3;
	}

	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBackground(Color.WHITE);
			panel_4.setLayout(new GridLayout(0, 1, 0, 0));
			panel_4.add(getPanel_5());
			panel_4.add(getLbNombre());
		}
		return panel_4;
	}

	public JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("Nombre jugador");
			lbNombre.setBackground(Color.WHITE);
			lbNombre.setFont(new Font("Calibri", Font.BOLD, 21));
			lbNombre.setVerticalAlignment(SwingConstants.BOTTOM);
			lbNombre.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbNombre;
	}

	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setBackground(Color.WHITE);
		}
		return panel_5;
	}

	public JButton getBtnEntrar() {
		if (btnEntrar == null) {
			btnEntrar = new JButton("Entrar");
			btnEntrar.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnEntrar.setBackground(new Color(176, 196, 222));
		}
		return btnEntrar;
	}

	public JLabel getLbPrioridad() {
		if (lbPrioridad == null) {
			lbPrioridad = new JLabel("Prioridad: <prio>");
			lbPrioridad.setBackground(Color.WHITE);
			lbPrioridad.setFont(new Font("Calibri", Font.PLAIN, 15));
			lbPrioridad.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbPrioridad;
	}

	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setBackground(Color.WHITE);
			panel_6.add(getLbAltaMedica());
		}
		return panel_6;
	}

	public JLabel getLbAltaMedica() {
		if (lbAltaMedica == null) {
			lbAltaMedica = new JLabel("¿Alta médica?  ");
			lbAltaMedica.setBackground(Color.LIGHT_GRAY);
			lbAltaMedica.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbAltaMedica;
	}
}
