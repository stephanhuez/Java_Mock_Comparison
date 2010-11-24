package shz.mock_comparison;

import java.io.InputStream;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

public class XmlSourceReader implements TransactionSourceReader {

	private InputStream _inputStream;
	private Document _document;
	private Elements _transactionElements;
	private int _positionInElementList = 0;

	public XmlSourceReader(InputStream inputStream) {
		_inputStream = inputStream;
		init();
	}

	private void init() {
		Builder builder = new Builder();

		try {
			_document = builder.build(_inputStream);
			Element root = _document.getRootElement();
			_transactionElements = root.getChildElements();
		} catch (Exception e) {
			throw new ImpossibleToOpenSource(e);
		}

	}

	@Override
	public Boolean hasNextElement() {
		return _positionInElementList < _transactionElements.size();
	}

	@Override
	public Element nextElement() {
		return _transactionElements.get(_positionInElementList++);
	}

}
