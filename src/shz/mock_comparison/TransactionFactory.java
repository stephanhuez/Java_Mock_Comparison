package shz.mock_comparison;

import java.util.ArrayList;

public interface TransactionFactory {

    public abstract Transaction get(String key, ArrayList<String> arguments);

}