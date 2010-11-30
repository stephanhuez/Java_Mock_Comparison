package shz.mock_comparison;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Test_CreateProductTransaction {

    @Test
    public void should_Populate_Product_With_Arguments() {
        // Given
        ArrayList<String> arguments = new ArrayList<String>();
        arguments.add("1");
        arguments.add("Description");
        arguments.add("1000");
        
        // When
        CreateProductTransaction transaction = new CreateProductTransaction(arguments);
        
        // Then
        Product expectedProduct = new Product("1", "Description", 1000);
        assertThat(transaction.getProduct(),equalTo(expectedProduct));
    }

}
