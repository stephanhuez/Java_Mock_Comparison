package shz.mock_comparison.transaction;

import java.util.ArrayList;

import shz.mock_comparison.Repository;
import shz.mock_comparison.Transaction;
import shz.mock_comparison.domain.Product;

/**
 * This {@link Transaction} deletes a product.
 * 
 * @author Stephan Huez
 *
 */
public class DeleteProductTransaction implements Transaction {

    private Product _product;
    private Repository _repository;

    public DeleteProductTransaction(ArrayList<String> arguments, Repository repository) {
        _product = new Product(arguments.get(0));
        _repository = repository;
    }

    public Product getProduct() {
        return _product;
    }

    @Override
    public void execute() {
        _repository.deleteProduct(_product);
    }
}
