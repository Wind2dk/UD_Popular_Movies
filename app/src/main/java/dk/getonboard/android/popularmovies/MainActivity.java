package dk.getonboard.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dk.getonboard.android.popularmovies.adapter.GridAdapter;
import dk.getonboard.android.popularmovies.database.AppDatabase;
import dk.getonboard.android.popularmovies.model.Movie;
import dk.getonboard.android.popularmovies.utility.AppExecutors;
import dk.getonboard.android.popularmovies.utility.TheMovieDbApi;
import dk.getonboard.android.popularmovies.utility.TheMovieDbApiListener;

public class MainActivity extends AppCompatActivity implements TheMovieDbApiListener {

    private static final int SHOW_DETAILS_REQUEST_CODE = 100;

    @BindView(R.id.main_gridview) GridView gridview;

    private GridAdapter mAdapter;
    private TheMovieDbApi mTheMovieDbApi;
    private AppDatabase mDb;
    private ArrayList<Movie> mMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mDb = AppDatabase.getInstance(this);
        mTheMovieDbApi = new TheMovieDbApi(this, this);

        if (savedInstanceState == null) {
            mTheMovieDbApi.getMovies("popular");
        } else {
            mMovies = savedInstanceState.getParcelableArrayList("movies");
            showMoviesGrid(mMovies);
            gridview.smoothScrollToPositionFromTop(savedInstanceState.getInt("position"), savedInstanceState.getInt("top"));
        }
    }

    @Override
    public void onMovieResponse(final List<Movie> movies) {
        mMovies = (ArrayList<Movie>) movies;
        showMoviesGrid(movies);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("movies", mMovies);

        // https://stackoverflow.com/questions/3014089/maintain-save-restore-scroll-position-when-returning-to-a-listview
        // save index and top position
        int index = gridview.getFirstVisiblePosition();
        View v = gridview.getChildAt(0);
        int top = (v == null) ? 0 : (v.getTop() - gridview.getPaddingTop());

        outState.putInt("position", index);
        outState.putInt("top", top);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popular:
                mTheMovieDbApi.getMovies("popular");
                return true;
            case R.id.top_rated:
                mTheMovieDbApi.getMovies("top_rated");
                return true;
            case R.id.favorites:
                showFavorites();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showFavorites() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Movie> movies = mDb.movieDao().loadFavoriteMovies();
                mMovies = (ArrayList<Movie>) movies;
                AppExecutors.getInstance().mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        showMoviesGrid(movies);
                    }
                });
            }
        });
    }

    private void showMoviesGrid(final List<Movie> movies) {
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
