package shz.mock_comparison.repository;

import org.junit.Test;

import shz.mock_comparison.Repository;
import shz.mock_comparison.domain.Product;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * 
 * @author Stephan Huez
 * 
 */
public class Test_InMemoryRepository_Product {

    private Repository _repository;
    private Product _foundProduct;

    @Test
    public void should_Find_Created_Product() {
        given_AnInMemoryRepository();
        given_ThisProductInTheRepository(new Product("0001", "123456789", 78945.01));

        when_LookingForProductWithId("0001");

        then_FoundProductShouldEqualToExpectedProduct(new Product("0001", "123456789", 78945.01));
    }

    private void given_ThisProductInTheRepository(Product product) {
        _repository.createProduct(product);
    }

    @Test
    public void should_Find_Product_Among_Created_Products() {
        given_AnInMemoryRepository();
        given_ThisProductInTheRepository(new Product("111", "789456153", 45.01));
        given_ThisProductInTheRepository(new Product("0001", "123456789", 78945.01));
        given_ThisProductInTheRepository(new Product("0002", "6789", 999.99));

        when_LookingForProductWithId("0001");
        
        then_FoundProductShouldEqualToExpectedProduct(new Product("0001", "123456789", 78945.01));
    }

    @Test
    public void should_Not_Find_Product_After_Deletion() {
        given_AnInMemoryRepository();
        given_ThisProductInTheRepository(new Product("111", "789456153", 45.01));
        given_ThisProductInTheRepository(new Product("0001", "123456789", 78945.01));
        given_ThisProductInTheRepository(new Product("0002", "6789", 999.99));

        when_DeletingAProduct(new Product("0002"));
        when_LookingForProductWithId("0002");

        then_FoundProductShouldBeNull();
    }

    private void then_FoundProductShouldBeNull() {
        assertThat(_foundProduct, nullValue());
    }

    @Test
    public void should_Update_Product() {
        given_AnInMemoryRepository();
        given_ThisProductInTheRepository(new Product("0001", "Bogus", 154.98));

        when_UpdatingAProduct(new Product("0001", "Description", 785.24));
        when_LookingForProductWithId("0001");
        
        then_FoundProductShouldEqualToExpectedProduct(new Product("0001", "Description", 785.24));
    }

    private void when_LookingForProductWithId(String id) {
        _foundProduct = _repository.findProduct(id);
    }

    private void then_FoundProductShouldEqualToExpectedProduct(Product product) {
        assertThat(_foundProduct, equalTo(product));
    }

    private void when_UpdatingAProduct(Product product) {
        _repository.updateProduct(product);
    }

    private void given_AnInMemoryRepository() {
        _repository = new InMemoryRepository();
    }

    private void when_DeletingAProduct(Product product) {
        _repository.deleteProduct(product);
    }

}
