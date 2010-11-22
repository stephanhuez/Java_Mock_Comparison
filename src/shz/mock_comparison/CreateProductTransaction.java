package shz.mock_comparison;

public class CreateProductTransaction implements Transaction{
	private Product _product;

	public CreateProductTransaction(String[] arguments) {
		_product = new Product(arguments[0],arguments[1],Double.valueOf(arguments[2]));
	}

	public Product getProduct() {
		return _product;
	}

}
