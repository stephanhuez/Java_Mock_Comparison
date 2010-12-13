package shz.mock_comparison;

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
    Product find(String id);

}
