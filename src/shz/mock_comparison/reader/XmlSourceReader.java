package shz.mock_comparison.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import shz.mock_comparison.TransactionSourceReader;

public class XmlSourceReader implements TransactionSourceReader {

	private InputStream _inputStream;
	private int _positionInElementList = 0;
	private ArrayList<Node> _transactionElements;
    private Node _root;

	public XmlSourceReader(InputStream inputStream) {
		_inputStream = inputStream;
		init();
	}

	private void init() {
		try {
			extractRoot();
			extractNodeElements();
		} catch (Exception e) {
			throw new ImpossibleToOpenSource(e);
		}
	}

    private void extractRoot() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory
        		.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(_inputStream);
        document.getDocumentElement().normalize();
        _root = document.getDocumentElement();
    }

	private void extractNodeElements() {
	    _transactionElements = new ArrayList<Node>(); 
        NodeList childNodes = _root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                _transactionElements.add(childNode);
            }
        }
    }

    @Override
	public Boolean hasNextElement() {
		return _positionInElementList < _transactionElements.size();
	}

	@Override
	public Node nextElement() {
		return _transactionElements.get(_positionInElementList++);
	}


}
