package giis.demo.tkrun.estadisticasPartido;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.estadisticasPartido.EventoPartidoDAO.EventoPartidoDTO;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.medico.AddLesionController;
import giis.demo.tkrun.partidos.Partido;
import giis.demo.tkrun.partidos.PartidoDAO;
import giis.demo.tkrun.teammanagement.MetodosEquipo;
import giis.demo.tkrun.teammanagement.MetodosJugador;
import giis.demo.tkrun.teammanagement.PersonDTO;
import giis.demo.ui.estadisticasPartido.EstadisticasPartidoView;
import giis.demo.ui.medico.AddLesionView;

public class EstadisticasPartidoController extends AbstractController {

    private EstadisticasPartidoView view;
    private PartidoDAO partidoDAO = new PartidoDAO();
    private EventoPartidoDAO eventoDAO = new EventoPartidoDAO();

    public EstadisticasPartidoController(EstadisticasPartidoView view, AuditService audit) {
        super(audit);
        this.view = view;
        initController();
    }

    private void initController() {
        cargarPartidos();
        addListeners();

        if (view.getCbPartido().getItemCount() > 0) {
            view.getCbPartido().setSelectedIndex(0);
            actualizarPartidoSeleccionado();
        }
    }


private void cargarPartidos() {
    List<Partido> partidos = partidoDAO.obtenerTodos();
    JComboBox<Partido> cb = view.getCbPartido();
    cb.removeAllItems();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
    LocalDate hoy = LocalDate.now();

    for (Partido p : partidos) {
        LocalDate fechaPartido = LocalDate.parse(p.getFecha(), formatter);
        if (fechaPartido.isBefore(hoy)) { // Solo partidos anteriores a hoy
            cb.addItem(p);
        }
    }
}


    private void addListeners() {

        view.getCbPartido().addActionListener(e -> actualizarPartidoSeleccionado());

        addLoggedAction(view.btnAddGol, "Añadir goles", () -> addGoles());

        addLoggedAction(view.btnAddTarjeta, "Añadir tarjeta", () -> addTarjeta());

        addLoggedAction(view.btnGuardarMarcador, "Guardar marcador", () -> guardarMarcador());

        addLoggedAction(view.btnAddLesion, "Abrir ventana Añadir Lesión",
                () -> new AddLesionController(new AddLesionView(), audit));
        
        addLoggedAction(view.btnEliminarGol, "Eliminar gol", () -> eliminarGol());
        addLoggedAction(view.btnEliminarTarjeta, "Eliminar tarjeta", () -> eliminarTarjeta());

    }


    private void actualizarPartidoSeleccionado() {
        Partido partido = (Partido) view.getCbPartido().getSelectedItem();
        if (partido == null) return;

        view.getTxtLocal().setText(partido.getEquipoLocal());
        view.getTxtVisitante().setText(partido.getEquipoVisitante());

        cargarJugadoresEquipoLocal(partido.getEquipoLocal());
        inicializarMarcador();
        cargarGoles();
        cargarTarjetas();
        cargarLesiones();
    }

    private void cargarJugadoresEquipoLocal(String equipoLocal) {
        MetodosEquipo me = new MetodosEquipo();
        int idEquipo = me.getTeamIdByName(equipoLocal);
        MetodosJugador mj = new MetodosJugador();
        List<PersonDTO> jugadores = mj.getPlayersOfTeam(idEquipo);
        DefaultComboBoxModel<PersonDTO> modelGol = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<PersonDTO> modelTar = new DefaultComboBoxModel<>();
        for (PersonDTO j : jugadores) {
            modelGol.addElement(j);
            modelTar.addElement(j);
        }
        view.cbJugadorGol.setModel(modelGol);
        view.cbJugadorTarjeta.setModel(modelTar);
    }

    private void addGoles() {
        PersonDTO jugador = (PersonDTO) view.cbJugadorGol.getSelectedItem();
        int goles = (Integer) view.spnNumGoles.getValue();
        int idPartido = getIdPartidoSeleccionado();
        if (jugador == null || goles <= 0 || idPartido == -1) return;

        int[] marcador = partidoDAO.getGolesPartido(idPartido);
        int maxGoles = marcador[0];
        int golesActuales = eventoDAO.getGolesPartidoConId(idPartido).size();

        if (golesActuales + goles > maxGoles) {
            JOptionPane.showMessageDialog(view,
                "El total de goles NO PUEDE superar " + maxGoles + ".\nGoles registrados: " + golesActuales,
                "Límite de goles excedido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i = 0; i < goles; i++) {
            eventoDAO.insertarEvento(idPartido, jugador.getId(), "Gol");
        }

        cargarGoles();
    }


    private void addTarjeta() {
        PersonDTO jugador = (PersonDTO) view.cbJugadorTarjeta.getSelectedItem();
        int idPartido = getIdPartidoSeleccionado();
        if (jugador == null || idPartido == -1) return;

        if (view.rbAmarilla.isSelected()) {
            if (eventoDAO.countAmarillas(idPartido, jugador.getId()) >= 2) {
                JOptionPane.showMessageDialog(view, "Este jugador ya tiene 2 amarillas.",
                        "No permitido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            eventoDAO.insertarEvento(idPartido, jugador.getId(), "TarjetaAmarilla");

        } else if (view.rbRoja.isSelected()) {
            if (eventoDAO.countRojas(idPartido, jugador.getId()) >= 1) {
                JOptionPane.showMessageDialog(view, "Este jugador ya tiene una tarjeta roja.",
                        "No permitido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            eventoDAO.insertarEvento(idPartido, jugador.getId(), "TarjetaRoja");

        } else {
            JOptionPane.showMessageDialog(view, "Debes seleccionar Amarilla o Roja.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        cargarTarjetas();
    }


    private void guardarMarcador() {
        int idPartido = getIdPartidoSeleccionado();
        if (idPartido == -1) return;

        int golesLocal = Integer.parseInt(view.getTxtLocal().getText());
        int golesVisitante = Integer.parseInt(view.getTxtVisitante().getText());
        
        if(golesLocal < 0 || golesVisitante < 0) {
        	JOptionPane.showMessageDialog(view, "No peude haber goles negativos",
                    "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        partidoDAO.actualizarMarcador(idPartido, golesLocal, golesVisitante);

        JOptionPane.showMessageDialog(view, "Marcador actualizado correctamente.");

        inicializarMarcador();
    }


    private int getIdPartidoSeleccionado() {
        Partido p = (Partido) view.getCbPartido().getSelectedItem();
        return p != null ? p.getIdPartido() : -1;
    }

    private void inicializarMarcador() {
        int idPartido = getIdPartidoSeleccionado();
        if (idPartido == -1) {
            view.getTxtLocal().setText("0");
            view.getTxtVisitante().setText("0");
            return;
        }
        int[] goles = partidoDAO.getGolesPartido(idPartido);
        view.getTxtLocal().setText(String.valueOf(goles[0]));
        view.getTxtVisitante().setText(String.valueOf(goles[1]));
    }

    private void cargarGoles() {
        int idPartido = getIdPartidoSeleccionado();
        DefaultListModel<EventoPartidoDTO> model = new DefaultListModel<>();
        for (EventoPartidoDTO e : eventoDAO.getGolesPartidoConId(idPartido)) {
            model.addElement(e);
        }
        view.getListaGoles().setModel(model);
    }

    private void cargarTarjetas() {
        int idPartido = getIdPartidoSeleccionado();
        DefaultListModel<EventoPartidoDTO> model = new DefaultListModel<>();
        for (EventoPartidoDTO e : eventoDAO.getTarjetasPartidoConId(idPartido)) {
            model.addElement(e);
        }
        view.getListaTarjetas().setModel(model);
    }

    private void cargarLesiones() {
        int idPartido = getIdPartidoSeleccionado();
        DefaultListModel<EventoPartidoDTO> model = new DefaultListModel<>();
        for (EventoPartidoDTO e : eventoDAO.getLesionesPartidoConId(idPartido)) {
            model.addElement(e);
        }
        view.getListaLesiones().setModel(model);
    }
    
    private void eliminarGol() {
        EventoPartidoDTO evento = view.getListaGoles().getSelectedValue();
        if (evento == null) {
            JOptionPane.showMessageDialog(view, "Selecciona un gol para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        eventoDAO.eliminarEvento(evento.idEvento);
        cargarGoles();
    }
    
    private void eliminarTarjeta() {
        EventoPartidoDTO evento = view.getListaTarjetas().getSelectedValue();
        if (evento == null) {
            JOptionPane.showMessageDialog(view, "Selecciona una tarjeta para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        eventoDAO.eliminarEvento(evento.idEvento);
        cargarTarjetas();
    }

}
