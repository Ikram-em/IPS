package giis.demo.ui.medico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import giis.demo.tkrun.employee.EmployeeViewDTO;
import giis.demo.tkrun.teammanagement.EquipoViewDTO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddLesionView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lbTitulo;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JLabel lbSeleccionarEquipo;
	private JLabel lbSeleccionarJugador;
	private JComboBox<EquipoViewDTO> cbSeleccionarEquipo;
	private JComboBox<EmployeeViewDTO> cbSeleccionarJugador;
	private JPanel panel_9;
	private JPanel panel_11;
	private JLabel lblNewLabel;
	private JPanel panel_11_1;
	private JLabel lblNewLabel_1;
	private JButton btnAdd;
	private JButton btnSalir;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panel_19;
	private JPanel panel_12;
	private JPanel panel_10;
	private JPanel panel_13;
	private JPanel panel_14;
	private JPanel panel_15;
	private JPanel panel_16;
	private JPanel panel_17;
	private JPanel panel_18;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JRadioButton rdBtEntrenamiento;
	private JRadioButton rdBtPartido;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JComboBox<Object> cbPartidoEntreno;
	private JLabel lblNewLabel_7;
	private JComboBox<String> cbPrioridad;
	private JPanel panel_20;
	private JPanel panel_21;
	private JLabel lblNewLabel_8;
	private JTextArea textArea;


	/**
	 * Create the frame.
	 */
	public AddLesionView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 572, 653);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.NORTH);
		contentPane.add(getPanel_1(), BorderLayout.SOUTH);
		contentPane.add(getPanel_2(), BorderLayout.CENTER);

	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.add(getLbTitulo());
		}
		return panel;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			panel_1.add(getBtnAdd());
			panel_1.add(getBtnSalir());
		}
		return panel_1;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(Color.WHITE);
			panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(getPanel_3(), BorderLayout.NORTH);
			panel_2.add(getPanel_4(), BorderLayout.CENTER);
		}
		return panel_2;
	}

	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Añadir nueva lesión");
			lbTitulo.setFont(new Font("Calibri", Font.BOLD, 25));
		}
		return lbTitulo;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(Color.WHITE);
			panel_3.setLayout(new GridLayout(2, 2, 0, 0));
			panel_3.add(getPanel_5());
			panel_3.add(getPanel_6());
			panel_3.add(getPanel_7());
			panel_3.add(getPanel_8());
		}
		return panel_3;
	}

	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBackground(Color.WHITE);
			panel_4.setLayout(new BorderLayout(0, 0));
			panel_4.add(getPanel_9(), BorderLayout.NORTH);
			panel_4.add(getPanel_11(), BorderLayout.WEST);
			panel_4.add(getPanel_11_1(), BorderLayout.EAST);
			panel_4.add(getPanel_19(), BorderLayout.CENTER);
		}
		return panel_4;
	}

	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setBackground(Color.WHITE);
			panel_5.add(getLbSeleccionarEquipo());
		}
		return panel_5;
	}

	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setBackground(Color.WHITE);
			panel_6.add(getCbSeleccionarEquipo());
		}
		return panel_6;
	}

	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.setBackground(Color.WHITE);
			panel_7.add(getLbSeleccionarJugador());
		}
		return panel_7;
	}

	private JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
			panel_8.setBackground(Color.WHITE);
			panel_8.add(getCbSeleccionarJugador());
		}
		return panel_8;
	}

	private JLabel getLbSeleccionarEquipo() {
		if (lbSeleccionarEquipo == null) {
			lbSeleccionarEquipo = new JLabel("Seleccionar equipo:");
			lbSeleccionarEquipo.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbSeleccionarEquipo;
	}

	private JLabel getLbSeleccionarJugador() {
		if (lbSeleccionarJugador == null) {
			lbSeleccionarJugador = new JLabel("Seleccionar jugador:");
			lbSeleccionarJugador.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbSeleccionarJugador;
	}

	public JComboBox<EquipoViewDTO> getCbSeleccionarEquipo() {
		if (cbSeleccionarEquipo == null) {
			cbSeleccionarEquipo = new JComboBox<EquipoViewDTO>();
			cbSeleccionarEquipo.setSize(new Dimension(38, 0));
			cbSeleccionarEquipo.setPreferredSize(new Dimension(200, 22));
			cbSeleccionarEquipo.setMinimumSize(new Dimension(46, 22));
			cbSeleccionarEquipo.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return cbSeleccionarEquipo;
	}

	public JComboBox<EmployeeViewDTO> getCbSeleccionarJugador() {
		if (cbSeleccionarJugador == null) {
			cbSeleccionarJugador = new JComboBox<EmployeeViewDTO>();
			cbSeleccionarJugador.setSize(new Dimension(38, 0));
			cbSeleccionarJugador.setPreferredSize(new Dimension(200, 22));
			cbSeleccionarJugador.setMinimumSize(new Dimension(46, 22));
			cbSeleccionarJugador.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return cbSeleccionarJugador;
	}

	private JPanel getPanel_9() {
		if (panel_9 == null) {
			panel_9 = new JPanel();
			panel_9.setBackground(Color.WHITE);
		}
		return panel_9;
	}

	private JPanel getPanel_11() {
		if (panel_11 == null) {
			panel_11 = new JPanel();
			panel_11.setBackground(Color.WHITE);
			panel_11.add(getLblNewLabel());
		}
		return panel_11;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("                    ");
		}
		return lblNewLabel;
	}

	private JPanel getPanel_11_1() {
		if (panel_11_1 == null) {
			panel_11_1 = new JPanel();
			panel_11_1.setBackground(Color.WHITE);
			panel_11_1.add(getLblNewLabel_1());
		}
		return panel_11_1;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("                    ");
		}
		return lblNewLabel_1;
	}

	public JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Agregar");
			btnAdd.setBackground(new Color(176, 196, 222));
			btnAdd.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnAdd;
	}

	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrar();
				}
			});
			btnSalir.setBackground(new Color(176, 196, 222));
			btnSalir.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnSalir;
	}
	
	private void cerrar() {
		this.dispose();
	}

	private JPanel getPanel_19() {
		if (panel_19 == null) {
			panel_19 = new JPanel();
			panel_19.setBackground(Color.WHITE);
			panel_19.setLayout(new BorderLayout(0, 0));
			panel_19.add(getPanel_12(), BorderLayout.SOUTH);
			panel_19.add(getPanel_10(), BorderLayout.CENTER);
		}
		return panel_19;
	}
	private JPanel getPanel_12() {
		if (panel_12 == null) {
			panel_12 = new JPanel();
			panel_12.setBackground(Color.WHITE);
			panel_12.setLayout(new BorderLayout(0, 0));
			panel_12.add(getPanel_20(), BorderLayout.NORTH);
			panel_12.add(getPanel_21(), BorderLayout.SOUTH);
		}
		return panel_12;
	}

	private JPanel getPanel_10() {
		if (panel_10 == null) {
			panel_10 = new JPanel();
			panel_10.setBorder(new LineBorder(Color.LIGHT_GRAY));
			panel_10.setBackground(Color.WHITE);
			panel_10.setLayout(new GridLayout(3, 2, 0, 0));
			panel_10.add(getPanel_14());
			panel_10.add(getPanel_15());
			panel_10.add(getPanel_17());
			panel_10.add(getPanel_13());
			panel_10.add(getPanel_16());
			panel_10.add(getPanel_18());
		}
		return panel_10;
	}
	private JPanel getPanel_13() {
		if (panel_13 == null) {
			panel_13 = new JPanel();
			panel_13.setBackground(Color.WHITE);
			panel_13.setLayout(new GridLayout(3, 0, 0, 0));
			panel_13.add(getLblNewLabel_6());
			panel_13.add(getCbPartidoEntreno());
		}
		return panel_13;
	}
	private JPanel getPanel_14() {
		if (panel_14 == null) {
			panel_14 = new JPanel();
			panel_14.setBackground(Color.WHITE);
			panel_14.setLayout(new GridLayout(0, 1, 0, 0));
			panel_14.add(getLblNewLabel_2());
		}
		return panel_14;
	}
	private JPanel getPanel_15() {
		if (panel_15 == null) {
			panel_15 = new JPanel();
			panel_15.setBackground(Color.WHITE);
			panel_15.setLayout(new BoxLayout(panel_15, BoxLayout.Y_AXIS));
			panel_15.add(getLblNewLabel_5());
			panel_15.add(getRdBtEntrenamiento());
			panel_15.add(getRdBtPartido());
		}
		return panel_15;
	}
	private JPanel getPanel_16() {
		if (panel_16 == null) {
			panel_16 = new JPanel();
			panel_16.setBackground(Color.WHITE);
			panel_16.setLayout(new GridLayout(0, 1, 0, 0));
			panel_16.add(getLblNewLabel_4());
		}
		return panel_16;
	}
	private JPanel getPanel_17() {
		if (panel_17 == null) {
			panel_17 = new JPanel();
			panel_17.setBackground(Color.WHITE);
			panel_17.setLayout(new GridLayout(0, 1, 0, 0));
			panel_17.add(getLblNewLabel_3());
		}
		return panel_17;
	}

	private JPanel getPanel_18() {
		if (panel_18 == null) {
			panel_18 = new JPanel();
			panel_18.setBackground(Color.WHITE);
			panel_18.setLayout(new GridLayout(3, 0, 0, 0));
			panel_18.add(getLblNewLabel_7());
			panel_18.add(getCbPrioridad());
		}
		return panel_18;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Se lesionó en:");
			lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 15));
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel_2;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Entrenamiento/Partido:");
			lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 15));
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel_3;
	}

	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("Prioridad");
			lblNewLabel_4.setFont(new Font("Calibri", Font.PLAIN, 15));
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel_4;
	}

	public JRadioButton getRdBtEntrenamiento() {
		if (rdBtEntrenamiento == null) {
			rdBtEntrenamiento = new JRadioButton("Entrenamiento");
			rdBtEntrenamiento.setFont(new Font("Calibri", Font.PLAIN, 15));
			buttonGroup.add(rdBtEntrenamiento);
			rdBtEntrenamiento.setBackground(Color.WHITE);
		}
		return rdBtEntrenamiento;
	}

	public JRadioButton getRdBtPartido() {
		if (rdBtPartido == null) {
			rdBtPartido = new JRadioButton("Partido");
			rdBtPartido.setFont(new Font("Calibri", Font.PLAIN, 15));
			buttonGroup.add(rdBtPartido);
			rdBtPartido.setBackground(Color.WHITE);
		}
		return rdBtPartido;
	}

	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("                             ");
			lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lblNewLabel_5;
	}

	private JLabel getLblNewLabel_6() {
		if (lblNewLabel_6 == null) {
			lblNewLabel_6 = new JLabel("");
		}
		return lblNewLabel_6;
	}

	public JComboBox<Object> getCbPartidoEntreno() {
		if (cbPartidoEntreno == null) {
			cbPartidoEntreno = new JComboBox<Object>();
			cbPartidoEntreno.setModel(new DefaultComboBoxModel<Object>(new String[] {"Seleccione entrenamiento o partido"}));
			cbPartidoEntreno.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return cbPartidoEntreno;
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

	private JPanel getPanel_20() {
		if (panel_20 == null) {
			panel_20 = new JPanel();
			panel_20.setBackground(Color.WHITE);
			panel_20.add(getLblNewLabel_8());
		}
		return panel_20;
	}

	private JPanel getPanel_21() {
		if (panel_21 == null) {
			panel_21 = new JPanel();
			panel_21.setBackground(Color.WHITE);
			panel_21.add(getTextArea());
		}
		return panel_21;
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
			textArea.setBorder(new LineBorder(Color.LIGHT_GRAY));
			textArea.setLineWrap(true);
			textArea.setColumns(30);
			textArea.setFont(new Font("Calibri", Font.PLAIN, 12));
			textArea.setRows(5);
		}
		return textArea;
	}
}
