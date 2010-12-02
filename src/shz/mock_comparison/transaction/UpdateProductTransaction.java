package shz.mock_comparison.transaction;

import java.util.ArrayList;

import shz.mock_comparison.Transaction;
import shz.mock_comparison.domain.Product;

public class UpdateProductTransaction implements Transaction {

    private Product _product;

    public UpdateProductTransaction(ArrayList<String> arguments) {
        _product = new Product(arguments.get(0), arguments.get(1), Double.valueOf(arguments.get(2)));
    }

    public Product getProduct() {
        return _product;
    }

}
