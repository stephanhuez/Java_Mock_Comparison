package shz.mock_comparison;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test_TextFileReader {

	private TextFileReader reader;
	private InputStream stream;

	@Before
	public void given() {
		stream = ClassLoader.getSystemResourceAsStream("shz/mock_comparison/Dummy.txt");
		reader = new TextFileReader(stream);

	}

	@After
	public void tearDown() throws Exception {
		reader.close();
		stream.close();
	}

	@Test
	public void should__Have_Next_Line() {
		// Then
		assertThat(reader.hasNextElement(), is(true));
	}

	@Test
	public void should__Have_No_More_Line() {
		// When
		reader.nextElement();
		reader.nextElement();
		reader.nextElement();
		
		// Then
		assertThat(reader.hasNextElement(), is(false));
	}
	
	@Test
	public void should_Read_Lines_From_File(){
		// Then
		assertThat(reader.nextElement(),equalTo("I am a line"));
		assertThat(reader.nextElement(),equalTo("I am another line"));
		assertThat(reader.nextElement(),equalTo("I am yet another line"));
	}
}
