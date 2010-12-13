package shz.mock_comparison.transaction;

import static org.mockito.Mockito.*;

import org.junit.Test;

import shz.mock_comparison.domain.Product;
import shz.mock_comparison.transaction.CreateProductTransaction;

public class Test_CreateProductTransaction extends AbstractProductTransactionTests {

    @Test
    public void should_Store_Product_In_Repository() {
        given_TheFollowingArguments("1", "Description", "1000");
        given_ARepositoryTestDouble();
        given_ACreateProductTransaction();

        when_TheTransactionExecutes();

        then_TheTransactionShouldHaveCreatedTheExpectedProductInTheRepository(new Product("1",
                "Description", 1000));
    }

    private void given_ACreateProductTransaction() {
        _transaction = new CreateProductTransaction(_arguments, _repositoryTestDouble);
    }

    private void then_TheTransactionShouldHaveCreatedTheExpectedProductInTheRepository(
            Product expectedProduct) {
        verify(_repositoryTestDouble).createProduct(eq(expectedProduct));
    }

}
