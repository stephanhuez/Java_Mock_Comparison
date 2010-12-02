package shz.mock_comparison;


public class TransactionIterator {

	private TransactionSourceReader _sourceReader;
	private TransactionParser _parser;

	public TransactionIterator(TransactionSourceReader sourceReader,
			TransactionParser parser) {
		_sourceReader = sourceReader;
		_parser = parser;
	}

	public Boolean hasNextTransaction() {
		return _sourceReader.hasNextElement();
	}

	public Transaction nextTransaction() {
		if (hasNextTransaction()) {
			return _parser.parse(_sourceReader.nextElement());
		}
		throw new NoMoreTransactionAvailable();
	}
}
