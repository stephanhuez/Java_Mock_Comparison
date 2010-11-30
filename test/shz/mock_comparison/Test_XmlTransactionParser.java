package shz.mock_comparison;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * 
 * @author Stephan Huez
 * 
 */
public class Test_XmlTransactionParser {

    private TransactionParser _parser;

    @Before
    public void given() {
        _parser = new XmlTransactionParser();
    }

    @Test
    public void should_Parse_Delete_Product_Transaction() throws Exception {
        Node node = newXmlNode("<DeleteProduct><id>000001</id></DeleteProduct>");

        // When
        DeleteProductTransaction transaction = (DeleteProductTransaction) _parser.parse(node);

        // Then

        Product expectedProduct = new Product("000001");
        assertThat(transaction.getProduct(), equalTo(expectedProduct));
    }

    @Test
    public void should_Parse_Create_Product_Transaction() throws Exception {
        // Given
        Node node = newXmlNode("<CreateProduct><id>000002</id><description>Product Two</description><price>999.99</price></CreateProduct>");

        // When
        CreateProductTransaction transaction = (CreateProductTransaction) _parser.parse(node);

        // Then
        Product expectedProduct = new Product("000002", "Product Two", 999.99);
        assertThat(transaction.getProduct(), equalTo(expectedProduct));
    }

    @Test
    public void should_Parse_Update_Product_Transaction() throws Exception {
        // Given
        Node node = newXmlNode("<UpdateProduct><id>000009</id><description>Product number 26589</description><price>1548.24</price></UpdateProduct>");

        // When
        UpdateProductTransaction transaction = (UpdateProductTransaction) _parser.parse(node);

        // Then
        Product expectedProduct = new Product("000009", "Product number 26589", 1548.24);
        assertThat(transaction.getProduct(), equalTo(expectedProduct));
    }

    @Test
    public void should_Fail_To_Parse_Unkown_Transaction() throws Exception {
        // Given
        Node node = newXmlNode("<Bogus><id>000009</id><description>Product number 26589</description><price>1548.24</price></Bogus>");

        // When
        try {
            _parser.parse(node);
            fail("Should have thrown an exception");
        } catch (InvalidTransactionIdentifier iti) {
            // Then should raise an exception
        }
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
