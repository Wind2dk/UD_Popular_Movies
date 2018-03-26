package dk.getonboard.android.popularmovies.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import dk.getonboard.android.popularmovies.R;
import dk.getonboard.android.popularmovies.model.Movie;

/**
 * Created by Wind on 26-03-2018.
 */

public class GridAdapter extends BaseAdapter {
    private final static String TAG = "GridAdapter";
    private Context mContext;
    private List<Movie> mMovies;

    public GridAdapter(Context c, List<Movie> movies) {
        mContext = c;
        mMovies = movies;
    }

    public int getCount() {
        return mMovies.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        final Movie movie = mMovies.get(position);

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.grid_item, null);
        }

        final ImageView imageView = convertView.findViewById(R.id.grid_movieImage);
        final TextView title = convertView.findViewById(R.id.grid_tv_title);
        final TextView userRating = convertView.findViewById(R.id.grid_tv_userRating);

        //title.setText(movie.getTitle());
        userRating.setText(String.valueOf(movie.getVoteAverage()));
        String path = "http://image.tmdb.org/t/p/" + "w185" + movie.getPosterPath();
        Picasso.get().load(path).into(imageView);

        return convertView;
    }


}
