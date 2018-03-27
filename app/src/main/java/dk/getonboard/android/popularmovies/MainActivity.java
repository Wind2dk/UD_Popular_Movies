package dk.getonboard.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dk.getonboard.android.popularmovies.adapter.GridAdapter;
import dk.getonboard.android.popularmovies.model.Movie;
import dk.getonboard.android.popularmovies.utility.TheMovieDbApi;
import dk.getonboard.android.popularmovies.utility.TheMovieDbApiListener;

public class MainActivity extends AppCompatActivity implements TheMovieDbApiListener {

    private static final int SHOW_DETAILS_REQUEST_CODE = 100;

    @BindView(R.id.main_gridview) private GridView gridview;

    private GridAdapter mAdapter;
    private TheMovieDbApi mTheMovieDbApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mTheMovieDbApi = new TheMovieDbApi(this, this);
        mTheMovieDbApi.getMovies();
    }

    @Override
    public void onMovieResponse(final List<Movie> movies) {

        mAdapter = new GridAdapter(this, movies);
        gridview.setAdapter(mAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("movie", movies.get(position));
                startActivityForResult(intent, SHOW_DETAILS_REQUEST_CODE);
            }
        });
    }
}
