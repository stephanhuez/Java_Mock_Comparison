package shz.mock_comparison.parser;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import shz.mock_comparison.TransactionParser;

/**
 * 
 * @author Stephan Huez
 * 
 */
public class Test_XmlTransactionParser extends AbstractTransactionParserTests {

    private TransactionParser _parser;

    @Test
    public void should_Parse_Delete_Product_Transaction() throws Exception {
        given_ATransactionFactory();
        given_ATransactionParser();
        given_TheFollowingArguments("000001");
        given_TheFollowingKey("DeleteProduct");
        given_TheFactoryReturnsATransaction();

        when_CallingTheParserWith(newXmlNode("<DeleteProduct><id>000001</id></DeleteProduct>"));

        then_ActualTransactionShouldBeExpectedTransaction();
    }

    @Test
    public void should_Parse_Create_Product_Transaction() throws Exception {
        given_ATransactionFactory();
        given_ATransactionParser();
        given_TheFollowingArguments("000002", "Product Two", "999.99");
        given_TheFollowingKey("CreateProduct");
        given_TheFactoryReturnsATransaction();

        when_CallingTheParserWith(newXmlNode("<CreateProduct><id>000002</id><description>Product Two</description><price>999.99</price></CreateProduct>"));

        then_ActualTransactionShouldBeExpectedTransaction();
    }

    @Test
    public void should_Parse_Update_Product_Transaction() throws Exception {
        given_ATransactionFactory();
        given_ATransactionParser();
        given_TheFollowingArguments("000009", "Product number 26589", "1548.24");
        given_TheFollowingKey("UpdateProduct");
        given_TheFactoryReturnsATransaction();

        when_CallingTheParserWith(newXmlNode("<UpdateProduct><id>000009</id><description>Product number 26589</description><price>1548.24</price></UpdateProduct>"));

        then_ActualTransactionShouldBeExpectedTransaction();
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

    @Override
    protected TransactionParser getParser() {
        return _parser;
    }

    private void given_ATransactionParser() {
        _parser = new XmlTransactionParser(_transactionFactoryStub);
    }

}
