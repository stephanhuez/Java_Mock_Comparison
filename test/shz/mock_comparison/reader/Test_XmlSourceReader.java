package shz.mock_comparison.reader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.w3c.dom.Element;

import shz.mock_comparison.TransactionSourceReader;
import shz.mock_comparison.reader.XmlSourceReader;

/**
 * 
 * @author Stephan Huez
 * 
 */
public class Test_XmlSourceReader {

	private InputStream _inputStream;
	private TransactionSourceReader _sourceReader;

	@Before
	public void given() {
		_inputStream = ClassLoader
				.getSystemResourceAsStream("shz/mock_comparison/reader/Transactions.xml");
		_sourceReader = new XmlSourceReader(_inputStream);

	}

	@After
	public void tearDown() throws Exception {
		_inputStream.close();
	}

	@Test
	public void should_Have_Next_Element() {
		// Then
		assertThat(_sourceReader.hasNextElement(), is(true));
	}

	@Test
	public void should_Have_No_More_Elements() {
		// When
		_sourceReader.nextElement();
		_sourceReader.nextElement();
		_sourceReader.nextElement();
		_sourceReader.nextElement();
		_sourceReader.nextElement();
		_sourceReader.nextElement();

		// Then
		assertThat(_sourceReader.hasNextElement(), is(false));
	}

	@Test
	public void should_Read_First_Element_From_File() {
		// When
		Element element = (Element) _sourceReader.nextElement();
		
		// Then
		assertThat(element.getNodeName(),equalTo("CreateProduct"));
	}

	@Test
	public void should_Read_Second_Element_From_File() {
		// When
		_sourceReader.nextElement();
		Element element = (Element) _sourceReader.nextElement();
		
		// Then
		assertThat(element.getNodeName(),equalTo("UpdateProduct"));
	}

	@Test
	public void should_Read_Last_Element_From_File() {
		// When
		_sourceReader.nextElement();
		_sourceReader.nextElement();
		_sourceReader.nextElement();
		_sourceReader.nextElement();
		_sourceReader.nextElement();
		Element element = (Element) _sourceReader.nextElement();
		
		// Then
		assertThat(element.getNodeName(),equalTo("DeleteProduct"));
	}
}
