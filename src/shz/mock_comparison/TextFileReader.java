package shz.mock_comparison;

import java.io.InputStream;
import java.util.Scanner;

public class TextFileReader implements TransactionSourceReader {

	private InputStream _inputStream;
	private Scanner _scanner;

	public TextFileReader(InputStream inputStream) {
		_inputStream = inputStream;
		_scanner = new Scanner(_inputStream);
	}

	@Override
	public Boolean hasNextElement() {
		return _scanner.hasNextLine();
	}

	public void close() {
		_scanner.close();
	}

	@Override
	public String nextElement() {
		String nextLine =  _scanner.nextLine();
		System.err.println(nextLine);
		return nextLine;
	}

}
