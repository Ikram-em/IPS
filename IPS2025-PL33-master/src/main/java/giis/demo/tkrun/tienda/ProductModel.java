package giis.demo.tkrun.tienda;

import java.util.List;

import giis.demo.tkrun.ingresos.Ingreso;
import giis.demo.tkrun.ingresos.IngresoDAO;
import giis.demo.tkrun.ingresos.TipoIngreso;
import giis.demo.util.Database;

public class ProductModel {
	private Database database = new Database();
	
	private final static String SQL_FIND_PRODUCTS = "SELECT * FROM Producto";
	
	private final static String SQL_CREATE_PURCHASE = "INSERT INTO Purchase (id_user, fecha, precio, tipo) "
			+ "VALUES (?, datetime('now'), ?, ?)";
	
	private final static String SQL_CREATE_PURCHASE_ITEM =
		    "INSERT INTO Purchase_Item (id_purchase, id_producto, quantity) VALUES (?, ?, ?)";
	
	private final static String SQL_CREATE_RESTOCK_ORDER = "INSERT INTO RestockOrder (total, fecha, finalizada, resumen) VALUES (?, datetime('now'), 0, ?)";
	
	private final static String SQL_CREATE_RESTOCK_DETAIL = "INSERT INTO RestockDetail (id_producto, id_order, cantidad) VALUES (?, ?, ?)";
	
	private final static String SQL_CREATE_RESTOCK_EXPENSE = "INSERT INTO Gasto (total, tipo, fecha_hora, concepto) VALUES (?, 'Restock', ?, ?)";
	
	public List<ProductDTO> getProducts() {
		return database.executeQueryPojo(ProductDTO.class, SQL_FIND_PRODUCTS);
	}


	public void addPurchase(int userId, List<UserProductViewDTO> userProducts, String type) {
	    double total = 0;
	    StringBuilder concepto = new StringBuilder();
	    for (int i = 0; i < userProducts.size(); i++) {
	        UserProductViewDTO userProduct = userProducts.get(i);
	        double subtotal = userProduct.getProduct().getPrecio() * userProduct.getQuantity();
	        total += subtotal;
	        concepto.append(userProduct.getProduct().getNombre())
	                .append(" x")
	                .append(userProduct.getQuantity());

	        if (i < userProducts.size() - 1) {
	            concepto.append(", ");
	        }
	    }

	    // 🔹 Insertar la compra principal
	    Number id = database.executeInsertAndReturnId(SQL_CREATE_PURCHASE, userId, total, type);

	    // 🔹 Insertar los productos de la compra
	    for (UserProductViewDTO userProduct : userProducts) {
	        database.executeUpdate(SQL_CREATE_PURCHASE_ITEM, id,
	                userProduct.getProduct().getId_producto(),
	                userProduct.getQuantity());
	        updateStock(userProduct.getProduct().getId_producto(), -userProduct.getQuantity());
	    }
	    Ingreso ingreso = new Ingreso(
		        TipoIngreso.MERCHANDISING,    
		        concepto.toString(),           
		        new java.util.Date(),         
		        total                        
		    );

		    IngresoDAO ingresoDAO = new IngresoDAO();
		    ingresoDAO.insertIngreso(ingreso);
		}

	public void updateStock(int idProducto, int cantidad) {
	    database.executeUpdate("UPDATE Producto SET stock = stock + ? WHERE id_producto = ?", cantidad, idProducto);
	}

	public void addRestockOrder(int userId, List<UserProductViewDTO> userProducts) {
		double total = 0;
	    StringBuilder concepto = new StringBuilder();
	    for (int i = 0; i < userProducts.size(); i++) {
	        UserProductViewDTO userProduct = userProducts.get(i);
	        double subtotal = userProduct.getProduct().getPrecio_mayorista() * userProduct.getQuantity();
	        total += subtotal;
	        concepto.append(userProduct.getProduct().getNombre())
	                .append(" x")
	                .append(userProduct.getQuantity());

	        if (i < userProducts.size() - 1) {
	            concepto.append(", ");
	        }
	    }
	    
	    Number idOrder = database.executeInsertAndReturnId(SQL_CREATE_RESTOCK_ORDER, total, concepto);
	    
	    for (UserProductViewDTO userProduct : userProducts) {
	        database.executeUpdate(SQL_CREATE_RESTOCK_DETAIL, userProduct.getProduct().id_producto, idOrder, userProduct.getQuantity());
	    }
	    
	    
		database.executeUpdate(SQL_CREATE_RESTOCK_EXPENSE, total, new java.util.Date(), concepto); 
	}
}
