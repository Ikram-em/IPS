package giis.demo.tkrun.tienda;

public class UserProductViewDTO {
	private int user_id;
	private ProductDTO product;
	private int quantity;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public UserProductViewDTO(int user_id, ProductDTO product, int quantity) {
		super();
		this.user_id = user_id;
		this.product = product;
		this.quantity = quantity;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
