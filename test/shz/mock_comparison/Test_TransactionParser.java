package shz.mock_comparison;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

/**
 * @author Stephan Huez
 * 
 */
public class Test_TransactionParser {

	private static final String CREATE_PRODUCT_0000001_BOGUS_PRODUCT_1200_99 = "CreateProduct|0000001|Bogus Product|1200.99";
	private static final String UPDATE_PRODUCT_0000001_BOGUS_PRODUCT_1200_99 = "UpdateProduct|0000001|Bogus Product|1200.99";
	private static final String DELETE_PRODUCT_0000001 = "DeleteProduct|0000001";
	private TransactionParser parser;

	@Before
	public void given(){
		parser = new TransactionParser();
	}
	
	@Test
	public void should_Parse_Create_Product_Transaction() {
		// When
		Transaction transaction = parser.parse(CREATE_PRODUCT_0000001_BOGUS_PRODUCT_1200_99);
		
		// Then
		assertThat(transaction, instanceOf(CreateProductTransaction.class));
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
		Transaction transaction = parser.parse(UPDATE_PRODUCT_0000001_BOGUS_PRODUCT_1200_99);
		
		// Then
		assertThat(transaction, instanceOf(UpdateProductTransaction.class));
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
		Transaction transaction = parser.parse(DELETE_PRODUCT_0000001);
		
		// Then
		assertThat(transaction, instanceOf(DeleteProductTransaction.class));
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
