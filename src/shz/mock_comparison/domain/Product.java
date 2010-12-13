package shz.mock_comparison.domain;

import static shz.mock_comparison.domain.ValidationHelper.*;
import org.apache.commons.lang3.builder.*;

/**
 * Represents a Product in the domain.
 * 
 * @author Stephan Huez
 * 
 */
public class Product {

    private static final int DESCRIPTION_MAX_LENGTH = 100;
    private static final String ID_FORMAT_REGEX = "[0-9|a-z|A-Z]{25}";
    private String _id;
    private String _description;
    private Double _price;

    public Product(String id, String description, double price) {
        _id = id;
        _description = description;
        _price = price;
    }

    public Product(String id) {
        _id = id;
    }

    public String getId() {
        return _id;
    }

    public void validate() {
        validateId();
        validateDescription();
        validatePrice();
    }

    private void validatePrice() {
        if (!isGreaterThanZero(_price)){
            throw new ValidationFailure("Price must be greater than zero");
        }
    }

    private void validateDescription() {
        if (!(isNotNull(_description) && isNotEmpty(_description) && isLongerThan(_description, DESCRIPTION_MAX_LENGTH))) {
            throw new ValidationFailure("Description cannot be null or empty");
        }
    }

    private void validateId() {
        if (!(isNotNull(_id) && isValidAgainstRegularExpression(_id, ID_FORMAT_REGEX))) {
            throw new ValidationFailure("Id must contain exactly 25 allowed characters 0..9 a..z A..Z");
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(_price).append(_description).append(_id).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Product rhs = (Product) obj;
        return new EqualsBuilder().append(_id, rhs._id).append(_description, rhs._description)
                .append(_price, rhs._price).isEquals();
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }

    public String getDescription() {
        return _description;
    }

    public Double getPrice() {
        return _price;
    }

}
