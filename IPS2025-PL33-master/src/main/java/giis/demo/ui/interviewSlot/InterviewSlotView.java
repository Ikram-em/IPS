package giis.demo.ui.interviewSlot;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import giis.demo.tkrun.interviewSlot.MetodosEntrevista;
import giis.demo.tkrun.interviewSlot.ResultadoFranja;
import giis.demo.tkrun.login.Session;
import giis.demo.tkrun.teammanagement.MetodosEntrenador;
import giis.demo.tkrun.teammanagement.MetodosEquipo;
import giis.demo.tkrun.teammanagement.PersonDTO;
import giis.demo.util.Util;

public class InterviewSlotView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel pnJugadores;
	private JPanel pnInfo;
	private JPanel pnCrearFranja;
	private JScrollPane scrollPane;
	private JPanel pnJugadoresEntrenador;
	private JPanel pnInfoJugador;
	private JPanel pnInfoEntrenador;

	private JTextField txFranja;
	private JTextField txEntrena;
	private JTextField txPartido;
	private JTextField textField_3;
	private JTextField textField_4;

	private JLabel lbEntrenador;
	private JComboBox<String> cbEntrenador;
	private JSeparator separator;
	private JLabel lbInfoJugador;
	private JLabel lbEntrenamiento;
	private JLabel lblNewLabel;
	private JLabel lbPartido;
	private JLabel lbDia;
	private JComboBox<String> cbDia;
	private JLabel lbMes;
	private JComboBox<String> comboBoxMes;
	private JLabel lbAnio;
	private JComboBox<String> comboBoxAnio;
	private JLabel lbHoraInicial;
	private JLabel lblNewLabel_1;
	private JButton btGuardarCambios;
	
	private ButtonGroup toggleButtonGroup = new ButtonGroup();


	public InterviewSlotView() {
		setTitle("Crear Franajs Para Entrevistas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 473);
		setMinimumSize(getSize());
		setContentPane(getContentPn());
		
		 inicializarComboEntrenadores();
		 inicializarComboFecha();
		 setLocationRelativeTo(null);
		 setVisible(true);
	}

	private JPanel getContentPn() {
		if (contentPane == null) {
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new GridLayout(0, 3, 0, 0));
			contentPane.add(getPnJugadores());
			contentPane.add(getPnInfo());
			contentPane.add(getPnCrearFranja());
		}
		return contentPane;
	}

	private JPanel getPnJugadores() {
		if (pnJugadores == null) {
			pnJugadores = new JPanel();
			pnJugadores.setBorder(null);
			pnJugadores.setLayout(new GridLayout(0, 1, 0, 0));
			pnJugadores.add(getScrollPane());
		}
		return pnJugadores;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getPnJugadoresEntrenador());
		}
		return scrollPane;
	}

	private JPanel getPnJugadoresEntrenador() {
		if (pnJugadoresEntrenador == null) {
			pnJugadoresEntrenador = new JPanel();
			pnJugadoresEntrenador.setBorder(new TitledBorder(null, "Jugadores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnJugadoresEntrenador.setBackground(Color.WHITE);
			pnJugadoresEntrenador.setLayout(new GridLayout(0, 1, 0, 0));
		}
		return pnJugadoresEntrenador;
	}

	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setBackground(Color.WHITE);
			pnInfo.setLayout(new GridLayout(0, 1, 0, 30));
			pnInfo.add(getPnInfoEntrenador());
			pnInfo.add(getPnInfoJugador());
		}
		return pnInfo;
	}

	private JPanel getPnInfoEntrenador() {
		if (pnInfoEntrenador == null) {
			pnInfoEntrenador = new JPanel();
			pnInfoEntrenador.setBackground(Color.WHITE);
			pnInfoEntrenador.setLayout(new GridLayout(4, 1, 0, 20));
			pnInfoEntrenador.add(getLbEntrenador());
			pnInfoEntrenador.add(getCbEntrenador());
			pnInfoEntrenador.add(getSeparator());
			pnInfoEntrenador.add(getLbInfoJugador());
		}
		return pnInfoEntrenador;
	}

	private JLabel getLbEntrenador() {
		if (lbEntrenador == null) {
			lbEntrenador = new JLabel("Entrenador:");
			lbEntrenador.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbEntrenador;
	}

	private JComboBox<String> getCbEntrenador() {
	    if (cbEntrenador == null) {
			cbEntrenador = new JComboBox<String>();
	        cbEntrenador.addActionListener(e -> {
	            String seleccionado = (String) cbEntrenador.getSelectedItem();
	            if (seleccionado != null && seleccionado.contains("-")) {
	                // Extraemos el ID (antes del guion)
	                String idStr = seleccionado.split("-")[0].trim();
	                try {
	                    int idEntrenador = Integer.parseInt(idStr);
	                    actualizarJugadores(idEntrenador);
	                } catch (NumberFormatException ex) {
	                    System.err.println("Error al obtener ID del entrenador: " + idStr);
	                }
	            }
	        });
	    }
	    return cbEntrenador;
	}



	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}

	private JLabel getLbInfoJugador() {
		if (lbInfoJugador == null) {
			lbInfoJugador = new JLabel("Informacion del Jugador:");
			lbInfoJugador.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbInfoJugador;
	}

	private JPanel getPnInfoJugador() {
		if (pnInfoJugador == null) {
			pnInfoJugador = new JPanel();
			pnInfoJugador.setBackground(Color.WHITE);
			pnInfoJugador.setLayout(new GridLayout(2, 3, 0, 0));
			pnInfoJugador.add(getLbEntrenamiento());
			pnInfoJugador.add(getLblNewLabel());
			pnInfoJugador.add(getLbPartido());
			pnInfoJugador.add(getTxEntrena());
			pnInfoJugador.add(getTxFranja());
			pnInfoJugador.add(getTxPartido());
		}
		return pnInfoJugador;
	}

	private JLabel getLbEntrenamiento() {
		if (lbEntrenamiento == null) {
			lbEntrenamiento = new JLabel("¿Entrena?");
			lbEntrenamiento.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbEntrenamiento;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("¿Tiene Entrevista?");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel;
	}

	private JLabel getLbPartido() {
		if (lbPartido == null) {
			lbPartido = new JLabel("¿Partido?");
			lbPartido.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbPartido;
	}

	private JTextField getTxEntrena() {
		if (txEntrena == null) {
			txEntrena = new JTextField();
			txEntrena.setHorizontalAlignment(SwingConstants.CENTER);
			txEntrena.setEnabled(false);
			txEntrena.setBackground(Color.WHITE);
			txEntrena.setEditable(false);
			txEntrena.setColumns(10);
		}
		return txEntrena;
	}

	private JTextField getTxFranja() {
		if (txFranja == null) {
			txFranja = new JTextField();
			txFranja.setHorizontalAlignment(SwingConstants.CENTER);
			txFranja.setEnabled(false);
			txFranja.setBackground(Color.WHITE);
			txFranja.setEditable(false);
			txFranja.setColumns(10);
		}
		return txFranja;
	}

	private JTextField getTxPartido() {
		if (txPartido == null) {
			txPartido = new JTextField();
			txPartido.setHorizontalAlignment(SwingConstants.CENTER);
			txPartido.setEnabled(false);
			txPartido.setBackground(Color.WHITE);
			txPartido.setEditable(false);
			txPartido.setColumns(10);
		}
		return txPartido;
	}
	
	public JButton getBtGuardarCambios() {
	    if (btGuardarCambios == null) {
	        btGuardarCambios = new JButton("Guardar");
	        btGuardarCambios.setBackground(new Color(50, 205, 50));

	    }
	    return btGuardarCambios;
	}
	
	private JPanel getPnCrearFranja() {
		if (pnCrearFranja == null) {
			pnCrearFranja = new JPanel();
			pnCrearFranja.setBorder(new TitledBorder(null, "Crear Franja", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnCrearFranja.setBackground(Color.WHITE);
			pnCrearFranja.setLayout(new GridLayout(0, 2, 0, 30));
			pnCrearFranja.add(getLbDia());
			pnCrearFranja.add(getCbDia());
			pnCrearFranja.add(getLbMes());
			pnCrearFranja.add(getComboBoxMes());
			pnCrearFranja.add(getLbAnio());
			pnCrearFranja.add(getComboBoxAnio());
			pnCrearFranja.add(getLbHoraInicial());
			pnCrearFranja.add(getTextField_3());
			pnCrearFranja.add(getLblNewLabel_1());
			pnCrearFranja.add(getTextField_4());
			
			JLabel lbVacia = new JLabel("");
			pnCrearFranja.add(lbVacia);
			
			pnCrearFranja.add(getBtGuardarCambios());
			pnCrearFranja.add(btGuardarCambios);
		}
		return pnCrearFranja;
	}
	
	


	public Integer guardarFranja() {
	    if (toggleButtonGroup.getSelection() == null) {
	        JOptionPane.showMessageDialog(this, "Selecciona un jugador.", "Error", JOptionPane.WARNING_MESSAGE);
			return null;
	    }

	    String idJugadorStr = toggleButtonGroup.getSelection().getActionCommand();
	    int idJugador = Integer.parseInt(idJugadorStr);

	    String dia = (String) cbDia.getSelectedItem();
	    String mesStr = (String) comboBoxMes.getSelectedItem();
	    String anio = (String) comboBoxAnio.getSelectedItem();
	    String horaInicio = textField_3.getText().trim();
	    String horaFin = textField_4.getText().trim();

	    if (!validarHora(horaInicio) || !validarHora(horaFin)) {
	        JOptionPane.showMessageDialog(this, "Formato de hora incorrecto. Usa HH:mm", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
	    }

	    int mes = getNumeroMes(mesStr);
	    LocalDate fechaLocal = LocalDate.of(Integer.parseInt(anio), mes, Integer.parseInt(dia));
	    String fechaIso = Util.dateToIsoString2(Date.valueOf(fechaLocal));

	    MetodosEntrevista met = new MetodosEntrevista();
	    
	    
		ResultadoFranja resultado = met.crearFranja(idJugador, Date.valueOf(fechaIso), horaInicio, horaFin);

		switch (resultado.getConflicto()) {
	        case NINGUNO:
	            JOptionPane.showMessageDialog(this, "Franja guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				return resultado.getIdFranja();
	        case ENTRENAMIENTO:
	            JOptionPane.showMessageDialog(this, "Solapamiento con entrenamiento detectado.", "Conflicto", JOptionPane.WARNING_MESSAGE);
	            break;
	        case PARTIDO:
	            JOptionPane.showMessageDialog(this, "Solapamiento con partido detectado.", "Conflicto", JOptionPane.WARNING_MESSAGE);
	            break;
	        case ENTREVISTA:
	            JOptionPane.showMessageDialog(this, "Solapamiento con otra franja de entrevista.", "Conflicto", JOptionPane.WARNING_MESSAGE);
	            break;
	        case ERROR:
	        default:
	            JOptionPane.showMessageDialog(this, "No se pudo guardar la franja por un error desconocido.", "Error", JOptionPane.ERROR_MESSAGE);
	            break;
	    }
	    
	    actualizarInfoJugadorSeleccionado();
		this.dispose();
		return null;
	}


	private int getNumeroMes(String nombreMes) {
	    String[] meses = {
	        "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
	        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
	    };
	    for (int i = 0; i < meses.length; i++) {
	        if (meses[i].equalsIgnoreCase(nombreMes))
	            return i + 1;
	    }
	    return 1;
	}


	private JLabel getLbDia() {
		if (lbDia == null) {
			lbDia = new JLabel("Día:");
			lbDia.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbDia;
	}

	private JComboBox<String> getCbDia() {
		if (cbDia == null) {
			cbDia = new JComboBox<String>();
		}
		return cbDia;
	}

	private JLabel getLbMes() {
		if (lbMes == null) {
			lbMes = new JLabel("Mes:");
			lbMes.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbMes;
	}

	private JComboBox<String> getComboBoxMes() {
		if (comboBoxMes == null) {
			comboBoxMes = new JComboBox<String>();
			comboBoxMes.addActionListener(e -> actualizarComboDias());
		}
		return comboBoxMes;
	}

	private JLabel getLbAnio() {
		if (lbAnio == null) {
			lbAnio = new JLabel("Año:");
			lbAnio.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbAnio;
	}

	private JComboBox<String> getComboBoxAnio() {
		if (comboBoxAnio == null) {
			comboBoxAnio = new JComboBox<String>();
			comboBoxAnio.addActionListener(e -> actualizarComboDias());
		}
		return comboBoxAnio;
	}

	private JLabel getLbHoraInicial() {
		if (lbHoraInicial == null) {
			lbHoraInicial = new JLabel("Hora Inicio:");
			lbHoraInicial.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbHoraInicial;
	}

	private JTextField getTextField_3() {
		if (textField_3 == null) {
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			setupHoraTextField(textField_3);
		}
		return textField_3;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Hora fin:");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel_1;
	}

	private JTextField getTextField_4() {
		if (textField_4 == null) {
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			setupHoraTextField(textField_4);
		}
		return textField_4;
	}
	
	/**
	 * Rellena el combo con los entrenadores que tienen equipos asignados.
	 */
	private void inicializarComboEntrenadores() {
	    MetodosEntrenador metodosEntrenador = new MetodosEntrenador();
	    List<String> entrenadores;
	    String roleName = Session.get().getRoleName();
	    if (roleName.equals("Entrenador")) {
	    	entrenadores = metodosEntrenador.getEntrenadoresDeEquiposProfesionalesConId(Session.get().getEmployeeID());
	    } else {
	    	entrenadores = metodosEntrenador.getEntrenadoresDeEquiposProfesionales();	    	
	    }

		JComboBox<String> combo = getCbEntrenador();
	    combo.removeAllItems();

	    for (String entrenador : entrenadores) {
	        combo.addItem(entrenador);
	    }

	    if (combo.getItemCount() == 0) {
	        combo.addItem("No hay entrenadores con equipo asignado");
	    }
	}
	

	private void actualizarJugadores(int idEntrenador) {
	    MetodosEquipo metodosEquipo = new MetodosEquipo();
	    List<PersonDTO> jugadores = metodosEquipo.getJugadoresPorEntrenador(idEntrenador);

	    JPanel panelJugadores = getPnJugadoresEntrenador();
	    panelJugadores.removeAll();

	    // *** REINICIALIZAR el ButtonGroup para quitar referencias a botones previos ***
	    toggleButtonGroup = new ButtonGroup();

	    if (jugadores.isEmpty()) {
	        JLabel noJugadores = new JLabel("No hay jugadores asignados");
	        noJugadores.setHorizontalAlignment(SwingConstants.CENTER);
	        panelJugadores.add(noJugadores);
	    } else {
	        for (PersonDTO jugador : jugadores) {
	            JToggleButton btnJugador = crearBoton(jugador);
	            panelJugadores.add(btnJugador);
	        }
	    }

	    // Limpiar selección y campos de info al cambiar entrenador
	    toggleButtonGroup.clearSelection();
	    txEntrena.setText("");
	    txPartido.setText("");
	    txFranja.setText("");

	    panelJugadores.revalidate();
	    panelJugadores.repaint();
	}


	private JToggleButton crearBoton(PersonDTO jugador) {
	    JToggleButton btnJugador =
	            new JToggleButton(jugador.getNombre() + " " + jugador.getApellido());
	    btnJugador.setBackground(Color.WHITE);
	    btnJugador.setFocusPainted(false);
	    btnJugador.setActionCommand(String.valueOf(jugador.getId()));
	    toggleButtonGroup.add(btnJugador);
	    btnJugador.addActionListener(e -> {
	        int idJugador = Integer.parseInt(btnJugador.getActionCommand());
	        actualizarInfoJugador(idJugador);
	    });
	    return btnJugador;
	}

	
	private void inicializarComboFecha() {
		JComboBox<String> comboAnio = getComboBoxAnio();
		JComboBox<String> comboMes = getComboBoxMes();
		JComboBox<String> comboDia = getCbDia();

	    comboAnio.removeAllItems();

	    int anioActual = Year.now().getValue();
	    for (int a = anioActual; a < anioActual + 10; a++) {
	        comboAnio.addItem(String.valueOf(a));
	    }

	    // Seleccionar año actual
	    LocalDate hoy = LocalDate.now();
	    comboAnio.setSelectedItem(String.valueOf(hoy.getYear()));

	    // Cargar meses según año seleccionado
	    actualizarComboMeses();

	    // Seleccionar día actual
	    comboDia.setSelectedItem(String.valueOf(hoy.getDayOfMonth()));

	    // Listeners para actualizar meses y días al cambiar año o mes
	    comboAnio.addActionListener(e -> {
	        actualizarComboMeses();
	    });

	    comboMes.addActionListener(e -> {
	        actualizarComboDias();
	    });
	    
	    cbDia.addActionListener(e -> actualizarInfoJugadorSeleccionado());
	    comboBoxMes.addActionListener(e -> actualizarInfoJugadorSeleccionado());
	    comboBoxAnio.addActionListener(e -> actualizarInfoJugadorSeleccionado());
	}

	private void actualizarComboMeses() {
		JComboBox<String> comboAnio = getComboBoxAnio();
		JComboBox<String> comboMes = getComboBoxMes();

	    comboMes.removeAllItems();

	    String[] meses = {
	        "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
	        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
	    };

	    int anioSeleccionado;
	    try {
	        anioSeleccionado = Integer.parseInt((String) comboAnio.getSelectedItem());
	    } catch (Exception e) {
	        anioSeleccionado = Year.now().getValue();
	    }

	    LocalDate hoy = LocalDate.now();
	    int mesMinimo = 1;
	    if (anioSeleccionado == hoy.getYear()) {
	        mesMinimo = hoy.getMonthValue();
	    }

	    for (int i = mesMinimo - 1; i < meses.length; i++) {
	        comboMes.addItem(meses[i]);
	    }

	    // Selecciona primer mes válido
	    if (comboMes.getItemCount() > 0)
	        comboMes.setSelectedIndex(0);

	    actualizarComboDias();
	}

	private void actualizarComboDias() {
		JComboBox<String> comboAnio = getComboBoxAnio();
		JComboBox<String> comboMes = getComboBoxMes();
		JComboBox<String> comboDia = getCbDia();

	    comboDia.removeAllItems();

	    int anioSeleccionado;
	    try {
	        anioSeleccionado = Integer.parseInt((String) comboAnio.getSelectedItem());
	    } catch (Exception e) {
	        anioSeleccionado = Year.now().getValue();
	    }

	    LocalDate hoy = LocalDate.now();

	    // Calcula el mes real
	    int mesMinimo = 1;
	    if (anioSeleccionado == hoy.getYear()) {
	        mesMinimo = hoy.getMonthValue();
	    }

	    int mesIndex = comboMes.getSelectedIndex() + mesMinimo;

	    if (mesIndex < 1 || mesIndex > 12) {
	        return; // mes inválido
	    }

	    YearMonth yearMonth = YearMonth.of(anioSeleccionado, mesIndex);
	    int diasDelMes = yearMonth.lengthOfMonth();

	    int diaMinimo = 1;
	    if (anioSeleccionado == hoy.getYear() && mesIndex == hoy.getMonthValue()) {
	        diaMinimo = hoy.getDayOfMonth();
	    }

	    for (int dia = diaMinimo; dia <= diasDelMes; dia++) {
	        comboDia.addItem(String.valueOf(dia));
	    }

	    if (comboDia.getItemCount() > 0)
	        comboDia.setSelectedIndex(0);
	    
	    actualizarInfoJugadorSeleccionado();
	}

	
	private void setupHoraTextField(JTextField textField) {
	    final String placeholder = "HH:mm (Ej: 12:50)";

	    textField.setText(placeholder);
	    textField.setForeground(Color.GRAY);

	    textField.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusGained(FocusEvent e) {
	            if (textField.getText().equals(placeholder)) {
	                textField.setText("");
	                textField.setForeground(Color.BLACK);
	            }
	        }

	        @Override
	        public void focusLost(FocusEvent e) {
	            if (textField.getText().isEmpty()) {
	                textField.setForeground(Color.GRAY);
	                textField.setText(placeholder);
	            }
	        }
	    });
	}

	
	private boolean validarHora(String hora) {
	    if (hora == null || hora.length() != 5) {
	        return false;
	    }
	    if (hora.charAt(2) != ':') {
	        return false;
	    }

	    try {
	        int hh = Integer.parseInt(hora.substring(0, 2));
	        int mm = Integer.parseInt(hora.substring(3, 5));

	        if (hh < 0 || hh > 23) return false;
	        if (mm < 0 || mm > 59) return false;

	    } catch (NumberFormatException e) {
	        return false;
	    }

	    return true; 
	}
	 
	private void actualizarInfoJugador(int idJugador) {
		MetodosEntrevista met = new MetodosEntrevista();
	    LocalDate fecha = LocalDate.of(
	        Integer.parseInt((String) comboBoxAnio.getSelectedItem()),
	        getNumeroMes((String) comboBoxMes.getSelectedItem()),
	        Integer.parseInt((String) cbDia.getSelectedItem())
	    );

	    Date fechaSQL = Date.valueOf(fecha);

	    

	    String entrenamiento = met.getHorarioEntrenamiento(idJugador, fechaSQL).orElse("");
	    String partido = met.getHorarioPartido(idJugador, fechaSQL).orElse("");
	    String entrevista = met.getEntrevistaAsignada(idJugador, fechaSQL).orElse("");

	    txEntrena.setText(entrenamiento.isEmpty() ? "No" : entrenamiento);
	    txPartido.setText(partido.isEmpty() ? "No" : partido);
	    txFranja.setText(entrevista.isEmpty() ? "No" : entrevista);
	    
	 // Desactiva el botón si hay franja ya creada
	    if (entrevista.isEmpty() || entrevista.equalsIgnoreCase("No")) {
	        // No hay entrevista: puede crear una nueva
	        btGuardarCambios.setEnabled(true);
	    } else {
	        // Ya tiene ENTREVISTA: puede borrarla pero no crear otra
	        btGuardarCambios.setEnabled(false);
	    }
	}


 


	
	private void actualizarInfoJugadorSeleccionado() {
	    if (toggleButtonGroup.getSelection() != null) {
	        int idJugador = Integer.parseInt(toggleButtonGroup.getSelection().getActionCommand());
	        actualizarInfoJugador(idJugador); 
	    }
	}
	
	public enum ConflictoFranja {
	    NINGUNO,
	    ENTRENAMIENTO,
	    PARTIDO,
	    ENTREVISTA,
	    ERROR
	}

}
