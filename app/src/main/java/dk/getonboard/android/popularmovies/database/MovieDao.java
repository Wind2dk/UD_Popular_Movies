package dk.getonboard.android.popularmovies.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import dk.getonboard.android.popularmovies.model.Movie;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie WHERE favorite = 1 ORDER BY id")
    List<Movie> loadFavoriteMovies();

    @Query("SELECT * FROM movie WHERE id = :id")
    Movie loadMovie(int id);

    @Insert
    void insertMovie(Movie movie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);
}
