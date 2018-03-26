package dk.getonboard.android.popularmovies;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import dk.getonboard.android.popularmovies.model.Movie;

public class DetailsActivity extends AppCompatActivity {

    //region BindViews
    @BindView(R.id.details_movieImage) ImageView movieImage;
    @BindView(R.id.details_tv_title) TextView title;
    @BindView(R.id.details_tv_overview) TextView overview;
    @BindView(R.id.details_tv_userRating) TextView userRating;
    @BindView(R.id.details_tv_releaseDate) TextView releaseDate;
    //endregion

    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        movie = Movie.getMovie();
        updateViews();
    }

    private void updateViews() {
        if (movie != null) {
            // Show picasso errors
            /*Picasso.Builder builder = new Picasso.Builder(this);
            builder.listener(new Picasso.Listener()
            {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
                {
                    exception.printStackTrace();
                }
            });
            builder.build().load(movie.getPosterPath()).into(movieImage);*/
            Picasso.get()
                    .load(movie.getPosterPath())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(movieImage);
            title.setText(movie.getTitle());
            overview.setText(movie.getOverview());
            userRating.setText("");
        }
    }
}
