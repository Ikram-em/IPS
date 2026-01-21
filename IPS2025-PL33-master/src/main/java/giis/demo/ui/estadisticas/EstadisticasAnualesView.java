package giis.demo.ui.estadisticas;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class EstadisticasAnualesView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel panelGrafico;
    private JButton btnGenerar;
    private JTextField txtAno;
    private JLabel lblBalance;

    public EstadisticasAnualesView() {
        setTitle("Estadísticas Anuales del Club");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(Color.WHITE);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));

        JLabel lblAno = new JLabel("Año:");
        lblAno.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        topPanel.add(lblAno);

        txtAno = new JTextField(6);
        txtAno.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtAno.setText(String.valueOf(java.time.LocalDate.now().getYear()));

        // 👉 FILTRO: SOLO NÚMEROS
        ((javax.swing.text.AbstractDocument) txtAno.getDocument())
                .setDocumentFilter(new DocumentFilter() {
                    @Override
                    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                            throws BadLocationException {
                        if (text.matches("\\d*")) {
                            super.replace(fb, offset, length, text, attrs);
                        }
                    }

                    @Override
                    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                            throws BadLocationException {
                        if (text.matches("\\d*")) {
                            super.insertString(fb, offset, text, attr);
                        }
                    }
                });

        topPanel.add(txtAno);

        btnGenerar = new JButton("Generar estadísticas");
        btnGenerar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGenerar.setBackground(new Color(60, 179, 113));
        btnGenerar.setForeground(Color.WHITE);
        btnGenerar.setFocusPainted(false);
        topPanel.add(btnGenerar);

        lblBalance = new JLabel("Balance: 0 €");
        lblBalance.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblBalance.setForeground(new Color(30, 144, 255));
        topPanel.add(lblBalance);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        panelGrafico = new JPanel(new BorderLayout());
        panelGrafico.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        JLabel lblMensajeInicial = new JLabel("Introduce un año y pincha generar para ver las estadísticas");
        lblMensajeInicial.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensajeInicial.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        lblMensajeInicial.setForeground(Color.GRAY);
        panelGrafico.add(lblMensajeInicial, BorderLayout.CENTER);

        mainPanel.add(panelGrafico, BorderLayout.CENTER);

        getContentPane().setBackground(Color.WHITE);
        setContentPane(mainPanel);
    }

    public JButton getBtnGenerar() {
        return btnGenerar;
    }

    public JPanel getPanelGrafico() {
        return panelGrafico;
    }

    public String getAno() {
        return txtAno.getText();
    }

    public void setBalanceText(String text) {
        lblBalance.setText(text);
    }
}

