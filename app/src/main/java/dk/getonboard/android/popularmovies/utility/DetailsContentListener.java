package dk.getonboard.android.popularmovies.utility;

import java.util.List;

import dk.getonboard.android.popularmovies.model.Trailer;

public interface DetailsContentListener {
    void onTrailerResponse(List<Trailer> trailers);
}
