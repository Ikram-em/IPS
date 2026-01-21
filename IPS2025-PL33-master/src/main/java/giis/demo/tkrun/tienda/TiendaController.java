package giis.demo.tkrun.tienda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import giis.demo.tkrun.AbstractController;
import giis.demo.tkrun.logger.AuditService;
import giis.demo.tkrun.login.Session;
import giis.demo.ui.tienda.MerchPurchaseView;
import giis.demo.ui.tienda.PnProductData;
import giis.demo.ui.tienda.PnProductInCart;
import giis.demo.ui.tienda.TiendaView;

public class TiendaController extends AbstractController {
	private TiendaView tiendaView;
	private ProductModel productModel;
	private Map<Integer, UserProductViewDTO> userProducts = new HashMap<Integer, UserProductViewDTO>();

	public TiendaController(TiendaView tienda, AuditService audit) {
		super(audit);
		this.tiendaView = tienda;
		this.productModel = new ProductModel();
		initView();
	}

	private void initView() {
		loadProducts();
		this.tiendaView.setVisible(true);
	}

	private void loadProducts() {
		List<ProductDTO> products = this.productModel.getProducts();
		this.tiendaView.getPnProductsList().removeAll();
		for (ProductDTO product : products) {
			PnProductData pnProduct = new PnProductData(product);
			tiendaView.getPnProductsList().add(pnProduct);
			addProductActionListeners(pnProduct, product);
		}
		addFinishPurchaseListener();

		tiendaView.getPnProductsList().revalidate();
		tiendaView.getPnProductsList().repaint();
	}

	private void addProductActionListeners(PnProductData pn, ProductDTO product) {
		addLoggedAction(pn.getCartButton(), "Añadir producto al carrito: idProducto=" + product.getId_producto(),
				() -> {
					if (product.getStock() <= 0) {
						JOptionPane.showMessageDialog(tiendaView, "Producto sin stock", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					int userId = Session.get().getUserId();
					UserProductViewDTO userProductViewDTO = new UserProductViewDTO(userId, product, 1);

					PnProductInCart pnProductInCart = new PnProductInCart(userProductViewDTO, product.getPrecio());
					tiendaView.getPnProductsCart().add(pnProductInCart);
					pn.getCartButton().setEnabled(false);

					userProducts.put(product.getId_producto(), userProductViewDTO);
					userProducts.put(product.getId_producto(), userProductViewDTO);

				
					Float currentTotal = Float.parseFloat(tiendaView.getProductsTotal().getText());
					tiendaView.getProductsTotal()
						.setText(String.valueOf(currentTotal + userProductViewDTO.getProduct().getPrecio()));

					addCartActionListeners(pnProductInCart, userProductViewDTO, pn);
					addCartActionListeners(pnProductInCart, userProductViewDTO, pn);

					tiendaView.getPnProductsList().revalidate();
					tiendaView.getPnProductsList().repaint();
					tiendaView.getPnProductsCart().revalidate();
					tiendaView.getPnProductsCart().repaint();
				});
	}

	private void addCartActionListeners(PnProductInCart pnProductInCart, UserProductViewDTO userProductViewDTO,
			PnProductData pn) {

		pnProductInCart.getBtnLess().addActionListener(e -> {
			updateQuantity(userProductViewDTO, pnProductInCart, pn, -1);
		});

		pnProductInCart.getBtnMore().addActionListener(e -> {

			// COMPRUEBA SI HAY STOCK
			if (userProductViewDTO.getProduct().getStock() < userProductViewDTO.getQuantity() + 1) {
				JOptionPane.showMessageDialog(tiendaView, "No hay suficiente stock para añadir este producto.",
						"Stock insuficiente", JOptionPane.ERROR_MESSAGE);
				return;
			}

			updateQuantity(userProductViewDTO, pnProductInCart, pn, 1);
		});
	}

	private void updateQuantity(UserProductViewDTO userProductViewDTO, PnProductInCart pnProductInCart,
	        PnProductData pn, int delta) {

	    int currentQuantity = userProductViewDTO.getQuantity();
	    int newQuantity = currentQuantity + delta;

	    // Comprobación de stock al aumentar
	    if (delta > 0) {
	        if (newQuantity > userProductViewDTO.getProduct().getStock()) {
	            JOptionPane.showMessageDialog(tiendaView,
	                    "No puedes añadir más unidades. Stock disponible: "
	                            + userProductViewDTO.getProduct().getStock(),
	                    "Stock insuficiente", JOptionPane.WARNING_MESSAGE);
	            return; 
	        }
	    }
	    
	    if (newQuantity <= 0) {
			tiendaView.getPnProductsCart().remove(pnProductInCart);
			userProducts.remove(userProductViewDTO.getProduct().getId_producto());
			pn.getCartButton().setEnabled(true);
		} else {
			userProductViewDTO.setQuantity(newQuantity);
			pnProductInCart.getQuantity().setText(String.valueOf(newQuantity));
		}

	    //  Actualizar la cantidad
	    userProductViewDTO.setQuantity(Math.max(newQuantity, 0));
	    pnProductInCart.getQuantity().setText(String.valueOf(userProductViewDTO.getQuantity()));

	    Float currentTotal = Float.parseFloat(tiendaView.getProductsTotal().getText());
	    tiendaView.getProductsTotal()
	            .setText(String.valueOf(currentTotal + (userProductViewDTO.getProduct().getPrecio() * delta)));

	    tiendaView.getPnProductsList().revalidate();
	    tiendaView.getPnProductsList().repaint();
	    tiendaView.getPnProductsCart().revalidate();
	    tiendaView.getPnProductsCart().repaint();
	}

	private void addFinishPurchaseListener() {
		addLoggedAction(tiendaView.getBuyBtn(), "Finalizar compra", () -> {
			if (!userProducts.isEmpty()) {
	        	int userId = Session.get().getUserId();

				// Registrar en el log cada producto con su cantidad
				userProducts.values()
						.forEach(up -> audit.log("COMPRA_PRODUCTO", "idProducto=" + up.getProduct().getId_producto()
								+ ", cantidad=" + up.getQuantity() + ", usuarioId=" + userId));

				new PurchaseController(new MerchPurchaseView(), userProducts, audit);
	            productModel.addPurchase(userId, new ArrayList<>(userProducts.values()), "Merchandising");
	            tiendaView.dispose();
	        }
		});

	}

}
