package shz.mock_comparison.transaction;

/**
 * This exception is used to indicate that a factory failed to instantiate a
 * {@link Transaction}.
 * 
 * @author Stephan Huez
 * 
 */
public class FailedToInstantiateTransaction extends RuntimeException {

    private static final long serialVersionUID = -525170596022630786L;

    public FailedToInstantiateTransaction(String message) {
        super(message);
    }

    public FailedToInstantiateTransaction(String message, Throwable cause) {
        super(message, cause);
    }


}
