package giis.demo.ui.noticias;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import giis.demo.tkrun.noticias.NoticiaDto;

public class CreacionNoticias extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel pnIzquierda;
    private JPanel pnDerecha;

    private JLabel lbTitulo;
    private JTextField txtTitulo;

    private JLabel lbContenido;
    private JTextArea txtContenido;
    private JScrollPane scContenido;

    private JLabel lbPrevisualizar;
    private JButton btInsertarImagen;
    private JButton btBorrarImagen;

    private JPanel pnEstado;
    private JRadioButton rbBorrador;
    private JRadioButton rbPublicada;
    private ButtonGroup grupoEstado;
    
    private File imagenSeleccionada;
    private JButton btGuardar;
    
    private NoticiaDto noticia; 
    private int rowCounter = 0;

    /**
     * @wbp.parser.constructor
     */
	public CreacionNoticias() {
        setTitle("Creación de Noticias");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setContentPane(getContentPanePrincipal());
        setLocationRelativeTo(null);
        setMinimumSize(getSize());

    }


    public CreacionNoticias(GestionNoticias ventanaPadre, NoticiaDto noticia) {
        this.noticia = noticia;

        setTitle("Editar Noticia");

        getTxtTitulo().setText(noticia.getTitulo());
        getTxtContenido().setText(noticia.getContenido());

        if (noticia.isPublicada()) {
            getRbPublicada().setSelected(true);
        } else {
            getRbBorrador().setSelected(true);
        }

        if (noticia.getImagen() != null && !noticia.getImagen().isEmpty()) {
            //Se ejecutará cuando la ventana ya está renderizada
            SwingUtilities.invokeLater(() -> {
                File imgFile = new File("src/main/resources/images/" + noticia.getImagen());
                if (imgFile.exists()) {
                    mostrarImagen(imgFile.getAbsolutePath());
                    btInsertarImagen.setEnabled(true);
                    btBorrarImagen.setEnabled(true);
                }
            });
        }

        getBtGuardar().setText("Actualizar Noticia");

        getBtGuardar().removeActionListener(getBtGuardar().getActionListeners()[0]);
		// getBtGuardar().addActionListener(e -> actualizarNoticia());
    }

    
	public NoticiaDto getNoticia() {
		return this.noticia;
	}

	private JPanel getContentPanePrincipal() {
        if (contentPane == null) {
            contentPane = new JPanel();
            contentPane.setBackground(Color.WHITE);
            contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
            contentPane.setLayout(new BorderLayout(10, 10));
            contentPane.add(getPnIzquierda(), BorderLayout.CENTER);
            contentPane.add(getPnDerecha(), BorderLayout.EAST);
        }
        return contentPane;
    }

    private JPanel getPnIzquierda() {
        if (pnIzquierda == null) {
            pnIzquierda = new JPanel();
            pnIzquierda.setBackground(Color.WHITE);
            pnIzquierda.setLayout(new BorderLayout(10, 10));

            // Panel del título
            JPanel pnTitulo = new JPanel(new BorderLayout(5, 5));
            pnTitulo.setBackground(Color.WHITE);
            pnTitulo.add(getLbTitulo(), BorderLayout.NORTH);
            pnTitulo.add(getTxtTitulo(), BorderLayout.CENTER);

            // Panel del contenido
            JPanel pnContenido = new JPanel(new BorderLayout(5, 5));
            pnContenido.setBackground(Color.WHITE);
            pnContenido.add(getLbContenido(), BorderLayout.NORTH);
            pnContenido.add(getScContenido(), BorderLayout.CENTER);

            pnIzquierda.add(pnTitulo, BorderLayout.NORTH);
            pnIzquierda.add(pnContenido, BorderLayout.CENTER);
        }
        return pnIzquierda;
    }


    private void addToGrid(JPanel panel, Component comp, GridBagConstraints gbc) {
        gbc.gridy = rowCounter++;
        panel.add(comp, gbc);
    }

    private JPanel getPnDerecha() {
        if (pnDerecha == null) {
            pnDerecha = new JPanel(new GridBagLayout());
            pnDerecha.setBorder(new EmptyBorder(10, 20, 10, 20));
            pnDerecha.setBackground(Color.WHITE);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 0, 8, 0);
            gbc.gridx = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.CENTER;

            // Reseteamos el contador
            rowCounter = 0;

            addToGrid(pnDerecha, getLbPrevisualizar(), gbc);
            addToGrid(pnDerecha, getBtInsertarImagen(), gbc);
            addToGrid(pnDerecha, getBtBorrarImagen(), gbc);
            addToGrid(pnDerecha, getPnEstado(), gbc);
            addToGrid(pnDerecha, Box.createVerticalStrut(20), gbc);

            // Botón guardar centrado
            gbc.fill = GridBagConstraints.NONE;
            addToGrid(pnDerecha, getBtGuardar(), gbc);
        }
        return pnDerecha;
    }


    private JLabel getLbTitulo() {
        if (lbTitulo == null) {
            lbTitulo = new JLabel("TÍTULO DE LA NOTICIA");
        }
        return lbTitulo;
    }

	public JTextField getTxtTitulo() {
        if (txtTitulo == null) {
            txtTitulo = new JTextField();
            txtTitulo.setFont(new Font("Arial", Font.PLAIN, 18));
        }
        return txtTitulo;
    }

    private JLabel getLbContenido() {
        if (lbContenido == null) {
            lbContenido = new JLabel("CONTENIDO DE LA NOTICIA");
        }
        return lbContenido;
    }

    private JScrollPane getScContenido() {
        if (scContenido == null) {
            scContenido = new JScrollPane(getTxtContenido());
        }
        return scContenido;
    }

	public JTextArea getTxtContenido() {
        if (txtContenido == null) {
            txtContenido = new JTextArea();
            txtContenido.setFont(new Font("Arial", Font.PLAIN, 16));
            txtContenido.setLineWrap(true);
            txtContenido.setWrapStyleWord(true);
        }
        return txtContenido;
    }

	public JLabel getLbPrevisualizar() {
        if (lbPrevisualizar == null) {
            lbPrevisualizar = new JLabel("PREVISUALIZAR IMAGEN", SwingConstants.CENTER);
            lbPrevisualizar.setPreferredSize(new Dimension(200, 150));
            lbPrevisualizar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }
        return lbPrevisualizar;
    }

	public JButton getBtInsertarImagen() {
        if (btInsertarImagen == null) {
            btInsertarImagen = new JButton("INSERTAR IMAGEN");
            btInsertarImagen.addActionListener(e -> seleccionarImagen());
        }
        return btInsertarImagen;
    }


	public JButton getBtBorrarImagen() {
        if (btBorrarImagen == null) {
            btBorrarImagen = new JButton("BORRAR IMAGEN");
            btBorrarImagen.setEnabled(false);
            btBorrarImagen.addActionListener(e -> borrarImagen());
        }
        return btBorrarImagen;
    }

	public void setNoticia(NoticiaDto noticia) {
		this.noticia = noticia;
	}

    private JPanel getPnEstado() {
        if (pnEstado == null) {
            pnEstado = new JPanel(new FlowLayout(FlowLayout.LEFT));
            pnEstado.setBorder(new TitledBorder("ESTADO DE LA NOTICIA"));
            pnEstado.setBackground(Color.WHITE);
            pnEstado.add(getRbBorrador());
            pnEstado.add(getRbPublicada());
            getGrupoEstado();
        }
        return pnEstado;
    }

	public JRadioButton getRbBorrador() {
        if (rbBorrador == null) {
            rbBorrador = new JRadioButton("Borrador", true);
            rbBorrador.setBackground(Color.WHITE);
        }
        return rbBorrador;
    }

	public JRadioButton getRbPublicada() {
        if (rbPublicada == null) {
            rbPublicada = new JRadioButton("Publicada");
            rbPublicada.setBackground(Color.WHITE);
            
        }
        return rbPublicada;
    }

    private ButtonGroup getGrupoEstado() {
        if (grupoEstado == null) {
            grupoEstado = new ButtonGroup();
            grupoEstado.add(getRbBorrador());
            grupoEstado.add(getRbPublicada());
        }
        return grupoEstado;
    }

	public JButton getBtGuardar() {
        if (btGuardar == null) {
            btGuardar = new JButton("GUARDAR NOTICIA");
            btGuardar.setBackground(new Color(50, 205, 50));
        }
        return btGuardar;
    }

	public File getImagenSeleccionada() {
		return imagenSeleccionada;
	}
    
    

    
    private void seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(
                "Imágenes (JPG, PNG)", "jpg", "jpeg", "png"));

        int resultado = fileChooser.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            imagenSeleccionada = fileChooser.getSelectedFile();

            mostrarImagen(imagenSeleccionada.getAbsolutePath());

            btInsertarImagen.setEnabled(false);
            btBorrarImagen.setEnabled(true);
        }
    }


    private void mostrarImagen(String ruta) {
        ImageIcon iconoOriginal = new ImageIcon(ruta);
        Image imgEscalada = iconoOriginal.getImage().getScaledInstance(
                lbPrevisualizar.getWidth(), lbPrevisualizar.getHeight(), Image.SCALE_SMOOTH);

        lbPrevisualizar.setIcon(new ImageIcon(imgEscalada));
        lbPrevisualizar.setText(""); // Limpiar texto
    }

	public void borrarImagen() {
        imagenSeleccionada = null;
		noticia.setImagen(null);
        lbPrevisualizar.setIcon(null);
        lbPrevisualizar.setText("PREVISUALIZAR IMAGEN");
        btInsertarImagen.setEnabled(true);
        btBorrarImagen.setEnabled(false);
    }


	public void setImagenSeleccionada(File img) {
		this.imagenSeleccionada = img;
	}



}
