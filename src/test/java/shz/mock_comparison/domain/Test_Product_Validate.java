package shz.mock_comparison.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static shz.mock_comparison.utils.CustomAssertions.shouldHaveRaisedAnException;

import org.junit.Test;

/**
 * Test the {@link Product.#validate()} of the Product.
 * 
 * @author Stephan Huez
 * 
 */
public class Test_Product_Validate {
    private static final String VALID_DESCRIPTION = "Valid description";
    private static final String VALID_ID = "1234567891234567891234567";
    private static final String EXPECTED_ID_VALIDATION_MESSAGE = "Id must contain exactly 25 allowed characters 0..9 a..z A..Z";
    private static final String EXPECTED_DESCRIPTION_VALIDATION_MESSAGE = "Description cannot be null or empty";
    private static final String EXPECTED_PRICE_VALIDATION_MESSAGE = "Price must be greater than zero";
    private static final double VALID_PRICE = 99.94;
    private Product _product;

    @Test
    public void should_Reject_Empty_Id() {
        given_AProduct(new Product(""));

        then_TheProductShouldFailValidatingWithErrorMessage(EXPECTED_ID_VALIDATION_MESSAGE);

    }

    @Test
    public void should_Reject_Null_Id() {
        given_AProduct(new Product(null));

        then_TheProductShouldFailValidatingWithErrorMessage(EXPECTED_ID_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Reject_Id_That_Does_Not_Have_25_Characters() {
        given_AProduct(new Product("0000000000000",VALID_DESCRIPTION,VALID_PRICE));

        then_TheProductShouldFailValidatingWithErrorMessage(EXPECTED_ID_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Accept_Valid_Id() {
        given_AProduct(new Product(VALID_ID, VALID_DESCRIPTION, VALID_PRICE));

        then_TheProductShouldPassValidating();
    }

    @Test
    public void should_Reject_Id_That_Contains_Illegal_Characters() {
        given_AProduct(new Product("123456789#234567891234567",VALID_DESCRIPTION,VALID_PRICE));

        then_TheProductShouldFailValidatingWithErrorMessage(EXPECTED_ID_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Reject_Null_Description() {
        given_AProduct(new Product(VALID_ID, null, VALID_PRICE));

        then_TheProductShouldFailValidatingWithErrorMessage(EXPECTED_DESCRIPTION_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Reject_Empty_Description() {
        given_AProduct(new Product(VALID_ID, "", VALID_PRICE));

        then_TheProductShouldFailValidatingWithErrorMessage(EXPECTED_DESCRIPTION_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Reject_Too_Long_Description() {
        given_AProduct(new Product(
                VALID_ID,
                "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ",
                VALID_PRICE));

        then_TheProductShouldFailValidatingWithErrorMessage(EXPECTED_DESCRIPTION_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Reject_Price_Less_Than_Zero() {
        given_AProduct(new Product(
                VALID_ID,
                VALID_DESCRIPTION,
                -1));
        
        then_TheProductShouldFailValidatingWithErrorMessage(EXPECTED_PRICE_VALIDATION_MESSAGE);
    }
    
    @Test
    public void should_Reject_Price_Equal_To_Zero(){
        given_AProduct(new Product(
                VALID_ID,
                VALID_DESCRIPTION,
                0.0));
        
        then_TheProductShouldFailValidatingWithErrorMessage(EXPECTED_PRICE_VALIDATION_MESSAGE);        
    }

    private void given_AProduct(Product product) {
        _product = product;

    }

    private void then_TheProductShouldFailValidatingWithErrorMessage(String expectedIdValidationMessage) {
        try {
            _product.validate();
            shouldHaveRaisedAnException();
        } catch (ValidationFailure vf) {
            // Then
            assertThat(vf.getMessage(), equalTo(expectedIdValidationMessage));
        }
    }

    private void then_TheProductShouldPassValidating() {
        _product.validate();
    }

}
