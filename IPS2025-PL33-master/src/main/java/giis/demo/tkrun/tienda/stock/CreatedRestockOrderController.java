package giis.demo.tkrun.tienda.stock;

import java.util.Map;

import javax.swing.table.DefaultTableModel;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.tienda.UserProductViewDTO;
import giis.demo.ui.tienda.stock.CreatedRestockOrderView;

public class CreatedRestockOrderController extends AbstractController {
	private CreatedRestockOrderView createdRestockOrderView;
	private Map<Integer, UserProductViewDTO> userProducts;
	
	public CreatedRestockOrderController(CreatedRestockOrderView createdRestockOrderView, AuditService audit, Map<Integer, UserProductViewDTO> userProducts) {
		super(audit);
		this.createdRestockOrderView = createdRestockOrderView;
		this.userProducts = userProducts;
		initView();
	}
	
	private void initView() {
        loadDetails();
        this.createdRestockOrderView.setVisible(true);
    }
	
	private void loadDetails() {
        DefaultTableModel model = (DefaultTableModel) this.createdRestockOrderView.getPnPurchaseTable().getModel();
        model.setRowCount(0);

        for (UserProductViewDTO userProductView : userProducts.values()) {
            model.addRow(new Object[]{
                    userProductView.getProduct().getNombre(),
                    userProductView.getQuantity(),
                    userProductView.getProduct().getPrecio_mayorista(),
                    userProductView.getProduct().getPrecio_mayorista() * userProductView.getQuantity()
            });
        }
    }
}
