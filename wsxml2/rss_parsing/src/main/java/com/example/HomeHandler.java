package com.example;

import com.example.templating.Renderer;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.HashMap;

public class HomeHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        HashMap<String, Object> modelData = new HashMap<>();
        modelData.put("items", TechCrunchFeed.load());
        modelData.put("kursna_lista", NBSSrednjiKursTable.load());
        context.html(Renderer.render("home.ftl", modelData));
    }
}
