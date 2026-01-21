package giis.demo.ui.medico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class HistorialLesionView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lbHistorial;
	private JButton btnNewButton;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public HistorialLesionView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 602, 468);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel());

	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getScrollPane());
			panel.add(getPanel_1(), BorderLayout.NORTH);
			panel.add(getPanel_2(), BorderLayout.SOUTH);
		}
		return panel;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			panel_1.add(getLbHistorial());
		}
		return panel_1;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(Color.WHITE);
			panel_2.add(getBtnNewButton());
		}
		return panel_2;
	}

	private JLabel getLbHistorial() {
		if (lbHistorial == null) {
			lbHistorial = new JLabel("Historial de la lesión");
			lbHistorial.setFont(new Font("Calibri", Font.BOLD, 25));
		}
		return lbHistorial;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Salir");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrar();
				}
			});
			btnNewButton.setBackground(new Color(176, 196, 222));
			btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 15));
		}
		return btnNewButton;
	}

	private void cerrar() {
		this.dispose();
	}

	public JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setBackground(Color.WHITE);
			table.setFont(new Font("Calibri", Font.PLAIN, 15));
			table.setModel(new DefaultTableModel(new Object[][] {},
					new String[] { "Fecha y hora", "Acci\u00F3n", "Prioridad", "Descripci\u00F3n" }) {
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] { Object.class, String.class, String.class, String.class };

				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			JTableHeader header = table.getTableHeader();
			header.setBackground(new Color(70, 130, 180));
			header.setForeground(Color.WHITE);
			header.setFont(new Font("Calibri", Font.PLAIN, 15));
			((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
			// Centrar todas las columnas
	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	        for (int i = 0; i < table.getColumnCount(); i++) {
	            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	        }
		}
		return table;
	}
}
