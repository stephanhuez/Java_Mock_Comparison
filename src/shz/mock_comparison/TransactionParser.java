package shz.mock_comparison;

public class TransactionParser {

	public Transaction parse(String stringToParse) {
		String[] tokens = parseString(stringToParse);
		String[] arguments = extractArgumentsFromTokens(tokens);
		if ("CreateProduct".equals(tokens[0])){
			return new CreateProductTransaction(arguments);
			
		}else if ("UpdateProduct".equals(tokens[0])){
			return new UpdateProductTransaction();
		}else if ("DeleteProduct".equals(tokens[0])){
			return new DeleteProductTransaction();			
		}
		throw new InvalidTransactionIdentifier();
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
