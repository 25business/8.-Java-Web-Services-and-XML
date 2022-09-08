package com.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File(System.getenv("JAVA_RESOURCES") + "/wsxml1/people.xml"));
            doc.getDocumentElement().normalize();

            NodeList person_tags = doc.getElementsByTagName("person");
            ArrayList<Person> people = new ArrayList<>();
            for(int i = 0; i < person_tags.getLength(); i++) {
                var person_node = person_tags.item(i);
                /*if(person_node.getNodeType() == Node.ELEMENT_NODE) {
                    Element person_element = (Element) person_node;
                    String person_id = person_element.getAttribute("id");
                    System.out.println(person_id);
                    Element firstName_element = (Element) person_element.getElementsByTagName("first_name").item(0);
                    System.out.println(firstName_element.getTextContent());
                }*/
                people.add(Person.parseDOMElement((Element) person_node));
            }
            people.forEach(System.out::println);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
