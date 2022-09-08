package com.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File("C:/java_projects/wsxml2/resources/wsxml1/people.xml"));
            doc.getDocumentElement().normalize();

            XPath xpath = XPathFactory.newInstance().newXPath();
            String unos_korisnika = "30";
            String xpath_string = String.format("//person[age>%s]", unos_korisnika);
            NodeList person_tags = (NodeList) xpath.compile(xpath_string).evaluate(doc, XPathConstants.NODESET);
            ArrayList<Person> people = new ArrayList<>();
            for(int i = 0; i < person_tags.getLength(); i++) {
                var person_node = person_tags.item(i);
                people.add(Person.parseDOMElement((Element) person_node));
            }
            people.forEach(System.out::println);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
