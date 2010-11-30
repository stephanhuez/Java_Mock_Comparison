package shz.mock_comparison;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test_TextSourceReader {

	private TextSourceReader _sourceReader;
	private InputStream _inputStream;

	@Before
	public void given() {
		_inputStream = ClassLoader.getSystemResourceAsStream("shz/mock_comparison/Dummy.txt");
		_sourceReader = new TextSourceReader(_inputStream);

	}

	@After
	public void tearDown() throws Exception {
		_sourceReader.close();
		_inputStream.close();
	}

	@Test
	public void should__Have_Next_Element() {
		// Then
		assertThat(_sourceReader.hasNextElement(), is(true));
	}

	@Test
	public void should__Have_No_More_Elements() {
		// When
		_sourceReader.nextElement();
		_sourceReader.nextElement();
		_sourceReader.nextElement();
		
		// Then
		assertThat(_sourceReader.hasNextElement(), is(false));
	}
	
	@Test
	public void should_Read_Elements_From_File(){
		// Then
		assertThat(_sourceReader.nextElement(),equalTo("I am a line"));
		assertThat(_sourceReader.nextElement(),equalTo("I am another line"));
		assertThat(_sourceReader.nextElement(),equalTo("I am yet another line"));
	}
}
