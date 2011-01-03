package shz.eprocurement.transaction;

import java.util.ArrayList;

import shz.eprocurement.Repository;
import shz.eprocurement.Transaction;
import shz.eprocurement.domain.Product;

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
