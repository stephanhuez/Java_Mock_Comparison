package shz.mock_comparison.transaction;

import java.util.ArrayList;

import shz.mock_comparison.Repository;
import shz.mock_comparison.Transaction;
import shz.mock_comparison.domain.Product;

/**
 * This {@link Transaction} updates a product.
 * 
 * @author Stephan Huez
 *
 */
public class UpdateProductTransaction implements Transaction {

    private Product _product;
    private Repository _repository;

    public UpdateProductTransaction(ArrayList<String> arguments, Repository repository) {
        _product = new Product(arguments.get(0), arguments.get(1), Double.valueOf(arguments.get(2)));
        _repository = repository;
    }

    public Product getProduct() {
        return _product;
    }

    @Override
    public void execute() {
        _repository.updateProduct(_product);
    }

}
