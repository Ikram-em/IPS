package giis.demo.ui.partidos.precios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class PrecioSeccionView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Double price;
	private PnSectionPrices pnSectionPrices;
	private PnPrecioGeneral pnPrecio;
	private JButton acceptButton;

	public PrecioSeccionView(Double price, PnSectionPrices pnSectionPrices) {
		this.price = price;
		this.pnSectionPrices = pnSectionPrices;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 575, 243);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.WHITE);

		setContentPane(contentPane);
		contentPane.add(getPnTitle(), BorderLayout.NORTH);
		contentPane.add(getCenterPn(), BorderLayout.CENTER);
		contentPane.add(getBottomPanel(), BorderLayout.SOUTH);
	}
	
	private JPanel getPnTitle() {
		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnTitle.setBackground(Color.WHITE);
		pnTitle.add(createLabel("Sección  " + pnSectionPrices.getZona().getNombre(), Font.BOLD, 20));
		return pnTitle;
	}
	
	private JPanel getCenterPn() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setBorder(new EmptyBorder(20, 50, 20, 50));
		centerPanel.add(getPnPrecio());
		
		return centerPanel;
	}
	
	private JPanel getBottomPanel() {
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.setBorder(new EmptyBorder(20, 50, 20, 50));
		bottomPanel.add(getAcceptButton());
		
		return bottomPanel;
	}
	
	public JButton getAcceptButton() {
		if (acceptButton == null) {
			acceptButton = new JButton("Aceptar");
		}
		return acceptButton;
	}
	
	public PnPrecioGeneral getPnPrecio() {
		if (pnPrecio == null) {
			pnPrecio = new PnPrecioGeneral(price);
		}
		return pnPrecio;
	}
	
	private JLabel createLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, size));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(8,0,0,0));
        return label;
    }
	
	public PnSectionPrices getPnSectionPrices() {
		return this.pnSectionPrices;
	}

}
