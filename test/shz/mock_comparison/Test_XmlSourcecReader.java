package shz.mock_comparison;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.InputStream;

import nu.xom.Element;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Stephan Huez
 * 
 */
public class Test_XmlSourcecReader {

	private InputStream inputStream;
	private TransactionSourceReader sourceReader;

	@Before
	public void given() {
		inputStream = ClassLoader
				.getSystemResourceAsStream("shz/mock_comparison/Transactions.xml");
		sourceReader = new XmlSourceReader(inputStream);

	}

	@After
	public void tearDown() throws Exception {
		inputStream.close();
	}

	@Test
	public void should_Have_Next_Element() {
		// Then
		assertThat(sourceReader.hasNextElement(), is(true));
	}

	@Test
	public void should_Have_No_More_Elements() {
		// When
		sourceReader.nextElement();
		sourceReader.nextElement();
		sourceReader.nextElement();
		sourceReader.nextElement();
		sourceReader.nextElement();
		sourceReader.nextElement();

		// Then
		assertThat(sourceReader.hasNextElement(), is(false));
	}

	@Test
	public void should_Read_First_Element_From_File() {
		// When
		Element element = (Element) sourceReader.nextElement();
		
		// Then
		assertThat(element.getLocalName(),equalTo("CreateProduct"));
	}

	@Test
	public void should_Read_Second_Element_From_File() {
		// When
		sourceReader.nextElement();
		Element element = (Element) sourceReader.nextElement();
		
		// Then
		assertThat(element.getLocalName(),equalTo("UpdateProduct"));
	}

	@Test
	public void should_Read_Last_Element_From_File() {
		// When
		sourceReader.nextElement();
		sourceReader.nextElement();
		sourceReader.nextElement();
		sourceReader.nextElement();
		sourceReader.nextElement();
		Element element = (Element) sourceReader.nextElement();
		
		// Then
		assertThat(element.getLocalName(),equalTo("DeleteProduct"));
	}
}
