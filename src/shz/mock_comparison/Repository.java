package shz.mock_comparison;

import shz.mock_comparison.domain.Product;

public interface Repository {

    void createProduct(Product product);

    void deleteProduct(Product product);

    void updateProduct(Product product);

}
