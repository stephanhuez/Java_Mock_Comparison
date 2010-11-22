package shz.mock_comparison;

public class DeleteProductTransaction implements Transaction {

	private Product _product;

	public DeleteProductTransaction(String[] arguments) {
		_product = new Product(arguments[0]);
	}

	public Product getProduct() {
		return _product;
	}
}
