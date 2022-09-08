package com.example;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.ArrayList;

public class TechCrunchFeed {

    public static ArrayList<TechCrunchArticle> load() {
        ArrayList<TechCrunchArticle> items = new ArrayList<>();
        try {
            HttpResponse<String> response = Unirest.get("https://techcrunch.com/gadgets/feed/").asString();
            String xml_string = response.getBody();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            StringReader sr = new StringReader(xml_string);
            InputSource is = new InputSource(sr);
            Document doc = builder.parse(is);
            doc.getDocumentElement().normalize();

            NodeList article_items = doc.getElementsByTagName("item");
            for(int i = 0; i < article_items.getLength(); i++) {
                if(i == 5) break;

                Element item_node = (Element)article_items.item(i);
                items.add(TechCrunchArticle.parseDOMElement(item_node));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return items;
    }

    public static ArrayList<TechCrunchArticle> search(String category) {
        ArrayList<TechCrunchArticle> items = new ArrayList<>();
        try {
            HttpResponse<String> response = Unirest.get("https://techcrunch.com/gadgets/feed/").asString();
            String xml_string = response.getBody();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            StringReader sr = new StringReader(xml_string);
            InputSource is = new InputSource(sr);
            Document doc = builder.parse(is);
            doc.getDocumentElement().normalize();

            XPath xpath = XPathFactory.newInstance().newXPath();
            String xpath_string = String.format("//item[contains(category, \"%s\")]", category);
            System.out.println(xpath_string);
            NodeList article_items = (NodeList) xpath.compile(xpath_string).evaluate(doc, XPathConstants.NODESET);
            for(int i = 0; i < article_items.getLength(); i++) {
                if(i == 5) break;

                Element item_node = (Element)article_items.item(i);
                items.add(TechCrunchArticle.parseDOMElement(item_node));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return items;
    }
}
