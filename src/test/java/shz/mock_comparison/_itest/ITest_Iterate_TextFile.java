package shz.mock_comparison._itest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;

import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shz.mock_comparison.Repository;
import shz.mock_comparison.TransactionFactory;
import shz.mock_comparison.TransactionIterator;
import shz.mock_comparison.TransactionParser;
import shz.mock_comparison.parser.TextTransactionParser;
import shz.mock_comparison.reader.TextSourceReader;
import shz.mock_comparison.transaction.CreateProductTransaction;
import shz.mock_comparison.transaction.DeleteProductTransaction;
import shz.mock_comparison.transaction.TransactionFactoryImpl;
import shz.mock_comparison.transaction.UpdateProductTransaction;

/**
 * 
 * @author Stephan Huez
 *
 */
public class ITest_Iterate_TextFile {

	private TextSourceReader _sourceReader;
	private InputStream _inputStream;
	private TransactionParser _transactionParser;
	private TransactionIterator _transactionIterator;

	@Before
	public void given() {
		_inputStream = ClassLoader
				.getSystemResourceAsStream("shz/mock_comparison/reader/Transactions.txt");
		_sourceReader = new TextSourceReader(_inputStream);
		TransactionFactory transactionFactory = new TransactionFactoryImpl(mock(Repository.class));
		_transactionParser = new TextTransactionParser(transactionFactory);
		_transactionIterator = new TransactionIterator(_sourceReader,
				_transactionParser);
	}

	@After
	public void tearDown() throws Exception {
		_sourceReader.close();
		_inputStream.close();
	}

	@Test
	public void should_Return_First_Transaction_In_File() {
		CreateProductTransaction transaction = (CreateProductTransaction) _transactionIterator
				.nextTransaction();
		assertThat(transaction, notNullValue());
        assertThat(transaction.getProductId(),equalTo("0000001"));
        assertThat(transaction.getProductDescription(),equalTo("Bogus Product A"));
        assertThat(transaction.getProductPrice(),equalTo(1200.99));
	}

	@Test
	public void should_Return_Second_Transaction_In_File() {
		_transactionIterator.nextTransaction();
		UpdateProductTransaction transaction = (UpdateProductTransaction) _transactionIterator
				.nextTransaction();
		assertThat(transaction, notNullValue());
        assertThat(transaction.getProductId(),equalTo("0000001"));
        assertThat(transaction.getProductDescription(),equalTo("Bogus Product AA"));
        assertThat(transaction.getProductPrice(),equalTo(1100.99));
	}

	@Test
	public void should_Return_Third_Transaction_In_File() {
		_transactionIterator.nextTransaction();
		_transactionIterator.nextTransaction();
		CreateProductTransaction transaction = (CreateProductTransaction) _transactionIterator
		.nextTransaction();
		assertThat(transaction, notNullValue());
		assertThat(transaction.getProductId(),equalTo("0000002"));
		assertThat(transaction.getProductDescription(),equalTo("Bogus Product BB"));
		assertThat(transaction.getProductPrice(),equalTo(1200.99));
	}

	@Test
	public void should_Return_Fourth_Transaction_In_File() {
		_transactionIterator.nextTransaction();
		_transactionIterator.nextTransaction();
		_transactionIterator.nextTransaction();
		UpdateProductTransaction transaction = (UpdateProductTransaction) _transactionIterator
		.nextTransaction();
		assertThat(transaction, notNullValue());
        assertThat(transaction.getProductId(),equalTo("0000002"));
        assertThat(transaction.getProductDescription(),equalTo("Bogus Product B"));
        assertThat(transaction.getProductPrice(),equalTo(2200.99));
	}

	@Test
	public void should_Return_Fifth_Transaction_In_File() {
		_transactionIterator.nextTransaction();
		_transactionIterator.nextTransaction();
		_transactionIterator.nextTransaction();
		_transactionIterator.nextTransaction();
		DeleteProductTransaction transaction = (DeleteProductTransaction) _transactionIterator
		.nextTransaction();
		assertThat(transaction, notNullValue());
		
		assertThat(transaction.getProductId(),equalTo("0000001"));
	}
}