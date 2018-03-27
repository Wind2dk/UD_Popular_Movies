package dk.getonboard.android.popularmovies.utility;

import java.util.List;

import dk.getonboard.android.popularmovies.model.Movie;

/**
 * Created by Wind on 26-03-2018.
 */

public interface TheMovieDbApiListener {
    void onMovieResponse(List<Movie> movies);
}
