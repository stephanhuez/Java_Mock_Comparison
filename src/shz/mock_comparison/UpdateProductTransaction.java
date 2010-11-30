package shz.mock_comparison;

import java.util.ArrayList;

public class UpdateProductTransaction implements Transaction {

    private Product _product;

    public UpdateProductTransaction(ArrayList<String> arguments) {
        _product = new Product(arguments.get(0), arguments.get(1), Double.valueOf(arguments.get(2)));
    }

    public Product getProduct() {
        return _product;
    }

}
