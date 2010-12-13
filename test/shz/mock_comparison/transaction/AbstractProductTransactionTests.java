package shz.mock_comparison.transaction;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import shz.mock_comparison.Repository;
import shz.mock_comparison.Transaction;

/**
 * @author Stephan Huez
 * 
 */
public abstract class AbstractProductTransactionTests {

    protected ArrayList<String> _arguments;
    protected Repository _repositoryTestDouble;
    protected Transaction _transaction;

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

    protected void given_ARepositoryTestDouble() {
        _repositoryTestDouble = mock(Repository.class);
    }

    protected void when_TheTransactionExecutes() {
        _transaction.execute();
    }

}
