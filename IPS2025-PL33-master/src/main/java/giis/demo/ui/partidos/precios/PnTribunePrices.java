package giis.demo.ui.partidos.precios;

import java.awt.Font;
import javax.swing.JTextArea;

import giis.demo.tkrun.partidos.entradas.ZoneDTO;

public class PnTribunePrices extends PnZonePrices {

	private static final long serialVersionUID = 1L;

	public PnTribunePrices(ZoneDTO zona, String tipoZona) {
		super(zona, tipoZona);
	}
	
	protected JTextArea createText(String text, int style, int size) {
		JTextArea textArea = new JTextArea(text);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setOpaque(false);
		textArea.setFont(new Font("SansSerif", style, size));
		textArea.setColumns(12);
		
		return textArea;
	}	
	
}
