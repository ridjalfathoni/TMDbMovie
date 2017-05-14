package id.sch.smktelkom_mlg.privateassignment.xirpl423.tmdbmovie.Model;

/**
 * Created by Ridjal Fathoni on 14/05/2017.
 */

public class Hasil {
    public String overview;
    public int id;
    public String title;
    public String vote_average;
    public String backdrop_path;
    private String poster_path;
    private boolean adult;
    private String release_date;
    private String[] genre_ids;
    private String language;
    private String org_title;
    private String org_language;

    private float popularity;
    private int vote_count;
    private boolean video;

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(String[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOrg_title() {
        return org_title;
    }

    public void setOrg_title(String org_title) {
        this.org_title = org_title;
    }

    public String getOrg_language() {
        return org_language;
    }

    public void setOrg_language(String org_language) {
        this.org_language = org_language;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }
}
