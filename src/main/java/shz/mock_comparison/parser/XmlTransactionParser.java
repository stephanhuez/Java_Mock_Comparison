package shz.mock_comparison.parser;

import java.util.ArrayList;

import org.w3c.dom.Node;

import shz.mock_comparison.Transaction;
import shz.mock_comparison.TransactionFactory;
import shz.mock_comparison.TransactionParser;

/**
 * Parses XML {@link Transaction} formatted as:
 *      <CreateProduct>
 *          <id>000001</id>
 *          <description>Product One</description>
 *          <price>76.49</price>
 *      </CreateProduct>
 * and uses a {@link TransactionFactory} to instantiate the {@link Transaction}.  
 * 
 * @author Stephan Huez
 *
 */
public class XmlTransactionParser implements TransactionParser {

    private TransactionFactory _transactionFactory;

    public XmlTransactionParser(TransactionFactory transactionFactory) {
        _transactionFactory = transactionFactory;
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
