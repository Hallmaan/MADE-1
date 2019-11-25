package com.example.dicoding_made_1.view.main;

import android.content.Context;

import com.example.dicoding_made_1.R;
import com.example.dicoding_made_1.api.ApiClient;
import com.example.dicoding_made_1.api.ApiService;
import com.example.dicoding_made_1.model.MovieResponse;
import com.example.dicoding_made_1.view.base.Presenter;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements Presenter<MainView> {
    private MainView view;
    private ApiService apiService = ApiClient.getClient();

    @Override
    public void onAttach(MainView view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    public void searchMovie(final Context context, String query){
        if(view != null) view.showLoader();
        apiService.search(context.getString(R.string.api_key), "en-US", query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieResponse movieResponse) {
                        if(view != null){
                            view.hideLoader();
                            view.onSuccessGetMovie(movieResponse.getMovies());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(view != null){
                            view.hideLoader();
                            if(e instanceof IOException) view.onFailedGetMovie("Koneksi gagal");
                            else view.onFailedGetMovie(e.toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
