package shz.mock_comparison.repository;

import java.util.concurrent.ConcurrentHashMap;

import shz.mock_comparison.Repository;
import shz.mock_comparison.domain.Product;

/**
 * An In-Memory implementation of the {@link Repository} interface that uses a
 * HashMap to store objects.
 * 
 * @author Stephan Huez
 * 
 */
public class InMemoryRepository implements Repository {

    private ConcurrentHashMap<String, Product> _products;

    public InMemoryRepository() {
        _products = new ConcurrentHashMap<String, Product>();
    }

    @Override
    public void createProduct(Product product) {
        _products.put(product.getId(), product);
    }

    @Override
    public void deleteProduct(Product product) {
        _products.remove(product.getId());
    }

    @Override
    public void updateProduct(Product product) {
        _products.put(product.getId(), product);
    }

    @Override
    public Product find(String id) {
        return _products.get(id);
    }

}
