package giis.demo.ui.tienda;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MerchPurchaseView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JScrollPane sPnDetailedPurchaseList;
	private JTable pnPurchaseTable;

	// Campos del cliente
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtEmail;

	// Botones
	private JButton btnSendEmail;
	private JButton btnVerFactura;
	private JButton closeBtn;

	public MerchPurchaseView() {
		setTitle("Resumen de Compra");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(700, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		// Panel superior con título
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.setBackground(Color.WHITE);
		topPanel.add(createLabel("¡Gracias por tu compra!", Font.BOLD, 18));
		topPanel.add(createLabel("Tu resumen es:", Font.PLAIN, 14));
		topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
		getContentPane().add(topPanel, BorderLayout.NORTH);

		// Tabla de compra
		getContentPane().add(getSPnDetailedPurchase(), BorderLayout.CENTER);

		// Panel de datos del cliente
		JPanel customerPanel = new JPanel(new GridLayout(5, 2, 5, 5));
		customerPanel.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));

		customerPanel.add(new JLabel("Nombre *:"));
		txtNombre = new JTextField(20);
		customerPanel.add(txtNombre);

		customerPanel.add(new JLabel("Dirección *:"));
		txtDireccion = new JTextField(20);
		customerPanel.add(txtDireccion);

		customerPanel.add(new JLabel("Teléfono *:"));
		txtTelefono = new JTextField(20);
		customerPanel.add(txtTelefono);

		customerPanel.add(new JLabel("Email *:"));
		txtEmail = new JTextField(20);
		customerPanel.add(txtEmail);

		btnVerFactura = new JButton("Ver Factura");
		btnVerFactura.setEnabled(true);
		customerPanel.add(btnVerFactura);

		btnSendEmail = new JButton("Enviar confirmación");
		customerPanel.add(btnSendEmail);

		// Panel inferior con cerrar
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		closeBtn = new JButton("Aceptar");
		closeBtn.setPreferredSize(new Dimension(150, 30));
		closeBtn.addActionListener(e -> dispose());
		buttonPanel.add(closeBtn);

		// Contenedor final (datos + botones)
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
		southPanel.add(customerPanel);
		southPanel.add(buttonPanel);

		getContentPane().add(southPanel, BorderLayout.SOUTH);
	}

	public JScrollPane getSPnDetailedPurchase() {
		if (sPnDetailedPurchaseList == null) {
			sPnDetailedPurchaseList = new JScrollPane(getPnPurchaseTable());
			Border line = BorderFactory.createLineBorder(Color.GRAY, 1, true);
			Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
			sPnDetailedPurchaseList.setBorder(BorderFactory.createCompoundBorder(padding, line));
		}
		return sPnDetailedPurchaseList;
	}

	public JTable getPnPurchaseTable() {
		if (pnPurchaseTable == null) {
			String[] columnNames = { "Producto", "Cantidad", "Precio por unidad", "Precio total" };
			DefaultTableModel model = new DefaultTableModel(columnNames, 0);
			pnPurchaseTable = new JTable(model);

			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

			for (int i = 0; i < pnPurchaseTable.getColumnCount(); i++) {
				pnPurchaseTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
			}
		}
		return pnPurchaseTable;
	}

	protected JLabel createLabel(String text, int style, int size) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("SansSerif", style, size));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
		return label;
	}

	// Getters para el controlador
	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtDireccion() {
		return txtDireccion;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JButton getBtnSendEmail() {
		return btnSendEmail;
	}

	public JButton getBtnVerFactura() {
		return btnVerFactura;
	}
}
