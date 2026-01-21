package giis.demo.ui.tienda.stock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CreatedRestockOrderView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton closeBtn;
	private JScrollPane sPnDetailedPurchaseList;
	private JTable pnPurchaseTable;

	public CreatedRestockOrderView() {
		setTitle("Resumen de Compra");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(700, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());

		// Panel superior con título
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.setBackground(Color.WHITE);
		topPanel.add(createLabel("El pedido de restock fue realizado con éxito", Font.BOLD, 18));
		topPanel.add(createLabel("Resumen:", Font.PLAIN, 14));
		topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
		getContentPane().add(topPanel, BorderLayout.NORTH);

		// Tabla de compra
		getContentPane().add(getSPnDetailedPurchase(), BorderLayout.CENTER);

		// Panel inferior con cerrar
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		closeBtn = new JButton("Aceptar");
		closeBtn.setPreferredSize(new Dimension(150, 30));
		closeBtn.addActionListener(e -> dispose());
		buttonPanel.add(closeBtn);

		// Contenedor final (datos + botones)
		JPanel southPanel = new JPanel();
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

}
