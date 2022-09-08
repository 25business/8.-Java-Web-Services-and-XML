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

public class NBSSrednjiKursTable {

    public static ArrayList<NBSVrednost> load() {
        ArrayList<NBSVrednost> items = new ArrayList<>();
        try {
            HttpResponse<String> response = Unirest.get("https://www.nbs.rs/kursnaListaModul/srednjiKurs.faces?lang=lat").asString();
            String xml_string = response.getBody();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            StringReader sr = new StringReader(xml_string);
            InputSource is = new InputSource(sr);
            Document doc = builder.parse(is);
            doc.getDocumentElement().normalize();

            XPath xpath = XPathFactory.newInstance().newXPath();
            String xpath_string = "//tbody//td";
            NodeList article_items = (NodeList) xpath.compile(xpath_string).evaluate(doc, XPathConstants.NODESET);
            System.out.println("!!!!! " + article_items.getLength());
            for(int i = 0; i < article_items.getLength(); i+=5) {
                Element valuta_node = (Element)article_items.item(i);
                Element vrednost_node = (Element)article_items.item(i+4);
                items.add(NBSVrednost.fromElements(valuta_node, vrednost_node));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return items;
    }
}
