package shz.eprocurement.transaction;

import java.util.ArrayList;

import shz.eprocurement.Repository;
import shz.eprocurement.Transaction;
import shz.eprocurement.domain.Product;

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