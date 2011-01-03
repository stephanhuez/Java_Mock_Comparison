package shz.eprocurement.domain;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static shz.eprocurement.utils.CustomAssertions.then_AnExceptionShoulBeRaised;

/**
 *
 * @author Stephan Huez
 *
 */
public class Test_Customer_Validate {
    private static final String OVERSIZED_STRING = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    private static final String EXPECTED_ID_VALIDATION_MESSAGE = "Id must contain exactly 25 allowed characters 0..9 a..z A..Z";
    private static final String EXPECTED_FIRST_NAME_VALIDATION_MESSAGE = "First Name must contain at least one character and at most 25";
    private static final String EXPECTED_LAST_NAME_VALIDATION_MESSAGE = "Last Name must contain at least one character and at most 30";
    private static final String EXPECTED_ADDRESS_VALIDATION_MESSAGE = "Address must contain at least one character and at most 100";
    private static final String VALID_ID = "0123456789012345678901234";
    private static final String VALID_FIRST_NAME = "Billy";
    private static final String VALID_LAST_NAME = "The Kid";
    private static final String VALID_ADDRESS = "Billy's address";
    private Customer _customer;

    @Test
    public void should_Reject_Empty_Id() {
        given_ACustomer(new Customer(""));

        then_ValidationShouldFailWithErrorMessage(EXPECTED_ID_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Reject_Null_Id() {
        given_ACustomer(new Customer(null));

        then_ValidationShouldFailWithErrorMessage(EXPECTED_ID_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Reject_Id_That_Does_Not_Have_25_Characters() {
        given_ACustomer(new Customer("0000000000000", VALID_FIRST_NAME, VALID_LAST_NAME,
                VALID_ADDRESS));

        then_ValidationShouldFailWithErrorMessage(EXPECTED_ID_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Reject_Id_That_Contains_Illegal_Characters() {
        given_ACustomer(new Customer("123456789#234567891234567", VALID_FIRST_NAME,
                VALID_LAST_NAME, VALID_ADDRESS));

        then_ValidationShouldFailWithErrorMessage(EXPECTED_ID_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Accept_Valid_Customer() {
        given_ACustomer(new Customer(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, VALID_ADDRESS));

        then_ValidationShouldPass();
    }

    @Test
    public void should_Reject_Null_First_Name() {
        given_ACustomer(new Customer(VALID_ID, null, VALID_LAST_NAME, VALID_ADDRESS));

        then_ValidationShouldFailWithErrorMessage(EXPECTED_FIRST_NAME_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Reject_Empty_First_Name() {
        given_ACustomer(new Customer(VALID_ID, "", VALID_LAST_NAME, VALID_ADDRESS));

        then_ValidationShouldFailWithErrorMessage(EXPECTED_FIRST_NAME_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Reject_Too_Long_First_Name() {
        given_ACustomer(new Customer(VALID_ID, OVERSIZED_STRING, VALID_LAST_NAME, VALID_ADDRESS));

        then_ValidationShouldFailWithErrorMessage(EXPECTED_FIRST_NAME_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Reject_Null_Last_Name() {
        given_ACustomer(new Customer(VALID_ID, VALID_FIRST_NAME, null, VALID_ADDRESS));

        then_ValidationShouldFailWithErrorMessage(EXPECTED_LAST_NAME_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Reject_Empty_Last_Name() {
        given_ACustomer(new Customer(VALID_ID, VALID_FIRST_NAME, "", VALID_ADDRESS));

        then_ValidationShouldFailWithErrorMessage(EXPECTED_LAST_NAME_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Reject_Too_Long_Last_Name() {
        given_ACustomer(new Customer(VALID_ID, VALID_FIRST_NAME, OVERSIZED_STRING, VALID_ADDRESS));

        then_ValidationShouldFailWithErrorMessage(EXPECTED_LAST_NAME_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Reject_Null_Address() {
        given_ACustomer(new Customer(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, null));

        then_ValidationShouldFailWithErrorMessage(EXPECTED_ADDRESS_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Reject_Empty_Address() {
        given_ACustomer(new Customer(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, ""));

        then_ValidationShouldFailWithErrorMessage(EXPECTED_ADDRESS_VALIDATION_MESSAGE);
    }

    @Test
    public void should_Reject_Too_Long_Address() {
        given_ACustomer(new Customer(VALID_ID, VALID_FIRST_NAME, VALID_LAST_NAME, OVERSIZED_STRING));

        then_ValidationShouldFailWithErrorMessage(EXPECTED_ADDRESS_VALIDATION_MESSAGE);
    }

    private void then_ValidationShouldPass() {
        _customer.validate();
    }

    private void then_ValidationShouldFailWithErrorMessage(String expectedIdValidationMessage) {
        try {
            _customer.validate();
            then_AnExceptionShoulBeRaised();
        } catch (ValidationFailure vf) {
            // Then
            assertThat(vf.getMessage(), equalTo(expectedIdValidationMessage));
        }
    }

    private void given_ACustomer(Customer customer) {
        _customer = customer;
    }

}
