package shz.mock_comparison.transaction;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;

import org.junit.Test;

import shz.mock_comparison.domain.Product;
import shz.mock_comparison.transaction.UpdateProductTransaction;

public class Test_DeleteProductTransaction {

    @Test
    public void should_Populate_Product_With_Arguments() {
        // Given
        ArrayList<String> arguments = new ArrayList<String>();
        arguments.add("1");
        arguments.add("Description");
        arguments.add("1000");
        
        // When
        UpdateProductTransaction transaction = new UpdateProductTransaction(arguments);
        
        // Then
        Product expectedProduct = new Product("1", "Description", 1000);
        assertThat(transaction.getProduct(),equalTo(expectedProduct));
    }

}