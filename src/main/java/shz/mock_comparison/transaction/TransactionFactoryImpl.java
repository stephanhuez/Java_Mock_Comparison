package shz.mock_comparison.transaction;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import shz.mock_comparison.Repository;
import shz.mock_comparison.Transaction;
import shz.mock_comparison.TransactionFactory;

/**
 * This class implements a {@link TransactionFactory}.
 * 
 * @author Stephan Huez
 *
 */
public class TransactionFactoryImpl implements TransactionFactory {

	private ConcurrentHashMap<String, Constructor<?>> _constructors;
    private Repository _repository;

	public TransactionFactoryImpl(Repository repository) {
	    _repository = repository;
	    init();
	}

    private void init() {
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
			return getFirstConstructorInList(clazz);
		} catch (SecurityException e) {
			throw new FailedToInstantiateTransaction(
					"Failed to retrieve constructor for class", e);
		}
	}

    private Constructor<?> getFirstConstructorInList(Class<?> clazz) {
        return (Constructor<?>) clazz.getConstructors()[0];
    }

    @Override
    public Transaction get(String key, ArrayList<String> arguments) {
        if (_constructors.containsKey(key)) {
            Constructor<?> constructor = _constructors.get(key);
            try {
                return (Transaction) constructor
                        .newInstance(buildConstructorArguments(arguments));
            } catch (Exception e) {
                throw new FailedToInstantiateTransaction(
                        "Failed to instantiate class for key " + key, e);
            }
        }
        throw new InvalidTransactionKey();
    }

    /**
     * The constructor takes two parameters by convention.
     * 
     * @param arguments
     * @return
     */
    private Object[] buildConstructorArguments(ArrayList<String> arguments) {
        return new Object[] { arguments,_repository };
    }

}
