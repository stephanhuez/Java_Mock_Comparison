package shz.mock_comparison.transaction;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import shz.mock_comparison.Transaction;
import shz.mock_comparison.TransactionFactory;

public class TransactionFactoryImpl implements TransactionFactory {

	private ConcurrentHashMap<String, Constructor<?>> _constructors;

	public TransactionFactoryImpl() {
		_constructors = new ConcurrentHashMap<String, Constructor<?>>();
		_constructors.put("CreateProduct",
				getConstructor(CreateProductTransaction.class));
		_constructors.put("DeleteProduct",
				getConstructor(DeleteProductTransaction.class));
		_constructors.put("UpdateProduct",
				getConstructor(UpdateProductTransaction.class));

	}

	private Constructor<?> getConstructor(Class<?> clazz) {
		try {
			return (Constructor<?>) clazz.getConstructors()[0];
		} catch (SecurityException e) {
			throw new ImpossibleFactoryInstantiation(
					"Failed to retrieve constructor for class", e);
		}
	}

    /* (non-Javadoc)
     * @see shz.mock_comparison.TransactionFactory#get(java.lang.String, java.util.ArrayList)
     */
    @Override
    public Transaction get(String key, ArrayList<String> arguments) {
        if (_constructors.containsKey(key)) {
            Constructor<?> constructor = _constructors.get(key);
            try {
                return (Transaction) constructor
                        .newInstance(new Object[] { arguments });
            } catch (Exception e) {
                throw new ImpossibleFactoryInstantiation(
                        "Failed to instantiate class for key " + key, e);
            }
        }
        throw new InvalidTransactionIdentifier();
    }

}
