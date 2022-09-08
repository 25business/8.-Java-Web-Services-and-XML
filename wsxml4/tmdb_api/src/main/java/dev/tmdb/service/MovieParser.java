package dev.tmdb.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dev.tmdb.entities.Movie;
import dev.tmdb.exceptions.MovieNotFoundException;
import dev.tmdb.exceptions.ServiceErrorException;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MovieParser {

    public static ArrayList<Movie> all() throws ServiceErrorException {
        Gson gson = new Gson();
        try {
            File f = new File(System.getenv("JAVA_RESOURCES") + "/wsxml3/tmdb_movies.json");
            InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
            return gson.fromJson(isr, new TypeToken<ArrayList<Movie>>(){}.getType());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceErrorException();
        }
    }

    public static Movie one(int movie_id) throws ServiceErrorException, MovieNotFoundException  {
        Gson gson = new Gson();
        try {
            File f = new File(System.getenv("JAVA_RESOURCES") + "/wsxml3/tmdb_movies.json");
            InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
            ArrayList<Movie> movies = gson.fromJson(isr, new TypeToken<ArrayList<Movie>>(){}.getType());
            for(var movie : movies) {
                if(movie.getId() == movie_id) return movie;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServiceErrorException();
        }
        throw new MovieNotFoundException();
    }
}
