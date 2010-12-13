package shz.mock_comparison;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static shz.mock_comparison.utils.CustomAssertions.*;

import org.junit.Before;
import org.junit.Test;


/**
 * 
 * @author Stephan Huez
 * 
 */
public class Test_TransactionIterator {
	
	private TransactionSourceReader _sourceReaderStub;
	private TransactionParser _parserStub;
	private TransactionIterator _transactionReader;

	@Before
	public void given(){
		_parserStub = mock(TransactionParser.class);
		_sourceReaderStub = mock(TransactionSourceReader.class);
		_transactionReader = new TransactionIterator(
				_sourceReaderStub, _parserStub);
		
	}

	@Test
	public void should_Report_No_More_Transactions() {
		// Given
		when(_sourceReaderStub.hasNextElement()).thenReturn(false);

		// Then
		assertThat(_transactionReader.hasNextTransaction(), is(false));
	}

	@Test
	public void should_Report_More_Transactions_Left() {
		// Given
		when(_sourceReaderStub.hasNextElement()).thenReturn(true);

		// Then
		assertThat(_transactionReader.hasNextTransaction(), is(true));

	}	
	
	@Test
	public void should_Return_Three_Different_Transaction_For_Three_Elements_In_Source() {
		// Given
		when(_sourceReaderStub.hasNextElement())
		        .thenReturn(true)
				.thenReturn(true)
				.thenReturn(true)
				.thenReturn(false);

		Transaction expectedTransaction1 = mock(Transaction.class);
		Transaction expectedTransaction2 = mock(Transaction.class);
		Transaction expectedTransaction3 = mock(Transaction.class);
		when(_parserStub.parse(anyString()))
		        .thenReturn(expectedTransaction1)
				.thenReturn(expectedTransaction2)
				.thenReturn(expectedTransaction3);


		// Then
		assertThat(_transactionReader.nextTransaction(),
				equalTo(expectedTransaction1));
		assertThat(_transactionReader.nextTransaction(),
				equalTo(expectedTransaction2));
		assertThat(_transactionReader.nextTransaction(),
				equalTo(expectedTransaction3));
	}
	
	@Test
	public void should_Fail_When_Asking_For_Transaction_When_No_More(){
		// Given
		when(_sourceReaderStub.hasNextElement()).thenReturn(false);
		
		// When
		try {
			_transactionReader.nextTransaction();
			shouldHaveRaisedAnException();
		} catch (NoMoreTransactionInIterator e) {
			// Then
			// Should fail
		}
		
	}
}
