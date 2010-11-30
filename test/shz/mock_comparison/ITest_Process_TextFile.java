package shz.mock_comparison;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ITest_Process_TextFile {

	private TextSourceReader _sourceReader;
	private InputStream _inputStream;
	private TransactionParser _transactionParser;
	private TransactionReader _transactionReader;

	@Before
	public void given() {
		_inputStream = ClassLoader
				.getSystemResourceAsStream("shz/mock_comparison/Transactions.txt");
		_sourceReader = new TextSourceReader(_inputStream);
		_transactionParser = new TextTransactionParser();
		_transactionReader = new TransactionReader(_sourceReader,
				_transactionParser);
	}

	@After
	public void tearDown() throws Exception {
		_sourceReader.close();
		_inputStream.close();
	}

	@Test
	public void should_Return_First_Transaction_In_File() {
		CreateProductTransaction transaction = (CreateProductTransaction) _transactionReader
				.nextTransaction();
		assertThat(transaction, notNullValue());
		Product expectedProduct = new Product("0000001","Bogus Product A",1200.99);
		assertThat(transaction.getProduct(),equalTo(expectedProduct));
	}

	@Test
	public void should_Return_Second_Transaction_In_File() {
		_transactionReader.nextTransaction();
		UpdateProductTransaction transaction = (UpdateProductTransaction) _transactionReader
				.nextTransaction();
		assertThat(transaction, notNullValue());
		Product expectedProduct = new Product("0000001","Bogus Product AA",1100.99);
		assertThat(transaction.getProduct(),equalTo(expectedProduct));
	}

	@Test
	public void should_Return_Third_Transaction_In_File() {
		_transactionReader.nextTransaction();
		_transactionReader.nextTransaction();
		CreateProductTransaction transaction = (CreateProductTransaction) _transactionReader
		.nextTransaction();
		assertThat(transaction, notNullValue());
		Product expectedProduct = new Product("0000002","Bogus Product BB",1200.99);
		assertThat(transaction.getProduct(),equalTo(expectedProduct));
	}

	@Test
	public void should_Return_Fourth_Transaction_In_File() {
		_transactionReader.nextTransaction();
		_transactionReader.nextTransaction();
		_transactionReader.nextTransaction();
		UpdateProductTransaction transaction = (UpdateProductTransaction) _transactionReader
		.nextTransaction();
		assertThat(transaction, notNullValue());
		Product expectedProduct = new Product("0000002","Bogus Product B",2200.99);
		assertThat(transaction.getProduct(),equalTo(expectedProduct));
	}

	@Test
	public void should_Return_Fifth_Transaction_In_File() {
		_transactionReader.nextTransaction();
		_transactionReader.nextTransaction();
		_transactionReader.nextTransaction();
		_transactionReader.nextTransaction();
		DeleteProductTransaction transaction = (DeleteProductTransaction) _transactionReader
		.nextTransaction();
		assertThat(transaction, notNullValue());
		
		Product expectedProduct = new Product("0000001");
		assertThat(transaction.getProduct(),equalTo(expectedProduct));
	}
}
