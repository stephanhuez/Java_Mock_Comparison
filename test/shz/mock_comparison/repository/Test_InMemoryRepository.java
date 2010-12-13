package shz.mock_comparison.repository;

import org.junit.Before;
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
public class Test_InMemoryRepository {
    
    private Repository _repository;

    @Before
    public void given(){
        _repository = new InMemoryRepository();
    }

    @Test
    public void should_Find_Created_Product() {
        // When
        _repository.createProduct(new Product("0001", "123456789", 78945.01));

        // Then
        Product actualProduct = _repository.find("0001");
        Product expectedProduct = new Product("0001", "123456789", 78945.01);
        assertThat(actualProduct, equalTo(expectedProduct));
    }

    @Test
    public void should_Find_Product_Among_Created_Products() {
        // When
        _repository.createProduct(new Product("111", "789456153", 45.01));
        _repository.createProduct(new Product("0001", "123456789", 78945.01));
        _repository.createProduct(new Product("0002", "6789", 999.99));

        // Then
        Product expectedProduct = new Product("0001", "123456789", 78945.01);
        Product actualProduct = _repository.find("0001");
        assertThat(actualProduct, equalTo(expectedProduct));
    }

    @Test
    public void should_Not_Find_Product_After_Deletion() {
        // Given
        _repository.createProduct(new Product("111", "789456153", 45.01));
        _repository.createProduct(new Product("0001", "123456789", 78945.01));
        _repository.createProduct(new Product("0002", "6789", 999.99));

        // When
        _repository.deleteProduct(new Product("0002"));

        // Then
        assertThat(_repository.find("0002"), nullValue());
    }

    @Test
    public void should_Update_Product() {
        // Given
        _repository.createProduct(new Product("0001","Bogus",154.98));

        // When
        _repository.updateProduct(new Product("0001","Description",785.24));
        
        // Then
        Product expectedProduct = new Product("0001", "Description", 785.24);
        Product actualProduct = _repository.find("0001");
        assertThat(actualProduct, equalTo(expectedProduct));
    }
}
