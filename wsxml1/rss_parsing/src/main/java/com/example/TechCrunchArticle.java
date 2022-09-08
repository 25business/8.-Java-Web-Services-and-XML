package com.example;

import org.w3c.dom.Element;

public class TechCrunchArticle {
    private String guid;
    private String title;
    private String link;
    private String description;

    public static TechCrunchArticle parseDOMElement(Element element) {
        TechCrunchArticle t = new TechCrunchArticle();
        Element guid_element = (Element) element.getElementsByTagName("guid").item(0);
        Element title_element = (Element) element.getElementsByTagName("title").item(0);
        Element link_element = (Element) element.getElementsByTagName("link").item(0);
        Element description_element = (Element) element.getElementsByTagName("description").item(0);
        t.guid = guid_element.getTextContent();
        t.title = title_element.getTextContent();
        t.link = link_element.getTextContent();
        t.description = description_element.getTextContent();
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
}
