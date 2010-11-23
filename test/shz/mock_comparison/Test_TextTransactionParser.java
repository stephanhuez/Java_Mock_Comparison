package shz.mock_comparison;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Stephan Huez
 * 
 */
public class Test_TextTransactionParser {

	private static final String CREATE_PRODUCT_0000001_BOGUS_PRODUCT_1200_99 = "CreateProduct|0000001|Bogus Product|1200.99";
	private static final String UPDATE_PRODUCT_0000001_BOGUS_PRODUCT_1200_99 = "UpdateProduct|0000001|Bogus Product|1200.99";
	private static final String DELETE_PRODUCT_0000001 = "DeleteProduct|0000001";
	private TextTransactionParser parser;

	@Before
	public void given(){
		parser = new TextTransactionParser();
	}
	
	@Test
	public void should_Parse_Create_Product_Transaction() {
		// When
		CreateProductTransaction transaction = (CreateProductTransaction) parser.parse(CREATE_PRODUCT_0000001_BOGUS_PRODUCT_1200_99);
		
		// Then
		assertThat(transaction, notNullValue());
	}

	@Test
	public void should_Parse_Create_Product_Transaction_Parameters() {
		// When
		CreateProductTransaction transaction = (CreateProductTransaction) parser.parse(CREATE_PRODUCT_0000001_BOGUS_PRODUCT_1200_99);
		
		// Then
		Product actualProduct = transaction.getProduct();
		Product expectedProduct = new Product("0000001","Bogus Product",1200.99);
		assertThat(actualProduct,equalTo(expectedProduct));		
	}
	
	@Test
	public void should_Parse_Update_Product_Transaction() {
		// When
		UpdateProductTransaction transaction = (UpdateProductTransaction) parser.parse(UPDATE_PRODUCT_0000001_BOGUS_PRODUCT_1200_99);
		
		// Then
		assertThat(transaction, notNullValue());
	}
	
	@Test
	public void should_Parse_Update_Product_Transaction_Parameters() {
		// When
		UpdateProductTransaction transaction = (UpdateProductTransaction) parser.parse(UPDATE_PRODUCT_0000001_BOGUS_PRODUCT_1200_99);
		
		// Then
		Product actualProduct = transaction.getProduct();
		Product expectedProduct = new Product("0000001","Bogus Product",1200.99);
		assertThat(actualProduct,equalTo(expectedProduct));		
	}

	
	@Test
	public void should_Parse_Delete_Product_Transaction() {
		// When
		DeleteProductTransaction transaction = (DeleteProductTransaction) parser.parse(DELETE_PRODUCT_0000001);
		
		// Then
		assertThat(transaction, notNullValue());
	}

	@Test
	public void should_Parse_Delete_Product_Transaction_Parameters() {
		// When
		DeleteProductTransaction transaction = (DeleteProductTransaction) parser.parse(DELETE_PRODUCT_0000001);
		
		// Then
		Product actualProduct = transaction.getProduct();
		Product expectedProduct = new Product("0000001");
		assertThat(actualProduct,equalTo(expectedProduct));		
	}
	
	@Test
	public void should_Fail_To_Parse_Unkown_Transaction(){
		// When
		try{
			parser.parse("Bogus");			
			fail("Should have thrown an exception");
		}catch(InvalidTransactionIdentifier iti){
			// Then should raise an exception
		}
	}
}
