package giis.demo.ui.estadisticasPartido;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import giis.demo.tkrun.estadisticasPartido.EventoPartidoDAO.EventoPartidoDTO;
import giis.demo.tkrun.partidos.Partido;
import giis.demo.tkrun.teammanagement.PersonDTO;

public class EstadisticasPartidoView extends JFrame {

    private static final long serialVersionUID = 1L;

    private JComboBox<Partido> cbPartido;
    private JTextField txtLocal;
    private JTextField txtVisitante;

    private JList<EventoPartidoDTO> listaGoles;
    private JList<EventoPartidoDTO> listaTarjetas;
    private JList<EventoPartidoDTO> listaLesiones;

    public JButton btnAddGol;
    public JButton btnAddTarjeta;
    public JButton btnGuardarMarcador;
    public JButton btnAddLesion;

    public JComboBox<PersonDTO> cbJugadorGol;
    public JComboBox<PersonDTO> cbJugadorTarjeta;
    public JSpinner spnNumGoles;
    public JRadioButton rbAmarilla;
    public JRadioButton rbRoja;
    public JButton btnEliminarGol;
    public JButton btnEliminarTarjeta;



    public EstadisticasPartidoView() {
        setTitle("Estadísticas de Partido");
        setSize(1500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Panel superior
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        cbPartido = new JComboBox<>();
        panelSuperior.add(new JLabel("SELECCIÓN DE PARTIDO"));
        panelSuperior.add(cbPartido);

        txtLocal = new JTextField(5);
        txtVisitante = new JTextField(5);
        panelSuperior.add(txtLocal);
        panelSuperior.add(new JLabel(" - "));
        panelSuperior.add(txtVisitante);

        btnGuardarMarcador = new JButton("Guardar Marcador");
        panelSuperior.add(btnGuardarMarcador);

        getContentPane().add(panelSuperior, BorderLayout.NORTH);

        // Panel central con tres columnas
        JPanel panelColumnas = new JPanel(new GridLayout(1, 3, 20, 0));
        panelColumnas.add(crearPanelGoles());
        panelColumnas.add(crearPanelTarjetas());
        panelColumnas.add(crearPanelLesiones());
        getContentPane().add(panelColumnas, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel crearPanelGoles() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("GOLES"));

        listaGoles = new JList<>(new DefaultListModel<>());
        panel.add(new JScrollPane(listaGoles), BorderLayout.CENTER);

        JPanel sub = new JPanel(new GridLayout(4, 1));
        cbJugadorGol = new JComboBox<>();
        sub.add(cbJugadorGol);

        spnNumGoles = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        sub.add(spnNumGoles);

        btnAddGol = new JButton("Añadir Goles");
        sub.add(btnAddGol);

        btnEliminarGol = new JButton("Eliminar Gol");
        sub.add(btnEliminarGol);

        panel.add(sub, BorderLayout.SOUTH);
        return panel;
    }


    private JPanel crearPanelTarjetas() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("TARJETAS"));

        listaTarjetas = new JList<>(new DefaultListModel<>());
        panel.add(new JScrollPane(listaTarjetas), BorderLayout.CENTER);

        JPanel sub = new JPanel(new GridLayout(4, 1));
        cbJugadorTarjeta = new JComboBox<>();
        sub.add(cbJugadorTarjeta);

        JPanel tipoTarjeta = new JPanel();
        rbAmarilla = new JRadioButton("Amarilla");
        rbRoja = new JRadioButton("Roja");
        ButtonGroup group = new ButtonGroup();
        group.add(rbAmarilla);
        group.add(rbRoja);
        tipoTarjeta.add(rbAmarilla);
        tipoTarjeta.add(rbRoja);
        sub.add(tipoTarjeta);

        btnAddTarjeta = new JButton("Añadir Tarjeta");
        sub.add(btnAddTarjeta);

        btnEliminarTarjeta = new JButton("Eliminar Tarjeta");
        sub.add(btnEliminarTarjeta);

        panel.add(sub, BorderLayout.SOUTH);
        return panel;
    }


    private JPanel crearPanelLesiones() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("LESIONES"));

        listaLesiones = new JList<>(new DefaultListModel<>());
        panel.add(new JScrollPane(listaLesiones), BorderLayout.CENTER);

        btnAddLesion = new JButton("Añadir Lesión");
        panel.add(btnAddLesion, BorderLayout.SOUTH);

        return panel;
    }

    // Getters
    public JComboBox<Partido> getCbPartido() { return cbPartido; }
    public JList<EventoPartidoDTO> getListaGoles() { return listaGoles; }
    public JList<EventoPartidoDTO> getListaTarjetas() { return listaTarjetas; }
    public JList<EventoPartidoDTO> getListaLesiones() { return listaLesiones; }
    public JTextField getTxtLocal() { return txtLocal; }
    public JTextField getTxtVisitante() { return txtVisitante; }
}
