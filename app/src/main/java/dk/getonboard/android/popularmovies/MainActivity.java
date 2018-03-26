package dk.getonboard.android.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dk.getonboard.android.popularmovies.adapter.GridAdapter;
import dk.getonboard.android.popularmovies.model.Movie;
import dk.getonboard.android.popularmovies.utility.TheMovieDbApi;
import dk.getonboard.android.popularmovies.utility.TheMovieDbApiListener;

public class MainActivity extends AppCompatActivity implements TheMovieDbApiListener {

    @BindView(R.id.main_gridview) GridView gridview;
    GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        TheMovieDbApi api = new TheMovieDbApi(this, this);
        api.getMovies();

        /*Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });*/


    }

    @Override
    public void onMovieResponse(final List<Movie> movies) {

        adapter = new GridAdapter(this, movies);
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("movie", movies.get(position));
                startActivityForResult(intent, 1);
            }
        });
    }
}
