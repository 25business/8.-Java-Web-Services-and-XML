package com.example;

import io.javalin.Javalin;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.StringReader;

public class Program {
    public static void main(String[] args) {
        try {
            Javalin app = Javalin.create().start(9000);
            app.get("/", new HomeHandler());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
