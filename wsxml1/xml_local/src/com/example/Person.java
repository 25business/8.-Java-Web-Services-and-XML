package com.example;

import org.w3c.dom.Element;

public class Person {
    private int id;
    private String first_name;
    private String last_name;
    private int age;

    public static Person parseDOMElement(Element person_element) {
        Person p = new Person();
        p.id = Integer.parseInt(
                person_element.getAttribute("id")
        );
        Element firstName_element = (Element) person_element.getElementsByTagName("first_name").item(0);
        p.first_name = firstName_element.getTextContent();
        Element lastName_element = (Element) person_element.getElementsByTagName("last_name").item(0);
        p.last_name = lastName_element.getTextContent();
        Element age_element = (Element) person_element.getElementsByTagName("age").item(0);
        p.age = Integer.parseInt(age_element.getTextContent());

        return p;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                '}';
    }
}
