package giis.demo.ui.employee.schedule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PnSchedule extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnVacioArriba;
	private JButton btnGuardarHorarioSemanal;
	private JPanel pnVacioDerecha;
	private JPanel pnVacioIz;
	private JPanel pnVacioAbajo;
	private JScrollPane scrollPane;
	private JTable tableSemanal;

	/**
	 * Create the panel.
	 */
	public PnSchedule() {	
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		add(getPnVacioArriba(), BorderLayout.NORTH);
		add(getPnVacioDerecha(), BorderLayout.EAST);
		add(getPnVacioIz(), BorderLayout.WEST);
		add(getPnVacioAbajo(), BorderLayout.SOUTH);
		add(getScrollPane(), BorderLayout.CENTER);

	}
	
	private JPanel getPnVacioArriba() {
		if (pnVacioArriba == null) {
			pnVacioArriba = new JPanel();
			pnVacioArriba.setBackground(Color.WHITE);
		}
		return pnVacioArriba;
	}
	public JButton getBtnGuardarHorarioSemanal() {
		if (btnGuardarHorarioSemanal == null) {
			btnGuardarHorarioSemanal = new JButton("Guardar");
			btnGuardarHorarioSemanal.setBackground(new Color(176, 196, 222));
			btnGuardarHorarioSemanal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return btnGuardarHorarioSemanal;
	}
	private JPanel getPnVacioDerecha() {
		if (pnVacioDerecha == null) {
			pnVacioDerecha = new JPanel();
		}
		return pnVacioDerecha;
	}
	private JPanel getPnVacioIz() {
		if (pnVacioIz == null) {
			pnVacioIz = new JPanel();
		}
		return pnVacioIz;
	}
	private JPanel getPnVacioAbajo() {
		if (pnVacioAbajo == null) {
			pnVacioAbajo = new JPanel();
		}
		return pnVacioAbajo;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTableSemanal());
			scrollPane.setPreferredSize(new Dimension(700, 200));
		}
		return scrollPane;
	}
	
	public JTable getTableSemanal() {
		if (tableSemanal == null) {
	        tableSemanal = new JTable();
	        tableSemanal.setFont(new Font("Calibri", Font.PLAIN, 15));
			tableSemanal.setFillsViewportHeight(false);

	        // Modelo de la tabla
	        tableSemanal.setModel(new DefaultTableModel(
	            new Object[][] {
	                {"Lunes", null, null},
	                {"Martes", null, null},
	                {"Miércoles", null, null},
	                {"Jueves", null, null},
	                {"Viernes", null, null},
	                {"Sábado", null, null},
	                {"Domingo", null, null},
	            },
	            new String[] {"Día", "Hora Inicio", "Hora Fin"}
	        ) {
	            private static final long serialVersionUID = 1L;
	            boolean[] columnEditables = {false, true, true};
	            public boolean isCellEditable(int row, int column) {
	                return columnEditables[column];
	            }
	        });

	        // No redimensionable
	        for (int i = 0; i < tableSemanal.getColumnCount(); i++) {
	            tableSemanal.getColumnModel().getColumn(i).setResizable(false);
	        }

	        // Centrar la columna de días
	        DefaultTableCellRenderer centerRendererText = new DefaultTableCellRenderer();
	        centerRendererText.setHorizontalAlignment(SwingConstants.CENTER);
	        tableSemanal.getColumnModel().getColumn(0).setCellRenderer(centerRendererText);

	        // Columnas de hora: formato HH:mm y vacío si null
	        DefaultTableCellRenderer centerRendererTime = new DefaultTableCellRenderer() {
	            private static final long serialVersionUID = 1L;
	            @Override
	            public void setValue(Object value) {
	                if (value instanceof String) {
	                    setText((String) value);
	                } else {
	                    setText(""); // vacío si null
	                }
	                setHorizontalAlignment(SwingConstants.CENTER);
	            }
	        };
	        tableSemanal.getColumnModel().getColumn(1).setCellRenderer(centerRendererTime);
	        tableSemanal.getColumnModel().getColumn(2).setCellRenderer(centerRendererTime);

	        // Crear lista de horas: "" + 00:00, 00:30, 01:00, ... 23:30
	        String[] horas = new String[49];
	        horas[0] = ""; // valor vacío
	        int idx = 1;
	        for (int h = 0; h < 24; h++) {
	            horas[idx++] = String.format("%02d:00", h);
	            horas[idx++] = String.format("%02d:30", h);
	        }

	        // Combobox para inicio y fin con doble clic para editar
	        JComboBox<String> comboInicio = new JComboBox<>(horas);
	        DefaultCellEditor editorInicio = new DefaultCellEditor(comboInicio);
	        editorInicio.setClickCountToStart(2); // doble clic
	        tableSemanal.getColumnModel().getColumn(1).setCellEditor(editorInicio);

	        JComboBox<String> comboFin = new JComboBox<>(horas);
	        DefaultCellEditor editorFin = new DefaultCellEditor(comboFin);
	        editorFin.setClickCountToStart(2); // doble clic
	        tableSemanal.getColumnModel().getColumn(2).setCellEditor(editorFin);
	    }
	    return tableSemanal;
	}
	

}

