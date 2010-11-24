package shz.mock_comparison;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test_TextSourceReader {

	private TextSourceReader sourceReader;
	private InputStream inputStream;

	@Before
	public void given() {
		inputStream = ClassLoader.getSystemResourceAsStream("shz/mock_comparison/Dummy.txt");
		sourceReader = new TextSourceReader(inputStream);

	}

	@After
	public void tearDown() throws Exception {
		sourceReader.close();
		inputStream.close();
	}

	@Test
	public void should__Have_Next_Element() {
		// Then
		assertThat(sourceReader.hasNextElement(), is(true));
	}

	@Test
	public void should__Have_No_More_Elements() {
		// When
		sourceReader.nextElement();
		sourceReader.nextElement();
		sourceReader.nextElement();
		
		// Then
		assertThat(sourceReader.hasNextElement(), is(false));
	}
	
	@Test
	public void should_Read_Elements_From_File(){
		// Then
		assertThat(sourceReader.nextElement(),equalTo("I am a line"));
		assertThat(sourceReader.nextElement(),equalTo("I am another line"));
		assertThat(sourceReader.nextElement(),equalTo("I am yet another line"));
	}
}
