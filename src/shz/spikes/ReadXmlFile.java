package shz.spikes;

import java.io.IOException;
import java.io.InputStream;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

public class ReadXmlFile {
	public static void main(String[] args) throws ValidityException,
			ParsingException, IOException {
		InputStream xmlFile = ClassLoader
				.getSystemResourceAsStream("shz/spikes/Transactions.xml");

		Builder builder = new Builder();

		Document document = builder.build(xmlFile);

		Element root = document.getRootElement();

		Elements entries = root.getChildElements();

		// print out the entries
		for (int x = 0; x < entries.size(); x++) {
			Element element = entries.get(x);
			System.out.println(element.getLocalName());
			System.out.println(element.getValue());
		}

	}
}
