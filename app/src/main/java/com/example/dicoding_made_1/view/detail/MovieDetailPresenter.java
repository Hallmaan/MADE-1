package com.example.dicoding_made_1.view.detail;

import android.content.Context;

import com.example.dicoding_made_1.R;
import com.example.dicoding_made_1.api.ApiClient;
import com.example.dicoding_made_1.api.ApiService;
import com.example.dicoding_made_1.model.MovieDetailResponse;
import com.example.dicoding_made_1.view.base.Presenter;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailPresenter implements Presenter<MovieDetailView> {

    private MovieDetailView view;
    private ApiService apiService = ApiClient.getClient();

    @Override
    public void onAttach(MovieDetailView view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    public void getMovieDetail(final Context context, int id){
        if(view != null) view.showLoader();
        apiService.getMovieDetail(id, context.getString(R.string.api_key))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MovieDetailResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MovieDetailResponse response) {
                if (view != null) {
                    view.hideLoader();
                    view.onSuccessGetDetail(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (view != null) {
                    view.hideLoader();
                    if (e instanceof IOException) view.onFailedGetDetail("Koneksi gagal");
                    else view.onFailedGetDetail("Terjadi Kesalahan");
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
