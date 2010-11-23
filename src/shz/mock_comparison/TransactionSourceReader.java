package shz.mock_comparison;

public interface TransactionSourceReader {

	public abstract Boolean hasNextElement();

	public abstract String nextElement();

}