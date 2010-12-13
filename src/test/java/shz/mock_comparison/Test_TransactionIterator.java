package shz.mock_comparison;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shz.mock_comparison.utils.CustomAssertions.*;

import org.junit.Test;

/**
 * 
 * @author Stephan Huez
 * 
 */
public class Test_TransactionIterator {

    private TransactionSourceReader _sourceReaderStub;
    private TransactionParser _parserStub;
    private TransactionIterator _transactionReader;

    @Test
    public void should_Report_No_More_Transactions() {
        given_AParserStub();
        given_ASourceReaderStub();
        given_ATransactionReader();
        
        given_TheReaderHasNoMoreElement();

        then_TheIteratorShouldNotHaveANextTransaction();
    }

    @Test
    public void should_Report_More_Transactions_Left() {
        given_AParserStub();
        given_ASourceReaderStub();
        given_ATransactionReader();

        given_TheReaderHasMoreElements();

        then_TheIteratorShouldHaveANextTransaction();
    }

    @Test
    public void should_Fail_When_Asking_For_Transaction_When_No_More() {
        given_AParserStub();
        given_ASourceReaderStub();
        given_ATransactionReader();

        given_TheReaderHasNoMoreElement();

        then_TheIteratorShouldFailWhenAskedForNextTransaction();
    }

    @Test
    public void should_Return_Three_Different_Transaction_For_Three_Elements_In_Source() {
        // Given
        given_AParserStub();
        given_ASourceReaderStub();
        given_ATransactionReader();

        when(_sourceReaderStub.hasNextElement()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);

        Transaction expectedTransaction1 = mock(Transaction.class);
        Transaction expectedTransaction2 = mock(Transaction.class);
        Transaction expectedTransaction3 = mock(Transaction.class);
        when(_parserStub.parse(anyString())).thenReturn(expectedTransaction1).thenReturn(expectedTransaction2)
                .thenReturn(expectedTransaction3);

        then_TheIteratorShouldReturnNextTransactionEqualTo(expectedTransaction1);
        then_TheIteratorShouldReturnNextTransactionEqualTo(expectedTransaction2);
        then_TheIteratorShouldReturnNextTransactionEqualTo(expectedTransaction3);
    }

    private void given_TheReaderHasNoMoreElement() {
        when(_sourceReaderStub.hasNextElement()).thenReturn(false);
    }

    private void given_TheReaderHasMoreElements() {
        when(_sourceReaderStub.hasNextElement()).thenReturn(true);
    }

    private void then_TheIteratorShouldFailWhenAskedForNextTransaction() {
        try {
            _transactionReader.nextTransaction();
            shouldHaveRaisedAnException();
        } catch (NoMoreTransactionInIterator nmtii) {
        }
    }

    private void then_TheIteratorShouldNotHaveANextTransaction() {
        assertThat(_transactionReader.hasNextTransaction(), is(false));
    }

    private void then_TheIteratorShouldHaveANextTransaction() {
        assertThat(_transactionReader.hasNextTransaction(), is(true));
    }

    private void then_TheIteratorShouldReturnNextTransactionEqualTo(Transaction expectedTransaction) {
        assertThat(_transactionReader.nextTransaction(), equalTo(expectedTransaction));
    }

    private void given_ATransactionReader() {
        _transactionReader = new TransactionIterator(_sourceReaderStub, _parserStub);
    }

    private void given_ASourceReaderStub() {
        _sourceReaderStub = mock(TransactionSourceReader.class);
    }

    private void given_AParserStub() {
        _parserStub = mock(TransactionParser.class);
    }

}
