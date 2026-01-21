package giis.demo.ui.tienda;

import java.awt.Color;

import javax.swing.JPanel;

import giis.demo.tkrun.tienda.ProductDTO;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.border.LineBorder;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;

public class PnProductData extends JPanel {
	private static final long serialVersionUID = 1L;
	private ProductDTO product;
	private JPanel pnProductData;
	private JPanel pnProductImage;
	private JButton btnCart;
	
	public PnProductData(ProductDTO product) {
		this.product = product;
		setBorder(new LineBorder(Color.GRAY, 1, true));
		setBackground(Color.WHITE);
		setLayout(new FlowLayout(FlowLayout.LEFT, 4, 0));
		setPreferredSize(new Dimension(200, 150));
		
		add(getProductImage());
		add(getPnProductData());

	}
	
	private JPanel getPnProductData() {
		if (pnProductData == null) {
			pnProductData = new JPanel();
			pnProductData.setBackground(new Color(255, 255, 255));
			pnProductData.setLayout(new BoxLayout(pnProductData, BoxLayout.Y_AXIS));
			pnProductData.setPreferredSize(new Dimension(200, 148));
			
			pnProductData.add(createLabel(this.product.getNombre(), Font.BOLD, 14));
			pnProductData.add(createLabel(this.product.getTipo(), Font.ITALIC, 14));
			pnProductData.add(createLabel(String.format("%s", "€" + this.product.getPrecio()), Font.PLAIN, 14));
			pnProductData.add(Box.createVerticalGlue());

			pnProductData.add(getCartButton());
			pnProductData.add(Box.createVerticalStrut(5));
			
		}
		return pnProductData;
	}
	
	private JLabel createLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, size));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(8,0,0,0));
        return label;
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
}
