package shz.eprocurement.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides helper methods used for validating the values of objects.
 *
 * @author Stephan Huez
 *
 */
public abstract class ValidationHelper {
    private static final String ID_FORMAT_REGEX = "[0-9|a-z|A-Z]{25}";

    public static final boolean isValidAgainstRegularExpression(String value, String regEx) {
        if (isNull(value)){
            return false;
        }
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }

    public static final boolean isNull(String value) {
        return null == value;
    }

    public static final boolean isEmpty(String value) {
        return null == value || value.isEmpty();
    }

    public static final boolean isLongerThan(String value, int length) {
        return null != value && value.length() > length;
    }

    public static final boolean isGreaterThanZero(Double value) {
        return null != value && value > Double.valueOf(0);
    }

    public static void validateId(String id){
        if (!isValidAgainstRegularExpression(id, ID_FORMAT_REGEX)) {
            throw new ValidationFailure(
                    "Id must contain exactly 25 allowed characters 0..9 a..z A..Z");
        }
    }

}
