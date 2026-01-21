package giis.demo.ui.tienda.stock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class StockView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable stockTable;
    private JScrollPane scrollPane;
    private JButton btnRestock;
    private DefaultTableModel tableModel;
    private JScrollPane ongoingOrdersSP;
    private JPanel ongoingOrdersPn;

    public StockView() {
        setTitle("Gestión de Stock");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        // Logo arriba
        JLabel logoLabel = createLogoLabel();
        if (logoLabel != null) {
            getContentPane().add(logoLabel, BorderLayout.NORTH);
        }
        
        getContentPane().add(getCenterPn(), BorderLayout.CENTER);
        getContentPane().add(getBottomPn(), BorderLayout.SOUTH);
    }
    
    private JPanel getCenterPn() {
    	JPanel centerPn = new JPanel();
    	centerPn.setLayout(new BoxLayout(centerPn, BoxLayout.Y_AXIS));
    	
    	// Tabla de stock
    	scrollPane = new JScrollPane(getStockTable());
    	scrollPane.setBorder(BorderFactory.createTitledBorder("Stock de productos"));
    	
    	
    	centerPn.add(scrollPane);
    	centerPn.add(getOngoingOrdersSP());
    	
    	return centerPn;
    }
    
    private JScrollPane getOngoingOrdersSP() {
		if (ongoingOrdersSP == null) {
			ongoingOrdersSP = new JScrollPane(getOngoingOrdersPn());
			ongoingOrdersSP.setBorder(BorderFactory.createTitledBorder("Ordenes en curso"));
			ongoingOrdersSP.setBackground(Color.WHITE);
			ongoingOrdersSP.getVerticalScrollBar().setUnitIncrement(16);
			ongoingOrdersSP.setViewportView(getOngoingOrdersPn());
		}
		return ongoingOrdersSP;
    }
    
    public JPanel getOngoingOrdersPn() {
    	if (ongoingOrdersPn == null) {
    		ongoingOrdersPn = new JPanel();
    		ongoingOrdersPn.setBackground(Color.WHITE);
    		ongoingOrdersPn.setLayout(new BoxLayout(ongoingOrdersPn, BoxLayout.Y_AXIS));
		}
		return ongoingOrdersPn;
    }

    private JLabel createLogoLabel() {
        URL logoURL = getClass().getClassLoader().getResource("images/stock_logo.png");
        if (logoURL != null) {
            ImageIcon originalIcon = new ImageIcon(logoURL);
            // Escalar imagen con altura máxima de 150 px manteniendo proporción
            int maxHeight = 120;
            double scale = (double) maxHeight / originalIcon.getIconHeight();
            int newWidth = (int) (originalIcon.getIconWidth() * scale);
            int newHeight = (int) (originalIcon.getIconHeight() * scale);
            Image scaledImage = originalIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            return logoLabel;
        } else {
            System.err.println("No se encontró el logo en el classpath!");
            return null;
        }
    }

    public JTable getStockTable() {
        if (stockTable == null) {
            stockTable = new JTable(getTableModel());
            stockTable.setFillsViewportHeight(true);
            stockTable.setRowHeight(25);
            stockTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        }
        return stockTable;
    }
    
    public DefaultTableModel getTableModel() {
    	if (tableModel == null) {
    		String[] columns = {"Nombre", "Tipo", "Precio proveedor (€)", "Precio minorista (€)", "Stock"};
    		tableModel = new DefaultTableModel(columns, 0) {
                private static final long serialVersionUID = 1L;
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
    	}
    	return tableModel;
    }

    public void setStockData(Object[][] data) {
        DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
        model.setRowCount(0);
        for (Object[] row : data) {
            model.addRow(row);
        }
    }
    
    private JPanel getBottomPn() {
    	JPanel bottomPn = new JPanel();
    	bottomPn.setLayout(new FlowLayout(FlowLayout.CENTER));
    	bottomPn.add(getBtnRestock());
    	
    	return bottomPn;
    }
    
    public JButton getBtnRestock() {
		if (btnRestock == null) {
			btnRestock = new JButton("Realizar pedido ");
			btnRestock.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnRestock.setPreferredSize(new Dimension(150, 30));
			//btnRestock.setMaximumSize(new Dimension(50, 10));
			//btnRestock.setMinimumSize(new Dimension(50, 10));
		}
		return btnRestock;
	}
}

