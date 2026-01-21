package giis.demo.ui.tienda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import giis.demo.tkrun.tienda.UserProductViewDTO;

public class PnProductInCart extends JPanel {
	private static final long serialVersionUID = 1L;
	private UserProductViewDTO userProduct;
	private JPanel pnCartProduct;
	private JButton btnLess;
	private JButton btnMore;
	private JPanel pnQuantity;
	private JLabel quantity;
	private double precio;
	
	public PnProductInCart(UserProductViewDTO userProduct, double precioProducto) {
		this.userProduct = userProduct;
		this.precio = precioProducto;
		setBorder(new LineBorder(Color.GRAY, 1, true));
		setBackground(Color.WHITE);
		
		setLayout(new BorderLayout(8, 0));
		setPreferredSize(new Dimension(250, 80));
		setMaximumSize(new Dimension(250, 100));
		
		add(getPnCartProduct(), BorderLayout.WEST);
		add(getQuantityButtons(), BorderLayout.EAST);
	}
	
	private JPanel getPnCartProduct() {
		if (pnCartProduct == null) {
			pnCartProduct = new JPanel();
			pnCartProduct.setBackground(new Color(255, 255, 255));
			pnCartProduct.setLayout(new BoxLayout(pnCartProduct, BoxLayout.Y_AXIS));
			pnCartProduct.setPreferredSize(new Dimension(100, 90));
			pnCartProduct.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 0));
			
			pnCartProduct.add(createLabel(this.userProduct.getProduct().getNombre(), Font.BOLD, 14));
			pnCartProduct.add(createLabel(this.userProduct.getProduct().getTipo(), Font.ITALIC, 14));
			pnCartProduct.add(createLabel(String.format("%s", "€" + this.precio), Font.PLAIN, 14));			
		}
		return pnCartProduct;
	}
	
	private JLabel createLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, size));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(8,0,0,0));
        return label;
    }
	
	public JPanel getQuantityButtons() {
		if (pnQuantity == null) {
			pnQuantity = new JPanel();
			pnQuantity.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 0));
			pnQuantity.setBackground(Color.WHITE);
			
			pnQuantity.add(getBtnLess());
			pnQuantity.add(getQuantity());
			pnQuantity.add(getBtnMore());
		}
		
		 JPanel wrapper = new JPanel(new GridBagLayout());
		    wrapper.setBackground(Color.WHITE);
		    wrapper.add(pnQuantity, new GridBagConstraints());

		    return wrapper;
	}
	
	public JButton getBtnLess() {
		if (btnLess == null) {
			btnLess = new JButton("-");	
		}
		return btnLess;
	}
	
	public JButton getBtnMore() {
		if (btnMore == null) {
			btnMore = new JButton("+");			
		}
		return btnMore;
	}
	
	public JLabel getQuantity() {
		if (quantity == null) {
			quantity = createLabel(String.format("%s", this.userProduct.getQuantity()), Font.BOLD, 14);
		}
		return quantity;
	}
}
