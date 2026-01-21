package giis.demo.ui.reserva;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import giis.demo.tkrun.instalacion.InstalacionDTO;
import giis.demo.util.Util;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.toedter.calendar.JDateChooser;

public class ReservaView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JLabel lblEstadoDisponibilidad;
    private JTable tablaOcupacionResumen;
    private JScrollPane scrollPaneOcupacion;

    private JButton btnBuscarDisponibilidad;
    private JButton btnReservar;

    private JComboBox<InstalacionDTO> cmbInstalacion;
    private JDateChooser dateChooser;
    public JTextField txtNombreUsuario;
    public JTextField txtNumTarjeta;
    public JTextField txtDireccion;
    public JTextField txtTelefono;
    public JTextField txtEmail;

    private JComboBox<String> cmbHoraInicioReserva;
    private JComboBox<String> cmbHorasDuracion;
    private JComboBox<String> cmbMinutosDuracion;

    public ReservaView() {
        setTitle("Gestión de Reservas Externas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(850, 700);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        JPanel panelFiltros = new JPanel();
        panelFiltros.setLayout(new GridLayout(4, 1, 0, 10));
        contentPane.add(panelFiltros, BorderLayout.NORTH);

        // FILA 1: Instalación y fecha
        JPanel panelFila1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        panelFila1.setBorder(BorderFactory.createTitledBorder("Seleccione Instalación y Fecha"));
        panelFila1.add(new JLabel("Instalación:"));
        panelFila1.add(getInstalacionSelector());

        panelFila1.add(new JLabel("Fecha:"));
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setDate(new Date());
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        dateChooser.setMinSelectableDate(cal.getTime());
        dateChooser.setPreferredSize(new Dimension(120, 25));
        panelFila1.add(dateChooser);

        btnBuscarDisponibilidad = new JButton("Comprobar Estado");
        panelFila1.add(btnBuscarDisponibilidad);
        panelFiltros.add(panelFila1);

        // FILA 2: Datos del solicitante
        JPanel panelFila2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        panelFila2.setBorder(BorderFactory.createTitledBorder("Datos del Solicitante"));
        panelFila2.add(new JLabel("Nombre *:"));
        txtNombreUsuario = new JTextField(15);
        panelFila2.add(txtNombreUsuario);

        panelFila2.add(new JLabel("Tarjeta Bancaria*:"));
        txtNumTarjeta = new JTextField(16);
        panelFila2.add(txtNumTarjeta);
        panelFiltros.add(panelFila2);

        // FILA 3: Datos para factura
        JPanel panelFila3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        panelFila3.setBorder(BorderFactory.createTitledBorder("Datos para la Factura:"));
        panelFila3.add(new JLabel("Dirección*:"));
        txtDireccion = new JTextField(15);
        panelFila3.add(txtDireccion);

        panelFila3.add(new JLabel("Teléfono de contacto*:"));
        txtTelefono = new JTextField(10);
        panelFila3.add(txtTelefono);

        panelFila3.add(new JLabel("Email*:"));
        txtEmail = new JTextField(15);
        panelFila3.add(txtEmail);

        panelFiltros.add(panelFila3);

        // FILA 4: Definición de reserva
        JPanel panelFila4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        panelFila4.setBorder(BorderFactory.createTitledBorder("Definición de Reserva"));
        panelFila4.add(new JLabel("Hora Inicio:"));
        cmbHoraInicioReserva = new JComboBox<>(crearHorasReservables());
        panelFila4.add(cmbHoraInicioReserva);

        panelFila4.add(new JLabel("Duración:"));
        String[] horas = { "0h", "1h", "2h", "3h", "4h", "5h", "6h", "7h", "8h", "9h", "10h", "11h", "12h", "13h",
                "14h", "15h" };
        String[] minutos = { "00 min", "15 min", "30 min", "45 min" };
        cmbHorasDuracion = new JComboBox<>(horas);
        cmbMinutosDuracion = new JComboBox<>(minutos);
        panelFila4.add(cmbHorasDuracion);
        panelFila4.add(cmbMinutosDuracion);
        panelFiltros.add(panelFila4);

        // Panel central: mensaje + tabla
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        lblEstadoDisponibilidad = new JLabel("Pulse 'Comprobar Estado' para empezar.", SwingConstants.CENTER);
        lblEstadoDisponibilidad.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblEstadoDisponibilidad.setForeground(new Color(0, 102, 204));
        lblEstadoDisponibilidad.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(lblEstadoDisponibilidad);
        panelCentral.add(Box.createVerticalStrut(10));

        tablaOcupacionResumen = new JTable();
        tablaOcupacionResumen.setEnabled(false);
        String[] headers = { "Inicio Ocupado", "Fin Ocupado", "Tipo" };
        tablaOcupacionResumen.setModel(new DefaultTableModel(new Object[0][0], headers));
        scrollPaneOcupacion = new JScrollPane(tablaOcupacionResumen);
        scrollPaneOcupacion
                .setBorder(BorderFactory.createTitledBorder("Franjas Ocupadas (90 min de margen para empleados)"));
        scrollPaneOcupacion.setPreferredSize(new Dimension(780, 180));
        scrollPaneOcupacion.setVisible(false);
        panelCentral.add(scrollPaneOcupacion);

        contentPane.add(panelCentral, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        btnReservar = new JButton("Reservar Ahora (50€/h)");
        btnReservar.setBackground(new Color(0, 153, 76));
        btnReservar.setForeground(Color.WHITE);
        btnReservar.setFont(new Font("SansSerif", Font.BOLD, 14));
        panelInferior.add(btnReservar);
        contentPane.add(panelInferior, BorderLayout.SOUTH);
    }

    // ----------------- Getters -----------------
    public JComboBox<InstalacionDTO> getInstalacionSelector() {
        if (cmbInstalacion == null) {
            cmbInstalacion = new JComboBox<>();
        }
        return cmbInstalacion;
    }

    public JButton getBtnBuscarDisponibilidad() {
        return btnBuscarDisponibilidad;
    }

    public JButton getBtnReservar() {
        return btnReservar;
    }

    public String getFechaSeleccionadaString() {
        return dateChooser.getDate() == null ? "" : Util.dateToIsoString(dateChooser.getDate());
    }

    public String getNombreUsuario() {
        return txtNombreUsuario.getText();
    }

    public String getNumTarjeta() {
        return txtNumTarjeta.getText();
    }

    public String getDireccion() { return txtDireccion.getText(); }

    public String getTelefono() { return txtTelefono.getText(); }

    public String getEmail() { return txtEmail.getText(); }

    public String getHoraInicioReservaSeleccionada() {
        return (String) cmbHoraInicioReserva.getSelectedItem();
    }

    public String getDuracionFormateada() {
        return cmbHorasDuracion.getSelectedItem() + " " + cmbMinutosDuracion.getSelectedItem();
    }

    public int getDuracionEnMinutos() {
        int horas = Integer.parseInt(((String) cmbHorasDuracion.getSelectedItem()).replace("h", "").trim());
        int minutos = Integer.parseInt(((String) cmbMinutosDuracion.getSelectedItem()).replace("min", "").trim());
        return horas * 60 + minutos;
    }

    public double getDuracionEnHoras() {
        return getDuracionEnMinutos() / 60.0;
    }

    public JTable getTablaOcupacionResumen() {
        return tablaOcupacionResumen;
    }

    public JScrollPane getScrollPaneOcupacion() {
        return scrollPaneOcupacion;
    }

    public void setEstadoDisponibilidadMensaje(String mensaje, Color color) {
        lblEstadoDisponibilidad.setText(mensaje);
        lblEstadoDisponibilidad.setForeground(color);
    }

    private String[] crearHorasReservables() {
        List<String> horas = new ArrayList<>();
        for (int h = 8; h <= 21; h++) {
            horas.add(String.format("%02d:00", h));
            horas.add(String.format("%02d:30", h));
        }
        return horas.toArray(new String[0]);
    }
    
   
}
