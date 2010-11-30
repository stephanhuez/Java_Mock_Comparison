package shz.mock_comparison;

import java.util.ArrayList;

public class DeleteProductTransaction implements Transaction {

    private Product _product;

    public DeleteProductTransaction(ArrayList<String> arguments) {
        _product = new Product(arguments.get(0));
    }

    public Product getProduct() {
        return _product;
    }
}
