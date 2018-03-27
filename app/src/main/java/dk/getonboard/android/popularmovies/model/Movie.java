package dk.getonboard.android.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Wind2dk on 01-03-2018.
 */

public class Movie implements Parcelable {
    private int voteCount;
    private int id;
    private double voteAverage;
    private String title;
    private String posterPath;
    private int[] genreIds;
    private String overview;
    private String releaseDate;

    public Movie() {
    }

    public Movie(int voteCount, int id, double voteAverage, String title, String posterPath, int[] genreIds, String overview, String releaseDate) {
        this.voteCount = voteCount;
        this.id = id;
        this.voteAverage = voteAverage;
        this.title = title;
        this.posterPath = posterPath;
        this.genreIds = genreIds;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    protected Movie(Parcel in) {
        voteCount = in.readInt();
        id = in.readInt();
        voteAverage = in.readDouble();
        title = in.readString();
        posterPath = in.readString();
        genreIds = in.createIntArray();
        overview = in.readString();
        releaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(int[] genreIds) {
        this.genreIds = genreIds;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(voteCount);
        dest.writeInt(id);
        dest.writeDouble(voteAverage);
        dest.writeString(title);
        dest.writeString(posterPath);
        dest.writeIntArray(genreIds);
        dest.writeString(overview);
        dest.writeString(releaseDate);
    }
}
