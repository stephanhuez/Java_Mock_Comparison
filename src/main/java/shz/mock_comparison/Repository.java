package shz.mock_comparison;

import shz.mock_comparison.domain.Customer;
import shz.mock_comparison.domain.Product;

/**
 * This interface provides an abstraction on top of a repository of the domain
 * objects.
 * 
 * @author Stephan Huez
 * 
 */
public interface Repository {

    /**
     * Store a new product in the repository.
     * 
     * @param product
     */
    void createProduct(Product product);

    /**
     * Delete an existing product from the repository.
     * 
     * @param product
     */
    void deleteProduct(Product product);

    /**
     * Update an existing product in the repository.
     * 
     * @param product
     */
    void updateProduct(Product product);

    /**
     * Find a product with the provided identifier.
     * 
     * @param id
     * @return
     */
    Product findProduct(String id);

    /**
     * Store a new customer in the repository.
     * 
     * @param customer
     */
    void createCustomer(Customer customer);

    /**
     * Update an existing customer in the repository.
     * 
     * @param customer
     */
    void updateCustomer(Customer customer);

    /**
     * Delete an existing customer from the repository.
     * 
     * @param customer
     */
    void deleteCustomer(Customer eq);

    /**
     * Find a customer with the provided identifier.
     * 
     * @param id
     * @return
     */
    Customer findCustomer(String id);

}
