package giis.demo.tkrun.partidos.precios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import giis.demo.tkrun.partidos.entradas.ZoneDTO;
import giis.demo.ui.partidos.precios.PrecioSeccionView;

public class PrecioSeccionController {
	private PrecioSeccionView precioSeccionView;
	private TribunePricesDTO tribunePricesDTO;
	private ZoneDTO section;
	
	public PrecioSeccionController(PrecioSeccionView precioSeccionView, TribunePricesDTO tribunePrices, ZoneDTO seccion) {
		this.precioSeccionView = precioSeccionView;
		this.tribunePricesDTO = tribunePrices;
		this.section = seccion;
		initView();
	}
	
	private void initView() {
		addActionListeners();
		this.precioSeccionView.setVisible(true);
	}
	
	private void addActionListeners() {
		precioSeccionView.getAcceptButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JTextField input = precioSeccionView.getPnPrecio().getInputPrecioGral();
        		String text = input.getText().trim();
        		Double precioSeccion = null;
        		
        		if (!isValidPrice(text)) {
        			JOptionPane.showMessageDialog(precioSeccionView, "El precio debe ser un número mayor a 0");
        			return;
        		}
        		precioSeccion = Double.parseDouble(text);
            	precioSeccionView.getPnSectionPrices().setPrecioConOrigen(precioSeccion, "Sección");
            	tribunePricesDTO.setPrecioPorId(section.getId(), "Sección " + section.getNombre(), precioSeccion);
            	precioSeccionView.dispose();
            }
        });
	}
	
	private boolean isValidPrice(String priceText) {
		try {
			Double.parseDouble(priceText);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
