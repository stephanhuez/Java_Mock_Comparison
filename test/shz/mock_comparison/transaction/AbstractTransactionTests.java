package shz.mock_comparison.transaction;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import shz.mock_comparison.Repository;

/**
 * @author Stephan Huez
 *
 */
public abstract class AbstractTransactionTests {

    protected ArrayList<String> _arguments;
    protected Repository _repositoryStub;
    protected Repository _repositoryMock;

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

    protected void given_ARepositoryStub() {
        _repositoryStub = mock(Repository.class);
    }

    protected void given_ARepositoryMock() {
        _repositoryMock = mock(Repository.class);        
    }

}
