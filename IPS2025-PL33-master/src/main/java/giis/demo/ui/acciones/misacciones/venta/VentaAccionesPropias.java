package giis.demo.ui.acciones.misacciones.venta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class VentaAccionesPropias extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lbVenta;
    private JPanel pnListas;
    private JScrollPane scMisAcciones;
    private JPanel pnMisAcciones;
    private JScrollPane scEnVenta;
    private JPanel pnEnVenta;


    /**
     * Create the frame.
     */
	public VentaAccionesPropias() {
        setTitle("Venta de Acciones Propias");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        setLocationRelativeTo(null);
        setMinimumSize(getSize());

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        contentPane.add(getLbVenta(), BorderLayout.NORTH);
        contentPane.add(getPnListas(), BorderLayout.CENTER);

    }

    private JLabel getLbVenta() {
        if (lbVenta == null) {
            lbVenta = new JLabel("GESTIONAR MIS ACCIONES");
            lbVenta.setHorizontalAlignment(SwingConstants.CENTER);
            lbVenta.setFont(new Font("Arial", Font.BOLD, 20));
        }
        return lbVenta;
    }

    private JPanel getPnListas() {
        if (pnListas == null) {
            pnListas = new JPanel();
            pnListas.setBackground(Color.WHITE);
            pnListas.setLayout(new GridLayout(1, 2, 20, 10));
            pnListas.add(getScMisAcciones());
            pnListas.add(getScEnVenta());
        }
        return pnListas;
    }

    private JScrollPane getScMisAcciones() {
        if (scMisAcciones == null) {
            scMisAcciones = new JScrollPane(getPnMisAcciones());
            scMisAcciones.setBorder(new TitledBorder("Mis Acciones Disponibles"));
            scMisAcciones.getViewport().setBackground(Color.WHITE);
        }
        return scMisAcciones;
    }

	public JPanel getPnMisAcciones() {
        if (pnMisAcciones == null) {
            pnMisAcciones = new JPanel(new GridLayout(0, 1, 5, 5));
            pnMisAcciones.setBackground(Color.WHITE);
        }
        return pnMisAcciones;
    }

    private JScrollPane getScEnVenta() {
        if (scEnVenta == null) {
            scEnVenta = new JScrollPane(getPnEnVenta());
            scEnVenta.setBorder(new TitledBorder("Mis Acciones en Venta"));
            scEnVenta.getViewport().setBackground(Color.WHITE);
        }
        return scEnVenta;
    }

	public JPanel getPnEnVenta() {
        if (pnEnVenta == null) {
            pnEnVenta = new JPanel(new GridLayout(0, 1, 5, 5));
            pnEnVenta.setBackground(Color.WHITE);
        }
        return pnEnVenta;
    }



}
