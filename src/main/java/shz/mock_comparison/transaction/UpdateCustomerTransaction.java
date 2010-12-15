package shz.mock_comparison.transaction;

import java.util.ArrayList;

import shz.mock_comparison.Repository;
import shz.mock_comparison.Transaction;
import shz.mock_comparison.domain.Customer;

/**
 * This {@link Transaction} updates a customer.
 * 
 * @author Stephan Huez
 * 
 */
public class UpdateCustomerTransaction extends CustomerTransaction {

    public UpdateCustomerTransaction(ArrayList<String> arguments, Repository repository) {
        super(repository, new Customer(arguments.get(0), arguments.get(1), arguments.get(2),
                arguments.get(3)));
    }

    @Override
    public void execute() {
        getCustomer().validate();
        getRepository().updateCustomer(getCustomer());
    }

}
