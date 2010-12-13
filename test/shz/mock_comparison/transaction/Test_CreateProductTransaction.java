package shz.mock_comparison.transaction;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import shz.mock_comparison.domain.Product;
import shz.mock_comparison.transaction.CreateProductTransaction;

public class Test_CreateProductTransaction extends AbstractTransactionTests {

    private CreateProductTransaction _transaction;

    @Test
    public void should_Populate_Product_With_Arguments() {
        given_TheFollowingArguments("1", "Description", "1000");
        given_ARepositoryStub();

        given_ACreateProductTransactionWithARepositoryStub();

        then_TheProductInTheTransactionShouldEqualTheExpectedProduct(new Product("1",
                "Description", 1000));
    }

    @Test
    public void should_Store_Product_In_Repository() {
        given_TheFollowingArguments("1", "Description", "1000");
        given_ARepositoryMock();
        given_ACreateProductTransactionWithARepositoryMock();

        when_TheTransactionExecutes();

        then_TheTransactionShouldHaveCalledTheCreateProductOperationOfTheRepositoryMockWithTheExpectedProduct(new Product(
                "1", "Description", 1000));
    }

    private void then_TheTransactionShouldHaveCalledTheCreateProductOperationOfTheRepositoryMockWithTheExpectedProduct(
            Product expectedProduct) {
        verify(_repositoryMock).createProduct(eq(expectedProduct));
    }

    private void when_TheTransactionExecutes() {
        _transaction.execute();
    }

    private void then_TheProductInTheTransactionShouldEqualTheExpectedProduct(
            Product expectedProduct) {
        assertThat(_transaction.getProduct(), equalTo(expectedProduct));
    }

    private void given_ACreateProductTransactionWithARepositoryStub() {
        _transaction = new CreateProductTransaction(_arguments, _repositoryStub);
    }

    private void given_ACreateProductTransactionWithARepositoryMock() {
        _transaction = new CreateProductTransaction(_arguments, _repositoryMock);
    }

}
