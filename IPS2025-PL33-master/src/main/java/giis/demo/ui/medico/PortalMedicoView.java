package giis.demo.ui.medico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.employee.EmployeeViewDTO;
import giis.demo.tkrun.teammanagement.EquipoViewDTO;

public class PortalMedicoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel pnTitulo;
	private JPanel panel_2;
	private JPanel pnAgregarSalir;
	private JLabel lbTitulo;
	private JPanel pnSeleccionEquipoJugador;
	private JPanel pnJugadores;
	private JPanel pnLbEquipo;
	private JPanel pnCbEquipo;
	private JPanel pnLbSeleccionarJugador;
	private JPanel panel_6;
	private JLabel lbSeleccionarEquipo;
	private JComboBox<EquipoViewDTO> cbSeleccionarEquipo;
	private JLabel lbSeleccionarJugador;
	private JComboBox<EmployeeViewDTO> comboBox;
	private JScrollPane scrollPane;
	private JPanel pnContenedorJugadores;
	private JButton btnSalir;
	private JPanel panel_1;
	private JPanel panel_3;
	private JButton btnQuitarFiltro;
	private JButton btnAddLesion;


	/**
	 * Create the frame.
	 */
	public PortalMedicoView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 526, 564);
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
			panel.add(getPnTitulo(), BorderLayout.NORTH);
			panel.add(getPanel_2(), BorderLayout.CENTER);
			panel.add(getPnAgregarSalir(), BorderLayout.SOUTH);
		}
		return panel;
	}

	private JPanel getPnTitulo() {
		if (pnTitulo == null) {
			pnTitulo = new JPanel();
			pnTitulo.setBackground(Color.WHITE);
			pnTitulo.add(getLbTitulo());
		}
		return pnTitulo;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(Color.WHITE);
			panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(getPnSeleccionEquipoJugador(), BorderLayout.NORTH);
			panel_2.add(getPnJugadores(), BorderLayout.CENTER);
		}
		return panel_2;
	}

	private JPanel getPnAgregarSalir() {
		if (pnAgregarSalir == null) {
			pnAgregarSalir = new JPanel();
			pnAgregarSalir.setBackground(Color.WHITE);
			pnAgregarSalir.add(getBtnAddLesion());
			pnAgregarSalir.add(getBtnSalir());
		}
		return pnAgregarSalir;
	}

	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("PORTAL MÉDICO");
			lbTitulo.setFont(new Font("Calibri", Font.BOLD, 25));
		}
		return lbTitulo;
	}

	private JPanel getPnSeleccionEquipoJugador() {
		if (pnSeleccionEquipoJugador == null) {
			pnSeleccionEquipoJugador = new JPanel();
			pnSeleccionEquipoJugador.setBackground(Color.WHITE);
			pnSeleccionEquipoJugador.setLayout(new GridLayout(3, 2, 0, 0));
			pnSeleccionEquipoJugador.add(getPnLbEquipo());
			pnSeleccionEquipoJugador.add(getPnCbEquipo());
			pnSeleccionEquipoJugador.add(getPnLbSeleccionarJugador());
			pnSeleccionEquipoJugador.add(getPanel_6());
			pnSeleccionEquipoJugador.add(getPanel_1());
			pnSeleccionEquipoJugador.add(getPanel_3());
		}
		return pnSeleccionEquipoJugador;
	}

	private JPanel getPnJugadores() {
		if (pnJugadores == null) {
			pnJugadores = new JPanel();
			pnJugadores.setBackground(Color.WHITE);
			pnJugadores.setLayout(new BorderLayout(0, 0));
			pnJugadores.add(getScrollPane());
		}
		return pnJugadores;
	}

	private JPanel getPnLbEquipo() {
		if (pnLbEquipo == null) {
			pnLbEquipo = new JPanel();
			pnLbEquipo.setBackground(Color.WHITE);
			pnLbEquipo.add(getLbSeleccionarEquipo());
		}
		return pnLbEquipo;
	}

	private JPanel getPnCbEquipo() {
		if (pnCbEquipo == null) {
			pnCbEquipo = new JPanel();
			pnCbEquipo.setBackground(Color.WHITE);
			pnCbEquipo.add(getCbSeleccionarEquipo());
		}
		return pnCbEquipo;
	}

	private JPanel getPnLbSeleccionarJugador() {
		if (pnLbSeleccionarJugador == null) {
			pnLbSeleccionarJugador = new JPanel();
			pnLbSeleccionarJugador.setBackground(Color.WHITE);
			pnLbSeleccionarJugador.add(getLbSeleccionarJugador());
		}
		return pnLbSeleccionarJugador;
	}

	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setBackground(Color.WHITE);
			panel_6.add(getComboBox());
		}
		return panel_6;
	}

	private JLabel getLbSeleccionarEquipo() {
		if (lbSeleccionarEquipo == null) {
			lbSeleccionarEquipo = new JLabel("Seleccionar equipo:");
			lbSeleccionarEquipo.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbSeleccionarEquipo;
	}

	public JComboBox<EquipoViewDTO> getCbSeleccionarEquipo() {
		if (cbSeleccionarEquipo == null) {
			cbSeleccionarEquipo = new JComboBox<EquipoViewDTO>();
			cbSeleccionarEquipo.setPreferredSize(new Dimension(200, 22));
			cbSeleccionarEquipo.setSize(new Dimension(38, 0));
			cbSeleccionarEquipo.setMinimumSize(new Dimension(46, 22));
			cbSeleccionarEquipo.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return cbSeleccionarEquipo;
	}

	public JLabel getLbSeleccionarJugador() {
		if (lbSeleccionarJugador == null) {
			lbSeleccionarJugador = new JLabel("Seleccionar jugador:");
			lbSeleccionarJugador.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return lbSeleccionarJugador;
	}

	public JComboBox<EmployeeViewDTO> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<EmployeeViewDTO>();
			comboBox.setPreferredSize(new Dimension(200, 22));
			comboBox.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return comboBox;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setViewportView(getPnContenedorJugadores());
		}
		return scrollPane;
	}

	public JPanel getPnContenedorJugadores() {
		if (pnContenedorJugadores == null) {
			pnContenedorJugadores = new JPanel();
			pnContenedorJugadores.setBackground(Color.WHITE);
			pnContenedorJugadores.setLayout(new BoxLayout(pnContenedorJugadores, BoxLayout.Y_AXIS));
		}
		return pnContenedorJugadores;
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

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
		}
		return panel_1;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(Color.WHITE);
			panel_3.add(getBtnQuitarFiltro());
		}
		return panel_3;
	}

	public JButton getBtnQuitarFiltro() {
		if (btnQuitarFiltro == null) {
			btnQuitarFiltro = new JButton("Borrar filtro");
			btnQuitarFiltro.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnQuitarFiltro.setBackground(new Color(176, 196, 222));
		}
		return btnQuitarFiltro;
	}

	public JButton getBtnAddLesion() {
		if (btnAddLesion == null) {
			btnAddLesion = new JButton("Nueva lesión");
			btnAddLesion.setBackground(new Color(176, 196, 222));
			btnAddLesion.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnAddLesion;
	}
}
