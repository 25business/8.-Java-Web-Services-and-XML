package dev.tmdb;

import dev.tmdb.handlers.api_v1.AllMoviesHandler;
import dev.tmdb.handlers.api_v1.SearchMoviesHandler;
import dev.tmdb.handlers.api_v1.SingleMovieHandler;
import dev.tmdb.service.MovieParser;
import io.javalin.Javalin;

public class Program {

    public static void main(String[] args) {
        Javalin server = Javalin.create(javalinConfig -> {
            javalinConfig.enableCorsForAllOrigins();
        });

        server.get("/", ctx -> {
            // TODO: Stranica za dokumentaciju
            ctx.html("Dokumentacija");
        });

        server.get("/api/v1/movies", new AllMoviesHandler());
        server.get("/api/v1/movies/search", new SearchMoviesHandler());
        server.get("/api/v1/movies/<movie_id>", new SingleMovieHandler());


        server.start(9000);
    }
}
