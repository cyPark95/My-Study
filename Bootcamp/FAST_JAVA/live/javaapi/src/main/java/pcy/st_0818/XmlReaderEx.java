package pcy.st_0818;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlReaderEx {

    public static void main(String[] args) {
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("book.xml");

            NodeList books = document.getElementsByTagName("book");
            for (int i = 0; i < books.getLength(); i++) {
                Node bookNode = books.item(i);
                if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element bookElement = (Element) bookNode;
                    String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
                    String company = bookElement.getElementsByTagName("company").item(0).getTextContent();
                    String name = bookElement.getElementsByTagName("name").item(0).getTextContent();
                    int price = Integer.parseInt(bookElement.getElementsByTagName("price").item(0).getTextContent());

                    System.out.println("Title: " + title);
                    System.out.println("Company: " + company);
                    System.out.println("Name: " + name);
                    System.out.println("Price: " + price);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
