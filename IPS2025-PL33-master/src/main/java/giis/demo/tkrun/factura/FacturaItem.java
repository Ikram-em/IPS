package giis.demo.tkrun.factura;

public class FacturaItem {
	private String descripcion;
	private double precio;
	private int cantidad;

	public FacturaItem(String descripcion, double precio, int cantidad) {
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public double getTotal() {
		return precio * cantidad;
	}
}
