package giis.demo.ui.partidos.entradas;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import giis.demo.tkrun.partidos.entradas.ZoneDTO;

/**
 * Vista para la selección de asientos y la introducción de datos del cliente para la compra de entradas.
 */
public class SeleccionAsientosView extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel sPnSelectors;
    private JPanel sPnClientData;
    private JComboBox<ZoneDTO> tribuneSelector;
    private JComboBox<ZoneDTO> sectionSelector;
    private JSpinner numberSelector;
    private JButton btnComprarEntradas;
    private SeatMap seatMap;
    

    // Campos del cliente
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtEmail;

    public SeleccionAsientosView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 150, 1050, 650);
        setTitle("Selección de Asientos y Datos de Compra");

        contentPane = new JPanel(new BorderLayout(15, 15));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        // Panel superior: Datos del cliente
        contentPane.add(getSPnClientData(), BorderLayout.NORTH);

        // Panel central: Selectores + Mapa de asientos
        JPanel centerPanel = new JPanel(new BorderLayout(20, 0));
        centerPanel.add(getSpnSelectors(), BorderLayout.WEST);
        centerPanel.add(getSeatMap(), BorderLayout.CENTER);
        contentPane.add(centerPanel, BorderLayout.CENTER);

        // Panel inferior: Botón de compra
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.add(getBtnComprarEntradas());
        contentPane.add(southPanel, BorderLayout.SOUTH);
    }

    // --- Panel Datos del Cliente ---
    private JPanel getSPnClientData() {
        if (sPnClientData == null) {
            sPnClientData = new JPanel(new GridBagLayout());
            sPnClientData.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                    "Datos del Cliente para Facturación",
                    TitledBorder.LEFT, TitledBorder.TOP,
                    new Font("SansSerif", Font.BOLD, 16)));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 10, 8, 10);
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Fila 1
            gbc.gridx = 0; gbc.gridy = 0;
            sPnClientData.add(createLabel("Nombre:"), gbc);
            gbc.gridx = 1;
            sPnClientData.add(getTxtNombre(), gbc);

            gbc.gridx = 2;
            sPnClientData.add(createLabel("Dirección:"), gbc);
            gbc.gridx = 3;
            sPnClientData.add(getTxtDireccion(), gbc);

            // Fila 2
            gbc.gridx = 0; gbc.gridy = 1;
            sPnClientData.add(createLabel("Teléfono:"), gbc);
            gbc.gridx = 1;
            sPnClientData.add(getTxtTelefono(), gbc);

            gbc.gridx = 2;
            sPnClientData.add(createLabel("Email:"), gbc);
            gbc.gridx = 3;
            sPnClientData.add(getTxtEmail(), gbc);

            // Ajuste de tamaño uniforme
            Dimension fieldDim = new Dimension(180, 30);
            getTxtNombre().setPreferredSize(fieldDim);
            getTxtDireccion().setPreferredSize(fieldDim);
            getTxtTelefono().setPreferredSize(fieldDim);
            getTxtEmail().setPreferredSize(fieldDim);
        }
        return sPnClientData;
    }

    // --- Panel Selectores ---
    private JPanel getSpnSelectors() {
        if (sPnSelectors == null) {
            sPnSelectors = new JPanel();
            sPnSelectors.setLayout(new BoxLayout(sPnSelectors, BoxLayout.Y_AXIS));
            sPnSelectors.setPreferredSize(new Dimension(320, sPnSelectors.getPreferredSize().height));
            sPnSelectors.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                    "Selección de Ubicación y Cantidad",
                    TitledBorder.LEFT, TitledBorder.TOP,
                    new Font("SansSerif", Font.BOLD, 16)));

            sPnSelectors.add(Box.createVerticalStrut(10));
            sPnSelectors.add(createLabel("Seleccionar tribuna:", Font.BOLD, 14));
            sPnSelectors.add(getTribuneSelector());
            sPnSelectors.add(Box.createVerticalStrut(15));

            sPnSelectors.add(createLabel("Seleccionar sección:", Font.BOLD, 14));
            sPnSelectors.add(getSectionSelector());
            sPnSelectors.add(Box.createVerticalStrut(15));

            sPnSelectors.add(createLabel("Cantidad de entradas (Máx 15):", Font.BOLD, 14));
            JPanel spinnerWrap = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            spinnerWrap.add(getNumberSelector());
            spinnerWrap.setAlignmentX(Component.LEFT_ALIGNMENT);
            sPnSelectors.add(spinnerWrap);
            sPnSelectors.add(Box.createVerticalGlue());
        }
        return sPnSelectors;
    }

    // --- Mapa de asientos ---
    public SeatMap getSeatMap() {
        if (seatMap == null) {
            seatMap = new SeatMap();
            seatMap.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                    "Mapa de Asientos y Disponibilidad",
                    TitledBorder.LEFT, TitledBorder.TOP,
                    new Font("SansSerif", Font.BOLD, 16)));
        }
        return seatMap;
    }

    // --- Componentes ---
    public JComboBox<ZoneDTO> getTribuneSelector() {
        if (tribuneSelector == null) {
            tribuneSelector = new JComboBox<>();
            tribuneSelector.setPreferredSize(new Dimension(300, 35));
            tribuneSelector.setMaximumSize(new Dimension(Short.MAX_VALUE, 35));
            tribuneSelector.setFont(new Font("SansSerif", Font.PLAIN, 14));
        }
        return tribuneSelector;
    }

    public JComboBox<ZoneDTO> getSectionSelector() {
        if (sectionSelector == null) {
            sectionSelector = new JComboBox<>();
            sectionSelector.setPreferredSize(new Dimension(300, 35));
            sectionSelector.setMaximumSize(new Dimension(Short.MAX_VALUE, 35));
            sectionSelector.setFont(new Font("SansSerif", Font.PLAIN, 14));
        }
        return sectionSelector;
    }

    public JSpinner getNumberSelector() {
        if (numberSelector == null) {
            numberSelector = new JSpinner(new SpinnerNumberModel(1, 1, 15, 1));
            numberSelector.setPreferredSize(new Dimension(70, 35));
            numberSelector.setMaximumSize(new Dimension(70, 35));
            numberSelector.setFont(new Font("SansSerif", Font.BOLD, 16));
        }
        return numberSelector;
    }

    public JButton getBtnComprarEntradas() {
        if (btnComprarEntradas == null) {
            btnComprarEntradas = new JButton("Finalizar Compra");
            btnComprarEntradas.setPreferredSize(new Dimension(200, 45));
            btnComprarEntradas.setFont(new Font("SansSerif", Font.BOLD, 18));
            btnComprarEntradas.setBackground(new Color(70, 130, 180));
            btnComprarEntradas.setForeground(Color.WHITE);
            btnComprarEntradas.setFocusPainted(false);
        }
        return btnComprarEntradas;
    }

    // --- Campos cliente ---
    public JTextField getTxtNombre() {
        if (txtNombre == null) { txtNombre = createTextField(); }
        return txtNombre;
    }

    public JTextField getTxtDireccion() {
        if (txtDireccion == null) { txtDireccion = createTextField(); }
        return txtDireccion;
    }

    public JTextField getTxtTelefono() {
        if (txtTelefono == null) { txtTelefono = createTextField(); }
        return txtTelefono;
    }

    public JTextField getTxtEmail() {
        if (txtEmail == null) { txtEmail = createTextField(); }
        return txtEmail;
    }

    private JTextField createTextField() {
        JTextField txt = new JTextField();
        txt.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txt.setPreferredSize(new Dimension(180, 30));
        return txt;
    }

    // --- JLabel estandarizado ---
    private JLabel createLabel(String text) {
        return createLabel(text, Font.PLAIN, 14);
    }

    private JLabel createLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, size));
        label.setBorder(new EmptyBorder(5, 0, 5, 0));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
}
