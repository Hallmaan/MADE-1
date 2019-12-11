package com.example.dicoding_made_1.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetailResponse implements Parcelable {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("poster_path")
    @Expose
    private String poster;

    @SerializedName("vote_average")
    @Expose
    private String rating;

    @SerializedName("runtime")
    @Expose
    private String duration;

    @SerializedName("original_language")
    @Expose
    private String language;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("genres")
    @Expose
    private List<Genre> genres;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("tagline")
    @Expose
    private String tagline;

    public MovieDetailResponse() {}

    protected MovieDetailResponse(Parcel in) {
        title = in.readString();
        poster = in.readString();
        rating = in.readString();
        duration = in.readString();
        language = in.readString();
        releaseDate = in.readString();
        overview = in.readString();
        tagline = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster);
        dest.writeString(rating);
        dest.writeString(duration);
        dest.writeString(language);
        dest.writeString(releaseDate);
        dest.writeString(overview);
        dest.writeString(tagline);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieDetailResponse> CREATOR = new Creator<MovieDetailResponse>() {
        @Override
        public MovieDetailResponse createFromParcel(Parcel in) {
            return new MovieDetailResponse(in);
        }

        @Override
        public MovieDetailResponse[] newArray(int size) {
            return new MovieDetailResponse[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }
}
