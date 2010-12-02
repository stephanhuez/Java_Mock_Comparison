package shz.mock_comparison.transaction;

import java.util.ArrayList;

import shz.mock_comparison.Transaction;
import shz.mock_comparison.domain.Product;

public class DeleteProductTransaction implements Transaction {

    private Product _product;

    public DeleteProductTransaction(ArrayList<String> arguments) {
        _product = new Product(arguments.get(0));
    }

    public Product getProduct() {
        return _product;
    }
}
