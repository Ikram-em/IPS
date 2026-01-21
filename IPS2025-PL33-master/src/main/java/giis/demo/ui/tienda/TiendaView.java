package giis.demo.ui.tienda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class TiendaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane sPnProductsList;
	private JScrollPane sPnProductsCart;
	private JPanel pnProductsList;
	private JPanel pnProductsCartList;
	private JPanel pnCartTotal;
	private JLabel productsTotal;
	private JButton buyBtn;

	public TiendaView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 775, 443);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		JPanel leftPanel = new JPanel(new BorderLayout());
		leftPanel.add(getSPnProductList(), BorderLayout.CENTER);
		
		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.add(getCartPanel(), BorderLayout.CENTER);
		rightPanel.add(getCartTotalPanel(), BorderLayout.SOUTH);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		splitPane.setResizeWeight(0.50);

		contentPane.add(splitPane, BorderLayout.CENTER);
	}
	
	public JScrollPane getSPnProductList() {
		if (sPnProductsList == null) {
			sPnProductsList = new JScrollPane(getPnProductsList());
			sPnProductsList.setBorder(new TitledBorder(null, "Productos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
			sPnProductsList.setBackground(Color.WHITE);
			sPnProductsList.getVerticalScrollBar().setUnitIncrement(16);
			sPnProductsList.setViewportView(getPnProductsList());
		}
		return sPnProductsList;
	}
	
	public JPanel getPnProductsList() {
		if (pnProductsList == null) {
			pnProductsList = new JPanel();
			pnProductsList.setBackground(Color.WHITE);
			pnProductsList.setLayout(new BoxLayout(pnProductsList, BoxLayout.Y_AXIS));
		}
		return pnProductsList;
	}
		
	public JScrollPane getCartPanel() {
		if (sPnProductsCart == null) {
			sPnProductsCart = new JScrollPane(getPnProductsCart()); 
			sPnProductsCart.setBorder(new TitledBorder(null, "Cart", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY)); 
			sPnProductsCart.setBackground(Color.WHITE);
			sPnProductsCart.getVerticalScrollBar().setUnitIncrement(16);
		} 
		return sPnProductsCart;
	}
	
	public JPanel getPnProductsCart() {
		if (pnProductsCartList == null) {
			pnProductsCartList = new JPanel();
			pnProductsCartList.setBackground(Color.WHITE);
			pnProductsCartList.setLayout(new BoxLayout(pnProductsCartList, BoxLayout.Y_AXIS));
		}
		return pnProductsCartList;
	}
	
	public JPanel getCartTotalPanel() {
		if (pnCartTotal == null) {
			pnCartTotal = new JPanel();
			pnCartTotal.setBackground(Color.WHITE);
			pnCartTotal.setBorder(new TitledBorder(null, "Total", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
			pnCartTotal.add(getProductsTotal());
			pnCartTotal.add(getBuyBtn());
		}
		return pnCartTotal;
	}
	
	public JLabel getProductsTotal() {
		if (productsTotal == null) {
			productsTotal = new JLabel(String.format("%s", 0));
			productsTotal.setFont(new Font("SansSerif", Font.BOLD, 14));
		}
		return productsTotal;
	}
	
	public JButton getBuyBtn() {
		if (buyBtn == null) {
			buyBtn = new JButton("Finalizar compra");
		}
		return buyBtn;
	}
}
