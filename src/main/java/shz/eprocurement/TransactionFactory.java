package shz.eprocurement;

import java.util.ArrayList;

/**
 * This interface describes what a factory of transactions provides.
 *
 * @author Stephan Huez
 *
 */
public interface TransactionFactory {

    public abstract Transaction get(String key, ArrayList<String> arguments);

}