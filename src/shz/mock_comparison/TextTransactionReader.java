package shz.mock_comparison;

public class TextTransactionReader {

	private TextFileReader _textFileReader;
	private TextTransactionParser _parser;

	public TextTransactionReader(TextFileReader textFileReader,
			TextTransactionParser parser) {
		_textFileReader = textFileReader;
		_parser = parser;
	}

	public Boolean hasNextTransaction() {
		return _textFileReader.hasNextLine();
	}

	public Transaction nextTransaction() {
		if (hasNextTransaction()) {
			return _parser.parse(_textFileReader.nextLine());
		}
		throw new NoMoreTransactionAvailable();
	}
}
