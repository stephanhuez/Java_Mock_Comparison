package shz.eprocurement.domain;

/**
 * This type of exception represents a validation error.
 *
 * @author Stephan Huez
 *
 */
public class ValidationFailure extends RuntimeException {

    public ValidationFailure(String message) {
        super(message);
    }

    private static final long serialVersionUID = 5756959636324134817L;

}
