package shz.mock_comparison.transaction;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;
import static shz.mock_comparison.utils.CustomAssertions.*;

import java.util.ArrayList;

import org.junit.Test;

import shz.mock_comparison.Repository;
import shz.mock_comparison.transaction.CreateProductTransaction;
import shz.mock_comparison.transaction.DeleteProductTransaction;
import shz.mock_comparison.transaction.InvalidTransactionKey;
import shz.mock_comparison.transaction.TransactionFactoryImpl;
import shz.mock_comparison.transaction.UpdateProductTransaction;

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
        CreateProductTransaction transaction = (CreateProductTransaction) newTransactionFactory().get("CreateProduct",
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
        UpdateProductTransaction transaction = (UpdateProductTransaction) newTransactionFactory().get("UpdateProduct",
                arguments);

        // Then
        assertThat(transaction, notNullValue());
    }

    @Test
    public void should_Instantiate_DeleteProductTransaction() {
        // Given
        ArrayList<String> arguments = new ArrayList<String>();
        arguments.add("1");
        DeleteProductTransaction transaction = (DeleteProductTransaction) newTransactionFactory().get("DeleteProduct",
                arguments);

        // Then
        assertThat(transaction, notNullValue());
    }

    @Test
    public void should_Fail_On_Unkown_Key() {
        // When
        try {
            newTransactionFactory().get("Bogus", new ArrayList<String>());
            shouldHaveRaisedAnException();
        } catch (InvalidTransactionKey e) {
            // Then
            // Should have thrown an exception
        }
    }


    private TransactionFactoryImpl newTransactionFactory() {
        Repository repositoryStub = mock(Repository.class);
        return new TransactionFactoryImpl(repositoryStub);
    }

}
