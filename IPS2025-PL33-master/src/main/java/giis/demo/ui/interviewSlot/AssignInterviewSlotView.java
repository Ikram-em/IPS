package giis.demo.ui.interviewSlot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import giis.demo.tkrun.interviewSlot.FranjaEntrevistaDTO;
import giis.demo.tkrun.interviewSlot.MetodosEntrevista;
import giis.demo.tkrun.login.Session;
import giis.demo.tkrun.teammanagement.MetodosJugador;
import giis.demo.tkrun.teammanagement.PersonDTO;

public class AssignInterviewSlotView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnJugadoresFranjas;
	private JPanel pnFranjas;
	private JButton btGuardarCambios;
	private JLabel lbMedioComunicacion;
	private JTextField textField;

	private ButtonGroup grupoJugadores = new ButtonGroup();
	private ButtonGroup grupoFranjas = new ButtonGroup();

	private int ultimoJugadorSeleccionado = -1;
	private JPanel pnSouth;
	private JPanel pnLayout;


	public AssignInterviewSlotView() {
		setTitle("Asignar Franja Horaria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnSouth(), BorderLayout.SOUTH);
		contentPane.add(getPnLayout());

		inicializarJugadores();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void inicializarJugadores() {
	    MetodosJugador metJug = new MetodosJugador();
	    List<PersonDTO> jugadoresConFranjas;
	    String roleName = Session.get().getRoleName();
	    if (roleName.equals("Entrenador")) {
	    	jugadoresConFranjas = metJug.getJugadoresDeEntrenadorConFranjas(Session.get().getEmployeeID());
	    } else {
	    	jugadoresConFranjas = metJug.getJugadoresConFranjas();	    		    	
	    }

	    JPanel panelJugadores = getPnJugadoresFranjas();
	    panelJugadores.removeAll();
	    panelJugadores.setLayout(new GridLayout(0, 1, 0, 5));

	    if (jugadoresConFranjas.isEmpty()) {
	        panelJugadores.add(new JLabel("Ningún jugador tiene franjas", SwingConstants.CENTER));
	    } else {
	        for (PersonDTO jugador : jugadoresConFranjas) {
	            JToggleButton btn = new JToggleButton(jugador.getNombre() + " " + jugador.getApellido());
	            btn.setBackground(Color.WHITE);
	            btn.setFocusPainted(false);
	            btn.setActionCommand(String.valueOf(jugador.getId()));
	            btn.addActionListener(e -> {
	                ultimoJugadorSeleccionado = Integer.parseInt(e.getActionCommand());
	                mostrarFranjasJugador(ultimoJugadorSeleccionado);
	            });
	            grupoJugadores.add(btn);
	            panelJugadores.add(btn);
	        }
	    }

	    panelJugadores.revalidate();
	    panelJugadores.repaint();
	}

	private void mostrarFranjasJugador(int idJugador) {
	    MetodosEntrevista metEnt = new MetodosEntrevista();
	    List<FranjaEntrevistaDTO> franjas = metEnt.getFranjasDeJugador(idJugador);

	    JPanel panelFranjas = getPnFranjas();
	    panelFranjas.removeAll();
	    panelFranjas.setLayout(new GridLayout(0, 1, 0, 5));
	    grupoFranjas = new ButtonGroup();

	    boolean hayFranjasVisibles = false;

	    for (FranjaEntrevistaDTO franja : franjas) {
	        // Omitir franjas que ya tienen medio asignado
	        if (franja.getMedioComunicacion() != null && !franja.getMedioComunicacion().isEmpty()) {
	            continue;
	        }

	        String texto = franja.getFecha().toString() + " - " + franja.getHoraInicio() + " a " + franja.getHoraFin();
	        JToggleButton btn = new JToggleButton(texto);
	        btn.setActionCommand(String.valueOf(franja.getIdFranja()));
	        btn.setBackground(Color.WHITE);
	        btn.setFocusPainted(false);
	        grupoFranjas.add(btn); 
	        panelFranjas.add(btn);
	        hayFranjasVisibles = true;
	    }

	    if (!hayFranjasVisibles) {
	        panelFranjas.add(new JLabel("Este jugador tiene franjas pero no disponibles.", SwingConstants.CENTER));
	    }

	    panelFranjas.revalidate();
	    panelFranjas.repaint();
	}

	private JPanel getPnJugadoresFranjas() {
		if (pnJugadoresFranjas == null) {
			pnJugadoresFranjas = new JPanel();
			pnJugadoresFranjas.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
					"Jugadores con Franjas", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
			pnJugadoresFranjas.setBackground(Color.WHITE);
			pnJugadoresFranjas.setLayout(new GridLayout(0, 1, 0, 5));
		}
		return pnJugadoresFranjas;
	}

	private JPanel getPnFranjas() {
		if (pnFranjas == null) {
			pnFranjas = new JPanel();
			pnFranjas.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
					"Franjas Disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
			pnFranjas.setBackground(Color.WHITE);
			pnFranjas.setLayout(new GridLayout(0, 1, 0, 5));
		}
		return pnFranjas;
	}

	public JButton getBtGuardarCambios() {
		if (btGuardarCambios == null) {
			btGuardarCambios = new JButton("Guardar Cambios");
			btGuardarCambios.setBackground(new Color(50, 205, 50));
		}
		return btGuardarCambios;
	}

	private JLabel getLbMedioComunicacion() {
		if (lbMedioComunicacion == null) {
			lbMedioComunicacion = new JLabel("Medio de comunicación:");
			lbMedioComunicacion.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbMedioComunicacion;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setColumns(10);
		}
		return textField;
	}

	public Integer guardarCambios() {
	    ButtonModel jugadorSeleccionado = grupoJugadores.getSelection();
	    ButtonModel franjaSeleccionada = grupoFranjas.getSelection();

	    if (jugadorSeleccionado == null || franjaSeleccionada == null) {
	        JOptionPane.showMessageDialog(this, "Debe seleccionar un jugador y una franja.", "Error", JOptionPane.WARNING_MESSAGE);
			return null;
	    }

	    String medio = textField.getText().trim();
	    if (medio.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Debe indicar el medio de comunicación.", "Error", JOptionPane.WARNING_MESSAGE);
			return null;
	    }

	    int idJugador = Integer.parseInt(jugadorSeleccionado.getActionCommand());
	    int idFranja = Integer.parseInt(franjaSeleccionada.getActionCommand());

	    MetodosEntrevista metEnt = new MetodosEntrevista();
	    try {
	        metEnt.asignarEntrevistaYEliminarRestantes(idFranja, idJugador, medio);
	        JOptionPane.showMessageDialog(this, "Entrevista asignada correctamente.");
	        inicializarJugadores(); // Recargar jugadores

	        this.dispose(); // Cierra la ventana actual
	        new AssignInterviewSlotView(); // Crea una nueva instancia
			return idFranja;

	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this, "Error al asignar entrevista: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
		return null;
	}
	private JPanel getPnSouth() {
		if (pnSouth == null) {
			pnSouth = new JPanel();
			pnSouth.setBackground(Color.WHITE);
			pnSouth.setLayout(new GridLayout(0, 3, 0, 0));
			pnSouth.add(getLbMedioComunicacion());
			pnSouth.add(getTextField());
			pnSouth.add(getBtGuardarCambios());
		}
		return pnSouth;
	}
	private JPanel getPnLayout() {
		if (pnLayout == null) {
			pnLayout = new JPanel();
			pnLayout.setLayout(new GridLayout(1, 2, 0, 0));
			pnLayout.add(getPnJugadoresFranjas());
			pnLayout.add(getPnFranjas());
		}
		return pnLayout;
	}
}
