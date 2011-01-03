package shz.eprocurement.transaction;

import java.util.ArrayList;

import shz.eprocurement.Repository;
import shz.eprocurement.Transaction;
import shz.eprocurement.domain.Customer;

/**
 * This {@link Transaction} deletes a customer.
 *
 * @author Stephan Huez
 *
 */
public class DeleteCustomerTransaction extends CustomerTransaction {

    public DeleteCustomerTransaction(ArrayList<String> arguments, Repository repository) {
        super(repository, new Customer(arguments.get(0)));
    }

    @Override
    public void execute() {
        getRepository().deleteCustomer(getCustomer());
    }

}