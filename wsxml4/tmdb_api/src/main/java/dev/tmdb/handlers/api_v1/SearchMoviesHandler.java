package dev.tmdb.handlers.api_v1;

import com.google.gson.Gson;
import dev.tmdb.entities.Movie;
import dev.tmdb.service.MovieParser;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SearchMoviesHandler implements Handler {
    @Override
    public void handle(Context context) throws Exception {
        int status = 200;
        String json_response = "";
        try {
            ArrayList<Movie> movies = MovieParser.all();
            String genre = context.queryParam("genre");
            String release_year = context.queryParam("release_year");
            String vote_min = context.queryParam("vote_min");
            String vote_max = context.queryParam("vote_max");

            if(release_year != null) {
                movies = (ArrayList<Movie>) movies.stream().filter(movie -> {
                    return movie.getRelease_date().startsWith(release_year);
                }).collect(Collectors.toList());
            }
            if(vote_min != null) {
                double vm = Double.parseDouble(vote_min);
                movies = (ArrayList<Movie>) movies.stream().filter(movie -> {
                    return movie.getVote_average() >= vm;
                }).collect(Collectors.toList());
            }
            if(vote_max != null) {
                double vm = Double.parseDouble(vote_max);
                movies = (ArrayList<Movie>) movies.stream().filter(movie -> {
                    return movie.getVote_average() <= vm;
                }).collect(Collectors.toList());
            }

            if(genre != null) {
                if(genre.contains("_")) {
                    String[] split_genre = genre.split("_");
                    for(var g : split_genre) {
                        movies = filter_genre(g, movies);
                    }
                } else {
                    movies = filter_genre(genre, movies);
                }
            }

            Gson gson = new Gson();
            HashMap<String, Object> response_data = new HashMap<>();
            response_data.put("status", 200);
            HashMap<String, Object> pagination = new HashMap<>();
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
            if(movies.size() != 1) response_data.put("movies", movies.subList(start,end));
            else response_data.put("movies", movies);

            json_response = gson.toJson(response_data);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        context.status(status);
        context.header("Content-Type", "application/json");
        context.result(json_response);
    }

    private ArrayList<Movie> filter_genre(String genre, ArrayList<Movie> movies) {
        movies = (ArrayList<Movie>) movies.stream().filter(movie -> {
            boolean isFound = false;
            Object m_genre = movie.getGenres();
            if(m_genre instanceof String) {
                isFound = genre.contains(String.valueOf(m_genre));
            } else if(m_genre instanceof String[]) {
                for(var m_g : (String[])m_genre) {
                    if(genre.contains(m_g)) {
                        isFound = true; break;
                    }
                }
            } else if(m_genre instanceof List) {
                for(var m_g : (List<String>)m_genre) {
                    if(genre.contains(m_g)) {
                        isFound = true; break;
                    }
                }
            }
            return isFound;
        }).collect(Collectors.toList());
        return movies;
    }
}
