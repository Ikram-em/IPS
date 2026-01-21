package giis.demo.tkrun.partidos.precios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.partidos.PartidoDTO;
import giis.demo.tkrun.partidos.entradas.EntradasModel;
import giis.demo.tkrun.partidos.entradas.ZoneDTO;
import giis.demo.ui.partidos.precios.PnTribunePrices;
import giis.demo.ui.partidos.precios.PreciosEntradasView;
import giis.demo.ui.partidos.precios.PreciosTribunaView;

public class PreciosEntradasController extends AbstractController {
	private PreciosEntradasView preciosEntradasView;
	private EntradasModel entradasModel;
	private PartidoDTO partidoDTO;
	private List<PreciosTribunaController> preciosTribunaControllers = new ArrayList<PreciosTribunaController>();
	
	public PreciosEntradasController(PreciosEntradasView preciosEntradasView, PartidoDTO partido, AuditService audit) {
		super(audit);
		this.preciosEntradasView = preciosEntradasView;
		this.entradasModel = new EntradasModel();
		this.partidoDTO = partido;
		initView();
	}
	
	private void initView() {
		loadSections();
		addActionListeners();
		this.preciosEntradasView.setVisible(true);
	}
	
	private void loadSections() {
		preciosEntradasView.getTribunePriceMapPn().removeAll();
		List<ZoneDTO> tribunas = this.entradasModel.getTribunes();
		for (ZoneDTO tribuna : tribunas) {
			TribunePricesDTO tribunePricesDTO = new TribunePricesDTO();
			PnTribunePrices pnTribunePrices = new PnTribunePrices(tribuna, "Tribuna");
			PreciosTribunaController precioTribunaController = new PreciosTribunaController(new PreciosTribunaView(null, tribuna), tribuna, tribunePricesDTO, pnTribunePrices);
			preciosTribunaControllers.add(precioTribunaController);
            preciosEntradasView.getTribunePriceMapPn().add(pnTribunePrices);
            
            for (ActionListener al : pnTribunePrices.getBtnAddPrice().getActionListeners()) {
                pnTribunePrices.getBtnAddPrice().removeActionListener(al);
            }
            
            pnTribunePrices.getBtnAddPrice().addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
                	Double precioInicial = null;
                	SimpleEntry<String, Double> precioGeneral = tribunePricesDTO.getPriceById(0);
                	if (precioGeneral != null) {
                		precioInicial = precioGeneral.getValue();
                	}
            		precioTribunaController.initView(new PreciosTribunaView(precioInicial, tribuna));
            	}
            });
        }
	}
	
	private void addActionListeners() {
		JTextField input = preciosEntradasView.getPnPrecioGeneral().getInputPrecioGral();
		
		input.addActionListener(e -> setPrecioGral());
		
		preciosEntradasView.getPnPrecioGeneral().getAcceptButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setPrecioGral();
            }
        });
		
		preciosEntradasView.getPnPrecioGeneral().getTrashButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	removePrecioGeneral();
            }
        });
		
		addLoggedAction(preciosEntradasView.getAcceptButton(),
				"Asignación de precio de entrada para el partido con id=" + partidoDTO.getId_partido(), () -> {
					addMatchPrices();
				});

	}
	
	private void addMatchPrices() {
		boolean allPriced = true;
		Map<Integer, Map<Integer, Double>> priceByTribuneAndSection = new HashMap<Integer, Map<Integer, Double>>();
		
		for (PreciosTribunaController controller : preciosTribunaControllers) {
			Map<Integer, Double> priceBySectionMap = controller.getPriceBySection();
    		if (priceBySectionMap.size() < 6) {
    			allPriced = false;
    			break;
    		}
    		priceByTribuneAndSection.put(controller.getTribuneId(), priceBySectionMap);
    	}
		if (!allPriced) {
			JOptionPane.showMessageDialog(preciosEntradasView, "Todas las tribunas deben tener precio");
			return;
		}
		preciosEntradasView.dispose();
		entradasModel.saveTicketPrices(partidoDTO.getId_partido(), priceByTribuneAndSection);
	}
	
	private void setPrecioGral() {
		JTextField input = preciosEntradasView.getPnPrecioGeneral().getInputPrecioGral();
		String text = input.getText().trim();

    	if (!isValidPrice(text)) {
    	    JOptionPane.showMessageDialog(preciosEntradasView, "El precio debe ser un número mayor a 0");
    	    return;
    	}

    	for (PreciosTribunaController controller : preciosTribunaControllers) {
    		controller.setPrecioGeneralPartido(Double.parseDouble(text));
    	}
	}
	
	private void removePrecioGeneral() {
		preciosEntradasView.getPnPrecioGeneral().getInputPrecioGral().setText(null);;
		
		for (PreciosTribunaController controller : preciosTribunaControllers) {
    		controller.removePrecioGeneralPartido();
    	}
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
