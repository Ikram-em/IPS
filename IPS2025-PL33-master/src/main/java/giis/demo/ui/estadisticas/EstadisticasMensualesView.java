package giis.demo.ui.estadisticas;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.Date;

public class EstadisticasMensualesView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel panelGrafico;
    private JButton btnGenerar;
    private JDateChooser fechaInicio;
    private JDateChooser fechaFin;
    private JLabel lblBalance;

    public EstadisticasMensualesView() {
        setTitle("Estadísticas Mensuales del Club");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(Color.WHITE);

        // -------------------------------
        // Top panel: fechas y botón
        // -------------------------------
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        topPanel.setBackground(Color.WHITE);

        // Fecha inicio
        JLabel lblInicio = new JLabel("Fecha inicio:");
        lblInicio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        topPanel.add(lblInicio);

        fechaInicio = new JDateChooser(new Date(), "yyyy-MM-dd");
        fechaInicio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        topPanel.add(fechaInicio);

        // Fecha fin
        JLabel lblFin = new JLabel("Fecha fin:");
        lblFin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        topPanel.add(lblFin);

        fechaFin = new JDateChooser(new Date(), "yyyy-MM-dd");
        fechaFin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        topPanel.add(fechaFin);

        // Validación automática: fechaFin nunca antes de fechaInicio
        PropertyChangeListener fechaListener = evt -> {
            if ("date".equals(evt.getPropertyName())) {
                Date inicio = fechaInicio.getDate();
                Date fin = fechaFin.getDate();
                if (inicio != null && fin != null && fin.before(inicio)) {
                    fechaFin.setDate(inicio);
                }
            }
        };
        fechaInicio.getDateEditor().addPropertyChangeListener(fechaListener);
        fechaFin.getDateEditor().addPropertyChangeListener(fechaListener);

        // Botón generar
        btnGenerar = new JButton("Generar estadísticas");
        btnGenerar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGenerar.setBackground(new Color(60, 179, 113));
        btnGenerar.setForeground(Color.WHITE);
        btnGenerar.setFocusPainted(false);
        topPanel.add(btnGenerar);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // -------------------------------
        // Panel central: gráfico
        // -------------------------------
        panelGrafico = new JPanel(new BorderLayout());
        panelGrafico.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        JLabel lblMensajeInicial = new JLabel("Pincha para ver las estadísticas");
        lblMensajeInicial.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensajeInicial.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        lblMensajeInicial.setForeground(Color.GRAY);
        panelGrafico.add(lblMensajeInicial, BorderLayout.CENTER);

        mainPanel.add(panelGrafico, BorderLayout.CENTER);

        // -------------------------------
        // Panel sur: balance
        // -------------------------------
        JPanel panelBalance = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panelBalance.setBackground(Color.WHITE);
        lblBalance = new JLabel("Balance: 0 €");
        lblBalance.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblBalance.setForeground(new Color(30, 144, 255));
        panelBalance.add(lblBalance);

        mainPanel.add(panelBalance, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    // -------------------------------
    // Getters
    // -------------------------------
    public JButton getBtnGenerar() {
        return btnGenerar;
    }

    public JPanel getPanelGrafico() {
        return panelGrafico;
    }

    public Date getFechaInicio() {
        return fechaInicio.getDate();
    }

    public Date getFechaFin() {
        return fechaFin.getDate();
    }

    public void setBalanceText(String text) {
        lblBalance.setText(text);
    }
}
