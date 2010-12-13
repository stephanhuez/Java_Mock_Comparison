package shz.mock_comparison.parser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;

import shz.mock_comparison.Transaction;
import shz.mock_comparison.TransactionFactory;
import shz.mock_comparison.TransactionParser;

/**
 * @author Stephan Huez
 * 
 */
public abstract class AbstractTransactionParserTests {
    protected TransactionFactory _transactionFactoryStub;
    protected Transaction _expectedTransactionStub;
    protected ArrayList<String> _arguments;
    protected Transaction _actualTransaction;

    @Before
    public void given() {
        _transactionFactoryStub = mock(TransactionFactory.class);
        _expectedTransactionStub = mock(Transaction.class);
    }

    protected void given_FactoryReturnsExpectedTransactionMatchingTypeAndArguments(String transactionType) {
        when(_transactionFactoryStub.get(eq(transactionType), eq(_arguments))).thenReturn(_expectedTransactionStub);
    }

    protected void then_ActualTransactionShouldBeExpectedTransaction() {
        assertThat(_actualTransaction, is(_expectedTransactionStub));
    }

    @SuppressWarnings("serial")
    protected void given_TheFollowingArguments(final String... args) {
        _arguments = new ArrayList<String>() {
            {
                for (String arg : args) {
                    add(arg);
                }
            }
        };
    }

    protected void when_parserCalledWith(Object value) {
        _actualTransaction = getParser().parse(value);
    }

    protected abstract TransactionParser getParser();

}
