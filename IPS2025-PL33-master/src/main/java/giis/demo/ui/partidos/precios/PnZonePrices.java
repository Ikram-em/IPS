package giis.demo.ui.partidos.precios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import giis.demo.tkrun.partidos.entradas.ZoneDTO;

public abstract class PnZonePrices extends JPanel {

	private static final long serialVersionUID = -3886951330217556811L;
	private ZoneDTO zone;
	protected JPanel prices;
	private JButton btnAddPrice;
	private String tipoZona;
	private JScrollPane scrollPnPrices;

	public PnZonePrices(ZoneDTO zona, String tipoZona) {
		this.zone = zona;
		this.tipoZona = tipoZona;
		setBorder(new LineBorder(Color.GRAY, 1, true));
		setBackground(Color.WHITE);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(getTribuneNamePn());
		add(getPnPrices());
	}
	
	private JPanel getTribuneNamePn() {
		JPanel pnName = new JPanel();
		pnName.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnName.setBackground(Color.WHITE);
		pnName.add(createLabel(tipoZona + " " + zone.getNombre(), Font.BOLD, 14));
		return pnName;
	}
	
	private JPanel getPnPrices() {
		JPanel pnPrices = new JPanel();
		pnPrices.setLayout(new BorderLayout());
		pnPrices.setBackground(Color.WHITE);
		pnPrices.add(getSPrices(), BorderLayout.CENTER);
		pnPrices.add(getBtnAddPrice(), BorderLayout.SOUTH);
		return pnPrices;
	}
	
	public JScrollPane getSPrices() {
		if (scrollPnPrices == null) {
	        scrollPnPrices = new JScrollPane(getPrices());
	        JScrollBar verticalBar = scrollPnPrices.getVerticalScrollBar();
	        verticalBar.setPreferredSize(new Dimension(4, Integer.MAX_VALUE));
	        JScrollBar horizontalBar = scrollPnPrices.getHorizontalScrollBar();
	        horizontalBar.setPreferredSize(new Dimension(Integer.MAX_VALUE, 4));
	    }
	    return scrollPnPrices;
	}
	
	public JPanel getPrices() {
		if (prices == null) {
			prices = new JPanel();
			prices.setLayout(new BoxLayout(prices, BoxLayout.Y_AXIS));
	        prices.setBackground(Color.WHITE);
		}
		return prices;
	}
	
	public JButton getBtnAddPrice() {
		if (btnAddPrice == null) {
			btnAddPrice = new JButton("Cambiar precio");
		}
		return btnAddPrice;
	}
	
	public JPanel priceData(String key, String value) {
		JPanel priceData = new JPanel();
		priceData.setLayout(new FlowLayout(FlowLayout.LEFT));
		priceData.setBorder(BorderFactory.createEmptyBorder(0,5,0,0));
		priceData.setBackground(Color.WHITE);
		priceData.add(createText(key + ": " + value + "€", Font.ITALIC, 12));
		
		return priceData;
	}
	
	private JLabel createLabel(String text, int style, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, size));
        label.setBorder(BorderFactory.createEmptyBorder(8,0,8,0));
        label.setBackground(Color.WHITE);
        return label;
    }
	
	protected abstract JTextArea createText(String text, int style, int size);
	
	public ZoneDTO getZona() {
		return this.zone;
	}

}
