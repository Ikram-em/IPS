package giis.demo.ui.noticias;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.noticias.NoticiaDto;

public class VisualizarNoticia extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel pnTituloImagen;
    private JScrollPane scrollPane;
    private JPanel pnContenido;
    private JTextArea txContenido;
    private JLabel lbTitulo;
    private JLabel lbImagen;

    public VisualizarNoticia(NoticiaDto noticia) {
        setTitle("Visualización de noticias");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 702, 498);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(2, 1, 0, 0));

        contentPane.add(getPnTituloImagen());
        contentPane.add(getScrollPane());

        getLbTitulo().setText(noticia.getTitulo());
        getTxContenido().setText(noticia.getContenido());
        getTxContenido().setCaretPosition(0);

        if (noticia.getImagen() != null && !noticia.getImagen().isEmpty()) {
            String ruta = "src/main/resources/images/" + noticia.getImagen();
            ImageIcon icon = new ImageIcon(ruta);

            // Guardamos el icono original para futuros reescalados
            lbImagen.putClientProperty("originalIcon", icon);

            // Asignamos inicialmente el icono sin escalar
            lbImagen.setIcon(icon);
        } else {
            lbImagen.setText("Sin Imagen");
            lbImagen.setForeground(Color.GRAY);
        }

        setLocationRelativeTo(null);
        setVisible(true);
        setMinimumSize(getSize());
    }

    private JPanel getPnTituloImagen() {
        if (pnTituloImagen == null) {
            pnTituloImagen = new JPanel();
            pnTituloImagen.setBackground(Color.WHITE);
            pnTituloImagen.setLayout(new BorderLayout(0, 0));
            pnTituloImagen.add(getLbTitulo(), BorderLayout.NORTH);
            pnTituloImagen.add(getLbImagen(), BorderLayout.CENTER);
        }
        return pnTituloImagen;
    }

    private JScrollPane getScrollPane() {
        if (scrollPane == null) {
            scrollPane = new JScrollPane();
            scrollPane.setViewportView(getPnContenido());
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        }
        return scrollPane;
    }

    private JPanel getPnContenido() {
        if (pnContenido == null) {
            pnContenido = new JPanel();
            pnContenido.setLayout(new BorderLayout(0, 0));
            pnContenido.add(getTxContenido(), BorderLayout.CENTER);
        }
        return pnContenido;
    }

    private JTextArea getTxContenido() {
        if (txContenido == null) {
            txContenido = new JTextArea();
            txContenido.setFont(new Font("Arial", Font.PLAIN, 16));
            txContenido.setEditable(false);
            txContenido.setLineWrap(true);
            txContenido.setWrapStyleWord(true);
            
            // Añadimos margen: top, left, bottom, right
            txContenido.setBorder(new EmptyBorder(5, 5, 5, 10)); // margen derecho de 10px
        }
        return txContenido;
    }


    private JLabel getLbTitulo() {
        if (lbTitulo == null) {
            lbTitulo = new JLabel("TITULO");
            lbTitulo.setFont(new Font("Arial", Font.BOLD, 24));
            lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return lbTitulo;
    }

    private JLabel getLbImagen() {
        if (lbImagen == null) {
            lbImagen = new JLabel("");
            lbImagen.setHorizontalAlignment(SwingConstants.CENTER);
            lbImagen.setBackground(Color.WHITE);

            // Escuchador para redimensionar la imagen dinámicamente
            lbImagen.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    Object prop = lbImagen.getClientProperty("originalIcon");
                    if (prop instanceof ImageIcon) {
                        ImageIcon original = (ImageIcon) prop;
                        Image scaled = getScaledImage(original.getImage(), lbImagen.getWidth(), lbImagen.getHeight());
                        lbImagen.setIcon(new ImageIcon(scaled));
                    }
                }
            });
        }
        return lbImagen;
    }

    private Image getScaledImage(Image srcImg, int width, int height) {
        int originalWidth = srcImg.getWidth(null);
        int originalHeight = srcImg.getHeight(null);
        if (originalWidth <= 0 || originalHeight <= 0 || width <= 0 || height <= 0)
            return srcImg; // Evita errores

        // Mantener proporción
        double ratio = Math.min((double) width / originalWidth, (double) height / originalHeight);
        int newWidth = (int) (originalWidth * ratio);
        int newHeight = (int) (originalHeight * ratio);

        return srcImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }
}
