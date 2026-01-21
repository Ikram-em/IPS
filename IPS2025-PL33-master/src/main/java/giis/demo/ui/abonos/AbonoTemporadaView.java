package giis.demo.ui.abonos;

import giis.demo.tkrun.abonos.AbonoTemporadaController;
import giis.demo.tkrun.partidos.entradas.ZoneDTO;
import giis.demo.ui.partidos.entradas.SeatMap;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class AbonoTemporadaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private AbonoTemporadaController controller;

	private JTextField txtDniCliente, txtNombre, txtDireccion, txtTelefono, txtEmail;
	private JComboBox<ZoneDTO> comboTribuna, comboSeccion;
	private JComboBox<Integer> comboFila, comboAsiento;
	private JButton btnConfirmar, btnVerFactura;
	private JLabel lblPrecio;
	private SeatMap seatMap;

	public AbonoTemporadaView() {
		setTitle("Venta de Abonos de Temporada");
		setSize(750, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JScrollPane scrollPAne = new JScrollPane();
		JPanel panelMain = new JPanel(new GridBagLayout());
		panelMain.setBackground(Color.WHITE);
		panelMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		scrollPAne.setViewportView(panelMain);
		add(scrollPAne);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 8, 8, 8);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;

		// --- Título ---
		JLabel lblTitle = new JLabel("Venta de Abonos de Temporada", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		panelMain.add(lblTitle, gbc);

		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridwidth = 1;

		// --- Panel Datos del Cliente ---
		JPanel panelCliente = new JPanel(new GridBagLayout());
		panelCliente.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),
				"Datos del Cliente", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 16)));
		panelCliente.setBackground(Color.WHITE);
		GridBagConstraints gbcCliente = new GridBagConstraints();
		gbcCliente.insets = new Insets(5, 5, 5, 5);
		gbcCliente.fill = GridBagConstraints.HORIZONTAL;
		gbcCliente.gridx = 0;
		gbcCliente.gridy = 0;

		txtDniCliente = crearCampo(panelCliente, gbcCliente, "DNI Cliente *:");
		txtNombre = crearCampo(panelCliente, gbcCliente, "Nombre *:");
		txtDireccion = crearCampo(panelCliente, gbcCliente, "Dirección *:");
		txtTelefono = crearCampo(panelCliente, gbcCliente, "Teléfono *:");
		txtEmail = crearCampo(panelCliente, gbcCliente, "Email *:");

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		panelMain.add(panelCliente, gbc);

		// --- Panel Selección de Asiento ---
		JPanel panelAsiento = new JPanel(new GridBagLayout());
		panelAsiento.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),
				"Selección de Asiento", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 16)));
		panelAsiento.setBackground(Color.WHITE);
		GridBagConstraints gbcAsiento = new GridBagConstraints();
		gbcAsiento.insets = new Insets(5, 5, 5, 5);
		gbcAsiento.fill = GridBagConstraints.HORIZONTAL;
		gbcAsiento.gridx = 0;
		gbcAsiento.gridy = 0;

		comboTribuna = crearCombo(panelAsiento, gbcAsiento, "Tribuna:");
		comboSeccion = crearCombo(panelAsiento, gbcAsiento, "Sección:");
		comboFila = crearComboEnteros(panelAsiento, gbcAsiento, "Fila:", 1, 10);
		comboAsiento = crearComboEnteros(panelAsiento, gbcAsiento, "Asiento:", 1, 15);

		lblPrecio = new JLabel("Precio: 0.00 €");
		lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 18));
		gbcAsiento.gridy++;
		gbcAsiento.gridwidth = 2;
		gbcAsiento.anchor = GridBagConstraints.CENTER;
		panelAsiento.add(lblPrecio, gbcAsiento);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		panelMain.add(panelAsiento, gbc);

		// --- SeatMap ---
		seatMap = new SeatMap();
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		panelMain.add(seatMap, gbc);

		// --- Botones ---
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(Color.WHITE);
		btnConfirmar = crearBoton("Confirmar Abono", new Color(70, 130, 180), Color.WHITE);
		btnVerFactura = crearBoton("Ver Factura", new Color(60, 179, 113), Color.WHITE);
		btnVerFactura.setEnabled(false);
		panelBotones.add(btnConfirmar);
		panelBotones.add(btnVerFactura);

		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.CENTER;
		panelMain.add(panelBotones, gbc);
	}

	private JTextField crearCampo(JPanel panel, GridBagConstraints gbc, String label) {
		JLabel lbl = new JLabel(label);
		lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
		JTextField tf = new JTextField(20);
		tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		gbc.gridx = 0;
		panel.add(lbl, gbc);
		gbc.gridx = 1;
		panel.add(tf, gbc);

		gbc.gridy++;
		return tf;
	}

	private JComboBox<ZoneDTO> crearCombo(JPanel panel, GridBagConstraints gbc, String label) {
		JLabel lbl = new JLabel(label);
		lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
		JComboBox<ZoneDTO> combo = new JComboBox<>();
		combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		gbc.gridx = 0;
		panel.add(lbl, gbc);
		gbc.gridx = 1;
		panel.add(combo, gbc);

		gbc.gridy++;
		return combo;
	}

	private JComboBox<Integer> crearComboEnteros(JPanel panel, GridBagConstraints gbc, String label, int min, int max) {
		JLabel lbl = new JLabel(label);
		lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
		JComboBox<Integer> combo = new JComboBox<>();
		for (int i = min; i <= max; i++)
			combo.addItem(i);
		combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		gbc.gridx = 0;
		panel.add(lbl, gbc);
		gbc.gridx = 1;
		panel.add(combo, gbc);

		gbc.gridy++;
		return combo;
	}

	private JButton crearBoton(String text, Color bg, Color fg) {
		JButton btn = new JButton(text);
		btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btn.setBackground(bg);
		btn.setForeground(fg);
		btn.setFocusPainted(false);
		btn.setPreferredSize(new Dimension(180, 45));
		return btn;
	}

	// --- Getters y métodos para Controller ---
	// Igual que tu versión anterior

	// --- Getters para el controller ---
	public JButton getBtnConfirmar() {
		return btnConfirmar;
	}

	public JButton getBtnVerFactura() {
		return btnVerFactura;
	}

	public JComboBox<ZoneDTO> getComboTribuna() {
		return comboTribuna;
	}

	public JComboBox<ZoneDTO> getComboSeccion() {
		return comboSeccion;
	}

	public JComboBox<Integer> getComboFila() {
		return comboFila;
	}

	public JComboBox<Integer> getComboAsiento() {
		return comboAsiento;
	}

	public int getFilaSeleccionada() {
		return (Integer) comboFila.getSelectedItem();
	}

	public int getAsientoSeleccionado() {
		return (Integer) comboAsiento.getSelectedItem();
	}

	public String getTribunaSeleccionada() {
		ZoneDTO selected = (ZoneDTO) comboTribuna.getSelectedItem();
		return (selected != null) ? selected.getNombre() : "-- Selecciona --";
	}

	public String getSeccionSeleccionada() {
		ZoneDTO selected = (ZoneDTO) comboSeccion.getSelectedItem();
		return (selected != null) ? selected.getNombre() : "-- Selecciona --";
	}

	public String getDniCliente() {
		return txtDniCliente.getText().trim();
	}

	public String getNombreCliente() {
		return txtNombre.getText().trim();
	}

	public String getDireccion() {
		return txtDireccion.getText().trim();
	}

	public String getTelefono() {
		return txtTelefono.getText().trim();
	}

	public String getEmail() {
		return txtEmail.getText().trim();
	}

	public void actualizarPrecioAbono(double precio) {
		lblPrecio.setText(String.format("Precio: %.2f €", precio));
	}

	public void setController(AbonoTemporadaController controller) {
		this.controller = controller;
	}

	public SeatMap getSeatMap() {
		return seatMap;
	}

	public void confirmarAbono() {
		if (controller != null)
			controller.onConfirmarAbono();
	}

	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarResumenAbono(String dniCliente, String tribuna, String seccion, int fila, int asiento,
			double precioTotal, String fechaCompra) {
		String resumen = String.format(
				"Resumen de la compra:\n\nDNI Cliente: %s\nTribuna: %s\nSección: %s\nFila: %d\nAsiento: %d\nPrecio total: %.2f €\nFecha: %s",
				dniCliente, tribuna, seccion, fila, asiento, precioTotal, fechaCompra);
		JOptionPane.showMessageDialog(this, resumen, "Compra confirmada", JOptionPane.INFORMATION_MESSAGE);
	}
}
