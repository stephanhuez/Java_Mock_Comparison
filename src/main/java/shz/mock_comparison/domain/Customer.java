package shz.mock_comparison.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import static shz.mock_comparison.domain.ValidationHelper.*;

/**
 * Represents a customer in the domain.
 * 
 * @author Stephan Huez
 * 
 */
public class Customer extends DomainObject {
    private static final int ADDRESS_MAX_LENGTH = 100;
    private static final int LAST_NAME_MAX_LENGTH = 30;
    private static final int FIRST_NAME_MAX_LENGTH = 25;
    private String _id;
    private String _firstName;
    private String _lastName;
    private String _address;

    public Customer(String id, String firstName, String lastName, String address) {
        _id = id;
        _firstName = firstName;
        _lastName = lastName;
        _address = address;
    }

    public Customer(String id) {
        _id = id;
    }

    public void validate() {
        validateId();
        validateFirstName();
        validateLastName();
        validateAddress();
    }

    private void validateAddress() {
        if (isEmpty(_address) || isLongerThan(_address, ADDRESS_MAX_LENGTH)){
            throw new ValidationFailure("Address must contain at least one character and at most 100");
        }
    }

    private void validateLastName() {
        if (isEmpty(_lastName) || isLongerThan(_lastName, LAST_NAME_MAX_LENGTH)) {
            throw new ValidationFailure(
                    "Last Name must contain at least one character and at most 30");
        }
    }

    private void validateFirstName() {
        if (isEmpty(_firstName) || isLongerThan(_firstName, FIRST_NAME_MAX_LENGTH)) {
            throw new ValidationFailure(
                    "First Name must contain at least one character and at most 25");
        }
    }

    private void validateId() {
        ValidationHelper.validateId(_id);
    }

    @Override
    public boolean specialisedEquals(Object obj) {
        Customer rhs = (Customer) obj;
        return new EqualsBuilder().append(_id, rhs._id).append(_firstName, rhs._firstName)
                .append(_lastName, rhs._lastName).append(_address, rhs._address).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(_id).append(_firstName).append(_lastName)
                .append(_address).toHashCode();
    }

    public String getId() {
        return _id;
    }

    public String getAddress() {
        return _address;
    }

    public String getLastName() {
        return _lastName;
    }

    public String getFirstName() {
        return _firstName;
    }
}
