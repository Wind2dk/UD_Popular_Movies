package dk.getonboard.android.popularmovies.utility;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.nfc.Tag;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.WrongMethodTypeException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import dk.getonboard.android.popularmovies.R;
import dk.getonboard.android.popularmovies.model.Movie;

/**
 * Created by Wind2dk on 01-03-2018.
 */

public class TheMovieDbApi {

    private TheMovieDbApiListener listener;
    private Context context;
    private static final String TAG = "TheMovieDbApi";
    private final static String API_URL = "http://api.themoviedb.org/3";
    private final static String STANDARD_POSTER_SIZE = "w185";
    private final String API_KEY;

    public TheMovieDbApi(Context context, TheMovieDbApiListener listener) {
        this.context = context;
        this.listener = listener;
        API_KEY = context.getResources().getString(R.string.movie_db_api_key);

    }

    public void getMovies(List<Movie> movies) {
        // https://developer.android.com/training/volley/simple.html#java
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.context);
        String url = "http://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d(TAG, "Response is: "+ response.substring(0,500));
                        try {
                            listener.onMovieResponse(MovieJsonParser.ParseMovies(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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

}
