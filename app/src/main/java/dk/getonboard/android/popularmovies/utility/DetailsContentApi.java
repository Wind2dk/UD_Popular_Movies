package dk.getonboard.android.popularmovies.utility;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static dk.getonboard.android.popularmovies.BuildConfig.API_KEY;
import static dk.getonboard.android.popularmovies.utility.TheMovieDbApiKeys.API_BASE_URL;

public class DetailsContentApi {

    private static final String TAG = "DetailsContentApi";
    private final RequestQueue requestQueue;
    private final DetailsContentListener listener;

    public DetailsContentApi(Context context, DetailsContentListener listener) {
        this.listener = listener;
        requestQueue = Volley.newRequestQueue(context);
    }

    public void getTrailers(int movieId) {
        String url = API_BASE_URL + "/movie/" + movieId + "/videos?api_key=" + API_KEY;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d(TAG, "Response is: "+ response.substring(0,500));
                        listener.onTrailerResponse(MovieJsonParser.ParseTrailers(response));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "That didn't work!" + error);
            }
        });

        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest);
    }
}
