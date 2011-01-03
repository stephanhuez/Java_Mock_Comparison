package shz.eprocurement.reader;

import java.io.InputStream;
import java.util.Scanner;

import shz.eprocurement.TransactionSourceReader;

/**
 * Implementation of a {@link TransactionSourceReader} for text files.
 *
 * @author Stephan Huez
 *
 */
public class TextSourceReader implements TransactionSourceReader {

	private InputStream _inputStream;
	private Scanner _scanner;

	public TextSourceReader(InputStream inputStream) {
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
		return nextLine;
	}

}
