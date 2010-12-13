package shz.mock_comparison;

/**
 * This class iterates through the {@link Transaction} read from a
 * {@link TransactionSourceReader} and parsed by a {@link TransactionParser}.
 * 
 * @author Stephan Huez
 * 
 */
public class TransactionIterator {

    private TransactionSourceReader _sourceReader;
    private TransactionParser _parser;

    public TransactionIterator(TransactionSourceReader sourceReader, TransactionParser parser) {
        _sourceReader = sourceReader;
        _parser = parser;
    }

    /**
     * Tells whether there are one or more transactions left in the iterator.
     * 
     * @return
     */
    public Boolean hasNextTransaction() {
        return _sourceReader.hasNextElement();
    }

    /**
     * Return the next transaction in the iterator. The method with throw a
     * {@link NoMoreTransactionInIterator} exception if called when
     * {@link #hasNextTransaction()} returns false.
     * 
     * @return
     */
    public Transaction nextTransaction() {
        if (hasNextTransaction()) {
            return _parser.parse(_sourceReader.nextElement());
        }
        throw new NoMoreTransactionInIterator();
    }
}
