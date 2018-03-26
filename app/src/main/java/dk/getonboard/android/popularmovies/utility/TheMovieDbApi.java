package dk.getonboard.android.popularmovies.utility;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.WrongMethodTypeException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import dk.getonboard.android.popularmovies.model.Movie;

/**
 * Created by Wind2dk on 01-03-2018.
 */

public class TheMovieDbApi {
    private final static String API_URL = "http://api.themoviedb.org/3";
    private final static String STANDARD_POSTER_SIZE = "w185";
    private final static String API_KEY = "c56209799a8f31b427c3063b6a9cd29f";

    public static Movie[] getMovies(String filter) {
        switch (filter) {
            case "popular": getMovies(filter, true);
                break;
            default: throw new IllegalArgumentException("");
        }
        return null;
    }

    private static Movie[] getMovies(String filter, Boolean controlledArgument) {
        URL url = buildUrl(filter);
        try {
            String response = getResponseFromHttpUrl(url);
            Log.d("TAG", response);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static URL buildUrl(String searchFilter) {
        Uri builtUri = Uri.parse(API_URL).buildUpon()
                .appendPath(STANDARD_POSTER_SIZE)
                .appendPath(searchFilter)
                .appendQueryParameter("api_key", API_KEY)
                .build();

        Log.d("TAG", builtUri.toString());
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
