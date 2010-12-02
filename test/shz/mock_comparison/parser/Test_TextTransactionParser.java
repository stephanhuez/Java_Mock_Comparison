package shz.mock_comparison.parser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import shz.mock_comparison.Transaction;
import shz.mock_comparison.TransactionFactory;

/**
 * @author Stephan Huez
 * 
 */
@SuppressWarnings("serial")
public class Test_TextTransactionParser {

    private TextTransactionParser _parser;
    private TransactionFactory _transactionFactoryStub;
    private Transaction _transactionStub;

    @Before
    public void given() {
        _transactionFactoryStub = mock(TransactionFactory.class);
        _transactionStub = mock(Transaction.class);
        _parser = new TextTransactionParser(_transactionFactoryStub);
    }

    @Test
    public void should_Parse_Create_Product_Transaction() {
        // Given
        ArrayList<String> arguments = new ArrayList<String>() {
            {
                add("0000001");
                add("Bogus Product");
                add("1200.99");
            }
        };
        when(_transactionFactoryStub.get(eq("CreateProduct"), eq(arguments))).thenReturn(
                _transactionStub);

        // When
        Transaction transaction = _parser.parse("CreateProduct|0000001|Bogus Product|1200.99");

        // Then
        assertThat(transaction, is(_transactionStub));
    }

    @Test
    public void should_Parse_Update_Product_Transaction() {
        // Given
        ArrayList<String> arguments = new ArrayList<String>() {
            {
                add("0000001");
                add("Bogus Product");
                add("1200.99");
            }
        };
        when(_transactionFactoryStub.get(eq("UpdateProduct"), eq(arguments))).thenReturn(
                _transactionStub);

        // When
        Transaction transaction = _parser.parse("UpdateProduct|0000001|Bogus Product|1200.99");

        // Then
        assertThat(transaction, is(_transactionStub));
    }

    @Test
    public void should_Parse_Delete_Product_Transaction() {
        // Given
        ArrayList<String> arguments = new ArrayList<String>() {
            {
                add("0000001");
            }
        };
        when(_transactionFactoryStub.get(eq("DeleteProduct"), eq(arguments))).thenReturn(
                _transactionStub);

        // When
        Transaction transaction = _parser.parse("DeleteProduct|0000001");

        // Then
        assertThat(transaction, is(_transactionStub));
    }

}
