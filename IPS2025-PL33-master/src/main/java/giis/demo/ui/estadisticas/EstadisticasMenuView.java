package giis.demo.ui.estadisticas;

import javax.swing.*;
import java.awt.*;

public class EstadisticasMenuView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JButton btnEstadisticasClub;
    private JButton btnEstadisticasAnuales;

    public EstadisticasMenuView() {
        setTitle("Menú de Estadísticas");
        setSize(420, 310);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Fondo general
        getContentPane().setBackground(Color.WHITE);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 20, 20));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Botón 1
        btnEstadisticasClub = new JButton("Estadísticas Mensuales");
        estilizarBoton(btnEstadisticasClub);

        // Botón 2
        btnEstadisticasAnuales = new JButton("Estadísticas Anuales");
        estilizarBoton(btnEstadisticasAnuales);

        panel.add(btnEstadisticasClub);
        panel.add(btnEstadisticasAnuales);

        add(panel);
    }

    // Método para que los botones queden elegantes y consistentes
    private void estilizarBoton(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 17));
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(52, 152, 219)); // azul suave moderno
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(41, 128, 185), 2),
                BorderFactory.createEmptyBorder(12, 20, 12, 20)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto hover
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(41, 128, 185));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(52, 152, 219));
            }
        });
    }

    public JButton getBtnEstadisticasClub() { return btnEstadisticasClub; }
    public JButton getBtnEstadisticasAnuales() { return btnEstadisticasAnuales; }
}
