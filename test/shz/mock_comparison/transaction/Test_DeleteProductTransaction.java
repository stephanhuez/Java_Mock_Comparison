package shz.mock_comparison.transaction;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Test;

import shz.mock_comparison.Repository;
import shz.mock_comparison.domain.Product;

@SuppressWarnings("serial")
public class Test_DeleteProductTransaction {

    @Test
    public void should_Populate_Product_With_Arguments() {
        // Given
        ArrayList<String> arguments = new ArrayList<String>() {
            {
                add("1");
            }
        };

        // When
        Repository repositoryStub = mock(Repository.class);
        DeleteProductTransaction transaction = new DeleteProductTransaction(arguments, repositoryStub);

        // Then
        Product expectedProduct = new Product("1");
        assertThat(transaction.getProduct(), equalTo(expectedProduct));
    }

    @Test
    public void should_Remove_Product_From_The_Repository() {
        // Given
        ArrayList<String> arguments = new ArrayList<String>() {
            {
                add("999");
            }
        };
        Repository repositoryMock = mock(Repository.class);
        DeleteProductTransaction transaction = new DeleteProductTransaction(arguments, repositoryMock);

        // When
        transaction.execute();

        Product expectedProduct = new Product("999");
        // Then
        verify(repositoryMock).deleteProduct(eq(expectedProduct));
    }

}
