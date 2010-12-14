package shz.mock_comparison.parser;

import org.junit.Test;

import shz.mock_comparison.TransactionParser;

/**
 * @author Stephan Huez
 * 
 */
public class Test_TextTransactionParser extends AbstractTransactionParserTests {

    private TextTransactionParser _parser;

    @Test
    public void should_Parse_Create_Product_Transaction() {
        given_ATransactionFactory();
        given_ATransactionParser();
        given_TheFollowingArguments("0000001", "Bogus Product", "1200.99");
        given_TheFollowingKey("CreateProduct");
        given_TheFactoryReturnsATransaction();

        when_CallingTheParserWith("CreateProduct|0000001|Bogus Product|1200.99");

        then_ActualTransactionShouldBeExpectedTransaction();
    }

    @Test
    public void should_Parse_Update_Product_Transaction() {
        given_ATransactionFactory();
        given_ATransactionParser();
        given_TheFollowingArguments("0000001", "Bogus Product", "1200.99");
        given_TheFollowingKey("UpdateProduct");
        given_TheFactoryReturnsATransaction();

        when_CallingTheParserWith("UpdateProduct|0000001|Bogus Product|1200.99");

        then_ActualTransactionShouldBeExpectedTransaction();
    }

    @Test
    public void should_Parse_Delete_Product_Transaction() {
        given_ATransactionFactory();
        given_ATransactionParser();
        given_TheFollowingArguments("0000001");
        given_TheFactoryReturnsATransaction("DeleteProduct");
        given_TheFollowingKey("DeleteProduct");
        given_TheFactoryReturnsATransaction();

        when_CallingTheParserWith("DeleteProduct|0000001");

        then_ActualTransactionShouldBeExpectedTransaction();
    }

    protected TransactionParser getParser() {
        return _parser;
    }

    private void given_ATransactionParser() {
        _parser = new TextTransactionParser(_transactionFactoryStub);
    }

}
