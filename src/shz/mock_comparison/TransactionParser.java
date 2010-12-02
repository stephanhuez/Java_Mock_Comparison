package shz.mock_comparison;

/**
 * This interface defines the behaviour of a transaction parser.
 * 
 * @author Stephan Huez
 *
 */
public interface TransactionParser {

    /**
     * Based on the provided input, build a transaction.
     * 
     * @param inputToParse
     * @return
     */
	public abstract Transaction parse(Object inputToParse);

}