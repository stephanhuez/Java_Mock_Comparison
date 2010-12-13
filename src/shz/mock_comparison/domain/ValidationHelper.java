package shz.mock_comparison.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides helper methods used for validating the values of objects.
 * 
 * @author Stephan Huez
 * 
 */
public abstract class ValidationHelper {

    public static final boolean isValidAgainstRegularExpression(String value, String regEx) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }

    public static final boolean isNotNull(String value) {
        return null != value;
    }

    public static final boolean isNotEmpty(String value) {
        return null != value && !value.isEmpty();
    }

    public static final boolean isLongerThan(String value, int length) {
        return null != value && value.length() <= length;
    }
    
    public static final boolean isGreaterThanZero(Double value) {
        return null != value && value > Double.valueOf(0);
    }

}
