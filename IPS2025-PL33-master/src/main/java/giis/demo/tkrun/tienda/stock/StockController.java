package giis.demo.tkrun.tienda.stock;

import java.util.List;

import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.tienda.ProductDTO;
import giis.demo.tkrun.tienda.ProductModel;
import giis.demo.ui.tienda.TiendaView;
import giis.demo.ui.tienda.stock.PnOngoingOrderData;
import giis.demo.ui.tienda.stock.StockView;

public class StockController extends AbstractController {

    private StockView view;
    private ProductModel model;
    private List<ProductDTO> products;
    private StockDAO stockDAO;

	public StockController(StockView view, AuditService audit) {
		super(audit);
        this.view = view;
        this.model = new ProductModel();
        this.stockDAO = new StockDAO();
        loadStock();
        loadOngoingOrders();
        addActionListeners();
        view.setVisible(true);
    }

    private void loadStock() {
        products = model.getProducts();
        Object[][] data = new Object[products.size()][5];

        for (int i = 0; i < products.size(); i++) {
            ProductDTO p = products.get(i);
            data[i][0] = p.getNombre();
            data[i][1] = p.getTipo();
            data[i][2] = p.getPrecio_mayorista();
            data[i][3] = String.format("%.2f", p.getPrecio());
            data[i][4] = p.getStock(); 
        }

        view.setStockData(data);
    }
    
    private void loadOngoingOrders() {
    	List<RestockOrderDTO> ongoingOrders = stockDAO.getOngoingOrders();
    	this.view.getOngoingOrdersPn().removeAll();
		for (RestockOrderDTO order : ongoingOrders) {
			PnOngoingOrderData pnOrder = new PnOngoingOrderData(order);
			if (order.isFinalizada()) {
				pnOrder.getFinishOrderBtn().setEnabled(false);				
			}
			view.getOngoingOrdersPn().add(pnOrder);
			addOrderActionListeners(pnOrder, order);
		}

		view.getOngoingOrdersPn().revalidate();
		view.getOngoingOrdersPn().repaint();
    }
    
    private void addActionListeners() {
    	addLoggedAction(view.getBtnRestock(), "Iniciar orden de restock",
				() -> new RestockController(new TiendaView(), audit));
    }
    
    private void addOrderActionListeners(PnOngoingOrderData orderDataPn, RestockOrderDTO order) {
    	addLoggedAction(orderDataPn.getFinishOrderBtn(), "Se marcó como recibida la orden de restock con id " + order.getId_order(), () -> {
    		stockDAO.updateAsReceived(order);
    		JOptionPane.showMessageDialog(view, "La orden se ha marcado como recibida", "Detalles de la orden", JOptionPane.PLAIN_MESSAGE);
    		orderDataPn.getFinishOrderBtn().setEnabled(false);
    	});
    	orderDataPn.getBtnVerDetalle().addActionListener(e -> {
    		JOptionPane.showMessageDialog(view, order.getResumen(), "Detalles de la orden", JOptionPane.PLAIN_MESSAGE);
    	});
    }
}

