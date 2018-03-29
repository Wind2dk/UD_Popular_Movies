package dk.getonboard.android.popularmovies.utility;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import dk.getonboard.android.popularmovies.R;

/**
 * Created by Wind2dk on 01-03-2018.
 */

public class TheMovieDbApi {

    private final TheMovieDbApiListener listener;
    private final Context context;
    private static final String TAG = "TheMovieDbApi";
    private final static String API_BASE_URL = "http://api.themoviedb.org/3";
    private final static String API_POSTER_BASE_URL = "http://image.tmdb.org/t/p/";
    private final static String POSTER_SIZE_VERY_SMALL = "w92";
    private final static String POSTER_SIZE_SMALL = "w154";
    private final static String POSTER_SIZE_MEDIUM = "w185";
    private final static String POSTER_SIZE_LARGE = "w342";
    private final static String POSTER_SIZE_VERY_LARGE = "w500";
    private final static String POSTER_SIZE_HUGE = "w780";
    private final String API_KEY;

    public TheMovieDbApi(Context context, TheMovieDbApiListener listener) {
        this.context = context;
        this.listener = listener;
        API_KEY = context.getResources().getString(R.string.movie_db_api_key);

    }

    public void getMovies(String filter) {
        // https://developer.android.com/training/volley/simple.html#java
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.context);
        String url = API_BASE_URL + "/movie/" + filter + "?api_key=" + API_KEY;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d(TAG, "Response is: "+ response.substring(0,500));
                        listener.onMovieResponse(MovieJsonParser.ParseMovies(response));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "That didn't work!" + error);
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public static String getPoster(String path) {
        return getPoster(path, 2);
    }

    public static String getPoster(String path, int size) {
        switch (size) {
            case 0: return API_POSTER_BASE_URL + POSTER_SIZE_VERY_SMALL + path;
            case 1: return API_POSTER_BASE_URL + POSTER_SIZE_SMALL + path;
            case 2: return API_POSTER_BASE_URL + POSTER_SIZE_MEDIUM + path;
            case 3: return API_POSTER_BASE_URL + POSTER_SIZE_LARGE + path;
            case 4: return API_POSTER_BASE_URL + POSTER_SIZE_VERY_LARGE + path;
            case 5: return API_POSTER_BASE_URL + POSTER_SIZE_HUGE + path;
            default: return getPoster(path);
        }
    }
}
