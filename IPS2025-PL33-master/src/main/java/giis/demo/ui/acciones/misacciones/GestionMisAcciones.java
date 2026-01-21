package giis.demo.ui.acciones.misacciones;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GestionMisAcciones extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btVenta;
    private JLabel lbTitulo;
    private JButton btComprar;

    public GestionMisAcciones() {
        setTitle("Gestión de Mis Acciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setMinimumSize(getSize());

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        // Añadir componentes usando lazy getters
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Título ---
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(getLbTitulo(), gbc);

        // --- Botón ---
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(40, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(getBtVenta(), gbc);
        
        gbc.gridy = 3; // Nueva fila
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(getBtComprar(), gbc);
    }

    // --- Lazy Getters ---

    private JLabel getLbTitulo() {
        if (lbTitulo == null) {
            lbTitulo = new JLabel("MIS ACCIONES", SwingConstants.CENTER);
            lbTitulo.setFont(new Font("Arial", Font.BOLD, 30));
            lbTitulo.setForeground(new Color(30, 60, 120));
        }
        return lbTitulo;
    }

    public JButton getBtVenta() {
        if (btVenta == null) {
            btVenta = new JButton("PONER ACCIONES EN VENTA");
            btVenta.setFont(new Font("Arial", Font.BOLD, 15));
            btVenta.setBackground(new Color(60, 130, 200));
            btVenta.setForeground(Color.WHITE);
            btVenta.setFocusPainted(false);
            btVenta.setPreferredSize(new Dimension(250, 45));
        }
        return btVenta;
    }
    
	public JButton getBtComprar() {
        if (btComprar == null) {
            btComprar = new JButton("COMPRAR ACCIONES DE OTROS");
            btComprar.setFont(new Font("Arial", Font.BOLD, 15));
            btComprar.setBackground(new Color(60, 180, 120));
            btComprar.setForeground(Color.WHITE);
            btComprar.setFocusPainted(false);
            btComprar.setPreferredSize(new Dimension(250, 45));

        }
        return btComprar;
    }


}
