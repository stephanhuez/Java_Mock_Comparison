package shz.mock_comparison.utils;

import java.util.ArrayList;

import shz.mock_comparison.Transaction;

/**
 * Utility class for testing.
 *
 * @author stephan
 *
 */
public class TypeUtils {

    /**
     * Build arguments for {@link Transaction}
     *
     * @param args
     * @return
     */
    @SuppressWarnings("serial")
    public static ArrayList<String> buildArguments(final String[] args) {
        return new ArrayList<String>() {
            {
                for (String arg : args) {
                    add(arg);
                }
            }
        };

    }

}
