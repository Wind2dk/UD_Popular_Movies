package dk.getonboard.android.popularmovies.utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dk.getonboard.android.popularmovies.model.Movie;

/**
 * Created by Wind on 26-03-2018.
 */

public class MovieJsonParser {
    public static Movie ParseMovie(String json) {
        Movie movie = new Movie();
        try {
            JSONObject jsonMovie = new JSONObject(json);
            movie.setId(jsonMovie.getInt("id"));
            movie.setTitle(jsonMovie.getString("title"));
            movie.setOverview(jsonMovie.getString("overview"));
            movie.setPosterPath(jsonMovie.getString("poster_path"));
            movie.setGenreIds(intArrayFromJson(jsonMovie.getJSONArray("genre_ids")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
