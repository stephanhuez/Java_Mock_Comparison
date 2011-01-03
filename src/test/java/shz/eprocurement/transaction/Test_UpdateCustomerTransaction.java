package shz.eprocurement.transaction;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import shz.eprocurement.domain.Customer;
import shz.eprocurement.domain.ValidationFailure;
import shz.eprocurement.utils.CustomAssertions;

public class Test_UpdateCustomerTransaction extends AbstractTransactionTests {

    @Test
    public void should_Store_Customer_In_Repository() {
        given_TheFollowingArguments("0000000000000000000000001", "Billy", "The Kid",
                "Billy's address");
        given_ARepository();
        given_ATransaction();

        when_TheTransactionExecutes();

        then_TheTransactionShouldHaveUpdatedTheExpectedCustomerInTheRepository(new Customer(
                "0000000000000000000000001", "Billy", "The Kid", "Billy's address"));
    }

    @Test
    public void should_Validate_The_Customer() {
        given_TheFollowingArguments(null, "Billy", "The Kid", "Billy's address");
        given_ARepository();
        given_ATransaction();

        try {
            when_TheTransactionExecutes();
            CustomAssertions.then_AnExceptionShoulBeRaised();
        } catch (ValidationFailure e) {
        }
    }

    private void then_TheTransactionShouldHaveUpdatedTheExpectedCustomerInTheRepository(
            Customer customer) {
        verify(_repositoryTestDouble).updateCustomer(eq(customer));

    }

    private void given_ATransaction() {
        _transaction = new UpdateCustomerTransaction(_arguments, _repositoryTestDouble);

    }

}
