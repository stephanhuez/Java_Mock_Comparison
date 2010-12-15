package shz.mock_comparison.transaction;

import org.junit.Test;

import shz.mock_comparison.domain.Customer;
import shz.mock_comparison.domain.ValidationFailure;
import shz.mock_comparison.utils.CustomAssertions;
import static org.mockito.Mockito.*;

public class Test_CreateCustomerTransaction extends AbstractTransactionTests {

    @Test
    public void should_Store_Customer_In_Repository() {
        given_TheFollowingArguments("0000000000000000000000001", "Billy", "The Kid",
                "Billy's address");
        given_ARepository();
        given_ATransaction();

        when_TheTransactionExecutes();

        then_TheTransactionShouldHaveCreatedTheExpectedCustomerInTheRepository(new Customer(
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

    private void then_TheTransactionShouldHaveCreatedTheExpectedCustomerInTheRepository(
            Customer customer) {
        verify(_repositoryTestDouble).createCustomer(eq(customer));

    }

    private void given_ATransaction() {
        _transaction = new CreateCustomerTransaction(_arguments, _repositoryTestDouble);

    }
}
