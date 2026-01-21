package giis.demo.ui.jardineria;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PnJardineroView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnBoton;
	private JPanel pnNombre;
	private JLabel lbNombre;
	private JButton btnPlanificar;
	private JPanel panel;
	private JPanel panel_1;

	/**
	 * Create the panel.
	 */
	public PnJardineroView() {
		setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		setBackground(Color.WHITE);
		setLayout(new GridLayout(4, 0, 0, 0));
		add(getPanel());
		add(getPnNombre());
		add(getPnBoton());
		add(getPanel_1());

	}

	private JPanel getPnBoton() {
		if (pnBoton == null) {
			pnBoton = new JPanel();
			pnBoton.setBackground(Color.WHITE);
			pnBoton.add(getBtnPlanificar());
		}
		return pnBoton;
	}

	private JPanel getPnNombre() {
		if (pnNombre == null) {
			pnNombre = new JPanel();
			pnNombre.setBackground(Color.WHITE);
			pnNombre.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnNombre.add(getLbNombre());
		}
		return pnNombre;
	}

	public JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("nombre Jardinero");
			lbNombre.setFont(new Font("Calibri", Font.BOLD, 20));
		}
		return lbNombre;
	}

	public JButton getBtnPlanificar() {
		if (btnPlanificar == null) {
			btnPlanificar = new JButton("Planificar");
			btnPlanificar.setBackground(new Color(176, 196, 222));
			btnPlanificar.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnPlanificar;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
		}
		return panel_1;
	}
}
