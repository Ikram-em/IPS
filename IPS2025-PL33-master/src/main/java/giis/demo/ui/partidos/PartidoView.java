package giis.demo.ui.partidos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import giis.demo.tkrun.partidos.ParserPartidos;
import giis.demo.tkrun.partidos.Partido;
import giis.demo.tkrun.partidos.PartidoDTO;

public class PartidoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextArea txtMensajeInfo;
	private JButton btnVolver;
	private JButton btnAñadir;
	private JTable tablaPartidos;
	private JDateChooser dateChooser;
	private JComboBox<String> comboEquipoLocal;
	private JTextField txtEquipoVisitante;
	private JSpinner spinnerHoraInicio;
	private JSpinner spinnerHoraFin;

	// --- NUEVOS CAMPOS PARA SUPLEMENTO ---
	private JRadioButton rbtnSuplementoSi;
	private JRadioButton rbtnSuplementoNo;
	private JTextField txtPrecioSuplemento;
	
	private JButton btnCargarArchivo;

	public PartidoView() {
		setTitle("Gestión de Partidos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(800, 500));

		getContentPane().setLayout(new BorderLayout(10, 10));

		// --- Panel Superior: Añadir Partidos ---
		JPanel panelFiltros = new JPanel();
		panelFiltros.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 102, 204), 2),
				"Añadir Partidos", 0, 0, new Font("SansSerif", Font.BOLD, 14), new Color(0, 102, 204)));
		panelFiltros.setBackground(new Color(225, 235, 245));
		panelFiltros.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Fecha
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelFiltros.add(new JLabel("Fecha:"), gbc);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		Date hoy = new Date();
		dateChooser.setDate(hoy);
		dateChooser.setMinSelectableDate(hoy);
		gbc.gridx = 1;
		panelFiltros.add(dateChooser, gbc);

		// Hora Inicio
		gbc.gridx = 2;
		gbc.gridy = 0;
		panelFiltros.add(new JLabel("Hora Inicio:"), gbc);

		spinnerHoraInicio = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor editorInicio = new JSpinner.DateEditor(spinnerHoraInicio, "HH:mm");
		spinnerHoraInicio.setEditor(editorInicio);
		Dimension d = new Dimension(60, 20);
		editorInicio.getTextField().setPreferredSize(d);
		editorInicio.getTextField().setMinimumSize(d);
		editorInicio.getTextField().setMaximumSize(d);
		GridBagConstraints gbcHoraInicio = new GridBagConstraints();
		gbcHoraInicio.gridx = 3;
		gbcHoraInicio.gridy = 0;
		gbcHoraInicio.fill = GridBagConstraints.NONE;
		gbcHoraInicio.anchor = GridBagConstraints.WEST;
		gbcHoraInicio.insets = new Insets(5, 5, 5, 5);
		panelFiltros.add(spinnerHoraInicio, gbcHoraInicio);

		// Hora Fin
		gbc.gridx = 4;
		gbc.gridy = 0;
		panelFiltros.add(new JLabel("Hora Fin:"), gbc);

		spinnerHoraFin = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor editorFin = new JSpinner.DateEditor(spinnerHoraFin, "HH:mm");
		spinnerHoraFin.setEditor(editorFin);
		editorFin.getTextField().setPreferredSize(d);
		editorFin.getTextField().setMinimumSize(d);
		editorFin.getTextField().setMaximumSize(d);
		GridBagConstraints gbcHoraFin = new GridBagConstraints();
		gbcHoraFin.gridx = 5;
		gbcHoraFin.gridy = 0;
		gbcHoraFin.fill = GridBagConstraints.NONE;
		gbcHoraFin.anchor = GridBagConstraints.WEST;
		gbcHoraFin.insets = new Insets(5, 5, 5, 5);
		panelFiltros.add(spinnerHoraFin, gbcHoraFin);

		// --- Fila 1: Equipos ---
		gbc.gridy = 1;
		gbc.gridx = 0;
		panelFiltros.add(new JLabel("Equipo Local:"), gbc);
		comboEquipoLocal = new JComboBox<>();
		gbc.gridx = 1;
		panelFiltros.add(comboEquipoLocal, gbc);

		gbc.gridx = 2;
		panelFiltros.add(new JLabel("Equipo Visitante:"), gbc);
		txtEquipoVisitante = new JTextField(20);
		gbc.gridx = 3;
		panelFiltros.add(txtEquipoVisitante, gbc);

		btnAñadir = new JButton("Añadir Partido");
		btnAñadir.setBackground(new Color(0, 102, 204));
		btnAñadir.setForeground(Color.WHITE);
		btnAñadir.setFont(new Font("SansSerif", Font.BOLD, 14));
		
		// --- Botón para cargar archivo CSV/JSON ---
		gbc.gridy = 3;
		gbc.gridx = 0;
		gbc.gridwidth = 2;

		btnCargarArchivo = new JButton("Cargar desde archivo");
		btnCargarArchivo.setBackground(new Color(0, 153, 0));
		btnCargarArchivo.setForeground(Color.WHITE);
		btnCargarArchivo.setFont(new Font("SansSerif", Font.BOLD, 14));
		panelFiltros.add(btnCargarArchivo, gbc);

		
		gbc.gridx = 4;
		gbc.gridwidth = 2;
		panelFiltros.add(btnAñadir, gbc);

		// --- Fila 2: Suplemento ---
		gbc.gridy = 2;
		gbc.gridwidth = 1;

		gbc.gridx = 0;
		panelFiltros.add(new JLabel("Tiene Suplemento:"), gbc);

		rbtnSuplementoSi = new JRadioButton("Sí");
		rbtnSuplementoNo = new JRadioButton("No");
		rbtnSuplementoNo.setSelected(true);

		ButtonGroup grupoSuplemento = new ButtonGroup();
		grupoSuplemento.add(rbtnSuplementoSi);
		grupoSuplemento.add(rbtnSuplementoNo);

		JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		panelRadio.add(rbtnSuplementoSi);
		panelRadio.add(rbtnSuplementoNo);

		gbc.gridx = 1;
		panelFiltros.add(panelRadio, gbc);

		gbc.gridx = 2;
		panelFiltros.add(new JLabel("Precio Suplemento:"), gbc);

		txtPrecioSuplemento = new JTextField(5); // más pequeño
		txtPrecioSuplemento.setEnabled(false); // solo editable si "Sí"
		gbc.gridx = 3;
		panelFiltros.add(txtPrecioSuplemento, gbc);

		// --- Lógica habilitar/deshabilitar ---
		rbtnSuplementoSi.addActionListener(e -> txtPrecioSuplemento.setEnabled(true));
		rbtnSuplementoNo.addActionListener(e -> {
			txtPrecioSuplemento.setText("");
			txtPrecioSuplemento.setEnabled(false);
		});

		getContentPane().add(panelFiltros, BorderLayout.NORTH);

		// --- Panel Central: Tabla de Partidos ---
		String[] headers = { "ID Partido", "Equipo Local", "Equipo Visitante", "Fecha", "Hora Inicio", "Hora Fin", "Suplemento", "Precio Suplemento" };
		DefaultTableModel model = new DefaultTableModel(headers, 0) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaPartidos = new JTable(model);
		tablaPartidos.setRowHeight(25);
		tablaPartidos.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
		tablaPartidos.getTableHeader().setBackground(new Color(0, 102, 204));
		tablaPartidos.getTableHeader().setForeground(Color.WHITE);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < tablaPartidos.getColumnCount(); i++)
			tablaPartidos.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

		JScrollPane scrollTabla = new JScrollPane(tablaPartidos);
		scrollTabla.setBorder(BorderFactory.createTitledBorder(new LineBorder(new Color(0, 102, 204), 2),
				"Lista de Partidos", 0, 0, new Font("SansSerif", Font.BOLD, 14), new Color(0, 102, 204)));
		getContentPane().add(scrollTabla, BorderLayout.CENTER);

		// --- Panel Inferior: Mensaje + Volver ---
		JPanel panelInferior = new JPanel(new BorderLayout());
		panelInferior.setBackground(new Color(225, 235, 245));

		txtMensajeInfo = new JTextArea(2, 20);
		txtMensajeInfo.setEditable(false);
		txtMensajeInfo.setLineWrap(true);
		txtMensajeInfo.setWrapStyleWord(true);
		txtMensajeInfo.setForeground(Color.RED);
		txtMensajeInfo.setBackground(new Color(245, 245, 245));
		txtMensajeInfo.setFont(new Font("SansSerif", Font.BOLD, 14));
		JScrollPane scrollMensaje = new JScrollPane(txtMensajeInfo);
		scrollMensaje.setBorder(BorderFactory.createEmptyBorder());
		panelInferior.add(scrollMensaje, BorderLayout.CENTER);

		btnVolver = new JButton("Volver atrás");
		btnVolver.setBackground(new Color(200, 0, 0));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(new Font("SansSerif", Font.BOLD, 14));
		panelInferior.add(btnVolver, BorderLayout.EAST);

		getContentPane().add(panelInferior, BorderLayout.SOUTH);
	}

	// --- Getters ---
	public JButton getBtnAñadir() { return btnAñadir; }
	public JButton getBtnVolver() { return btnVolver; }
	public JTable getTablaPartidos() { return tablaPartidos; }
	public Date getFechaSeleccionada() { return dateChooser.getDate(); }
	public String getHoraInicio() { return new SimpleDateFormat("HH:mm").format(spinnerHoraInicio.getValue()); }
	public String getHoraFin() { return new SimpleDateFormat("HH:mm").format(spinnerHoraFin.getValue()); }
	public String getHoraInicioTexto() { return ((JSpinner.DateEditor) spinnerHoraInicio.getEditor()).getTextField().getText(); }
	public String getHoraFinTexto() { return ((JSpinner.DateEditor) spinnerHoraFin.getEditor()).getTextField().getText(); }
	public JComboBox<String> getComboEquipoLocal() { return comboEquipoLocal; }
	public JButton getBtnAtras() { return btnVolver; }
	public JTextField getTxtEquipoVisitante() { return txtEquipoVisitante; }
	public JRadioButton getRbtnSuplementoSi() { return rbtnSuplementoSi; }
	public JRadioButton getRbtnSuplementoNo() { return rbtnSuplementoNo; }
	public JTextField getTxtPrecioSuplemento() { return txtPrecioSuplemento; }
	public void setMensajeInfo(String mensaje) { txtMensajeInfo.setText(mensaje); }
	public JButton getBtnCargarArchivo() { return btnCargarArchivo; }


	// --- Mostrar Partidos con Suplemento ---
	public void mostrarPartidos(List<Partido> partidos) {
		DefaultTableModel model = (DefaultTableModel) tablaPartidos.getModel();
		model.setRowCount(0);

		for (Partido p : partidos) {
	        model.addRow(new Object[] {
	            p.getIdPartido(),
	            p.getEquipoLocal(),
	            p.getEquipoVisitante(),
	            p.getFecha(),
	            p.getHoraInicio(),
	            p.getHoraFin(),
	            p.isTieneSuplemento() ? "Sí" : "No",
	            String.format("%.2f", p.getPrecioSuplemento())
	        });
	    }
	}

}

