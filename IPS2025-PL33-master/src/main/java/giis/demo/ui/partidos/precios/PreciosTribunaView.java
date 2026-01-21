package giis.demo.ui.partidos.precios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import giis.demo.tkrun.partidos.entradas.ZoneDTO;

public class PreciosTribunaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Double precioTribuna;
	ZoneDTO tribuna;
	private JPanel tribunePriceMapPn;
	private PnPrecioGeneral pnPrecioGeneral;
	private JButton acceptButton;

	public PreciosTribunaView(Double precioTribuna, ZoneDTO tribuna) {
		this.precioTribuna = precioTribuna;
		this.tribuna = tribuna;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 775, 443);
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
		pnTitle.add(createLabel("Tribuna  " + tribuna.getNombre(), Font.BOLD, 20));
		return pnTitle;
	}
	
	private JPanel getCenterPn() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setBorder(new EmptyBorder(20, 50, 20, 50));
		centerPanel.add(getPnPrecioGeneral());
		centerPanel.add(Box.createVerticalStrut(5));
		centerPanel.add(getPrecioTitlePanel());
		centerPanel.add(Box.createVerticalStrut(5));
		centerPanel.add(getSectionPriceMapPn());
		
		return centerPanel;
	}
	
	public JPanel getPrecioTitlePanel() {
		JPanel precioPanel = new JPanel();
		precioPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		precioPanel.add(createLabel("Precios:", Font.BOLD, 16));
		precioPanel.setBackground(Color.WHITE);
		precioPanel.setPreferredSize(new Dimension(800, 40));
		precioPanel.setMaximumSize(new Dimension(800, 40));
		
		return precioPanel;
	}
	
	public PnPrecioGeneral getPnPrecioGeneral() {
		if (pnPrecioGeneral == null) {
			pnPrecioGeneral = new PnPrecioGeneral(precioTribuna);
		}
		return pnPrecioGeneral;
	}
	
	public JPanel getSectionPriceMapPn() {
		if (tribunePriceMapPn == null) {
			tribunePriceMapPn = new JPanel();
			tribunePriceMapPn.setBackground(Color.WHITE);
			tribunePriceMapPn.setLayout(new GridLayout(1, 4));
		}
		return tribunePriceMapPn;
	}
	
	private JPanel getBottomPanel() {
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.add(getAcceptButton());
		
		return bottomPanel;
	}
	
	public JButton getAcceptButton() {
		if (acceptButton == null) {
			acceptButton = new JButton("Aceptar");
		}
		return acceptButton;
	}
	
	private JLabel createLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, size));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(8,0,0,0));
        return label;
    }
}
