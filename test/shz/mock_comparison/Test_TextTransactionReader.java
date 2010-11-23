package shz.mock_comparison;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Stephan Huez
 * 
 */
public class Test_TextTransactionReader {
	
	private TextFileReader textFileReaderStub;
	private TextTransactionParser parserStub;
	private TextTransactionReader transactionReader;

	@Before
	public void given(){
		parserStub = mock(TextTransactionParser.class);
		textFileReaderStub = mock(TextFileReader.class);
		transactionReader = new TextTransactionReader(
				textFileReaderStub, parserStub);
		
	}

	@Test
	public void should_Report_No_More_Transactions() {
		// Given
		when(textFileReaderStub.hasNextLine()).thenReturn(false);

		// Then
		assertThat(transactionReader.hasNextTransaction(), is(false));
	}

	@Test
	public void should_Report_More_Transactions() {
		// Given
		when(textFileReaderStub.hasNextLine()).thenReturn(true);

		// Then
		assertThat(transactionReader.hasNextTransaction(), is(true));

	}	
	
	@Test
	public void should_Return_Three_Different_Transaction_For_Three_Lines_In_File() {
		// Given
		when(textFileReaderStub.hasNextLine())
		        .thenReturn(true)
				.thenReturn(true)
				.thenReturn(true)
				.thenReturn(false);

		Transaction expectedTransaction1 = mock(Transaction.class);
		Transaction expectedTransaction2 = mock(Transaction.class);
		Transaction expectedTransaction3 = mock(Transaction.class);
		when(parserStub.parse(anyString()))
		        .thenReturn(expectedTransaction1)
				.thenReturn(expectedTransaction2)
				.thenReturn(expectedTransaction3);


		// Then
		assertThat(transactionReader.nextTransaction(),
				equalTo(expectedTransaction1));
		assertThat(transactionReader.nextTransaction(),
				equalTo(expectedTransaction2));
		assertThat(transactionReader.nextTransaction(),
				equalTo(expectedTransaction3));
	}
	
	@Test
	public void should_Fail_When_Asking_For_Transaction_When_No_More(){
		// Given
		when(textFileReaderStub.hasNextLine()).thenReturn(false);
		
		// When
		try {
			transactionReader.nextTransaction();
			fail("Should have raised an exception");
		} catch (NoMoreTransactionAvailable e) {
			// Then
			// Should fail
		}
		
	}
}
