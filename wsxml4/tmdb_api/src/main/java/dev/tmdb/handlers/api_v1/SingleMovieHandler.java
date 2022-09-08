package dev.tmdb.handlers.api_v1;

import com.google.gson.Gson;
import dev.tmdb.entities.Movie;
import dev.tmdb.exceptions.ExceptionHandler;
import dev.tmdb.exceptions.MovieNotFoundException;
import dev.tmdb.service.MovieParser;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.HashMap;

public class SingleMovieHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        int status = 200;
        String json_response = "";
        try {
            Gson gson = new Gson();
            int movie_id = Integer.parseInt(context.pathParam("movie_id"));
            Movie movie = MovieParser.one(movie_id);
            HashMap<String, Object> response_data = new HashMap<>();
            response_data.put("status", 200);
            response_data.put("movie", movie);
            json_response = gson.toJson(response_data);

        } catch (MovieNotFoundException ex) {
            ex.printStackTrace();
            ExceptionHandler eh = new ExceptionHandler(404,
                    String.format("Movie with ID %s is not available",
                    context.pathParam("movie_id")));
            status = 404;
            json_response = eh.toJSONString();
        } catch (Exception ex) {
            ExceptionHandler eh = new ExceptionHandler(500, "Service has encountered internal error",
                    ex.getLocalizedMessage().replace('"','\''));
            ex.printStackTrace();
            status = 500;
            json_response = eh.toJSONString();
        }
        context.status(status);
        context.header("Content-Type", "application/json");
        context.result(json_response);
    }
}
