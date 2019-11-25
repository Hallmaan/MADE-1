package com.example.dicoding_made_1.view.detail;

import com.example.dicoding_made_1.model.Movie;
import com.example.dicoding_made_1.model.MovieDetailResponse;
import com.example.dicoding_made_1.view.base.View;

import java.util.List;

public interface MovieDetailView extends View {
    void showLoader();
    void hideLoader();
    void onSuccessGetDetail(MovieDetailResponse response);
    void onFailedGetDetail(String message);
}
