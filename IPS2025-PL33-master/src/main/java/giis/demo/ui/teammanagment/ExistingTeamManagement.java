package giis.demo.ui.teammanagment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import giis.demo.tkrun.teammanagement.EquipoDTO;
import giis.demo.tkrun.teammanagement.FormationTeamCategory;
import giis.demo.tkrun.teammanagement.MetodosEntrenador;
import giis.demo.tkrun.teammanagement.MetodosEntrenador.EntrenadorDTO;
import giis.demo.tkrun.teammanagement.MetodosEquipo;
import giis.demo.tkrun.teammanagement.MetodosJugador;
import giis.demo.tkrun.teammanagement.PersonDTO;
import giis.demo.tkrun.teammanagement.TeamType;

public class ExistingTeamManagement extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel pnExistingTeams;
    private JScrollPane scrTeams;
    private JPanel pnEquipos;

    private ButtonGroup toggleButtonGroup = new ButtonGroup();
    private JPanel pnJugadores;
    private JScrollPane scAgregarJugadores;
    private JScrollPane scJugadoresExistentes;
    private JPanel pnAgregar;
    private JPanel pnExistentes;
    private JButton btGuardarCambios;

    List<PersonDTO> jugadoresDelEquipo = new ArrayList<>();
    private List<PersonDTO> jugadoresOriginales = new ArrayList<>();
    private EquipoDTO equipoSeleccionado = null; // equipo activo

    private List<EquipoDTO> equipos;
    private JPanel pnTeam;
    private JPanel pnEntrenadores;
    private JLabel lbPrimerEntrenador;
    private JComboBox<EntrenadorDTO> cbPrimerEntrenador;
    private JLabel lbSegundoEntrenador;
    private JComboBox<EntrenadorDTO> cbSegundoEntrenador;

    private boolean actualizandoPrimerEntrenador = false;
    private boolean actualizandoSegundoEntrenador = false;

    private final MetodosEntrenador metodosEntrenador = new MetodosEntrenador();
    private final MetodosEquipo metodosEquipo = new MetodosEquipo();

    private List<EntrenadorDTO> entrenadoresDisponibles;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ExistingTeamManagement frame = new ExistingTeamManagement();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ExistingTeamManagement() {
        entrenadoresDisponibles = metodosEntrenador.getAllAvailableCoaches();

        setTitle("GestionEquiposExistentes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1332, 396);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(1, 2, 0, 0));
        contentPane.add(getPnExistingTeams());
        contentPane.add(getPnJugadores());
        generateTeams();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel getPnExistingTeams() {
        if (pnExistingTeams == null) {
            pnExistingTeams = new JPanel();
            pnExistingTeams.setBorder(
                    new TitledBorder(null, "Equipos existentes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            pnExistingTeams.setBackground(Color.WHITE);
            pnExistingTeams.setLayout(new BorderLayout(0, 10));
            pnExistingTeams.add(getBtGuardarCambios(), BorderLayout.SOUTH);
            pnExistingTeams.add(getPnTeam(), BorderLayout.CENTER);
        }
        return pnExistingTeams;
    }

    private JScrollPane getScrTeams() {
        if (scrTeams == null) {
            scrTeams = new JScrollPane();
            scrTeams.setBackground(Color.WHITE);
            scrTeams.setViewportView(getPnEquipos());
        }
        return scrTeams;
    }

    private JPanel getPnEquipos() {
        if (pnEquipos == null) {
            pnEquipos = new JPanel();
            pnEquipos.setBackground(Color.WHITE);
            pnEquipos.setLayout(new GridLayout(0, 1, 0, 0));
        }
        return pnEquipos;
    }

    private JPanel getPnJugadores() {
        if (pnJugadores == null) {
            pnJugadores = new JPanel();
            pnJugadores.setBorder(null);
            pnJugadores.setBackground(Color.WHITE);
            pnJugadores.setLayout(new GridLayout(2, 1, 0, 10));
            pnJugadores.add(getScJugadoresExistentes());
            pnJugadores.add(getScAgregarJugadores());
        }
        return pnJugadores;
    }

    private JScrollPane getScAgregarJugadores() {
        if (scAgregarJugadores == null) {
            scAgregarJugadores = new JScrollPane();
            scAgregarJugadores.setBackground(Color.WHITE);
            scAgregarJugadores.setViewportView(getPnAgregar());
        }
        return scAgregarJugadores;
    }

    private JScrollPane getScJugadoresExistentes() {
        if (scJugadoresExistentes == null) {
            scJugadoresExistentes = new JScrollPane();
            scJugadoresExistentes.setBackground(Color.WHITE);
            scJugadoresExistentes.setViewportView(getPnExistentes());
        }
        return scJugadoresExistentes;
    }

    private JPanel getPnAgregar() {
        if (pnAgregar == null) {
            pnAgregar = new JPanel();
            pnAgregar.setBorder(
                    new TitledBorder(null, "Agregar Jugadores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            pnAgregar.setBackground(Color.WHITE);
            pnAgregar.setLayout(new GridLayout(0, 1, 0, 0));
        }
        return pnAgregar;
    }

    private JPanel getPnExistentes() {
        if (pnExistentes == null) {
            pnExistentes = new JPanel();
            pnExistentes.setBorder(
                    new TitledBorder(null, "Jugadores Existentes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            pnExistentes.setBackground(Color.WHITE);
            pnExistentes.setLayout(new GridLayout(0, 1, 0, 0));
        }
        return pnExistentes;
    }

	public JButton getBtGuardarCambios() {
        if (btGuardarCambios == null) {
            btGuardarCambios = new JButton("Guardar Cambios");
            btGuardarCambios.setBackground(new Color(50, 205, 50));
        }
        return btGuardarCambios;
    }

    private void generateTeams() {
        equipos = metodosEquipo.getAllCreatedTeams();
        for (EquipoDTO equipo : equipos) {
            JToggleButton btn = generateButton(equipo);
            btn.addActionListener(e -> cargarPlantilla(equipo));
            getPnEquipos().add(btn);
        }
    }

    private JToggleButton generateButton(EquipoDTO equipo) {
        JToggleButton newButton = new JToggleButton();
        newButton.setText("Equipo: " + equipo.getName() + " | " + equipo.getTipoEquipo()
                + (equipo.getFormationTeamCategory() != null ? " | " + equipo.getFormationTeamCategory() : ""));
        toggleButtonGroup.add(newButton);
        return newButton;
    }

    public void agregarJugador(PersonDTO jugador) {
        if (!jugadoresDelEquipo.contains(jugador)) {
            jugadoresDelEquipo.add(jugador);
        }
    }

    public void eliminarJugador(PersonDTO jugador) {
        jugadoresDelEquipo.remove(jugador);
    }

	public Integer guardarCambios() {
        if (equipoSeleccionado == null)
			return null;

        EntrenadorDTO primer = (EntrenadorDTO) cbPrimerEntrenador.getSelectedItem();
        EntrenadorDTO segundo = (EntrenadorDTO) cbSegundoEntrenador.getSelectedItem();

        int idPrimerEntrenador = primer != null ? primer.getId_empleado() : -1;
        int idSegundoEntrenador = segundo != null ? segundo.getId_empleado() : -1;

        metodosEquipo.actualizarEntrenadoresDeEquipo(equipoSeleccionado.getId(), idPrimerEntrenador,
                idSegundoEntrenador);

        metodosEquipo.actualizarJugadoresDeEquipo(equipoSeleccionado.getId(), jugadoresOriginales, jugadoresDelEquipo);
        jugadoresOriginales = new ArrayList<>(jugadoresDelEquipo);

        JOptionPane.showMessageDialog(this,  "Cambios guardados con exito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();

        new ExistingTeamManagement();
		return equipoSeleccionado.getId();
    }

    private void refrescarJugadores() {
        if (equipoSeleccionado == null)
            return;

        pnExistentes.removeAll();
        pnAgregar.removeAll();

        MetodosJugador mj = new MetodosJugador();
        List<PersonDTO> jugadoresEquipo = mj.getPlayersOfTeam(equipoSeleccionado.getId());
        List<PersonDTO> todosLosJugadores = mj.getAllPlayers();

        jugadoresDelEquipo.clear();
        jugadoresDelEquipo.addAll(jugadoresEquipo);
        jugadoresOriginales = new ArrayList<>(jugadoresEquipo);

        for (PersonDTO jugador : jugadoresEquipo) {
            int edad = calcularEdad(jugador.getFechaNacimiento());
            if (isEdadValidaParaCategoria(edad, equipoSeleccionado.getTipoEquipo(),
                    equipoSeleccionado.getFormationTeamCategory())) {
                PlayerPaneE pane = new PlayerPaneE(this, jugador);
                pane.setEstado(true);
                pnExistentes.add(pane);
            }
        }

        for (PersonDTO jugador : todosLosJugadores) {
            if (!jugadoresEquipo.contains(jugador)) {
                int edad = calcularEdad(jugador.getFechaNacimiento());
                if (isEdadValidaParaCategoria(edad, equipoSeleccionado.getTipoEquipo(),
                        equipoSeleccionado.getFormationTeamCategory())) {
                    PlayerPaneE pane = new PlayerPaneE(this, jugador);
                    pane.setEstado(false);
                    pnAgregar.add(pane);
                }
            }
        }

        pnExistentes.revalidate();
        pnExistentes.repaint();
        pnAgregar.revalidate();
        pnAgregar.repaint();
    }

    private int calcularEdad(Date fechaNacimiento) {
        if (fechaNacimiento == null)
            return 0;
        return Period.between(fechaNacimiento.toLocalDate(), LocalDate.now()).getYears();
    }

    private boolean isEdadValidaParaCategoria(int edad, TeamType tipoEquipo, FormationTeamCategory categoria) {
        if (tipoEquipo != TeamType.FORMACION) {
            return true;
        }
        if (categoria == null)
            return false;

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
                return true;
        }
    }

    private JPanel getPnTeam() {
        if (pnTeam == null) {
            pnTeam = new JPanel();
            pnTeam.setLayout(new GridLayout(2, 1, 0, 0));
            pnTeam.add(getScrTeams());
            pnTeam.add(getPnEntrenadores());
        }
        return pnTeam;
    }

    private JPanel getPnEntrenadores() {
        if (pnEntrenadores == null) {
            pnEntrenadores = new JPanel();
            pnEntrenadores.setLayout(new GridLayout(1, 4, 0, 0));
            pnEntrenadores.add(getLbPrimerEntrenador());
            pnEntrenadores.add(getCbPrimerEntrenador());
            pnEntrenadores.add(getLbSegundoEntrenador());
            pnEntrenadores.add(getCbSegundoEntrenador());
        }
        return pnEntrenadores;
    }

    private JLabel getLbPrimerEntrenador() {
        if (lbPrimerEntrenador == null) {
            lbPrimerEntrenador = new JLabel("Primer Entrenador");
        }
        return lbPrimerEntrenador;
    }

    private JComboBox<EntrenadorDTO> getCbPrimerEntrenador() {
        if (cbPrimerEntrenador == null) {
            cbPrimerEntrenador = new JComboBox<>();
            cbPrimerEntrenador.addActionListener(e -> actualizarSegundoEntrenador());
        }
        return cbPrimerEntrenador;
    }

    private JLabel getLbSegundoEntrenador() {
        if (lbSegundoEntrenador == null) {
            lbSegundoEntrenador = new JLabel("Segundo Entrenador");
        }
        return lbSegundoEntrenador;
    }

    private JComboBox<EntrenadorDTO> getCbSegundoEntrenador() {
        if (cbSegundoEntrenador == null) {
            cbSegundoEntrenador = new JComboBox<>();
            cbSegundoEntrenador.addActionListener(e -> actualizarPrimerEntrenador());
        }
        return cbSegundoEntrenador;
    }

    private void cargarPlantilla(EquipoDTO equipoSeleccionado) {
        if (equipoSeleccionado == null) return;

        this.equipoSeleccionado = equipoSeleccionado;

        actualizandoPrimerEntrenador = true;
        actualizandoSegundoEntrenador = true;

        cbPrimerEntrenador.removeAllItems();
        cbSegundoEntrenador.removeAllItems();

        // Entrenadores asignados al equipo
        EntrenadorDTO primer = metodosEntrenador.getEntrenadorById(equipoSeleccionado.getPrimerEntrenador());
        EntrenadorDTO segundo = metodosEntrenador.getEntrenadorById(equipoSeleccionado.getSegundoEntrenador());

        // Entrenadores disponibles sin equipo
        List<EntrenadorDTO> disponibles = metodosEntrenador.getAllAvailableCoaches();

        // --- Primer Combo ---
        if (primer != null) cbPrimerEntrenador.addItem(primer);
        for (EntrenadorDTO e : disponibles) {
            if (primer == null || e.getId_empleado() != primer.getId_empleado()) {
                cbPrimerEntrenador.addItem(e);
            }
        }

        // --- Segundo Combo ---
        if (segundo != null) cbSegundoEntrenador.addItem(segundo);
        for (EntrenadorDTO e : disponibles) {
            // evitar duplicar primer o segundo
            if ((primer == null || e.getId_empleado() != primer.getId_empleado()) && (segundo == null || e.getId_empleado() != segundo.getId_empleado())) {
                cbSegundoEntrenador.addItem(e);
            }
        }

        cbPrimerEntrenador.setSelectedItem(primer);
        cbSegundoEntrenador.setSelectedItem(segundo);

        actualizandoPrimerEntrenador = false;
        actualizandoSegundoEntrenador = false;

        // Refrescar jugadores
        refrescarJugadores();
    }

    private void actualizarSegundoEntrenador() {
        if (actualizandoSegundoEntrenador) return;
        actualizandoSegundoEntrenador = true;

        EntrenadorDTO primer = (EntrenadorDTO) cbPrimerEntrenador.getSelectedItem();
        EntrenadorDTO seleccionadoSegundo = (EntrenadorDTO) cbSegundoEntrenador.getSelectedItem();

        cbSegundoEntrenador.removeAllItems();

        // Agregar entrenador actualmente asignado al equipo si existe
        if (equipoSeleccionado != null) {
            EntrenadorDTO segundoActual = metodosEntrenador.getEntrenadorById(equipoSeleccionado.getSegundoEntrenador());
            if (segundoActual != null && (primer == null || segundoActual.getId_empleado() != primer.getId_empleado())) {
                cbSegundoEntrenador.addItem(segundoActual);
            }
        }

        // Agregar todos los entrenadores disponibles que no sean el primer entrenador
        for (EntrenadorDTO e : entrenadoresDisponibles) {
            if (primer == null || e.getId_empleado() != primer.getId_empleado()) {
                cbSegundoEntrenador.addItem(e);
            }
        }

        // Mantener selección anterior si sigue siendo válida
        if (seleccionadoSegundo != null && (primer == null || primer.getId_empleado() != seleccionadoSegundo.getId_empleado())) {
            cbSegundoEntrenador.setSelectedItem(seleccionadoSegundo);
        }

        actualizandoSegundoEntrenador = false;
    }

    private void actualizarPrimerEntrenador() {
        if (actualizandoPrimerEntrenador) return;
        actualizandoPrimerEntrenador = true;

        EntrenadorDTO segundo = (EntrenadorDTO) cbSegundoEntrenador.getSelectedItem();
        EntrenadorDTO seleccionadoPrimer = (EntrenadorDTO) cbPrimerEntrenador.getSelectedItem();

        cbPrimerEntrenador.removeAllItems();

        // Agregar entrenador actualmente asignado al equipo si existe
        if (equipoSeleccionado != null) {
            EntrenadorDTO primerActual = metodosEntrenador.getEntrenadorById(equipoSeleccionado.getPrimerEntrenador());
            if (primerActual != null && (segundo == null || primerActual.getId_empleado() != segundo.getId_empleado())) {
                cbPrimerEntrenador.addItem(primerActual);
            }
        }

        // Agregar todos los entrenadores disponibles que no sean el segundo entrenador
        for (EntrenadorDTO e : entrenadoresDisponibles) {
            if (segundo == null || e.getId_empleado() != segundo.getId_empleado()) {
                cbPrimerEntrenador.addItem(e);
            }
        }

        // Mantener selección anterior si sigue siendo válida
        if (seleccionadoPrimer != null && (segundo == null || segundo.getId_empleado() != seleccionadoPrimer.getId_empleado())) {
            cbPrimerEntrenador.setSelectedItem(seleccionadoPrimer);
        }

        actualizandoPrimerEntrenador = false;
    }


}
