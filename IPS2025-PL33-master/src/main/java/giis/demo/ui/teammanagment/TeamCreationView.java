package giis.demo.ui.teammanagment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import giis.demo.tkrun.teammanagement.FormationTeamCategory;
import giis.demo.tkrun.teammanagement.MetodosEntrenador;
import giis.demo.tkrun.teammanagement.MetodosEntrenador.EntrenadorDTO;
import giis.demo.tkrun.teammanagement.MetodosJugador;
import giis.demo.tkrun.teammanagement.PersonDTO;
import giis.demo.tkrun.teammanagement.TeamType;
import giis.demo.util.Database;

public class TeamCreationView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel formulario;
	private JLabel lbTeamName;
	private JLabel lbTeamType;
	private JLabel lbTeamCategory;
	private JLabel lbFirstDt;
	private JLabel lbSecondDt;
	private JTextField textField;
	private JComboBox<String> cbTeamType;
	private JComboBox<FormationTeamCategory> cbTeamCategory;
	private JComboBox<EntrenadorDTO> cbPrimerEntrendor;
	private JComboBox<EntrenadorDTO> cbSegundoEntrenador;

	private JLabel lbJugadoresActuales;
	private JLabel lblMinJugadores;
	private JTextField txJugadores;
	private JScrollPane scrollJugadores;
	private JPanel pnJugadores;
	private JButton btAgregarEquipo;

	MetodosEntrenador met = new MetodosEntrenador();
	List<EntrenadorDTO> allCoachesWithoutTeam = met.getAllAvailableCoaches();

	private List<PersonDTO> jugadoresSeleccionados = new ArrayList<>();

	private int primerEntrenadorId;
	private int segundoEntrenadorId;
	
	private boolean actualizandoPrimerEntrenador = false;
	private boolean actualizandoSegundoEntrenador = false;

	private String AGREGAR_EQUIPO = "INSERT INTO Equipo (nombre, id_primer_entrenador, id_segundo_entrenador, tipo_equipo, categoria_equipo) "
			+ "VALUES (?, ?, ?, ?, ?)";
	private JPanel pnDerecho;
	private JPanel pnInfo;
	private JPanel pnJugadoresActuales;
	
	private String tipoEquipoAnterior = "Primer equipo";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamCreationView frame = new TeamCreationView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TeamCreationView() {
		setTitle("TeamManagment");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1332, 396);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 30, 0));
		contentPane.add(getFormulario());
		contentPane.add(getPnDerecho());
		setMinimumSize(getSize());
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public JButton getBtAgregarEquipo() {
		if (btAgregarEquipo == null) {
			btAgregarEquipo = new JButton("Crear Equipo");
			btAgregarEquipo.setToolTipText("");
			btAgregarEquipo.setBackground(new Color(50, 205, 50));
			btAgregarEquipo.setEnabled(false);
//			btAgregarEquipo.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					crearEquipo();
//				}
//			});
		}
		return btAgregarEquipo;
	}

	public Integer crearEquipo() {
	    String nombreEquipo = textField.getText().trim();
	    String tipoEquipo = (String) cbTeamType.getSelectedItem();
	    FormationTeamCategory categoria = (FormationTeamCategory) cbTeamCategory.getSelectedItem();
	    EntrenadorDTO primerEntrenador = (EntrenadorDTO) cbPrimerEntrendor.getSelectedItem();
	    EntrenadorDTO segundoEntrenador = (EntrenadorDTO) cbSegundoEntrenador.getSelectedItem();

	    if (primerEntrenador == null || segundoEntrenador == null) {
	        JOptionPane.showMessageDialog(this, "Debe seleccionar ambos entrenadores.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
	    }

	    primerEntrenadorId = primerEntrenador.getId_empleado();
	    segundoEntrenadorId = segundoEntrenador.getId_empleado();


	    if (nombreEquipo.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Debe introducir un nombre para el equipo.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
	    }

	    if (primerEntrenador == null || segundoEntrenador == null) {
	        JOptionPane.showMessageDialog(this, "Debe seleccionar ambos entrenadores.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
	    }

	    primerEntrenadorId = primerEntrenador.getId_empleado();
	    segundoEntrenadorId = segundoEntrenador.getId_empleado();

	    if (jugadoresSeleccionados.size() < 7) {
	        JOptionPane.showMessageDialog(this, "Debe seleccionar al menos 7 jugadores.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
	    }


	    // Insertar en la base de datos
	    Database db = new Database();
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = db.getConnection();
	        conn.setAutoCommit(false); // iniciar transacción

	        // 1️⃣ Insertar el equipo
	        ps = conn.prepareStatement(AGREGAR_EQUIPO);
	        ps.setString(1, nombreEquipo);
	        ps.setInt(2, primerEntrenadorId);
	        ps.setInt(3, segundoEntrenadorId);
	        ps.setString(4, tipoEquipoToDb(tipoEquipo));
	        ps.setString(5, (tipoEquipo.equals("Formacion")) ? categoria.name().toLowerCase() : null);
	        ps.executeUpdate();

	        rs = ps.getGeneratedKeys();
	        int equipoId = -1;
	        if (rs.next()) {
	            equipoId = rs.getInt(1);
	        }

	        if (equipoId == -1) {
	            throw new SQLException("No se pudo obtener el ID del equipo recién creado.");
	        }

	        // 2️⃣ Insertar jugadores en la tabla de relación
	        String sqlInsertJugador = "INSERT INTO Plantilla (id_equipo, id_jugador) VALUES (?, ?)";
	        try (PreparedStatement pstmtJugador = conn.prepareStatement(sqlInsertJugador)) {
	            for (PersonDTO jugador : jugadoresSeleccionados) {
	                pstmtJugador.setInt(1, equipoId);
	                pstmtJugador.setInt(2, jugador.getId());
	                pstmtJugador.addBatch();
	            }
	            pstmtJugador.executeBatch();
	        }

	        conn.commit();

	        JOptionPane.showMessageDialog(this, 
	            "Equipo '" + nombreEquipo + "' creado correctamente con " + jugadoresSeleccionados.size() + " jugadores.",
	            "Éxito", JOptionPane.INFORMATION_MESSAGE);

	        this.dispose();
			return equipoId;

	    } catch (SQLException ex) {
	        try {
	            if (conn != null) conn.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        JOptionPane.showMessageDialog(this, "Error al crear el equipo: " + ex.getMessage(),
	            "Error", JOptionPane.ERROR_MESSAGE);
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		return null;
	}
	
	private String tipoEquipoToDb(String tipoEquipo) {
	    switch (tipoEquipo) {
	        case "Primer Equipo":
	            return "profesional_primer_equipo";
	        case "Filial":
	            return "profesional_filial";
	        case "Formacion":
	            return "formacion";
	        default:
	            return tipoEquipo; // por si acaso coincide ya con la BD
	    }
	}


	private JPanel getFormulario() {
		if (formulario == null) {
			formulario = new JPanel();
			formulario.setBackground(new Color(255, 255, 255));
			formulario.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
					"Formulario Creacion de Equipo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			formulario.setLayout(new GridLayout(5, 2, 20, 30));
			formulario.add(getLbTeamName());
			formulario.add(getTextField());
			formulario.add(getLbTeamType());
			formulario.add(getCbTeamType());
			formulario.add(getLbTeamCategory());
			formulario.add(getCbTeamCategory());
			formulario.add(getLbFirstDt());
			formulario.add(getCbPrimerEntrendor());
			formulario.add(getLbSecondDt());
			formulario.add(getCbSegundoEntrenador());
		}
		return formulario;
	}

	private JLabel getLbTeamName() {
		if (lbTeamName == null) {
			lbTeamName = new JLabel("Nombre del equipo:");
			lbTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbTeamName;
	}

	private JLabel getLbTeamType() {
		if (lbTeamType == null) {
			lbTeamType = new JLabel("Tipo del equipo:");
			lbTeamType.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbTeamType;
	}

	private JLabel getLbTeamCategory() {
		if (lbTeamCategory == null) {
			lbTeamCategory = new JLabel("Categoría del equipo:");
			lbTeamCategory.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbTeamCategory;
	}

	private JLabel getLbFirstDt() {
		if (lbFirstDt == null) {
			lbFirstDt = new JLabel("Primer entrenador del equipo:");
			lbFirstDt.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbFirstDt;
	}

	private JLabel getLbSecondDt() {
		if (lbSecondDt == null) {
			lbSecondDt = new JLabel("Segundo entrenador del equipo:");
			lbSecondDt.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbSecondDt;
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
		}
		return textField;
	}

	private JComboBox<String> getCbTeamType() {
		if (cbTeamType == null) {
			cbTeamType = new JComboBox<String>();
			cbTeamType.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tipoEquipoAnterior = (String) cbTeamType.getSelectedItem();
					actualizarCategoryCb((String) cbTeamType.getSelectedItem());
				}
			});
			cbTeamType.setModel(new DefaultComboBoxModel<String>(crearContenidoComboBox()));
			cbTeamType.addActionListener(e -> actualizarJugadores());
		}
		return cbTeamType;
	}

	private String[] crearContenidoComboBox() {
		String[] valores = new String[TeamType.values().length];
		int contador = 0;
		for (TeamType value : TeamType.values()) {
			switch (value) {
			case PROFESIONAL_PRIMER_EQUIPO:
				valores[contador] = "Primer Equipo";
				contador++;
				break;
			case PROFESIONAL_FILIAL:
				valores[contador] = "Filial";
				contador++;
				break;
			case FORMACION:
				valores[contador] = "Formacion";
				contador++;
				break;
			}
		}
		return valores;
	}

	private void actualizarCategoryCb(String tipo) {
		getCbTeamCategory().setEnabled("Formacion".equals(tipo));
	}

	private JComboBox<FormationTeamCategory> getCbTeamCategory() {
		if (cbTeamCategory == null) {
			cbTeamCategory = new JComboBox<FormationTeamCategory>();
			cbTeamCategory.setModel(new DefaultComboBoxModel<FormationTeamCategory>(FormationTeamCategory.values()));
			actualizarCategoryCb((String) cbTeamType.getSelectedItem());
			cbTeamCategory.addActionListener(e -> actualizarJugadores());
		}
		return cbTeamCategory;
	}

	private JComboBox<EntrenadorDTO> getCbPrimerEntrendor() {
	    if (cbPrimerEntrendor == null) {
	        cbPrimerEntrendor = new JComboBox<>();
	        cargarEntrenadores(cbPrimerEntrendor, null, null); // cargar todos inicialmente
	        cbPrimerEntrendor.addActionListener(e -> actualizarSegundoEntrenador());
	        cbPrimerEntrendor.addActionListener(e -> checkConditionsOfCreation());
	    }
	    return cbPrimerEntrendor;
	}

	private JComboBox<EntrenadorDTO> getCbSegundoEntrenador() {
	    if (cbSegundoEntrenador == null) {
	        cbSegundoEntrenador = new JComboBox<>();
	        // Excluir el primer seleccionado al inicializar
	        EntrenadorDTO primerSeleccionado = (cbPrimerEntrendor.getItemCount() > 0) ? 
	                                          cbPrimerEntrendor.getItemAt(cbPrimerEntrendor.getSelectedIndex()) : null;
	        cargarEntrenadores(cbSegundoEntrenador, primerSeleccionado, null);
	        cbSegundoEntrenador.addActionListener(e -> actualizarPrimerEntrenador());
	        cbSegundoEntrenador.addActionListener(e -> checkConditionsOfCreation());
	    }
	    return cbSegundoEntrenador;
	}

	private void actualizarSegundoEntrenador() {
	    if (actualizandoSegundoEntrenador) return;
	    actualizandoPrimerEntrenador = true;

	    EntrenadorDTO primerSeleccionado = (EntrenadorDTO) cbPrimerEntrendor.getSelectedItem();
	    EntrenadorDTO segundoSeleccionado = (cbSegundoEntrenador.getItemCount() > 0) ? 
	                                        cbSegundoEntrenador.getItemAt(cbSegundoEntrenador.getSelectedIndex()) : null;

	    cargarEntrenadores(cbSegundoEntrenador, primerSeleccionado, segundoSeleccionado);

	    actualizandoPrimerEntrenador = false;
	}

	private void actualizarPrimerEntrenador() {
	    if (actualizandoPrimerEntrenador) return;
	    actualizandoSegundoEntrenador = true;

	    EntrenadorDTO segundoSeleccionado = (cbSegundoEntrenador.getItemCount() > 0) ? 
	                                        cbSegundoEntrenador.getItemAt(cbSegundoEntrenador.getSelectedIndex()) : null;
	    EntrenadorDTO primerSeleccionado = (cbPrimerEntrendor.getItemCount() > 0) ? 
	                                       cbPrimerEntrendor.getItemAt(cbPrimerEntrendor.getSelectedIndex()) : null;

	    // Solo excluir el segundo si ya hay selección
	    cargarEntrenadores(cbPrimerEntrendor, segundoSeleccionado, primerSeleccionado);

	    actualizandoSegundoEntrenador = false;
	}




	
	private void cargarEntrenadores(JComboBox<EntrenadorDTO> combo, EntrenadorDTO entrenadorExcluido, EntrenadorDTO seleccionado) {
	    combo.removeAllItems();
	    MetodosEntrenador met = new MetodosEntrenador();
	    List<EntrenadorDTO> entrenadores = met.getAllAvailableCoaches();

	    for (EntrenadorDTO e : entrenadores) {
	        if (entrenadorExcluido == null || e.getId_empleado() != entrenadorExcluido.getId_empleado()) {
	            combo.addItem(e);
	        }
	    }

	    // Mantener el seleccionado si sigue siendo válido
	    if (seleccionado != null) {
	        boolean encontrado = false;
	        for (int i = 0; i < combo.getItemCount(); i++) {
	            if (combo.getItemAt(i).getId_empleado() == seleccionado.getId_empleado()) {
	                combo.setSelectedIndex(i);
	                encontrado = true;
	                break;
	            }
	        }
	        if (!encontrado && combo.getItemCount() > 0) {
	            combo.setSelectedIndex(0); // seleccionar primer elemento disponible
	        }
	    } else if (combo.getItemCount() > 0) {
	        combo.setSelectedIndex(0);
	    }
	}




	private JLabel getLbJugadoresActuales() {
		if (lbJugadoresActuales == null) {
			lbJugadoresActuales = new JLabel("Jugadores actuales:");
			lbJugadoresActuales.setBackground(Color.WHITE);
			lbJugadoresActuales.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbJugadoresActuales;
	}

	private JLabel getLblMinJugadores() {
		if (lblMinJugadores == null) {
			lblMinJugadores = new JLabel("(Mínimo 7 jugadores para la creación)");
		}
		return lblMinJugadores;
	}

	private JTextField getTxJugadores() {
		if (txJugadores == null) {
			txJugadores = new JTextField();
			txJugadores.setEditable(false);
			txJugadores.setText("0");
			txJugadores.setHorizontalAlignment(SwingConstants.CENTER);
			txJugadores.setColumns(10);
		}
		return txJugadores;
	}

	private JScrollPane getScrollJugadores() {
		if (scrollJugadores == null) {
			scrollJugadores = new JScrollPane();
			scrollJugadores.setViewportBorder(
					new TitledBorder(null, "Agregar Jugadores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			scrollJugadores.setBackground(Color.WHITE);
			scrollJugadores.setViewportView(getPnJugadores());
		}
		return scrollJugadores;
	}

	private JPanel getPnJugadores() {
		if (pnJugadores == null) {
			pnJugadores = new JPanel();
			pnJugadores.setBorder(null);
			pnJugadores.setBackground(Color.WHITE);
			pnJugadores.setLayout(new GridLayout(0, 1, 0, 0));
			addDefaultPane();
			mostarJugadores();
		}
		return pnJugadores;
	}

	private void addDefaultPane() {
		PlayerPane playerPane = new PlayerPane(this, 0, "Nombre y", "Apellido", "DNI", "Telefono", "Fecha nacimiento");

		// Desactivar el botón si no cumple la edad
		playerPane.getBtAgregar().setText("Agregar Jugador");
		playerPane.getBtAgregar().setEnabled(false);
		playerPane.cambiarFondo(Color.LIGHT_GRAY);
		pnJugadores.add(playerPane);
	}

	private void mostarJugadores() {
		FormationTeamCategory categoriaSeleccionada = (FormationTeamCategory) cbTeamCategory.getSelectedItem();

		for (PersonDTO jugador : new MetodosJugador().getAllPlayers()) {
			int edad = calcularEdad(jugador.getFechaNacimiento());
			boolean edadValida = isEdadValidaParaCategoria(edad, categoriaSeleccionada);

			PlayerPane playerPane = new PlayerPane(this, jugador);

			// Desactivar el botón si no cumple la edad
			playerPane.getBtAgregar().setEnabled(edadValida);
			if(edadValida) {
				pnJugadores.add(playerPane);
			}
				
		}

		pnJugadores.revalidate();
		pnJugadores.repaint();
	}

	private boolean isEdadValidaParaCategoria(int edad, FormationTeamCategory categoria) {
		if (getCbTeamType().getSelectedItem().equals("Formacion")) {
			switch (categoria) {
			case PREBENJAMIN:
				return edad >= 5 && edad <= 8;
			case BENJAMIN:
				return edad >= 9 && edad <= 10;
			case ALEVIN:
				return edad >= 11 && edad <= 12;
			case INFANTIL:
				return edad >= 13 && edad <= 14;
			case CADETE:
				return edad >= 15 && edad <= 16;
			case JUVENIL:
				return edad >= 17 && edad <= 19;
			default:
				return true; // Por si acaso
			}
		} else {
			return true;
		}

	}

	private int calcularEdad(Date fechaNacimiento) {
		LocalDate nacimiento = fechaNacimiento.toLocalDate();
		LocalDate hoy = LocalDate.now();
		return Period.between(nacimiento, hoy).getYears();
	}

	private void actualizarJugadores() {
		if("Formacion".equals(getCbTeamType().getSelectedItem()) || "Formacion".equals(tipoEquipoAnterior)){
			pnJugadores.removeAll();
			getTxJugadores().setText("0");
			jugadoresSeleccionados.clear();
			addDefaultPane();
			mostarJugadores();
		}
	}

	void agregarJugador(PersonDTO jugador) {
		jugadoresSeleccionados.add(jugador);
		txJugadores.setText(String.valueOf(jugadoresSeleccionados.size()));
		checkConditionsOfCreation();
	}

	private void checkConditionsOfCreation() {
		boolean jugadoresSuficientes = jugadoresSeleccionados.size() >= 7;

		// Activar o desactivar el botón de creación
		boolean puedeCrear = jugadoresSuficientes;
		getBtAgregarEquipo().setEnabled(puedeCrear);

		// (opcional) Cambiar color para feedback visual
		if (puedeCrear) {
			btAgregarEquipo.setEnabled(true);
		} else {
			btAgregarEquipo.setEnabled(false);
		}
	}
	private JPanel getPnDerecho() {
		if (pnDerecho == null) {
			pnDerecho = new JPanel();
			pnDerecho.setBackground(Color.WHITE);
			pnDerecho.setLayout(new GridLayout(2, 0, 0, 0));
			pnDerecho.add(getScrollJugadores());
			pnDerecho.add(getPnInfo());
		}
		return pnDerecho;
	}
	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setBackground(Color.WHITE);
			pnInfo.setLayout(new BorderLayout(0, 0));
			pnInfo.add(getBtAgregarEquipo(), BorderLayout.SOUTH);
			pnInfo.add(getLblMinJugadores(), BorderLayout.EAST);
			pnInfo.add(getPnJugadoresActuales(), BorderLayout.WEST);
		}
		return pnInfo;
	}
	private JPanel getPnJugadoresActuales() {
		if (pnJugadoresActuales == null) {
			pnJugadoresActuales = new JPanel();
			pnJugadoresActuales.setBackground(Color.WHITE);
			pnJugadoresActuales.setLayout(new GridLayout(0, 2, 10, 50));
			pnJugadoresActuales.add(getLbJugadoresActuales());
			pnJugadoresActuales.add(getTxJugadores());
		}
		return pnJugadoresActuales;
	}
	
	void eliminarJugador(PersonDTO jugador) {
		jugadoresSeleccionados.remove(jugador);
		txJugadores.setText(String.valueOf(jugadoresSeleccionados.size()));
		checkConditionsOfCreation();
	}
}
