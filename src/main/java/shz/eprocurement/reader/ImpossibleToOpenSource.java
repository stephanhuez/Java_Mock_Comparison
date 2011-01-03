package shz.eprocurement.reader;

/**
 * This exception is used to indicate that a Source could not be opened.
 *
 * @author Stephan Huez
 *
 */
public class ImpossibleToOpenSource extends RuntimeException {
    private static final long serialVersionUID = 5321645639292291341L;

    public ImpossibleToOpenSource(Exception cause) {
        super(cause);
    }

}
