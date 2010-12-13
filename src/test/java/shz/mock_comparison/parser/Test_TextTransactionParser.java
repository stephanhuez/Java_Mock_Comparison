package shz.mock_comparison.parser;

import org.junit.Before;
import org.junit.Test;

import shz.mock_comparison.TransactionParser;

/**
 * @author Stephan Huez
 * 
 */
public class Test_TextTransactionParser extends AbstractTransactionParserTests {

    private TextTransactionParser _parser;

    @Before
    public void given() {
        super.given();
        _parser = new TextTransactionParser(_transactionFactoryStub);
    }

    @Test
    public void should_Parse_Create_Product_Transaction() {
        given_TheFollowingArguments("0000001", "Bogus Product", "1200.99");
        given_FactoryReturnsExpectedTransactionMatchingTypeAndArguments("CreateProduct");

        when_parserCalledWith("CreateProduct|0000001|Bogus Product|1200.99");

        then_ActualTransactionShouldBeExpectedTransaction();
    }

    @Test
    public void should_Parse_Update_Product_Transaction() {
        given_TheFollowingArguments("0000001", "Bogus Product", "1200.99");
        given_FactoryReturnsExpectedTransactionMatchingTypeAndArguments("UpdateProduct");

        when_parserCalledWith("UpdateProduct|0000001|Bogus Product|1200.99");

        then_ActualTransactionShouldBeExpectedTransaction();
    }

    @Test
    public void should_Parse_Delete_Product_Transaction() {
        given_TheFollowingArguments("0000001");
        given_FactoryReturnsExpectedTransactionMatchingTypeAndArguments("DeleteProduct");

        when_parserCalledWith("DeleteProduct|0000001");

        then_ActualTransactionShouldBeExpectedTransaction();
    }

    protected TransactionParser getParser() {
        return _parser;
    }

}
