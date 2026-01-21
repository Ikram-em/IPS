package giis.demo.ui.abonos;

import javax.swing.*;
import java.awt.*;

public class ResumenCompraView extends JFrame {

    private static final long serialVersionUID = 1L;

    public ResumenCompraView(String cliente, String tribuna, String seccion, int asiento, double precioTotal) {
        setTitle("Resumen de Compra");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;


        JLabel lblTitle = new JLabel("Resumen de su Abono");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);
        gbc.gridwidth = 1;

        panel.add(new JLabel("Cliente:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(cliente), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Tribuna:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(tribuna), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Sección:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(seccion), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Asiento:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(String.valueOf(asiento)), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Precio Total:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(String.format("%.2f €", precioTotal)), gbc);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnCerrar, gbc);

        add(panel);
    }
}
