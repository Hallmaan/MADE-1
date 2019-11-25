package com.example.dicoding_made_1.view.main;

import com.example.dicoding_made_1.model.Movie;
import com.example.dicoding_made_1.view.base.View;

import java.util.List;

public interface MainView extends View {
    void showLoader();
    void hideLoader();
    void onSuccessGetMovie(List<Movie> movies);
    void onFailedGetMovie(String message);
}
