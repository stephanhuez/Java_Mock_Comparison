package shz.mock_comparison.transaction;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import org.junit.Test;

import shz.mock_comparison.domain.Product;

public class Test_UpdateProductTransaction extends AbstractProductTransactionTests {

    @Test
    public void should_Update_Product_In_Repository() {
        given_TheFollowingArguments("1", "Description", "1000");
        given_ARepositoryTestDouble();

        given_AnUpdateProductTransaction();

        when_TheTransactionExecutes();

        then_TheTransactionShouldHaveUpdatedTheExpectedProductInTheRepository(new Product("1",
                "Description", 1000));

    }

    private void then_TheTransactionShouldHaveUpdatedTheExpectedProductInTheRepository(
            Product expectedProduct) {
        verify(_repositoryTestDouble).updateProduct(eq(expectedProduct));
    }

    private void given_AnUpdateProductTransaction() {
        _transaction = new UpdateProductTransaction(_arguments, _repositoryTestDouble);
    }

}
