package shz.eprocurement.transaction;

import static org.mockito.Mockito.*;

import org.junit.Test;

import shz.eprocurement.domain.Product;
import shz.eprocurement.domain.ValidationFailure;
import shz.eprocurement.transaction.CreateProductTransaction;
import shz.eprocurement.utils.CustomAssertions;

public class Test_CreateProductTransaction extends AbstractTransactionTests {

    @Test
    public void should_Store_Product_In_Repository() {
        given_TheFollowingArguments(VALID_PRODUCT_ID, "Description", "1000");
        given_ARepository();
        given_ACreateProductTransaction();

        when_TheTransactionExecutes();

        then_TheTransactionShouldHaveCreatedTheExpectedProductInTheRepository(new Product(
                VALID_PRODUCT_ID, "Description", 1000));
    }

    @Test
    public void should_Validate_The_Product() {
        given_TheFollowingArguments("1", "Description", "1000");
        given_ARepository();
        given_ACreateProductTransaction();

        try {
            when_TheTransactionExecutes();
            CustomAssertions.then_AnExceptionShoulBeRaised();
        } catch (ValidationFailure e) {
        }
    }

    private void given_ACreateProductTransaction() {
        _transaction = new CreateProductTransaction(_arguments, _repositoryTestDouble);
    }

    private void then_TheTransactionShouldHaveCreatedTheExpectedProductInTheRepository(
            Product expectedProduct) {
        verify(_repositoryTestDouble).createProduct(eq(expectedProduct));
    }

}
