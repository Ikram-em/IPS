package giis.demo.ui.ingresos;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import giis.demo.ui.MainMenuView;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class HistorialIngresosView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lblMensajeInfo;
	private JLabel lblSumaTotal;

	private JDateChooser dateChooserInicio;
	private JDateChooser dateChooserFin;
	private JButton btnBuscar;
	private JButton btnVolver;
	private JTable tablaIngresos;

	public HistorialIngresosView() {
		setTitle("Historial de Ingresos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setResizable(true);

		// --- Content Pane con BorderLayout ---
		JPanel contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBackground(new Color(245, 245, 245));
		setContentPane(contentPane);

		// --- Panel Filtros (NORTE) ---
		JPanel panelFiltros = new JPanel(new GridBagLayout());
		panelFiltros.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 102, 204), 2),
				"Rango de Fechas", 0, 0, new Font("SansSerif", Font.BOLD, 14), new Color(0, 102, 204)));
		panelFiltros.setBackground(new Color(225, 235, 245));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Desde
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelFiltros.add(new JLabel("Desde:"), gbc);
		dateChooserInicio = new JDateChooser();
		dateChooserInicio.setDateFormatString("yyyy-MM-dd");
		dateChooserInicio.setDate(getDefaultStartDate());
		gbc.gridx = 1;
		panelFiltros.add(dateChooserInicio, gbc);

		// Hasta
		gbc.gridx = 2;
		panelFiltros.add(new JLabel("Hasta:"), gbc);
		dateChooserFin = new JDateChooser();
		dateChooserFin.setDateFormatString("yyyy-MM-dd");
		dateChooserFin.setDate(new Date());
		gbc.gridx = 3;
		panelFiltros.add(dateChooserFin, gbc);

		// Botón Buscar
		btnBuscar = new JButton("Buscar Ingresos");
		btnBuscar.setBackground(new Color(0, 102, 204));
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnBuscar.setOpaque(true);
		btnBuscar.setBorderPainted(false);
		gbc.gridx = 4;
		panelFiltros.add(btnBuscar, gbc);

		// Mensaje info
		lblMensajeInfo = new JLabel("");
		lblMensajeInfo.setForeground(Color.RED);
		gbc.gridx = 5;
		panelFiltros.add(lblMensajeInfo, gbc);

		contentPane.add(panelFiltros, BorderLayout.NORTH);

		// --- Tabla Historial de Ingresos (CENTRO) ---
		String[] headersHistorial = { "Tipo de Ingreso", "Concepto", "Fecha y Hora", "Cuantía total (€)" };
		DefaultTableModel modelHistorial = new DefaultTableModel(headersHistorial, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tablaIngresos = new JTable(modelHistorial);
		tablaIngresos.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tablaIngresos.setRowHeight(25);
		tablaIngresos.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
		tablaIngresos.getTableHeader().setBackground(new Color(0, 102, 204));
		tablaIngresos.getTableHeader().setForeground(Color.WHITE);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < tablaIngresos.getColumnCount(); i++)
			tablaIngresos.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

		JScrollPane scrollHistorial = new JScrollPane(tablaIngresos);
		scrollHistorial.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 102, 204), 2),
				"Historial de Ingresos", 0, 0, new Font("SansSerif", Font.BOLD, 14), new Color(0, 102, 204)));
		contentPane.add(scrollHistorial, BorderLayout.CENTER);


		JPanel panelInferior = new JPanel(new GridBagLayout());
		panelInferior.setBackground(new Color(225, 235, 245));
		GridBagConstraints gbcInferior = new GridBagConstraints();
		gbcInferior.insets = new Insets(5, 5, 5, 5);
		gbcInferior.fill = GridBagConstraints.HORIZONTAL;

		lblSumaTotal = new JLabel("SUMA TOTAL INGRESOS: 0.00 €");
		lblSumaTotal.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblSumaTotal.setForeground(new Color(0, 102, 204));
		gbcInferior.gridx = 0;
		gbcInferior.gridy = 0;
		gbcInferior.weightx = 1;
		panelInferior.add(lblSumaTotal, gbcInferior);

		btnVolver = new JButton("Volver atrás");
		btnVolver.setBackground(new Color(200, 0, 0));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnVolver.setOpaque(true);
		btnVolver.setBorderPainted(false);
		btnVolver.addActionListener(e -> {
			dispose();
			SwingUtilities.invokeLater(() -> new MainMenuView().setVisible(true));
		});
		gbcInferior.gridx = 1;
		gbcInferior.gridy = 0;
		gbcInferior.weightx = 0;
		panelInferior.add(btnVolver, gbcInferior);

		contentPane.add(panelInferior, BorderLayout.SOUTH);
	}

	private Date getDefaultStartDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	// --- Getters ---
	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public JTable getTablaIngresos() {
		return tablaIngresos;
	}

	public Date getFechaInicioSeleccionada() {
		return dateChooserInicio.getDate();
	}

	public Date getFechaFinSeleccionada() {
		return dateChooserFin.getDate();
	}

	public void setSumaTotal(double total) {
		lblSumaTotal.setText(String.format("SUMA TOTAL INGRESOS: %.2f €", total));
	}

	public void setMensajeInfo(String mensaje) {
		lblMensajeInfo.setText(mensaje);
	}

}
