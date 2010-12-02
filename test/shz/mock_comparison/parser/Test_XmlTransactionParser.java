package shz.mock_comparison.parser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import shz.mock_comparison.Transaction;
import shz.mock_comparison.TransactionFactory;
import shz.mock_comparison.TransactionParser;

/**
 * 
 * @author Stephan Huez
 * 
 */
@SuppressWarnings("serial")
public class Test_XmlTransactionParser {

    private TransactionParser _parser;
    private TransactionFactory _transactionFactoryStub;
    private Transaction _transactionStub;

    @Before
    public void given() {
        _transactionStub = mock(Transaction.class);
        _transactionFactoryStub = mock(TransactionFactory.class);
        _parser = new XmlTransactionParser(_transactionFactoryStub);
    }

    @Test
    public void should_Parse_Delete_Product_Transaction() throws Exception {
        // Given
        Node node = newXmlNode("<DeleteProduct><id>000001</id></DeleteProduct>");
        final ArrayList<String> arguments = new ArrayList<String>() {
            {
                add("000001");
            }
        };
        when(_transactionFactoryStub.get(eq("DeleteProduct"), eq(arguments))).thenReturn(_transactionStub);

        // When
        Transaction transaction = _parser.parse(node);

        // Then
        assertThat(transaction,is(_transactionStub));
    }

    @Test
    public void should_Parse_Create_Product_Transaction() throws Exception {
        // Given
        Node node = newXmlNode("<CreateProduct><id>000002</id><description>Product Two</description><price>999.99</price></CreateProduct>");
        final ArrayList<String> arguments = new ArrayList<String>() {
            {
                 add("000002");
                 add("Product Two");
                 add("999.99");
            }
        };
        when(_transactionFactoryStub.get(eq("CreateProduct"), eq(arguments))).thenReturn(_transactionStub);

        // When
        Transaction transaction = _parser.parse(node);

        // Then
        assertThat(transaction,is(_transactionStub));
    }

    @Test
    public void should_Parse_Update_Product_Transaction() throws Exception {
        // Given
        Node node = newXmlNode("<UpdateProduct><id>000009</id><description>Product number 26589</description><price>1548.24</price></UpdateProduct>");
        final ArrayList<String> arguments = new ArrayList<String>() {
            {
                 add("000009");
                 add("Product number 26589");
                 add("1548.24");
            }
        };
        when(_transactionFactoryStub.get(eq("UpdateProduct"), eq(arguments))).thenReturn(_transactionStub);

        // When
        Transaction transaction = _parser.parse(node);

        // Then
        assertThat(transaction,is(_transactionStub));
    }

    private Element newXmlNode(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));
        Element node = doc.getDocumentElement();
        node.normalize();
        return node;
    }

}
