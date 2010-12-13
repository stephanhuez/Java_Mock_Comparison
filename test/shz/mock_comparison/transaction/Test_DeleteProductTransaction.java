package shz.mock_comparison.transaction;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import shz.mock_comparison.domain.Product;

public class Test_DeleteProductTransaction extends AbstractTransactionTests {

    @Test
    public void should_Populate_Product_With_Arguments() {
        given_TheFollowingArguments("1");
        given_ARepositoryStub();
        
        DeleteProductTransaction transaction = new DeleteProductTransaction(_arguments, _repositoryStub);

        // Then
        Product expectedProduct = new Product("1");
        assertThat(transaction.getProduct(), equalTo(expectedProduct));
    }

    @Test
    public void should_Remove_Product_From_The_Repository() {
        given_TheFollowingArguments("999");
        given_ARepositoryMock();
        
        DeleteProductTransaction transaction = new DeleteProductTransaction(_arguments, _repositoryMock);

        // When
        transaction.execute();

        Product expectedProduct = new Product("999");
        // Then
        verify(_repositoryMock).deleteProduct(eq(expectedProduct));
    }

}
