package shz.mock_comparison;

import java.io.InputStream;
import java.util.Scanner;

public class TextFileReader {

	private InputStream _inputStream;
	private Scanner _scanner;

	public TextFileReader(InputStream inputStream) {
		_inputStream = inputStream;
		_scanner = new Scanner(_inputStream);
	}

	public Boolean hasNextLine() {
		return _scanner.hasNextLine();
	}

	public void close() {
		_scanner.close();
	}

	public String nextLine() {
		String nextLine =  _scanner.nextLine();
		System.err.println(nextLine);
		return nextLine;
	}

}
