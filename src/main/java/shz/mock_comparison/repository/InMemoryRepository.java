package shz.mock_comparison.repository;

import java.util.concurrent.ConcurrentHashMap;

import shz.mock_comparison.Repository;
import shz.mock_comparison.domain.Customer;
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
    private ConcurrentHashMap<String, Customer> _customers;

    public InMemoryRepository() {
        _products = new ConcurrentHashMap<String, Product>();
        _customers = new ConcurrentHashMap<String, Customer>();
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
        createProduct(product);
    }

    @Override
    public Product findProduct(String id) {
        return _products.get(id);
    }

    @Override
    public void createCustomer(Customer customer) {
        _customers.put(customer.getId(), customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        createCustomer(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        _customers.remove(customer.getId());
    }

    @Override
    public Customer findCustomer(String id) {
        return _customers.get(id);
    }

}
