package giis.demo.ui.noticias;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import giis.demo.tkrun.noticias.MetodosNoticias;
import giis.demo.tkrun.noticias.NoticiaDto;

public class PanelNoticia extends JPanel {

    private static final long serialVersionUID = 1L;

    private ModoNoticia modo;

    private JLabel lbImagen;
    private JLabel lbTitulo;
    private JLabel lbEstado;
    private JLabel lbFecha;
    private JTextArea taContenidoPreview;
    private JPanel buttonsPanel;

    private JButton btEditar;
    private JButton btEliminar;

    private NoticiaDto noticia;

    public PanelNoticia(NoticiaDto noticia, ModoNoticia modo) {
        this.noticia = noticia;
        this.modo = modo;

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        crearUI();
        aplicarModo();
    }

    private void crearUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(getLbImagen(), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(getInfoPanel(), gbc);
    }

    private JLabel getLbImagen() {
        if (lbImagen == null) {
            lbImagen = new JLabel();
            lbImagen.setPreferredSize(new Dimension(140, 140));
            lbImagen.setHorizontalAlignment(SwingConstants.CENTER);

            if (noticia.getImagen() != null && !noticia.getImagen().isEmpty()) {
                String ruta = "src/main/resources/images/" + noticia.getImagen();
                ImageIcon icon = new ImageIcon(ruta);
                Image img = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
                lbImagen.setIcon(new ImageIcon(img));
            } else {
                lbImagen.setText("Sin Imagen");
                lbImagen.setForeground(Color.GRAY);
            }
        }
        return lbImagen;
    }

    private JPanel getInfoPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 0, 3, 0);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // TÍTULO
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(getLbTitulo(), gbc);

        // ESTADO + FECHA en la misma fila
        gbc.gridy++;
        JPanel estadoFechaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        estadoFechaPanel.setBackground(Color.WHITE);
        estadoFechaPanel.add(getLbEstado());
        estadoFechaPanel.add(getLbFecha());
        panel.add(estadoFechaPanel, gbc);

        // PREVIEW contenido
        gbc.gridy++;
        panel.add(getLbContenidoPreview(), gbc);

        // BOTONES abajo siempre alineados
        gbc.gridy++;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        panel.add(getButtonsPanel(), gbc);

        return panel;
    }


    private JLabel getLbTitulo() {
        if (lbTitulo == null) {
            lbTitulo = new JLabel(noticia.getTitulo());
            lbTitulo.setFont(new Font("Arial", Font.BOLD, 18));
            lbTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        }
        return lbTitulo;
    }

    private JLabel getLbEstado() {
        if (lbEstado == null) {
            lbEstado = new JLabel(noticia.isPublicada() ? "Publicada" : "En borrador");
            lbEstado.setForeground(noticia.isPublicada() ? Color.GREEN : Color.ORANGE);
            lbEstado.setAlignmentX(Component.LEFT_ALIGNMENT);
        }
        return lbEstado;
    }

    private JLabel getLbFecha() {
        if (lbFecha == null) {
            lbFecha = new JLabel("Fecha: " + noticia.getFechaPublicacion());
            lbFecha.setForeground(Color.GRAY);
            lbFecha.setAlignmentX(Component.LEFT_ALIGNMENT);
        }
        return lbFecha;
    }

    private JTextArea getLbContenidoPreview() {
        if (taContenidoPreview == null) {
            taContenidoPreview = new JTextArea(resumir(noticia.getContenido()));
            taContenidoPreview.setLineWrap(true);
            taContenidoPreview.setWrapStyleWord(true);
            taContenidoPreview.setEditable(false);
            taContenidoPreview.setFocusable(false);
            taContenidoPreview.setOpaque(false);
            taContenidoPreview.setBorder(null);
            taContenidoPreview.setFont(new Font("Arial", Font.PLAIN, 14));
            taContenidoPreview.setAlignmentX(Component.LEFT_ALIGNMENT);
            taContenidoPreview.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));
        }
        return taContenidoPreview;
    }


    private JPanel getButtonsPanel() {
        if (buttonsPanel == null) {
            buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            buttonsPanel.setBackground(Color.WHITE);

			buttonsPanel.add(getBtEditar());
            buttonsPanel.add(Box.createHorizontalStrut(10));
			buttonsPanel.add(getBtEliminar());
        }
        return buttonsPanel;
    }

	public JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton("Eliminar");
		}
		return btEliminar;
	}

	public JButton getBtEditar() {
		if (btEditar == null) {
			btEditar = new JButton("Editar");
		}
		return btEditar;
	}


    private void aplicarModo() {
        if (modo == ModoNoticia.PUBLIC) {
            getButtonsPanel().setVisible(false);
            getLbEstado().setVisible(false);
        } else {
        	getLbContenidoPreview().setText("");
            getLbContenidoPreview().setMaximumSize(new Dimension(Integer.MAX_VALUE, 0));
            getLbContenidoPreview().setPreferredSize(new Dimension(0, 0));
        }
    }

    public void addListeners(Runnable refrescarLista, JFrame parent) {
        btEliminar.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                    parent, "¿Seguro que deseas eliminar esta noticia?",
                    "Eliminar", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                new MetodosNoticias().eliminarNoticia(noticia.getId());
                refrescarLista.run();
            }
        });

        btEditar.addActionListener(e -> {
            new CreacionNoticias((GestionNoticias) parent, noticia).setVisible(true);
        });
    }

    private String resumir(String txt) {
        return txt.length() > 120 ? txt.substring(0, 120) + "..." : txt;
    }

    public enum ModoNoticia {
        ADMIN,
        PUBLIC
    }

	public NoticiaDto getNoticia() {
		return noticia;
	}
}
