package shz.mock_comparison;

public class TransactionParser {

	private TransactionFactory _transactionFactory;

	public TransactionParser() {
		_transactionFactory = new TransactionFactory();
	}

	public Transaction parse(String stringToParse) {
		String[] tokens = parseString(stringToParse);
		String[] arguments = extractArgumentsFromTokens(tokens);
		return _transactionFactory.get(tokens[0], arguments);
	}

	private String[] parseString(String stringToParse) {
		return stringToParse.split("\\|");
	}

	private String[] extractArgumentsFromTokens(String[] tokens) {
		String[] arguments = new String[tokens.length - 1];
		System.arraycopy(tokens, 1, arguments, 0, arguments.length);
		return arguments;
	}

}
