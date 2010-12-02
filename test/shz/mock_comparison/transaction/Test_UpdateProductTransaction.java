package shz.mock_comparison.transaction;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;

import org.junit.Test;

import shz.mock_comparison.domain.Product;
import shz.mock_comparison.transaction.DeleteProductTransaction;

public class Test_UpdateProductTransaction {

    @Test
    public void should_Populate_Product_With_Arguments() {
        // Given
        ArrayList<String> arguments = new ArrayList<String>();
        arguments.add("1");
        
        // When
        DeleteProductTransaction transaction = new DeleteProductTransaction(arguments);
        
        // Then
        Product expectedProduct = new Product("1");
        assertThat(transaction.getProduct(),equalTo(expectedProduct));
    }

}
