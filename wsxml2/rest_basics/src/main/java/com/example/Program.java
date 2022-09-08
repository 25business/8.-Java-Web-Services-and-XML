package com.example;

import io.javalin.Javalin;

public class Program {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7070);
        app.get("/", ctx -> ctx.result("Hello World"));
        app.get("/docs", ctx -> ctx.result("Hello World"));

        // Verzija 1
        app.get("/api/v1/products", ctx -> {
            /*ctx.status(200);
            ctx.contentType("application/json");
            ctx.result("""
                    {
                    "status": 200,
                    "data": {
                           "id": 1,
                           "title": "Product title"
                        }
                    }
                    """);*/

            ctx.status(404);
            ctx.contentType("application/json");
            ctx.result("""
                    {
                        "status" : 404,
                        "title": "Resource not found",
                        "message": "Resource requested not found",
                        "error": "Path /api/v1/products does not have any objects present."
                    }""");
        });


    }
}
