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
public class UpdateProductTransaction extends ProductTransaction {

    public UpdateProductTransaction(ArrayList<String> arguments, Repository repository) {
        super(repository, new Product(arguments.get(0), arguments.get(1), Double.valueOf(arguments
                .get(2))));
    }

    @Override
    public void execute() {
        getProduct().validate();
        getRepository().updateProduct(getProduct());
    }

}
