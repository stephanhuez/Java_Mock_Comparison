package shz.mock_comparison;

import java.util.ArrayList;

public class TextTransactionParser implements TransactionParser {

    private TransactionFactory _transactionFactory;

    public TextTransactionParser() {
        _transactionFactory = new TransactionFactory();
    }

    @Override
    public Transaction parse(Object inputToParse) {
        String[] tokens = parseString((String) inputToParse);
        ArrayList<String> arguments = extractArgumentsFromTokens(tokens);
        return _transactionFactory.get(tokens[0], arguments);
    }

    private String[] parseString(String stringToParse) {
        return stringToParse.split("\\|");
    }

    private ArrayList<String> extractArgumentsFromTokens(String[] tokens) {
        ArrayList<String> arguments = new ArrayList<String>();
        for (int i=1;i<tokens.length;i++) {
            String argument = tokens[i];
            arguments.add(argument);
        }
        return arguments;
    }

}
