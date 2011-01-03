package shz.eprocurement.transaction;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import org.junit.Test;

import shz.eprocurement.domain.Product;
import shz.eprocurement.domain.ValidationFailure;
import shz.eprocurement.utils.CustomAssertions;

public class Test_UpdateProductTransaction extends AbstractTransactionTests {

    @Test
    public void should_Update_Product_In_Repository() {
        given_TheFollowingArguments(VALID_PRODUCT_ID, "Description", "1000");
        given_ARepository();

        given_AnUpdateProductTransaction();

        when_TheTransactionExecutes();

        then_TheTransactionShouldHaveUpdatedTheExpectedProductInTheRepository(new Product(VALID_PRODUCT_ID,
                "Description", 1000));

    }

    @Test
    public void should_Validate_The_Product(){
        given_TheFollowingArguments("1", "Description", "1000");
        given_ARepository();
        given_AnUpdateProductTransaction();

        try {
            when_TheTransactionExecutes();
            CustomAssertions.then_AnExceptionShoulBeRaised();
        } catch (ValidationFailure e) {
        }
    }

    private void then_TheTransactionShouldHaveUpdatedTheExpectedProductInTheRepository(
            Product expectedProduct) {
        verify(_repositoryTestDouble).updateProduct(eq(expectedProduct));
    }

    private void given_AnUpdateProductTransaction() {
        _transaction = new UpdateProductTransaction(_arguments, _repositoryTestDouble);
    }

}
