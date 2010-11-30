package shz.mock_comparison;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

/**
 * @author Stephan Huez
 * 
 */
public class Test_TransactionFactory {

    @Test
    public void should_Instantiate_CreateProductTransaction() {
        // Given
        ArrayList<String> arguments = new ArrayList<String>();
        arguments.add("1");
        arguments.add("Description");
        arguments.add("1000");
        CreateProductTransaction transaction = (CreateProductTransaction) new TransactionFactory().get("CreateProduct",
                arguments);

        // Then
        assertThat(transaction, notNullValue());
    }

    @Test
    public void should_Instantiate_UpdateProductTransaction() {
        // Given
        ArrayList<String> arguments = new ArrayList<String>();
        arguments.add("1");
        arguments.add("Description");
        arguments.add("1000");
        UpdateProductTransaction transaction = (UpdateProductTransaction) new TransactionFactory().get("UpdateProduct",
                arguments);

        // Then
        assertThat(transaction, notNullValue());
    }

    @Test
    public void should_Instantiate_DeleteProductTransaction() {
        // Given
        ArrayList<String> arguments = new ArrayList<String>();
        arguments.add("1");
        DeleteProductTransaction transaction = (DeleteProductTransaction) new TransactionFactory().get("DeleteProduct",
                arguments);

        // Then
        assertThat(transaction, notNullValue());
    }

    @Test
    public void should_Fail_On_Unkown_Key() {
        // When
        try {
            new TransactionFactory().get("Bogus", new ArrayList<String>());
            fail("Should have raised an exception");
        } catch (InvalidTransactionIdentifier e) {
            // Then
            // Should fail
        }
    }
}
