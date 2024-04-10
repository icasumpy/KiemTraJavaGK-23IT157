package ktrgiuaki;

import java.io.File;
import java.util.concurrent.Callable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

class Thread1 implements Callable<String> {
    @Override
    public String call() {
        StringBuilder resultBuilder = new StringBuilder();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            File file = new File("student.xml");
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("student");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);

                String id = element.getAttribute("id");
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String address = element.getElementsByTagName("address").item(0).getTextContent();
                String dateOfBirth = element.getElementsByTagName("dateOfBirth").item(0).getTextContent();

                resultBuilder.append("<Student>\n");
                resultBuilder.append("<id>").append(id).append("</id>\n");
                resultBuilder.append("<name>").append(name).append("</name>\n");
                resultBuilder.append("<address>").append(address).append("</address>\n");
                resultBuilder.append("<dateOfBirth>").append(dateOfBirth).append("</dateOfBirth>\n");
                resultBuilder.append("</Student>\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultBuilder.toString();
    }
}
