package shz.mock_comparison;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionFactory {

	private ConcurrentHashMap<String, Constructor<?>> _constructors;

	public TransactionFactory() {
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
			throw new FactoryException(
					"Failed to retrieve constructor for class", e);
		}
	}

	public Transaction get(String key, String[] arguments) {
		if (_constructors.containsKey(key)) {
			Constructor<?> constructor = _constructors.get(key);
			try {
				return (Transaction) constructor
						.newInstance(new Object[] { arguments });
			} catch (Exception e) {
				throw new FactoryException(
						"Failed to instantiate class for key " + key, e);
			}
		}
		throw new InvalidTransactionIdentifier();
	}

}
