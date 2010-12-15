package shz.mock_comparison.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import org.junit.Test;


import shz.mock_comparison.domain.Product;

/**
 * @author Stephan Huez
 * 
 */
public class Test_Product {


    @Test
    public void should_Have_Different_HashCodes_With_Different_Contents() {
        // Given
        DomainObject productA = new Product("1", "Product 1", 1200.99);
        DomainObject productB = new Product("2", "Product 2", 1200.99);

        // Then
        assertThat(productA.hashCode(), not(equalTo(productB.hashCode())));
    }

    @Test
    public void should_Have_Same_HashCodes_With_Same_Contents() {
        // Given
        DomainObject productA = new Product("1", "Product 1", 1200.99);
        DomainObject productB = new Product("1", "Product 1", 1200.99);

        // Then
        assertThat(productA.hashCode(), equalTo(productB.hashCode()));
    }

    @Test
    public void should_Be_Different_With_Different_Contents() {
        // Given
        Product productA = new Product("1", "Product 1", 1200.99);
        Product productB = new Product("2", "Product 2", 1200.99);

        // Then
        assertThat(productA, not(equalTo(productB)));
    }

    @Test
    public void should_Be_Equal_With_Same_Contents() {
        // Given
        Product productA = new Product("1", "Product 1", 1200.99);
        Product productB = new Product("1", "Product 1", 1200.99);

        // Then
        assertThat(productA, equalTo(productB));
    }

    @Test
    public void should_Be_Equal_With_Same_Object() {
        // Given
        Product product = new Product("1", "Product 1", 1200.99);

        // Then
        assertThat(product, equalTo(product));
    }
    
    @Test
    public void should_Build_To_String_For_Full_Product(){
        // Given
        DomainObject product = new Product("00001", "Product One",12358.98);
        
        // Then
        assertThat(product.toString(),equalTo("Product[_id=00001,_description=Product One,_price=12358.98]"));
    }

    @Test
    public void should_Build_To_String_For_Non_Full_Product(){
        // Given
        DomainObject product = new Product("00001");
        
        // Then
        assertThat(product.toString(),equalTo("Product[_id=00001,_description=<null>,_price=<null>]"));
    }
}
