package ktrgiuaki;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ResultReader {
    public static void main(String[] args) {
        readResultFromXML("kq.xml");
    }

    public static void readResultFromXML(String fileName) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            File file = new File(fileName);
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Student");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                String id = element.getAttribute("id");
                String age = getElementTextContent(element, "age");
                String sum = getElementTextContent(element, "sum");
                String isDigit = getElementTextContent(element, "isDigit");

                System.out.println("Student ID: " + id);
                System.out.println("Age: " + age);
                System.out.println("Sum of digits: " + sum);
                System.out.println("Is sum prime?: " + isDigit);
                System.out.println();
                

//       		 System.out.println("ID "+ element.getAttribute("id"));
//       		 System.out.println("Title "+ element.getElementsByTagName("age").item(0).getTextContent());
//       		 System.out.println("Author "+ element.getElementsByTagName("sum").item(0).getTextContent());
//       		 System.out.println("Year "+ element.getElementsByTagName("isDigit").item(0).getTextContent());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getElementTextContent(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        } else {
            return ""; // Trả về chuỗi rỗng nếu phần tử không tồn tại
        }
    }
}
