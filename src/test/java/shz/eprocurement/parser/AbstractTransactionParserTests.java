package shz.eprocurement.parser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import shz.eprocurement.Transaction;
import shz.eprocurement.TransactionFactory;
import shz.eprocurement.TransactionParser;
import shz.eprocurement.utils.TypeUtils;

/**
 * @author Stephan Huez
 *
 */
public abstract class AbstractTransactionParserTests {
    protected TransactionFactory _transactionFactoryStub;
    protected Transaction _expectedTransactionStub =  mock(Transaction.class);
    protected ArrayList<String> _arguments;
    protected Transaction _actualTransaction;
    private String _typeKey;


    protected void given_TheFactoryReturnsATransaction(String transactionType) {
        when(_transactionFactoryStub.get(eq(transactionType), eq(_arguments))).thenReturn(
                _expectedTransactionStub);
    }

    protected void given_TheFollowingKey(String typeKey) {
        _typeKey = typeKey;
    }

    protected void given_TheFactoryReturnsATransaction() {
        when(_transactionFactoryStub.get(eq(_typeKey), eq(_arguments))).thenReturn(
                _expectedTransactionStub);
    }

    protected void then_ActualTransactionShouldBeExpectedTransaction() {
        assertThat(_actualTransaction, is(_expectedTransactionStub));
    }

    protected void given_TheFollowingArguments(final String... args) {
        _arguments = TypeUtils.buildArguments(args);
    }

    protected void when_CallingTheParserWith(Object value) {
        _actualTransaction = getParser().parse(value);
    }

    protected void given_ATransactionFactory() {
        _transactionFactoryStub = mock(TransactionFactory.class);
    }


    protected abstract TransactionParser getParser();

}
