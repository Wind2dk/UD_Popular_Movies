package dk.getonboard.android.popularmovies.utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dk.getonboard.android.popularmovies.model.Movie;
import dk.getonboard.android.popularmovies.model.Review;
import dk.getonboard.android.popularmovies.model.Trailer;

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

    private static final String RESULTS_KEY = "results";
    private static final String TRAILER_NAME_KEY = "name";
    private static final String TRAILER_KEY_KEY = "key";
    private static final String REVIEW_AUTHOR_KEY = "author";
    private static final String REVIEW_CONTENT_KEY = "content";
    private static final String REVIEW_ID_KEY = "id";
    private static final String REVIEW_URL_KEY = "url";

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

    static List<Trailer> ParseTrailers(String json) {
        List<Trailer> trailerList = new ArrayList<>();
        try {
            JSONObject jsonTrailers = new JSONObject(json);
            JSONArray jsonTrailerArray = jsonTrailers.getJSONArray(RESULTS_KEY);
            for (int i = 0; i < jsonTrailerArray.length(); i++) {
                JSONObject jsonTrailer = jsonTrailerArray.getJSONObject(i);
                Trailer trailer = new Trailer();
                trailer.setName(jsonTrailer.getString(TRAILER_NAME_KEY));
                trailer.setKey(jsonTrailer.getString(TRAILER_KEY_KEY));
                trailerList.add(trailer);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trailerList;
    }

    static List<Review> ParseReviews(String json) {
        List<Review> reviewList = new ArrayList<>();
        try {
            JSONObject jsonReviews = new JSONObject(json);
            JSONArray jsonReviewArray = jsonReviews.getJSONArray(RESULTS_KEY);
            for (int i = 0; i < jsonReviewArray.length(); i++) {
                JSONObject jsonReview = jsonReviewArray.getJSONObject(i);
                Review review = new Review();
                review.setAuthor(jsonReview.getString(REVIEW_AUTHOR_KEY));
                review.setContent(jsonReview.getString(REVIEW_CONTENT_KEY));
                review.setId(jsonReview.getString(REVIEW_ID_KEY));
                review.setUrl(jsonReview.getString(REVIEW_URL_KEY));
                reviewList.add(review);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reviewList;
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
