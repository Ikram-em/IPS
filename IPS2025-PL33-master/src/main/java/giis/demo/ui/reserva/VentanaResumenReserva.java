package giis.demo.ui.reserva;

import javax.swing.*;
import java.awt.*;
import giis.demo.tkrun.reserva.ReservaDTO;
import giis.demo.util.Util;
import giis.demo.tkrun.factura.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Desktop;
import java.io.File;

public class VentanaResumenReserva extends JDialog {
    private static final long serialVersionUID = 1L;
    private JButton btnVolverInicio;
    private JButton btnVerFactura;

    public VentanaResumenReserva(JFrame parent, ReservaDTO reserva, ReservaView view) {
        super(parent, "Resumen de Reserva", true); // modal, centrada sobre parent
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel("Reserva realizada con éxito", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(30, 60, 90));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(20));

        JTextArea txtResumen = new JTextArea();
        txtResumen.setEditable(false);
        txtResumen.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtResumen.setBackground(new Color(230, 230, 250));
        txtResumen.setForeground(new Color(30, 30, 60));
        txtResumen.setText(generarResumen(reserva));
        txtResumen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtResumen.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtResumen.setLineWrap(true);
        txtResumen.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(txtResumen);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        scroll.setPreferredSize(new Dimension(400, 200));
        panel.add(scroll);
        panel.add(Box.createVerticalStrut(20));

        add(panel, BorderLayout.CENTER);

        // Panel de botones
        btnVolverInicio = new JButton("Volver al Inicio");
        btnVolverInicio.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnVolverInicio.setBackground(new Color(30, 60, 90));
        btnVolverInicio.setForeground(Color.WHITE);
        btnVolverInicio.setFocusPainted(false);
        btnVolverInicio.addActionListener(e -> dispose());

        btnVerFactura = new JButton("Ver Factura");
        btnVerFactura.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnVerFactura.setBackground(new Color(0, 102, 204));
        btnVerFactura.setForeground(Color.WHITE);
        btnVerFactura.setFocusPainted(false);
        btnVerFactura.addActionListener(e -> generarFactura(reserva, view));

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(panel.getBackground());
        panelBoton.add(btnVolverInicio);
        panelBoton.add(btnVerFactura);
        add(panelBoton, BorderLayout.SOUTH);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    private String generarResumen(ReservaDTO r) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre usuario: ").append(r.getNombreUsuario()).append("\n");
        sb.append("Instalación: ").append(r.getNombre_instalacion()).append("\n");
        sb.append("Fecha: ").append(Util.dateToIsoString(r.getFecha())).append("\n");
        sb.append("Hora inicio: ").append(Util.timeToTimeString(r.getHoraInicio())).append("\n");
        sb.append("Hora fin: ").append(Util.timeToTimeString(r.getHoraFin())).append("\n");
        sb.append("Precio total: ").append(String.format("%.2f €", r.getPrecioTotal())).append("\n");
        return sb.toString();
    }

    private void generarFactura(ReservaDTO reserva, ReservaView view) {
        // Tomar los datos de los campos de la vista
        FacturaDatos cliente = new FacturaDatos(
                view.getNombreUsuario(),
                view.getDireccion(),
                view.getTelefono(),
                view.getEmail()
        );

        List<FacturaItem> items = new ArrayList<>();
        // Añadir la reserva como único item de la factura
        items.add(new FacturaItem("Reserva instalación " + reserva.getNombre_instalacion(),
                reserva.getPrecioTotal(), 1));

        FacturaPDFGenerator generador = new FacturaPDFGenerator();
        String rutaPDF = generador.generarFacturaPDF( cliente, items); // "001" puede ser dinámico

        // Abrir PDF automáticamente si el sistema lo permite
        try {
            Desktop.getDesktop().open(new File(rutaPDF));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "No se pudo abrir la factura automáticamente. Se generó en: " + rutaPDF,
                    "Factura Generada", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
