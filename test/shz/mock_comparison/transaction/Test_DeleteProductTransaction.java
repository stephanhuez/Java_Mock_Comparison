package shz.mock_comparison.transaction;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import shz.mock_comparison.domain.Product;

public class Test_DeleteProductTransaction extends AbstractProductTransactionTests {

    @Test
    public void should_Remove_Product_From_The_Repository() {
        given_TheFollowingArguments("999");
        given_ARepositoryTestDouble();
        given_ADeleteProductTransaction();

        when_TheTransactionExecutes();

        then_TheTransactionShouldHavDeletedTheExpectedProductFromTheRepository(new Product("999"));
    }

    private void given_ADeleteProductTransaction() {
        _transaction = new DeleteProductTransaction(_arguments, _repositoryTestDouble);
    }

    private void then_TheTransactionShouldHavDeletedTheExpectedProductFromTheRepository(
            Product expectedProduct) {
        verify(_repositoryTestDouble).deleteProduct(eq(expectedProduct));
    }

}
