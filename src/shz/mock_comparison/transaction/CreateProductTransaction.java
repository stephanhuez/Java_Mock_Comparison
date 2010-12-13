package shz.mock_comparison.transaction;

import java.util.ArrayList;

import shz.mock_comparison.Repository;
import shz.mock_comparison.Transaction;
import shz.mock_comparison.domain.Product;

/**
 * This {@link Transaction} creates a product.
 * 
 * @author Stephan Huez
 * 
 */
public class CreateProductTransaction extends ProductTransaction {
    public CreateProductTransaction(ArrayList<String> arguments, Repository repository) {
        super(repository, new Product(arguments.get(0), arguments.get(1), Double.valueOf(arguments
                .get(2))));
    }

    @Override
    public void execute() {
        getRepository().createProduct(getProduct());
    }

}
