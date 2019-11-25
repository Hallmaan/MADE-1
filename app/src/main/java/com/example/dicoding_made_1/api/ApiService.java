package com.example.dicoding_made_1.api;

import com.example.dicoding_made_1.model.MovieDetailResponse;
import com.example.dicoding_made_1.model.MovieResponse;

//import java.util.Observable;
import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/movie")
    Observable<MovieResponse> search(@Query("api_key") String apiKey,
                                     @Query("language") String language,
                                     @Query("query") String query);

    @GET("movie/{id}")
    Observable<MovieDetailResponse> getMovieDetail(@Path("id") int id,
                                                   @Query("api_key") String apiKey);

}
