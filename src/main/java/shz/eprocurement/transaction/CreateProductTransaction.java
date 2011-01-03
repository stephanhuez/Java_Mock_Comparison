package shz.eprocurement.transaction;

import java.util.ArrayList;

import shz.eprocurement.Repository;
import shz.eprocurement.Transaction;
import shz.eprocurement.domain.Product;

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
        getProduct().validate();
        getRepository().createProduct(getProduct());
    }

}
