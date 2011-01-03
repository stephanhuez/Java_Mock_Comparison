package shz.eprocurement.transaction;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import shz.eprocurement.Repository;
import shz.eprocurement.Transaction;
import shz.eprocurement.utils.TypeUtils;

/**
 * @author Stephan Huez
 *
 */
public abstract class AbstractTransactionTests {

	protected static final String VALID_PRODUCT_ID = "0000000000000000000000001";
    protected ArrayList<String> _arguments;
	protected Repository _repositoryTestDouble;
	protected Transaction _transaction;

	protected void given_TheFollowingArguments(final String... args) {
		_arguments = TypeUtils.buildArguments(args);
	}

	protected void given_ARepository() {
		_repositoryTestDouble = mock(Repository.class);
	}

	protected void when_TheTransactionExecutes() {
		_transaction.execute();
	}

}
