package shz.mock_comparison;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ITest_Process_XmlFile {

	private XmlSourceReader _sourceReader;
	private InputStream _inputStream;
	private TransactionParser _transactionParser;
	private TransactionReader _transactionReader;

	@Before
	public void given() {
		_inputStream = ClassLoader
				.getSystemResourceAsStream("shz/mock_comparison/Transactions.xml");
		_sourceReader = new XmlSourceReader(_inputStream);
		_transactionParser = new XmlTransactionParser();
		_transactionReader = new TransactionReader(_sourceReader,
				_transactionParser);
	}

	@After
	public void tearDown() throws Exception {
		_inputStream.close();
	}

	@Test
	public void should_Return_First_Transaction_In_File() {
		CreateProductTransaction transaction = (CreateProductTransaction) _transactionReader
				.nextTransaction();
		assertThat(transaction, notNullValue());
		Product expectedProduct = new Product("000001","Product One",76.49);
		assertThat(transaction.getProduct(),equalTo(expectedProduct));
	}

	@Test
	public void should_Return_Second_Transaction_In_File() {
		_transactionReader.nextTransaction();
		UpdateProductTransaction transaction = (UpdateProductTransaction) _transactionReader
				.nextTransaction();
		assertThat(transaction, notNullValue());
		Product expectedProduct = new Product("000001","Product One",134.98);
		assertThat(transaction.getProduct(),equalTo(expectedProduct));
	}

	@Test
	public void should_Return_Third_Transaction_In_File() {
		_transactionReader.nextTransaction();
		_transactionReader.nextTransaction();
		CreateProductTransaction transaction = (CreateProductTransaction) _transactionReader
		.nextTransaction();
		assertThat(transaction, notNullValue());
		Product expectedProduct = new Product("000002","Product Two",999.99);
		assertThat(transaction.getProduct(),equalTo(expectedProduct));
	}

	@Test
	public void should_Return_Fourth_Transaction_In_File() {
		_transactionReader.nextTransaction();
		_transactionReader.nextTransaction();
		_transactionReader.nextTransaction();
		CreateProductTransaction transaction = (CreateProductTransaction) _transactionReader
		.nextTransaction();
		assertThat(transaction, notNullValue());
		Product expectedProduct = new Product("000003","Product Three",194.98);
		assertThat(transaction.getProduct(),equalTo(expectedProduct));
	}

    @Test
    public void should_Return_Fifth_Transaction_In_File() {
        _transactionReader.nextTransaction();
        _transactionReader.nextTransaction();
        _transactionReader.nextTransaction();
        _transactionReader.nextTransaction();
        UpdateProductTransaction transaction = (UpdateProductTransaction) _transactionReader
        .nextTransaction();
        assertThat(transaction, notNullValue());
        Product expectedProduct = new Product("000003","Product Three Reviewed",234.98);
        assertThat(transaction.getProduct(),equalTo(expectedProduct));
    }

	@Test
	public void should_Return_Sixth_Transaction_In_File() {
		_transactionReader.nextTransaction();
		_transactionReader.nextTransaction();
		_transactionReader.nextTransaction();
		_transactionReader.nextTransaction();
		_transactionReader.nextTransaction();
		DeleteProductTransaction transaction = (DeleteProductTransaction) _transactionReader
		.nextTransaction();
		assertThat(transaction, notNullValue());
		
		Product expectedProduct = new Product("000001");
		assertThat(transaction.getProduct(),equalTo(expectedProduct));
	}
}
