package giis.demo.ui.partidos.precios;

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
import javax.swing.JTextField;

public class PnPrecioGeneral extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField inputPrecioGral;
	private Double precioInicial;
	private JButton acceptButton;
	private JButton trashButton;

	public PnPrecioGeneral(Double precioGeneral) {
		this.precioInicial = precioGeneral;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createCompoundBorder(
			    BorderFactory.createLineBorder(Color.BLACK, 1, true),
			    BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		add(createLabel("Precio general:", Font.BOLD, 14));
		add(Box.createHorizontalGlue());
		add(getPnInputPrecioGral());
	}
	
	private JPanel getPnInputPrecioGral() {
		JPanel pnInput = new JPanel();
		pnInput.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnInput.setPreferredSize(new Dimension(150, 35));
		pnInput.setMaximumSize(new Dimension(150, 35));
		pnInput.setBackground(Color.WHITE);
		pnInput.add(createLabel("€ ", Font.PLAIN, 14));
		pnInput.add(getInputPrecioGral());
		pnInput.add(getAcceptButton());
		pnInput.add(getTrashButton());
		return pnInput;
	}
		
	public JTextField getInputPrecioGral() {
		if (inputPrecioGral == null) {
			inputPrecioGral = new JTextField();
			if (precioInicial != null) {
				inputPrecioGral.setText(String.valueOf(precioInicial));
			}
			inputPrecioGral.setColumns(4);
			inputPrecioGral.setPreferredSize(new Dimension(80, 28));
			inputPrecioGral.setMaximumSize(new Dimension(80, 28));
		}
		return inputPrecioGral;
	}
	
	public JButton getAcceptButton() {
		if (acceptButton == null) {
			acceptButton = new JButton();
			InputStream is = getClass().getResourceAsStream("/images/check.png");
			BufferedImage img = null;
			try {
				img = ImageIO.read(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image scaledImg = img.getScaledInstance(12, 12, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(scaledImg);
			acceptButton.setIcon(icon);
		}
		return acceptButton;
	}
	
	public JButton getTrashButton() {
		if (trashButton == null) {
			trashButton = new JButton();
			InputStream is = getClass().getResourceAsStream("/images/trash.png");
			BufferedImage img = null;
			try {
				img = ImageIO.read(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image scaledImg = img.getScaledInstance(12, 12, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(scaledImg);
			trashButton.setIcon(icon);
		}
		return trashButton;
	}
	
	private JLabel createLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, size));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(8,0,0,0));
        return label;
    }

}
