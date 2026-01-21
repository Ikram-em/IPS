package giis.demo.ui.tienda.stock;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import giis.demo.tkrun.tienda.ProductDTO;

public class PnProductRestockData extends JPanel {

	private static final long serialVersionUID = 1L;
	private ProductDTO product;
	private JPanel pnProductData;
	private JButton btnCart;
	private JPanel pnProductImage;

	public PnProductRestockData(ProductDTO product) {
		this.product = product;
		setBorder(new LineBorder(Color.GRAY, 1, true));
		setBackground(Color.WHITE);
		setLayout(new FlowLayout(FlowLayout.LEFT, 4, 0));
		setPreferredSize(new Dimension(300, 150));
		
		add(getProductImage());
		add(getPnProductData());
	}
	
	private JPanel getPnProductData() {
		if (pnProductData == null) {
			pnProductData = new JPanel();
			pnProductData.setBackground(new Color(255, 255, 255));
			pnProductData.setLayout(new BoxLayout(pnProductData, BoxLayout.Y_AXIS));
			pnProductData.setPreferredSize(new Dimension(280, 148));
			pnProductData.setMaximumSize(new Dimension(280, 148));
			pnProductData.setMinimumSize(new Dimension(280, 148));
			
			pnProductData.add(getKeyValuePn(createLabel("Nombre: ", Font.BOLD, 15), createLabel(this.product.getNombre(), Font.PLAIN, 14)));
			pnProductData.add(getKeyValuePn(createLabel("Tipo: ", Font.BOLD, 15), createLabel(this.product.getTipo(), Font.PLAIN, 14)));
			pnProductData.add(getKeyValuePn(createLabel("Precio proveedor: ", Font.BOLD, 15), createLabel(String.format("%s", "€" + this.product.getPrecio_mayorista()), Font.PLAIN, 14)));
			pnProductData.add(Box.createVerticalGlue());

			pnProductData.add(getCartButton());
			pnProductData.add(Box.createVerticalStrut(5));
			
		}
		return pnProductData;
	}
	
	private JPanel getProductImage() {
		if (pnProductImage == null) {
			pnProductImage = new JPanel();
			
			InputStream is = getClass().getResourceAsStream(this.product.getImage());
			BufferedImage img = null;
			try {
				img = ImageIO.read(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image scaledImg = img.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(scaledImg);
			
			add(new JLabel(icon));
		}
		
		return pnProductImage;
	}
	
	public JButton getCartButton() {
		if (btnCart == null) {
			btnCart = new JButton("Add to cart");			
		}
		return btnCart;
	}
	
	private JLabel createLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, size));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(8,0,0,0));
        return label;
    }
	
	private JPanel getKeyValuePn(Component key, Component value) {
		JPanel keyValuePn = new JPanel();
		keyValuePn.setLayout(new FlowLayout(FlowLayout.LEFT));
		keyValuePn.setBackground(Color.WHITE);
		key.setPreferredSize(new Dimension(150, 30));
		value.setPreferredSize(new Dimension(100, 30));
		keyValuePn.add(key);
		keyValuePn.add(value);
		return keyValuePn;
	}

}
