package shz.mock_comparison.domain;

import static shz.mock_comparison.domain.ValidationHelper.*;
import org.apache.commons.lang3.builder.*;

/**
 * Represents a Product in the domain.
 * 
 * @author Stephan Huez
 * 
 */
public class Product extends DomainObject {

    private static final int DESCRIPTION_MAX_LENGTH = 100;
    private String _id;
    private String _description;
    private Double _price;

    public Product(String id) {
        _id = id;
    }

    public Product(String id, String description, double price) {
        _id = id;
        _description = description;
        _price = price;
    }

    public void validate() {
        validateId();
        validateDescription();
        validatePrice();
    }

    private void validatePrice() {
        if (!isGreaterThanZero(_price)) {
            throw new ValidationFailure("Price must be greater than zero");
        }
    }

    private void validateDescription() {
        if (isEmpty(_description) || isLongerThan(_description,
                DESCRIPTION_MAX_LENGTH)) {
            throw new ValidationFailure("Description cannot be null or empty");
        }
    }

    private void validateId() {
        ValidationHelper.validateId(_id);
    }
    
    public String getId() {
        return _id;
    }

    public String getDescription() {
        return _description;
    }

    public Double getPrice() {
        return _price;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(_price).append(_description).append(_id).toHashCode();
    }

    @Override
    public boolean specialisedEquals(Object obj) {
        Product rhs = (Product) obj;
        return new EqualsBuilder().append(_id, rhs._id).append(_description, rhs._description)
                .append(_price, rhs._price).isEquals();
    }

}
