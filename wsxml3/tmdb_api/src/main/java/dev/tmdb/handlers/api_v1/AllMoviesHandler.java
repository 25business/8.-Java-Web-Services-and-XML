package dev.tmdb.handlers.api_v1;

import com.google.gson.Gson;
import dev.tmdb.entities.Movie;
import dev.tmdb.exceptions.ExceptionHandler;
import dev.tmdb.exceptions.ServiceErrorException;
import dev.tmdb.service.MovieParser;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.HashMap;

public class AllMoviesHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        int status = 200;
        String json_response = "";
        try {
            Gson gson = new Gson();
            HashMap<String, Object> response_data = new HashMap<>();
            response_data.put("status", 200);
            ArrayList<Movie> movies = MovieParser.all();
            HashMap<String, Object> pagination = new HashMap<>();
            // Trenutno...
            String page = context.queryParam("page");
            page = page == null ? "" : page;
            int page_num = page.equals("") ? 1 : Integer.parseInt(page);

            pagination.put("page", page_num);
            pagination.put("records", 20);
            pagination.put("pages", Math.ceil(movies.size() / 20.0));
            pagination.put("total", movies.size());
            response_data.put("pagination", pagination);

            int start = (page_num - 1) * 20;
            int end = (page_num * 20)-1 >= movies.size() ? movies.size() - 1 : (page_num * 20)-1;
            // page: 1 -> 0 19
            // page: 10 -> 180 199
            response_data.put("movies", movies.subList(start,end));

            json_response = gson.toJson(response_data);

        }catch (Exception ex) {
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
