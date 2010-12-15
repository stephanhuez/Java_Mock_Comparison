package shz.mock_comparison.transaction;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import shz.mock_comparison.domain.Customer;
import shz.mock_comparison.domain.ValidationFailure;
import shz.mock_comparison.utils.CustomAssertions;

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
