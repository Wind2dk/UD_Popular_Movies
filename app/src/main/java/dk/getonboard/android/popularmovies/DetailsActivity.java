package dk.getonboard.android.popularmovies;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dk.getonboard.android.popularmovies.model.Movie;
import dk.getonboard.android.popularmovies.model.Trailer;
import dk.getonboard.android.popularmovies.utility.DetailsContentApi;
import dk.getonboard.android.popularmovies.utility.DetailsContentListener;
import dk.getonboard.android.popularmovies.utility.TheMovieDbApi;

public class DetailsActivity extends AppCompatActivity implements DetailsContentListener {

    //region BindViews
    @BindView(R.id.details_movieImage) ImageView movieImage;
    @BindView(R.id.details_tv_title) TextView title;
    @BindView(R.id.details_tv_overview) TextView overview;
    @BindView(R.id.details_tv_userRating) TextView userRating;
    @BindView(R.id.details_tv_releaseDate) TextView releaseDate;
    @BindView(R.id.details_btn_favorite) Button markFavorite;
    @BindView(R.id.details_btn_trailer) Button trailerBtn;
    //endregion

    Movie movie;
    DetailsContentApi detailsContentApi;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        this.context = this;
        Intent intent = getIntent();
        movie = intent.getParcelableExtra("movie");
        detailsContentApi = new DetailsContentApi(this, this);
        updateViews();
        detailsContentApi.getTrailers(movie.getId());
    }

    private void updateViews() {
        if (movie != null) {
            Picasso.get()
                    .load(TheMovieDbApi.getPoster(movie.getPosterPath()))
                    .placeholder(R.mipmap.ic_launcher)
                    .into(movieImage);
            title.setText(movie.getTitle());
            overview.setText(movie.getOverview());
            userRating.setText(String.valueOf(movie.getVoteAverage()));
            releaseDate.setText(movie.getReleaseDate());
        }
    }

    @Override
    public void onTrailerResponse(List<Trailer> trailers) {
        final Trailer trailer = trailers.get(0);
        trailerBtn.setText("Watch trailer - " + trailer.getName());
        trailerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inspiration for playing through youtube with fallback to web at
                // https://stackoverflow.com/questions/574195/android-youtube-app-play-video-intent
                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailer.getKey()));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + trailer.getKey()));
                try {
                    context.startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    context.startActivity(webIntent);
                }
            }
        });
    }
}
