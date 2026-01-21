package giis.demo.ui.partidos.precios;

import java.awt.Font;

import javax.swing.JTextArea;

import giis.demo.tkrun.partidos.entradas.ZoneDTO;

public class PnSectionPrices extends PnZonePrices {

	private static final long serialVersionUID = 1L;
	private String origenPrecio;
	private Double precio;

	public PnSectionPrices(ZoneDTO zona, String tipoZona) {
		super(zona, tipoZona);
	}
	
	public void setPrecioConOrigen(Double precio, String origen) {
		prices.removeAll();
		this.origenPrecio = origen;
		this.precio = precio;
		if (precio != null) {
			prices.add(priceData(origenPrecio, precio.toString()));			
		}
		prices.revalidate();
		prices.repaint();
	}
	
	public void removePrices() {
		prices.removeAll();
		prices.revalidate();
		prices.repaint();
	}
	
	protected JTextArea createText(String text, int style, int size) {
		JTextArea textArea = new JTextArea(text);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setFont(new Font("SansSerif", style, size));
		textArea.setColumns(8);
		
		return textArea;
	}
	
	public Double getPrice() {
		return this.precio;
	}

}
