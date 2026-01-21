package giis.demo.tkrun.tienda;

public class ProductDTO {
	public int id_producto;
	public String nombre;
	public String tipo;
	public double precio;
	public String imagen;
	public int stock;
	public int stock_minimo;
	private int precio_mayorista;

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStockMinimo() {
		return stock_minimo;
	}

	public void setStockMinimo(int stock_minimo) {
		this.stock_minimo = stock_minimo;
	}

	public int getId_producto() {
		return id_producto;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public double getPrecio() {
		return precio;
	}

	public String getImage() {
		return imagen;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getPrecio_mayorista() {
		return precio_mayorista;
	}

	public void setPrecio_mayorista(int precio_mayorista) {
		this.precio_mayorista = precio_mayorista;
	}

	
}
