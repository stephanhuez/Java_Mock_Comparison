package shz.mock_comparison;

import java.util.ArrayList;

import org.w3c.dom.Node;

public class XmlTransactionParser implements TransactionParser {

    private TransactionFactory _transactionFactory;

    public XmlTransactionParser() {
        _transactionFactory = new TransactionFactory();
    }

    @Override
    public Transaction parse(Object inputToParse) {
        Node xmlNode = (Node) inputToParse;
        ArrayList<String> arguments = parseArguments(xmlNode);
        return _transactionFactory.get(xmlNode.getNodeName(), arguments);
    }

    private ArrayList<String> parseArguments(Node xmlNode) {
        ArrayList<String> arguments = new ArrayList<String>();
        for (int i = 0; i < xmlNode.getChildNodes().getLength(); i++) {
            Node aNode = xmlNode.getChildNodes().item(i);
            if (aNode.getNodeType() == Node.ELEMENT_NODE) {
                arguments.add(aNode.getFirstChild().getNodeValue());
            }
        }
        return arguments;
    }
}
