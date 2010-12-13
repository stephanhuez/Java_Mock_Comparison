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
public class DeleteProductTransaction extends ProductTransaction {

    public DeleteProductTransaction(ArrayList<String> arguments, Repository repository) {
        super(repository, new Product(arguments.get(0)));
    }

    @Override
    public void execute() {
        getRepository().deleteProduct(getProduct());
    }

}