package shz.mock_comparison.repository;

import org.junit.Test;

import shz.mock_comparison.Repository;
import shz.mock_comparison.domain.Customer;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * 
 * @author Stephan Huez
 * 
 */
public class Test_InMemoryRepository_Customer {

    private Repository _repository;
    private Customer _foundCustomer;

    @Test
    public void should_Find_Created_Customer() {
        given_AnInMemoryRepository();
        given_ThisCustomerInTheRepository(new Customer("0001", "123456789", "",""));

        when_LookingForCustomerWithId("0001");

        then_FoundCustomerShouldEqualToExpectedCustomer(new Customer("0001", "123456789", "",""));
    }

    private void given_ThisCustomerInTheRepository(Customer customer) {
        _repository.createCustomer(customer);
    }

    @Test
    public void should_Find_Customer_Among_Created_Customers() {
        given_AnInMemoryRepository();
        given_ThisCustomerInTheRepository(new Customer("0001", "123456789", "",""));
        given_ThisCustomerInTheRepository(new Customer("0002", "123456789", "",""));
        given_ThisCustomerInTheRepository(new Customer("0002", "123456789", "",""));

        when_LookingForCustomerWithId("0001");
        
        then_FoundCustomerShouldEqualToExpectedCustomer(new Customer("0001", "123456789", "",""));
    }

    @Test
    public void should_Not_Find_Customer_After_Deletion() {
        given_AnInMemoryRepository();
        given_ThisCustomerInTheRepository(new Customer("0001", "123456789", "",""));
        given_ThisCustomerInTheRepository(new Customer("0002", "123456789", "",""));
        given_ThisCustomerInTheRepository(new Customer("0002", "123456789", "",""));

        when_Deleting(new Customer("0002"));
        when_LookingForCustomerWithId("0002");

        then_FoundCustomerShouldBeNull();
    }

    private void then_FoundCustomerShouldBeNull() {
        assertThat(_foundCustomer, nullValue());
    }

    @Test
    public void should_Update_Customer() {
        given_AnInMemoryRepository();
        given_ThisCustomerInTheRepository(new Customer("0001", "123456789", "",""));

        when_Updating(new Customer("0001", "AAAAA", "DDDD","SDSSS"));
        when_LookingForCustomerWithId("0001");
        
        then_FoundCustomerShouldEqualToExpectedCustomer(new Customer("0001", "AAAAA", "DDDD","SDSSS"));
    }

    private void when_LookingForCustomerWithId(String id) {
        _foundCustomer = _repository.findCustomer(id);
    }

    private void then_FoundCustomerShouldEqualToExpectedCustomer(Customer customer) {
        assertThat(_foundCustomer, equalTo(customer));
    }

    private void when_Updating(Customer customer) {
        _repository.updateCustomer(customer);
    }

    private void given_AnInMemoryRepository() {
        _repository = new InMemoryRepository();
    }

    private void when_Deleting(Customer customer) {
        _repository.deleteCustomer(customer);
    }

}
