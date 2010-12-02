package shz.mock_comparison;

/**
 * This interface defines the behaviour of a reader that provides an iterator on
 * elements coming from a given source.
 * 
 * @author Stephan Huez
 * 
 */
public interface TransactionSourceReader {

    /**
     * Indicate whether there are one or more elements left in the iterator.
     * 
     * @return
     */
    public abstract Boolean hasNextElement();

    /**
     * Return the next element in the iterator.
     * 
     * @return
     */
    public abstract Object nextElement();

}