package dk.getonboard.android.popularmovies.utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dk.getonboard.android.popularmovies.model.Movie;

/**
 * Created by Wind on 26-03-2018.
 */

public class MovieJsonParser {

    public static List<Movie> ParseMovies(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray results = jsonObject.getJSONArray("results");
        List<Movie> movies = new ArrayList<>(results.length());
        for (int i = 0; i < results.length(); i++) {
            movies.add(ParseMovie(results.get(i).toString()));
        }
        return movies;
    }

    public static Movie ParseMovie(String json) throws JSONException {
        Movie movie = new Movie();
        JSONObject jsonMovie = new JSONObject(json);
        movie.setId(jsonMovie.getInt("id"));
        movie.setTitle(jsonMovie.getString("title"));
        movie.setOverview(jsonMovie.getString("overview"));
        movie.setPosterPath(jsonMovie.getString("poster_path"));
        movie.setGenreIds(intArrayFromJson(jsonMovie.getJSONArray("genre_ids")));

        return movie;
    }

    //https://stackoverflow.com/questions/20068852/how-to-cast-jsonarray-to-int-array
    private static int[] intArrayFromJson(JSONArray array) {
        // Deal with the case of a non-array value.
        if (array == null) { return new int[0]; }

        // Create an int array to accomodate the numbers.
        int[] numbers = new int[array.length()];

        // Extract numbers from JSON array.
        for (int i = 0; i < array.length(); ++i) {
            numbers[i] = array.optInt(i);
        }

        return numbers;
    }
}
