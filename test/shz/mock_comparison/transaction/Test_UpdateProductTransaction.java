package shz.mock_comparison.transaction;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import shz.mock_comparison.domain.Product;

public class Test_UpdateProductTransaction extends AbstractTransactionTests {

    @Test
    public void should_Populate_Product_With_Arguments() {
        given_TheFollowingArguments("1", "Description", "1000");
        given_ARepositoryStub();

        UpdateProductTransaction transaction = new UpdateProductTransaction(_arguments, _repositoryStub);

        // Then
        Product expectedProduct = new Product("1", "Description", 1000);
        assertThat(transaction.getProduct(), equalTo(expectedProduct));
    }

    @Test
    public void should_Update_Product_In_Repository() {
        given_TheFollowingArguments("1", "Description", "1000");
        given_ARepositoryMock();
        
        UpdateProductTransaction transaction = new UpdateProductTransaction(_arguments, _repositoryMock);

        // When
        transaction.execute();

        // Then
        Product expectedProduct = new Product("1", "Description", 1000);
        verify(_repositoryMock).updateProduct(eq(expectedProduct));
    }

}
