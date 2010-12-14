package shz.mock_comparison;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shz.mock_comparison.utils.CustomAssertions.then_AnExceptionShoulBeRaised;

import org.junit.Test;

/**
 * 
 * @author Stephan Huez
 * 
 */
public class Test_TransactionIterator {

    private static final String ELEMENT_3 = "Element 3";
    private static final String ELEMENT_2 = "Element 2";
    private static final String ELEMENT_1 = "Element 1";
    private static final Transaction TRANSACTION_1 = mock(Transaction.class);
    private static final Transaction TRANSACTION_2 = mock(Transaction.class);
    private static final Transaction TRANSACTION_3 = mock(Transaction.class);
    private TransactionSourceReader _sourceReaderStub;
    private TransactionParser _parserStub;
    private TransactionIterator _transactionReader;
    private Boolean _hasNextTransaction;
    private Transaction _transaction;

    @Test
    public void should_Report_No_More_Transactions() {
        given_AParser();
        given_ASourceReader();
        given_ATransactionReader();
        given_TheReaderHasNoMoreElement();

        when_AskingWhetherThereIsANextTransaction();

        then_TheResponseShouldBe(false);
    }

    private void then_TheResponseShouldBe(boolean expected) {
        assertThat(_hasNextTransaction, is(expected));
    }

    private void when_AskingWhetherThereIsANextTransaction() {
        _hasNextTransaction = _sourceReaderStub.hasNextElement();
    }

    @Test
    public void should_Report_More_Transactions_Left() {
        given_AParser();
        given_ASourceReader();
        given_ATransactionReader();
        given_TheReaderHasMoreElements();

        when_AskingWhetherThereIsANextTransaction();

        then_TheResponseShouldBe(true);
    }

    @Test
    public void should_Fail_When_Asking_For_Transaction_When_No_More() {
        given_AParser();
        given_ASourceReader();
        given_ATransactionReader();
        given_TheReaderHasNoMoreElement();

        try {
            when_AskingForTheNextTransaction();
            then_AnExceptionShoulBeRaised();
        } catch (NoMoreTransactionInIterator nmtii) {
        }
    }

    @Test
    public void should_Return_Three_Different_Transaction_For_Three_Elements_In_Source() {
        // Given
        given_AParser();
        given_ASourceReader();
        given_ATransactionReader();

        given_AReaderThatReturnsThreeElements();
        given_AParserThatParsesThreeElements();

        when_AskingForTheNextTransaction();
        then_TheTransactionShouldBe(TRANSACTION_1);

        when_AskingForTheNextTransaction();
        then_TheTransactionShouldBe(TRANSACTION_2);

        when_AskingForTheNextTransaction();
        then_TheTransactionShouldBe(TRANSACTION_3);
    }

    private void given_AParserThatParsesThreeElements() {
        when(_parserStub.parse(eq(ELEMENT_1))).thenReturn(TRANSACTION_1);
        when(_parserStub.parse(eq(ELEMENT_2))).thenReturn(TRANSACTION_2);
        when(_parserStub.parse(eq(ELEMENT_3))).thenReturn(TRANSACTION_3);
    }

    private void given_AReaderThatReturnsThreeElements() {
        when(_sourceReaderStub.hasNextElement()).thenReturn(true).thenReturn(true).thenReturn(true)
                .thenReturn(false);
        when(_sourceReaderStub.nextElement()).thenReturn(ELEMENT_1).thenReturn(ELEMENT_2)
                .thenReturn(ELEMENT_3);
    }

    private void then_TheTransactionShouldBe(Transaction expectedTransaction) {
        assertThat(_transaction, equalTo(expectedTransaction));
    }

    private void when_AskingForTheNextTransaction() {
        _transaction = _transactionReader.nextTransaction();
    }

    private void given_TheReaderHasNoMoreElement() {
        when(_sourceReaderStub.hasNextElement()).thenReturn(false);
    }

    private void given_TheReaderHasMoreElements() {
        when(_sourceReaderStub.hasNextElement()).thenReturn(true);
    }

    private void given_ATransactionReader() {
        _transactionReader = new TransactionIterator(_sourceReaderStub, _parserStub);
    }

    private void given_ASourceReader() {
        _sourceReaderStub = mock(TransactionSourceReader.class);
    }

    private void given_AParser() {
        _parserStub = mock(TransactionParser.class);
    }

}
