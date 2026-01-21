package giis.demo.ui.logger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class PnIndividualLogger extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lbNombreSesion;
	private JButton btnVerLog;
	private JPanel panel_3;
	private JSeparator separator;

	/**
	 * Create the panel.
	 */
	public PnIndividualLogger() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		add(getPanel());
		add(getPanel_3(), BorderLayout.NORTH);

	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			panel.add(getPanel_1());
			panel.add(getPanel_2());
		}
		return panel;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getLbNombreSesion());
		}
		return panel_1;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(Color.WHITE);
			panel_2.setLayout(new GridLayout(0, 1, 0, 0));
			panel_2.add(getBtnVerLog());
		}
		return panel_2;
	}

	public JLabel getLbNombreSesion() {
		if (lbNombreSesion == null) {
			lbNombreSesion = new JLabel("empleado");
			lbNombreSesion.setFont(new Font("Calibri", Font.BOLD, 16));
			lbNombreSesion.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbNombreSesion;
	}

	public JButton getBtnVerLog() {
		if (btnVerLog == null) {
			btnVerLog = new JButton("Ver log");
			btnVerLog.setBackground(new Color(176, 196, 222));
			btnVerLog.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnVerLog;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(Color.WHITE);
			panel_3.setLayout(new BorderLayout(0, 0));
			panel_3.add(getSeparator_1());
		}
		return panel_3;
	}

	private JSeparator getSeparator_1() {
		if (separator == null) {
			separator = new JSeparator();
			separator.setForeground(Color.GRAY);
		}
		return separator;
	}
}
