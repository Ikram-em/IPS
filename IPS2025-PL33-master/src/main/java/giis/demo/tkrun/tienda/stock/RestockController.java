package giis.demo.tkrun.tienda.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.login.Session;
import giis.demo.tkrun.tienda.ProductDTO;
import giis.demo.tkrun.tienda.ProductModel;
import giis.demo.tkrun.tienda.UserProductViewDTO;
import giis.demo.ui.tienda.PnProductInCart;
import giis.demo.ui.tienda.TiendaView;
import giis.demo.ui.tienda.stock.CreatedRestockOrderView;
import giis.demo.ui.tienda.stock.PnProductRestockData;

public class RestockController extends AbstractController {
	private TiendaView restockView;
	private ProductModel productModel;
	private Map<Integer, UserProductViewDTO> userProducts = new HashMap<Integer, UserProductViewDTO>();
	
	public RestockController(TiendaView restockView, AuditService audit) {
		super(audit);
		this.restockView = restockView;
		restockView.getBuyBtn().setText("Finalizar orden");
		this.productModel = new ProductModel();
		initView();
	}
	
	private void initView() {
		loadProducts();
		restockView.setVisible(true);
	}
	
	private void loadProducts() {
		List<ProductDTO> products = this.productModel.getProducts();
		this.restockView.getPnProductsList().removeAll();
		for (ProductDTO product : products) {
			PnProductRestockData pnProduct = new PnProductRestockData(product);
			restockView.getPnProductsList().add(pnProduct);
			addProductActionListeners(pnProduct, product);
		}
		addFinishPurchaseListener();

		restockView.getPnProductsList().revalidate();
		restockView.getPnProductsList().repaint();
	}
	
	private void addProductActionListeners(PnProductRestockData pn, ProductDTO product) {
		addLoggedAction(pn.getCartButton(), "Añadir producto a orden de restock: idProducto=" + product.getId_producto(),
				() -> {
					int userId = Session.get().getUserId();
					UserProductViewDTO userProductViewDTO = new UserProductViewDTO(userId, product, 1);

					PnProductInCart pnProductInCart = new PnProductInCart(userProductViewDTO, product.getPrecio_mayorista());
					restockView.getPnProductsCart().add(pnProductInCart);
					pn.getCartButton().setEnabled(false);

					userProducts.put(product.getId_producto(), userProductViewDTO);
				
					Float currentTotal = Float.parseFloat(restockView.getProductsTotal().getText());
					restockView.getProductsTotal()
						.setText(String.valueOf(currentTotal + userProductViewDTO.getProduct().getPrecio_mayorista()));

					addCartActionListeners(pnProductInCart, userProductViewDTO, pn);

					restockView.getPnProductsList().revalidate();
					restockView.getPnProductsList().repaint();
					restockView.getPnProductsCart().revalidate();
					restockView.getPnProductsCart().repaint();
				});
	}
	
	private void addCartActionListeners(PnProductInCart pnProductInCart, UserProductViewDTO userProductViewDTO, PnProductRestockData pn) {

		pnProductInCart.getBtnLess().addActionListener(e -> {
			updateQuantity(userProductViewDTO, pnProductInCart, pn, -1);
		});

		pnProductInCart.getBtnMore().addActionListener(e -> {
			updateQuantity(userProductViewDTO, pnProductInCart, pn, 1);
		});
	}
	
	private void updateQuantity(UserProductViewDTO userProductViewDTO, PnProductInCart pnProductInCart, PnProductRestockData pn, int delta) {
	    int currentQuantity = userProductViewDTO.getQuantity();
	    int newQuantity = currentQuantity + delta;
	    
	    if (newQuantity <= 0) {
	    	restockView.getPnProductsCart().remove(pnProductInCart);
			userProducts.remove(userProductViewDTO.getProduct().getId_producto());
			pn.getCartButton().setEnabled(true);
		} else {
			userProductViewDTO.setQuantity(newQuantity);
			pnProductInCart.getQuantity().setText(String.valueOf(newQuantity));
		}

	    //  Actualizar la cantidad
	    userProductViewDTO.setQuantity(Math.max(newQuantity, 0));
	    pnProductInCart.getQuantity().setText(String.valueOf(userProductViewDTO.getQuantity()));

	    Float currentTotal = Float.parseFloat(restockView.getProductsTotal().getText());
	    restockView.getProductsTotal()
	            .setText(String.valueOf(currentTotal + (userProductViewDTO.getProduct().getPrecio_mayorista() * delta)));

	    restockView.getPnProductsList().revalidate();
	    restockView.getPnProductsList().repaint();
	    restockView.getPnProductsCart().revalidate();
	    restockView.getPnProductsCart().repaint();
	}
	
	private void addFinishPurchaseListener() {
		addLoggedAction(restockView.getBuyBtn(), "Finalizar orden de restock", () -> {
			if (!userProducts.isEmpty()) {
	        	int userId = Session.get().getUserId();

				// Registrar en el log cada producto con su cantidad
				userProducts.values()
						.forEach(up -> audit.log("RESTOCK_PRODUCTO_ORDENADO", "idProducto=" + up.getProduct().getId_producto()
								+ ", cantidad=" + up.getQuantity() + ", usuarioId=" + userId));

				new CreatedRestockOrderController(new CreatedRestockOrderView(), audit, userProducts);
	            productModel.addRestockOrder(userId, new ArrayList<>(userProducts.values()));
	            restockView.dispose();
	        }
		});

	}
}
