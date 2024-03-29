package com.example.dicoding_made_1.view.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dicoding_made_1.R;
import com.example.dicoding_made_1.adapter.GenreAdapter;
import com.example.dicoding_made_1.model.Genre;
import com.example.dicoding_made_1.model.Movie;
import com.example.dicoding_made_1.model.MovieDetailResponse;
import com.example.dicoding_made_1.view.base.View;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView {

    private MovieDetailPresenter presenter;
    private Context context;
    public static final String ID = "id";
    public static final String TITLE = "title";
    private int id;
    private SwipeRefreshLayout swrDetail;
    private CircleImageView ivPoster;
    private TextView tvTitle, tvTagline, tvRating, tvDuration, tvLanguage,
            tvReleaseDate, tvOverview;
    private RecyclerView rvGenre;
    private GenreAdapter adapter;
    private List<Genre> genres = new ArrayList<>();
    private Movie movie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (getIntent().hasExtra("MOVIE")) {
            movie = getIntent().getParcelableExtra("MOVIE");
        }

        Log.e("TESTING ", movie.getTitle());


        presenter = new MovieDetailPresenter();
        onAttachView();
        context = this;
        Intent intent = getIntent();
        ActionBar actionBar = getSupportActionBar();
        if(intent != null){
            id = movie.getId();
            actionBar.setTitle(movie.getTitle());
        }

        actionBar.setDisplayHomeAsUpEnabled(true);

        swrDetail = findViewById(R.id.swr_detail);
        ivPoster = findViewById(R.id.iv_poster);
        tvTitle = findViewById(R.id.tv_title);
        tvTagline = findViewById(R.id.tv_tagline);
        tvRating = findViewById(R.id.tv_rating);
        tvDuration = findViewById(R.id.tv_duration);
        tvLanguage = findViewById(R.id.tv_language);
        tvReleaseDate = findViewById(R.id.tv_release_date);
        tvOverview = findViewById(R.id.tv_overview);
        rvGenre = findViewById(R.id.rv_genre);
        adapter = new GenreAdapter();
        adapter.setGenres(genres);
        rvGenre.setLayoutManager(new GridLayoutManager(context, 5));
        rvGenre.setAdapter(adapter);
        rvGenre.setNestedScrollingEnabled(false);

        swrDetail.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getMovieDetail(context, id);
            }
        });
        presenter.getMovieDetail(context, id);
    }

    @Override
    public void showLoader() {

    }

    @Override
    protected void onDestroy() {
        onDetachView();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void hideLoader() {

    }

    @Override
    public void onSuccessGetDetail(MovieDetailResponse response) {
        Glide.with(context).load("https://image.tmdb.org/t/p/original/" + response.getPoster()).into(ivPoster);
        tvTitle.setText(response.getTitle());
        tvTagline.setText(response.getTagline());
        tvRating.setText(response.getRating());
        tvDuration.setText(response.getDuration());
        tvLanguage.setText(response.getLanguage());
        tvReleaseDate.setText(response.getReleaseDate());
        tvOverview.setText(response.getOverview());
        this.genres.clear();
        this.genres.addAll(response.getGenres());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailedGetDetail(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }
}
