package giis.demo.ui.medico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class LesionView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lbTitulo;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_10;
	private JPanel panel_11;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnSalir;
	private JButton btnActualizar;
	private JButton btnAlta;
	private JButton btnHistorial;
	private JPanel panel_12;
	private JPanel panel_13;
	private JPanel panel_14;
	private JPanel panel_15;
	private JPanel panel_16;
	private JLabel lblNewLabel_8;
	private JTextArea textArea;
	private JPanel panel_17;
	private JPanel panel_18;
	private JPanel panel_19;
	private JPanel panel_20;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField txEntrenamientoPartido;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JPanel panel_18_1;
	private JLabel lblNewLabel_7;
	private JComboBox<String> cbPrioridad;

	/**
	 * Create the frame.
	 */
	public LesionView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 588, 504);
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
			panel.setBackground(Color.WHITE);
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
			panel_2.add(getBtnHistorial());
			panel_2.add(getBtnAlta());
			panel_2.add(getBtnActualizar());
			panel_2.add(getBtnSalir());
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(Color.WHITE);
			panel_3.setLayout(new BorderLayout(0, 0));
			panel_3.add(getPanel_4(), BorderLayout.NORTH);
			panel_3.add(getPanel_5(), BorderLayout.CENTER);
		}
		return panel_3;
	}
	public JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Editar lesión:");
			lbTitulo.setFont(new Font("Calibri", Font.BOLD, 25));
		}
		return lbTitulo;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBackground(Color.WHITE);
			panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		}
		return panel_4;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setBackground(Color.WHITE);
			panel_5.setLayout(new BorderLayout(0, 0));
			panel_5.add(getPanel_10(), BorderLayout.WEST);
			panel_5.add(getPanel_11(), BorderLayout.EAST);
			panel_5.add(getPanel_12(), BorderLayout.CENTER);
		}
		return panel_5;
	}
	private JPanel getPanel_10() {
		if (panel_10 == null) {
			panel_10 = new JPanel();
			panel_10.setBackground(Color.WHITE);
			panel_10.add(getLblNewLabel());
		}
		return panel_10;
	}
	private JPanel getPanel_11() {
		if (panel_11 == null) {
			panel_11 = new JPanel();
			panel_11.setBackground(Color.WHITE);
			panel_11.add(getLblNewLabel_1());
		}
		return panel_11;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("                    ");
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("                    ");
		}
		return lblNewLabel_1;
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

	public JButton getBtnActualizar() {
		if (btnActualizar == null) {
			btnActualizar = new JButton("Actualizar");
			btnActualizar.setBackground(new Color(176, 196, 222));
			btnActualizar.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnActualizar;
	}

	public JButton getBtnAlta() {
		if (btnAlta == null) {
			btnAlta = new JButton("Dar el alta");
			btnAlta.setBackground(new Color(176, 196, 222));
			btnAlta.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnAlta;
	}

	public JButton getBtnHistorial() {
		if (btnHistorial == null) {
			btnHistorial = new JButton("Ver historial lesión");
			btnHistorial.setBackground(new Color(176, 196, 222));
			btnHistorial.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnHistorial;
	}
	private JPanel getPanel_12() {
		if (panel_12 == null) {
			panel_12 = new JPanel();
			panel_12.setLayout(new BorderLayout(0, 0));
			panel_12.add(getPanel_13(), BorderLayout.CENTER);
			panel_12.add(getPanel_14(), BorderLayout.NORTH);
		}
		return panel_12;
	}
	private JPanel getPanel_13() {
		if (panel_13 == null) {
			panel_13 = new JPanel();
			panel_13.setBackground(Color.WHITE);
			panel_13.setLayout(new BorderLayout(0, 0));
			panel_13.add(getPanel_15(), BorderLayout.NORTH);
			panel_13.add(getPanel_16(), BorderLayout.CENTER);
		}
		return panel_13;
	}
	private JPanel getPanel_14() {
		if (panel_14 == null) {
			panel_14 = new JPanel();
			panel_14.setBackground(Color.WHITE);
			panel_14.setLayout(new GridLayout(2, 2, 2, 0));
			panel_14.add(getPanel_18());
			panel_14.add(getPanel_17());
			panel_14.add(getPanel_19());
			panel_14.add(getPanel_20());
		}
		return panel_14;
	}
	private JPanel getPanel_15() {
		if (panel_15 == null) {
			panel_15 = new JPanel();
			panel_15.setBackground(Color.WHITE);
			panel_15.add(getLblNewLabel_8());
		}
		return panel_15;
	}
	private JPanel getPanel_16() {
		if (panel_16 == null) {
			panel_16 = new JPanel();
			panel_16.setBackground(Color.WHITE);
			panel_16.add(getTextArea());
		}
		return panel_16;
	}
	private JLabel getLblNewLabel_8() {
		if (lblNewLabel_8 == null) {
			lblNewLabel_8 = new JLabel("Diagnóstico:");
			lblNewLabel_8.setFont(new Font("Calibri", Font.PLAIN, 15));
			lblNewLabel_8.setBackground(Color.WHITE);
		}
		return lblNewLabel_8;
	}

	public JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setRows(5);
			textArea.setLineWrap(true);
			textArea.setFont(new Font("Calibri", Font.PLAIN, 12));
			textArea.setColumns(30);
			textArea.setBorder(new LineBorder(Color.LIGHT_GRAY));
		}
		return textArea;
	}
	private JPanel getPanel_17() {
		if (panel_17 == null) {
			panel_17 = new JPanel();
			panel_17.setBackground(Color.WHITE);
			panel_17.setLayout(new GridLayout(0, 1, 0, 0));
			panel_17.add(getLblNewLabel_4());
			panel_17.add(getTxEntrenamientoPartido());
			panel_17.add(getLblNewLabel_5());
		}
		return panel_17;
	}
	private JPanel getPanel_18() {
		if (panel_18 == null) {
			panel_18 = new JPanel();
			panel_18.setBackground(Color.WHITE);
			panel_18.setLayout(new GridLayout(0, 1, 0, 0));
			panel_18.add(getLblNewLabel_2());
		}
		return panel_18;
	}
	private JPanel getPanel_19() {
		if (panel_19 == null) {
			panel_19 = new JPanel();
			panel_19.setBackground(Color.WHITE);
			panel_19.setLayout(new GridLayout(0, 1, 0, 0));
			panel_19.add(getLblNewLabel_3());
		}
		return panel_19;
	}
	private JPanel getPanel_20() {
		if (panel_20 == null) {
			panel_20 = new JPanel();
			panel_20.setBackground(Color.WHITE);
			panel_20.setLayout(new GridLayout(0, 1, 0, 0));
			panel_20.add(getPanel_18_1());
		}
		return panel_20;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Partido/Entrenamiento:");
			lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 15));
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Prioridad:");
			lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 15));
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel_3;
	}
	public JTextField getTxEntrenamientoPartido() {
		if (txEntrenamientoPartido == null) {
			txEntrenamientoPartido = new JTextField();
			txEntrenamientoPartido.setFocusable(false);
			txEntrenamientoPartido.setBackground(Color.WHITE);
			txEntrenamientoPartido.setEditable(false);
			txEntrenamientoPartido.setFont(new Font("Calibri", Font.PLAIN, 13));
			txEntrenamientoPartido.setColumns(10);
		}
		return txEntrenamientoPartido;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("");
		}
		return lblNewLabel_4;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("");
		}
		return lblNewLabel_5;
	}
	private JPanel getPanel_18_1() {
		if (panel_18_1 == null) {
			panel_18_1 = new JPanel();
			panel_18_1.setBackground(Color.WHITE);
			panel_18_1.setLayout(new GridLayout(3, 0, 0, 0));
			panel_18_1.add(getLblNewLabel_7());
			panel_18_1.add(getCbPrioridad());
		}
		return panel_18_1;
	}
	private JLabel getLblNewLabel_7() {
		if (lblNewLabel_7 == null) {
			lblNewLabel_7 = new JLabel("");
		}
		return lblNewLabel_7;
	}

	public JComboBox<String> getCbPrioridad() {
		if (cbPrioridad == null) {
			cbPrioridad = new JComboBox<String>();
			cbPrioridad.setModel(new DefaultComboBoxModel<String>(new String[] { "Alta", "Media", "Baja" }));
			cbPrioridad.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return cbPrioridad;
	}
}
