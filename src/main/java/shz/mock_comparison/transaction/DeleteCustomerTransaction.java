package shz.mock_comparison.transaction;

import java.util.ArrayList;

import shz.mock_comparison.Repository;
import shz.mock_comparison.Transaction;
import shz.mock_comparison.domain.Customer;

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