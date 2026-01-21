package giis.demo.tkrun.tienda.stock;

import java.util.List;

import giis.demo.util.Database;

public class StockDAO {
	private Database database = new Database();
	
	private static String SQL_GET_ORDERS = "SELECT * FROM RestockOrder ORDER BY fecha ASC";
	
	private static String SQL_MARK_ORDER_AS_RECEIVED = "UPDATE RestockOrder SET finalizada = 1 WHERE id_order = ?";
	
	private static String SQL_GET_ORDER_DETAILS = "SELECT * FROM RestockDetail WHERE id_order = ?";
	
	private static String SQL_UPDATE_STOCK = "UPDATE Producto SET stock = stock + ? WHERE id_producto = ?";
	
	public List<RestockOrderDTO> getOngoingOrders() {
		return database.executeQueryPojo(RestockOrderDTO.class, SQL_GET_ORDERS);
	}
	
	public void updateAsReceived(RestockOrderDTO order) {
		database.executeUpdate(SQL_MARK_ORDER_AS_RECEIVED, order.getId_order());
		
		List<RestockOrderDetailDTO> detalles = database.executeQueryPojo(RestockOrderDetailDTO.class, SQL_GET_ORDER_DETAILS, order.getId_order());
		
		for (RestockOrderDetailDTO restockDetail : detalles) {
			database.executeUpdate(SQL_UPDATE_STOCK, restockDetail.getCantidad(), restockDetail.getId_producto());
		}
	}
}
