package giis.demo.tkrun.partidos.precios;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.AbstractMap.SimpleEntry;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import giis.demo.tkrun.partidos.entradas.EntradasModel;
import giis.demo.tkrun.partidos.entradas.ZoneDTO;
import giis.demo.ui.partidos.precios.PnSectionPrices;
import giis.demo.ui.partidos.precios.PnTribunePrices;
import giis.demo.ui.partidos.precios.PrecioSeccionView;
import giis.demo.ui.partidos.precios.PreciosTribunaView;

public class PreciosTribunaController {
	private PreciosTribunaView preciosTribunaView;
	private EntradasModel entradasModel;
	private ZoneDTO tribuna;
	private List<ZoneDTO> secciones;
	private TribunePricesDTO tribunePricesDTO;
	private PnTribunePrices pnTribunePrices;
	
	public PreciosTribunaController(PreciosTribunaView preciosTribunaView, ZoneDTO tribuna, TribunePricesDTO tribunePricesDTO, PnTribunePrices pnTribunePrices) {
		this.tribuna = tribuna;
		this.entradasModel = new EntradasModel();
		this.pnTribunePrices = pnTribunePrices;
		this.tribunePricesDTO = tribunePricesDTO;
		loadSectionData();
	}
	
	public void initView(PreciosTribunaView preciosTribunaView) {
		this.preciosTribunaView = preciosTribunaView;
		loadSectionPanels();
		addActionListeners();
		this.preciosTribunaView.setVisible(true);
	}
	
	public void setPrecioGeneralPartido(Double precioPartido) {
		updatePnTribunePrices(precioPartido);
		tribunePricesDTO.setPrecioGeneral(precioPartido, "Partido");
	}
	
	public void removePrecioGeneralPartido() {
		removePnTribunePrice();
		tribunePricesDTO.removePrecioGeneral();
	}
	
	public Map<Integer, Double> getPriceBySection() {
		return this.tribunePricesDTO.getPriceBySection(secciones.stream().map(s -> s.getId()).collect(Collectors.toList()));
	}
	
	public int getTribuneId() {
		return this.tribuna.getId();
	}
	
	private void loadSectionData() {
		secciones = this.entradasModel.getSections();
	}
	
	private void updatePnTribunePrices(Double precioPartido) {
		pnTribunePrices.getPrices().removeAll();
		pnTribunePrices.getPrices().add(pnTribunePrices.priceData("General partido", precioPartido.toString()));
		pnTribunePrices.getPrices().revalidate();
		pnTribunePrices.getPrices().repaint();
	}
	
	private void removePnTribunePrice() {
		pnTribunePrices.getPrices().removeAll();
		pnTribunePrices.getPrices().revalidate();
		pnTribunePrices.getPrices().repaint();
	}
	
	private void loadSectionPanels() {
		preciosTribunaView.getSectionPriceMapPn().removeAll();
		for (ZoneDTO seccion : secciones) {
			PnSectionPrices pnSectionPrices = new PnSectionPrices(seccion, "Sección");
			SimpleEntry<String, Double> sectionPrice = tribunePricesDTO.getPriceById(seccion.getId());
			if (sectionPrice != null) {
				pnSectionPrices.setPrecioConOrigen(sectionPrice.getValue(), sectionPrice.getKey());
			}
			preciosTribunaView.getSectionPriceMapPn().add(pnSectionPrices);
            pnSectionPrices.getBtnAddPrice().addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		new PrecioSeccionController(new PrecioSeccionView(pnSectionPrices.getPrice(), pnSectionPrices), tribunePricesDTO, seccion);
            	}
            });
        }
		if (tribunePricesDTO.getPriceById(0) == null) {
			tribunePricesDTO.setPrecioPorId(0, "Tribuna", null);			
		}
	}
	
	private void addActionListeners() {
		JTextField input = preciosTribunaView.getPnPrecioGeneral().getInputPrecioGral();
		
		input.addActionListener(e -> setPrecioGral());
		
		preciosTribunaView.getPnPrecioGeneral().getAcceptButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setPrecioGral();
            }
        });
		
		preciosTribunaView.getPnPrecioGeneral().getTrashButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	removePrecioGeneral();
            }
        });
		
		preciosTribunaView.getAcceptButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	addTribunePrices();
            }
        });
	}
	
	private void setPrecioGral() {
		JTextField input = preciosTribunaView.getPnPrecioGeneral().getInputPrecioGral();
		String text = input.getText().trim();

    	if (!isValidPrice(text)) {
    	    JOptionPane.showMessageDialog(preciosTribunaView, "El precio debe ser un número mayor a 0");
    	    return;
    	}

    	Double precioGeneral = Double.parseDouble(text);
    	for (Component pnSectionComponent : preciosTribunaView.getSectionPriceMapPn().getComponents()) {
    		PnSectionPrices pnSection = (PnSectionPrices) pnSectionComponent;
    		pnSection.setPrecioConOrigen(precioGeneral, "Precio general tribuna");
    	}
    	tribunePricesDTO.setPrecioGeneral(precioGeneral, "Tribuna");
	}
	
	private void removePrecioGeneral() {
		preciosTribunaView.getPnPrecioGeneral().getInputPrecioGral().setText(null);
		
		for (Component pnSectionComponent : preciosTribunaView.getSectionPriceMapPn().getComponents()) {
    		PnSectionPrices pnSection = (PnSectionPrices) pnSectionComponent;
    		pnSection.removePrices();
    	}
		tribunePricesDTO.removePrecioGeneral();
	}
	
	private void addTribunePrices() {
		boolean allPriced = true;
		for (Component pnSectionComponent : preciosTribunaView.getSectionPriceMapPn().getComponents()) {
    		PnSectionPrices pnSection = (PnSectionPrices) pnSectionComponent;
    		Double sectionPrice = pnSection.getPrice();
    		if (sectionPrice == null) {
    			allPriced = false;
    			break;
    		}
    	}
		if (!allPriced) {
			JOptionPane.showMessageDialog(preciosTribunaView, "Todas las secciones deben tener precio");
			return;
		}
		pnTribunePrices.getPrices().removeAll();
		Map<String, Double> tribunePricesMap = tribunePricesDTO.getSpecificPrices();
		for (Map.Entry<String, Double> precioPorZona : tribunePricesMap.entrySet()) {
			pnTribunePrices.getPrices().add(pnTribunePrices.priceData(precioPorZona.getKey(), precioPorZona.getValue().toString()));
		}
		pnTribunePrices.getPrices().revalidate();
		pnTribunePrices.getPrices().repaint();
		
		preciosTribunaView.dispose();
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
