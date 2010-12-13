package shz.spikes;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXmlFile {
    public static void main(String argv[]) {

        try {

            InputStream inputStream = ClassLoader.getSystemResourceAsStream("shz/mock_comparison/Transactions.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputStream);
            doc.getDocumentElement().normalize();

            Node root = doc.getDocumentElement();
            System.out.println("Root element :" + root.getNodeName());

            NodeList nList = root.getChildNodes();
            System.out.println("-----------------------");
            System.out.println("nList.getLength() " + nList.getLength());

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println(eElement.getNodeName());
                    NodeList childNodes = eElement.getChildNodes();
                    for (int i = 0; i < childNodes.getLength(); i++) {
                        Node childNode = childNodes.item(i);
                        if (childNode.getNodeType() == Node.ELEMENT_NODE) {

                            Element childElement = (Element) childNode;
                            System.out.println(childElement.getNodeName());
                            System.out.println(childElement.getFirstChild().getNodeValue());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
