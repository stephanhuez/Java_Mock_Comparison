package shz.eprocurement.transaction;

import shz.eprocurement.Repository;
import shz.eprocurement.Transaction;
import shz.eprocurement.domain.Product;

/**
 * An abstract class that deals with {@link Product} based {@link Transaction}.
 * @author Stephan Huez
 *
 */
public abstract class ProductTransaction implements Transaction {

    private Product _product;
    private Repository _repository;

    public ProductTransaction(Repository repository, Product product) {
        _product = product;
        _repository = repository;
    }

    public Product getProduct() {
        return _product;
    }

    public String getProductId() {
        return _product.getId();
    }

    public String getProductDescription() {
        return _product.getDescription();
    }

    public Double getProductPrice() {
        return _product.getPrice();
    }

    protected Repository getRepository() {
        return _repository;
    }
}
