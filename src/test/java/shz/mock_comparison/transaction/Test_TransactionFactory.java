package shz.mock_comparison.transaction;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.mock;
import static shz.mock_comparison.utils.CustomAssertions.*;

import java.util.ArrayList;

import org.junit.Test;

import shz.mock_comparison.Repository;
import shz.mock_comparison.Transaction;
import shz.mock_comparison.TransactionFactory;
import shz.mock_comparison.utils.TypeUtils;

/**
 * @author Stephan Huez
 * 
 */
public class Test_TransactionFactory {

    private ArrayList<String> _arguments;
    private TransactionFactory _transactionFactory;
    private Transaction _transaction;
    private String _transactionType;

    @Test
    public void should_Instantiate_CreateProductTransaction() {
        given_ATransactionFactory();
        given_TheFollowingArguments("1", "Description", "1000");
        given_TheFollowingKey("CreateProduct");

        when_AskingForATransaction();

        then_TheTransactionMustBeOfType(CreateProductTransaction.class);
    }

    @Test
    public void should_Instantiate_UpdateProductTransaction() {
        given_ATransactionFactory();
        given_TheFollowingArguments("1", "Description", "1000");
        given_TheFollowingKey("UpdateProduct");

        when_AskingForATransaction();

        then_TheTransactionMustBeOfType(UpdateProductTransaction.class);
    }

    @Test
    public void should_Instantiate_DeleteProductTransaction() {
        given_ATransactionFactory();
        given_TheFollowingArguments("1");
        given_TheFollowingKey("DeleteProduct");

        when_AskingForATransaction();

        then_TheTransactionMustBeOfType(DeleteProductTransaction.class);
    }

    private void given_TheFollowingKey(String transactionType) {
        _transactionType = transactionType;

    }

    @Test
    public void should_Fail_On_Unkown_Key() {
        given_ATransactionFactory();
        given_TheFollowingArguments();
        given_TheFollowingKey("Bogus");
        
        try {
            when_AskingForATransaction();
            then_AnExceptionShoulBeRaised();
        } catch (InvalidTransactionKey e) {
        }
    }

    private void then_TheTransactionMustBeOfType(Class<?> clazz) {
        assertThat(_transaction, instanceOf(clazz));

    }

    private void when_AskingForATransaction() {
        _transaction = _transactionFactory.get(_transactionType, _arguments);
    }

    private void given_ATransactionFactory() {
        Repository repositoryStub = mock(Repository.class);
        _transactionFactory = new TransactionFactoryImpl(repositoryStub);
    }

    protected void given_TheFollowingArguments(final String... args) {
        _arguments = TypeUtils.buildArguments(args);
    }
}
