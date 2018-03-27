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

class MovieJsonParser {

    private static final String MOVIE_ID_KEY = "id";
    private static final String MOVIE_TITLE_KEY = "title";
    private static final String MOVIE_OVERVIEW_KEY = "overview";
    private static final String MOVIE_POSTER_PATH_KEY = "poster_path";
    private static final String MOVIE_GENRE_IDS_KEY = "genre_ids";
    private static final String MOVIE_VOTE_AVERAGE_KEY = "vote_average";
    private static final String MOVIE_VOTE_COUNT_KEY = "vote_count";
    private static final String MOVIE_RELEASE_DATE_KEY = "release_date";

    static List<Movie> ParseMovies(String json) {
        JSONObject jsonObject;
        List<Movie> movies = new ArrayList<>();
        try {
            jsonObject = new JSONObject(json);
            JSONArray results = jsonObject.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                movies.add(ParseMovie(results.get(i).toString()));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }

    private static Movie ParseMovie(String json) throws JSONException {
        Movie movie = new Movie();
        JSONObject jsonMovie = new JSONObject(json);
        movie.setId(jsonMovie.getInt(MOVIE_ID_KEY));
        movie.setTitle(jsonMovie.getString(MOVIE_TITLE_KEY));
        movie.setOverview(jsonMovie.getString(MOVIE_OVERVIEW_KEY));
        movie.setPosterPath(jsonMovie.getString(MOVIE_POSTER_PATH_KEY));
        movie.setGenreIds(intArrayFromJson(jsonMovie.getJSONArray(MOVIE_GENRE_IDS_KEY)));
        movie.setVoteAverage(jsonMovie.getDouble(MOVIE_VOTE_AVERAGE_KEY));
        movie.setVoteCount(jsonMovie.getInt(MOVIE_VOTE_COUNT_KEY));
        movie.setReleaseDate(jsonMovie.getString(MOVIE_RELEASE_DATE_KEY));
        return movie;
    }

    //https://stackoverflow.com/questions/20068852/how-to-cast-jsonarray-to-int-array
    private static int[] intArrayFromJson(JSONArray array) {
        // Deal with the case of a non-array value.
        if (array == null) { return new int[0]; }

        // Create an int array to accommodate the numbers.
        int[] numbers = new int[array.length()];

        // Extract numbers from JSON array.
        for (int i = 0; i < array.length(); ++i) {
            numbers[i] = array.optInt(i);
        }

        return numbers;
    }
}
