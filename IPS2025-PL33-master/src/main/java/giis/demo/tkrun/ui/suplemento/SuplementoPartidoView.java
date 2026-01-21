package giis.demo.tkrun.ui.suplemento;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SuplementoPartidoView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JComboBox<String> comboAbonados;
	private JComboBox<String> comboPartidos;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtEmail;
	private JButton btnPagar;
	private JButton btnVerFactura;
	private JButton btnVolver;
	private JTextArea resumen;
	private JLabel lblPrecioSuplemento;

	public SuplementoPartidoView() {
		setTitle("Pago de Suplemento por Partido");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(700, 500);
		setLocationRelativeTo(null);
		setResizable(true);

		JPanel contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBackground(new Color(245, 245, 245));
		setContentPane(contentPane);

		initPanelSeleccion(contentPane);
		initResumenPanel(contentPane);
		initInferiorPanel(contentPane);
	}

	private void initPanelSeleccion(JPanel contentPane) {
		JPanel panelSeleccion = new JPanel(new GridBagLayout());
		panelSeleccion.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 102, 204), 2),
				"Datos y Selección", 0, 0, new Font("SansSerif", Font.BOLD, 14), new Color(0, 102, 204)));
		panelSeleccion.setBackground(new Color(225, 235, 245));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Combo Abonados
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		panelSeleccion.add(new JLabel("Abonado:"), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		comboAbonados = new JComboBox<>();
		panelSeleccion.add(comboAbonados, gbc);

		// Combo Partidos
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		panelSeleccion.add(new JLabel("Partido:"), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		comboPartidos = new JComboBox<>();
		panelSeleccion.add(comboPartidos, gbc);

		// Teléfono
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		panelSeleccion.add(new JLabel("Teléfono *:"), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		txtTelefono = new JTextField();
		panelSeleccion.add(txtTelefono, gbc);

		// Dirección
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0;
		panelSeleccion.add(new JLabel("Dirección *:"), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		txtDireccion = new JTextField();
		panelSeleccion.add(txtDireccion, gbc);

		// Email
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weightx = 0;
		panelSeleccion.add(new JLabel("Email: *"), gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		txtEmail = new JTextField();
		panelSeleccion.add(txtEmail, gbc);

		// Precio suplemento
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		lblPrecioSuplemento = new JLabel("Precio suplemento: 0.00 €");
		lblPrecioSuplemento.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblPrecioSuplemento.setForeground(new Color(0, 102, 204));
		panelSeleccion.add(lblPrecioSuplemento, gbc);

		// Botones Pagar y Ver Factura
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		btnPagar = new JButton("Pagar Suplemento");
		btnPagar.setBackground(new Color(0, 102, 204));
		btnPagar.setForeground(Color.WHITE);
		btnPagar.setOpaque(true);
		btnPagar.setBorderPainted(false);
		panelSeleccion.add(btnPagar, gbc);

		gbc.gridx = 1;
		btnVerFactura = new JButton("Ver Factura");
		btnVerFactura.setEnabled(false); // solo habilitado tras pagar
		btnVerFactura.setBackground(new Color(102, 204, 0));
		btnVerFactura.setForeground(Color.WHITE);
		btnVerFactura.setOpaque(true);
		btnVerFactura.setBorderPainted(false);
		panelSeleccion.add(btnVerFactura, gbc);

		contentPane.add(panelSeleccion, BorderLayout.NORTH);
	}

	private void initResumenPanel(JPanel contentPane) {
		resumen = new JTextArea();
		resumen.setEditable(false);
		resumen.setFont(new Font("SansSerif", Font.PLAIN, 14));
		JScrollPane scrollResumen = new JScrollPane(resumen);
		scrollResumen.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 102, 204), 2),
				"Resumen de Pago", 0, 0, new Font("SansSerif", Font.BOLD, 14), new Color(0, 102, 204)));
		contentPane.add(scrollResumen, BorderLayout.CENTER);
	}

	private void initInferiorPanel(JPanel contentPane) {
		JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelInferior.setBackground(new Color(225, 235, 245));
		btnVolver = new JButton("Volver atrás");
		btnVolver.setBackground(new Color(200, 0, 0));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setOpaque(true);
		btnVolver.setBorderPainted(false);
		panelInferior.add(btnVolver);
		contentPane.add(panelInferior, BorderLayout.SOUTH);
	}

	// --- Getters ---
	public JComboBox<String> getComboAbonados() {
		return comboAbonados;
	}

	public JComboBox<String> getComboPartidos() {
		return comboPartidos;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public JTextField getTxtDireccion() {
		return txtDireccion;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JButton getBtnPagar() {
		return btnPagar;
	}

	public JButton getBtnVerFactura() {
		return btnVerFactura;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setPrecioSuplemento(double precio) {
		lblPrecioSuplemento.setText(String.format("Precio suplemento: %.2f €", precio));
	}

	public void mostrarResumen(String texto) {
		resumen.setText(texto);
	}
}
