package shz.eprocurement.parser;

import java.util.ArrayList;

import shz.eprocurement.Transaction;
import shz.eprocurement.TransactionFactory;
import shz.eprocurement.TransactionParser;

/**
 * Parses plain text {@link Transaction} formatted as: id|description|price and uses a {@link TransactionFactory}
 * to instantiate the {@link Transaction}.
 *
 * @author Stephan Huez
 *
 */
public class TextTransactionParser implements TransactionParser {

    private TransactionFactory _transactionFactory;

    public TextTransactionParser(TransactionFactory transactionFactory) {
        _transactionFactory = transactionFactory;
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
