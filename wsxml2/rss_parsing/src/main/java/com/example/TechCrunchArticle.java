package com.example;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class TechCrunchArticle {
    private String guid;
    private String title;
    private String link;
    private String description;
    private ArrayList<String> categories = new ArrayList<>();

    public static TechCrunchArticle parseDOMElement(Element element) {
        TechCrunchArticle t = new TechCrunchArticle();
        Element guid_element = (Element) element.getElementsByTagName("guid").item(0);
        Element title_element = (Element) element.getElementsByTagName("title").item(0);
        Element link_element = (Element) element.getElementsByTagName("link").item(0);
        Element description_element = (Element) element.getElementsByTagName("description").item(0);
        NodeList category_elements = element.getElementsByTagName("category");
        t.guid = guid_element.getTextContent();
        t.title = title_element.getTextContent();
        t.link = link_element.getTextContent();
        t.description = description_element.getTextContent();
        for(int i = 0; i < category_elements.getLength(); i++) {
            Element category_element = (Element) category_elements.item(i);
            t.categories.add(category_element.getTextContent());
        }
        return t;
    }

    public String getGuid() {
        return guid;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }
}
