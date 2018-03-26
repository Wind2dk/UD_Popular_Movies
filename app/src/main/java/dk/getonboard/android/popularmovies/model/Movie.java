package dk.getonboard.android.popularmovies.model;

import java.util.Date;

/**
 * Created by Wind2dk on 01-03-2018.
 */

public class Movie {
    private int voteCount;
    private int id;
    private double voteAverage;
    private String title;
    private String posterPath;
    private int[] genreIds;
    private String overview;
    private Date releaseDate;

    public Movie() {
    }

    public Movie(int voteCount, int id, double voteAverage, String title, String posterPath, int[] genreIds, String overview, Date releaseDate) {
        this.voteCount = voteCount;
        this.id = id;
        this.voteAverage = voteAverage;
        this.title = title;
        this.posterPath = posterPath;
        this.genreIds = genreIds;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public static Movie getMovie() {
        return new Movie(4, 1337, 6, "Tomb Raider", "https://image.tmdb.org/t/p/original/ePyN2nX9t8SOl70eRW47Q29zUFO.jpg", null, "Lara Croft, the fiercely independent daughter of a missing adventurer, must push herself beyond her limits when she finds herself on the island where her father disappeared.", new Date(132423231));
    }

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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
