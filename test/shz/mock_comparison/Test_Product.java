package shz.mock_comparison;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import org.junit.Test;

/**
 * @author Stephan Huez
 * 
 */
public class Test_Product {

	@Test
	public void should_Have_Different_HashCode_With_Different_Contents() {
		// Given
		Product productA = new Product("1", "Product 1", 1200.99);
		Product productB = new Product("2", "Product 2", 1200.99);

		// Then
		assertThat(productA.hashCode(), not(equalTo(productB.hashCode())));
	}

	@Test
	public void should_Have_Same_HashCode_With_Same_Contents() {
		// Given
		Product productA = new Product("1", "Product 1", 1200.99);
		Product productB = new Product("1", "Product 1", 1200.99);

		// Then
		assertThat(productA.hashCode(), equalTo(productB.hashCode()));
	}
	
	@Test
	public void should_Have_Same_HashCode_With_Same_Object() {
		// Given
		Product product = new Product("1", "Product 1", 1200.99);

		// Then
		assertThat(product.hashCode(), equalTo(product.hashCode()));
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
	
}
