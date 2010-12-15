package shz.mock_comparison.transaction;

import shz.mock_comparison.Repository;
import shz.mock_comparison.Transaction;
import shz.mock_comparison.domain.Customer;

/**
 * 
 * 
 * @author Stephan Huez
 * 
 */
public abstract class CustomerTransaction implements Transaction {

    private Repository _repository;
    private Customer _customer;

    public CustomerTransaction(Repository repository, Customer customer) {
        _repository = repository;
        _customer = customer;
    }

    protected Repository getRepository() {
        return _repository;
    }

    public Customer getCustomer() {
        return _customer;
    }

    public String getCustomerId() {
        return _customer.getId();
    }
    
    public String getCustomerFirstName() {
        return _customer.getFirstName();
    }
    
    public String getCustomerLastName() {
        return _customer.getLastName();
    }
    
    public String getCustomerAddress() {
        return _customer.getAddress();
    }

}
