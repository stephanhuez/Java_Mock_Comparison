package shz.mock_comparison.transaction;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Test;

import shz.mock_comparison.Repository;
import shz.mock_comparison.domain.Product;
import shz.mock_comparison.transaction.CreateProductTransaction;

@SuppressWarnings("serial")
public class Test_CreateProductTransaction {

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

        // When
        Repository repositoryStub = mock(Repository.class);
        CreateProductTransaction transaction = new CreateProductTransaction(arguments,repositoryStub);

        // Then
        Product expectedProduct = new Product("1", "Description", 1000);
        assertThat(transaction.getProduct(), equalTo(expectedProduct));
    }

    @Test
    public void should_Store_Product_In_Repository() {
        // Given
        ArrayList<String> arguments = new ArrayList<String>() {
            {
                add("1");
                add("Description");
                add("1000");
            }
        };
        Repository repositoryMock = mock(Repository.class);
        CreateProductTransaction transaction = new CreateProductTransaction(arguments, repositoryMock);

        // When
        transaction.execute();

        // Then
        Product expectedProduct = new Product("1", "Description", 1000);
        verify(repositoryMock).createProduct(eq(expectedProduct));
    }

}
