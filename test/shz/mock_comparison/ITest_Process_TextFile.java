package shz.mock_comparison;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ITest_Process_TextFile {

	private TextFileReader sourceReader;
	private InputStream inputStream;
	private TransactionParser transactionParser;
	private TransactionReader transactionReader;

	@Before
	public void given() {
		inputStream = ClassLoader
				.getSystemResourceAsStream("shz/mock_comparison/Transactions.txt");
		sourceReader = new TextFileReader(inputStream);
		transactionParser = new TextTransactionParser();
		transactionReader = new TransactionReader(sourceReader,
				transactionParser);
	}

	@After
	public void tearDown() throws Exception {
		sourceReader.close();
		inputStream.close();
	}

	@Test
	public void should_Return_First_Transaction_InFile() {
		CreateProductTransaction transaction = (CreateProductTransaction) transactionReader
				.nextTransaction();
		assertThat(transaction, notNullValue());
		Product expectedProduct = new Product("0000001","Bogus Product A",1200.99);
		assertThat(transaction.getProduct(),equalTo(expectedProduct));
	}

	@Test
	public void should_Return_Second_Transaction_InFile() {
		transactionReader.nextTransaction();
		UpdateProductTransaction transaction = (UpdateProductTransaction) transactionReader
				.nextTransaction();
		assertThat(transaction, notNullValue());
		Product expectedProduct = new Product("0000001","Bogus Product AA",1100.99);
		assertThat(transaction.getProduct(),equalTo(expectedProduct));
	}

	@Test
	public void should_Return_Third_Transaction_InFile() {
		transactionReader.nextTransaction();
		transactionReader.nextTransaction();
		CreateProductTransaction transaction = (CreateProductTransaction) transactionReader
		.nextTransaction();
		assertThat(transaction, notNullValue());
		Product expectedProduct = new Product("0000002","Bogus Product BB",1200.99);
		assertThat(transaction.getProduct(),equalTo(expectedProduct));
	}

	@Test
	public void should_Return_Fourth_Transaction_InFile() {
		transactionReader.nextTransaction();
		transactionReader.nextTransaction();
		transactionReader.nextTransaction();
		UpdateProductTransaction transaction = (UpdateProductTransaction) transactionReader
		.nextTransaction();
		assertThat(transaction, notNullValue());
		Product expectedProduct = new Product("0000002","Bogus Product B",2200.99);
		assertThat(transaction.getProduct(),equalTo(expectedProduct));
	}

	@Test
	public void should_Return_Fifth_Transaction_InFile() {
		transactionReader.nextTransaction();
		transactionReader.nextTransaction();
		transactionReader.nextTransaction();
		transactionReader.nextTransaction();
		DeleteProductTransaction transaction = (DeleteProductTransaction) transactionReader
		.nextTransaction();
		assertThat(transaction, notNullValue());
		
		Product expectedProduct = new Product("0000001");
		assertThat(transaction.getProduct(),equalTo(expectedProduct));
	}
}
