package shz.eprocurement.transaction;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import shz.eprocurement.domain.Customer;


public class Test_DeleteCustomerTransaction extends AbstractTransactionTests {

    @Test
    public void should_Remove_Product_From_The_Repository() {
        given_TheFollowingArguments("999");
        given_ARepository();
        given_ATransaction();

        when_TheTransactionExecutes();

        then_TheTransactionShouldHavDeletedTheExpectedCustomerFromTheRepository(new Customer("999"));
    }

    private void given_ATransaction() {
        _transaction = new DeleteCustomerTransaction(_arguments, _repositoryTestDouble);
    }

    private void then_TheTransactionShouldHavDeletedTheExpectedCustomerFromTheRepository(
            Customer expectedCustomer) {
        verify(_repositoryTestDouble).deleteCustomer(eq(expectedCustomer));
    }


}
