package giis.demo.ui.noticias;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import giis.demo.tkrun.noticias.MetodosNoticias;
import giis.demo.tkrun.noticias.NoticiaDto;
import giis.demo.ui.noticias.PanelNoticia.ModoNoticia;

public class GaleriaNoticias extends JFrame {

    private static final long serialVersionUID = 1L;

    private List<NoticiaDto> noticias;
    private JPanel listaPanel;
    private int cargadas = 1; 

    public GaleriaNoticias() {
        setTitle("Noticias");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setMinimumSize(getSize());

        cargarNoticias();
        setVisible(true);
    }

    private void cargarNoticias() {
        MetodosNoticias model = new MetodosNoticias();
        noticias = model.getNoticiasPublicadas();

        if (noticias.isEmpty()) {
            getContentPane().add(new JLabel("No hay noticias.", SwingConstants.CENTER));
            return;
        }

        PanelNoticia destacada = new PanelNoticia(noticias.get(0), ModoNoticia.PUBLIC);
        
        destacada.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new VisualizarNoticia(noticias.get(0));
            }
        });
        
        getContentPane().add(destacada, BorderLayout.NORTH);

        // ✅ El resto van en scroll y lazy-loading
        listaPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        JScrollPane scroll = new JScrollPane(listaPanel);

        scroll.getVerticalScrollBar().addAdjustmentListener(e -> {
            JScrollBar bar = scroll.getVerticalScrollBar();
            if (!bar.getValueIsAdjusting() &&
                bar.getValue() + bar.getVisibleAmount() == bar.getMaximum()) {
                cargarMasNoticias();
            }
        });

        getContentPane().add(scroll, BorderLayout.CENTER);
        cargarMasNoticias();
    }

    private void cargarMasNoticias() {
        int hasta = Math.min(cargadas + 4, noticias.size());
        for (int i = cargadas; i < hasta; i++) {
            PanelNoticia panel = new PanelNoticia(noticias.get(i), ModoNoticia.PUBLIC);

            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new VisualizarNoticia(panel.getNoticia());
                }
            });

            listaPanel.add(panel);
        }
        cargadas = hasta;
        listaPanel.revalidate();
        listaPanel.repaint();
    }


}
