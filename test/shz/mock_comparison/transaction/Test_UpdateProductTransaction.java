package shz.mock_comparison.transaction;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Test;

import shz.mock_comparison.Repository;
import shz.mock_comparison.domain.Product;

@SuppressWarnings("serial")
public class Test_UpdateProductTransaction {

    @Test
    public void should_Populate_Product_With_Arguments() {
        // Given
        ArrayList<String> arguments = new ArrayList<String>() {
            {
                add("1");
                add("Description");
                add("1000");
            }
        };
        Repository repositoryStub = mock(Repository.class);
        UpdateProductTransaction transaction = new UpdateProductTransaction(arguments,repositoryStub);

        // Then
        Product expectedProduct = new Product("1", "Description", 1000);
        assertThat(transaction.getProduct(), equalTo(expectedProduct));
    }

    @Test
    public void should_Update_Product_In_Repository() {
        // Given
        ArrayList<String> arguments = new ArrayList<String>() {
            {
                add("1");
                add("Description");
                add("1000");
            }
        };
        Repository repositoryMock = mock(Repository.class);
        UpdateProductTransaction transaction = new UpdateProductTransaction(arguments, repositoryMock);

        // When
        transaction.execute();

        // Then
        Product expectedProduct = new Product("1", "Description", 1000);
        verify(repositoryMock).updateProduct(eq(expectedProduct));

    }

}
