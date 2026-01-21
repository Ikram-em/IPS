package giis.demo.ui.noticias;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class GestionNoticias extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JLabel lbTitulo;
    private JScrollPane scrollNoticias;
    private JPanel pnListaNoticias;
    private JButton btCrear;


    public GestionNoticias() {
        setTitle("Gestión de Noticias");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 625, 600);
        setMinimumSize(getSize());
        setContentPane(getContentPaneLazy());
        setLocationRelativeTo(null);
        setVisible(true);
        setMinimumSize(getSize());
    }

    private JPanel getContentPaneLazy() {
        if (contentPane == null) {
            contentPane = new JPanel(new BorderLayout(10,10));
            contentPane.setBackground(Color.WHITE);

            contentPane.add(getLbTitulo(), BorderLayout.NORTH);
            contentPane.add(getScrollNoticias(), BorderLayout.CENTER);
            contentPane.add(getBtCrear(), BorderLayout.SOUTH);
        }
        return contentPane;
    }

    private JLabel getLbTitulo() {
        if (lbTitulo == null) {
            lbTitulo = new JLabel("NOTICIAS", SwingConstants.CENTER);
            lbTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        }
        return lbTitulo;
    }

    private JScrollPane getScrollNoticias() {
        if (scrollNoticias == null) {
            scrollNoticias = new JScrollPane(getPnListaNoticias());
            scrollNoticias.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        }
        return scrollNoticias;
    }

	public JPanel getPnListaNoticias() {
        if (pnListaNoticias == null) {
            pnListaNoticias = new JPanel();
            pnListaNoticias.setLayout(new BoxLayout(pnListaNoticias, BoxLayout.Y_AXIS));
            pnListaNoticias.setBackground(Color.WHITE);
        }
        return pnListaNoticias;
    }

    public JButton getBtCrear() {
        if (btCrear == null) {
            btCrear = new JButton("Crear Noticia");
        }
        return btCrear;
    }



}
