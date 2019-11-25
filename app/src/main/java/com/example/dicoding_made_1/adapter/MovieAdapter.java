package com.example.dicoding_made_1.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dicoding_made_1.R;
import com.example.dicoding_made_1.model.Movie;
import com.example.dicoding_made_1.utils.GlideModule;
import com.example.dicoding_made_1.view.detail.MovieDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;

    private List<Movie> movies = new ArrayList<>();

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public MovieAdapter(Context context) {this.context = context;}

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_movie, viewGroup, false);

        return new MovieViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        final Movie movie =  getMovies().get(i);

        Glide.with(context).load("https://image.tmdb.org/t/p/original/" + movie.getPoster()).into(movieViewHolder.ivPoster);
        movieViewHolder.tvTitle.setText(movie.getTitle());
        movieViewHolder.tvShortDesc.setText(movie.getShortDesc());
        movieViewHolder.cvMovie.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(context, MovieDetailActivity.class);
                detailIntent.putExtra(MovieDetailActivity.ID, movie.getId());
                detailIntent.putExtra(MovieDetailActivity.TITLE, movie.getTitle());
                context.startActivity(detailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getMovies().size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        CardView cvMovie;
        ImageView ivPoster;
        TextView tvTitle, tvShortDesc;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            cvMovie = itemView.findViewById(R.id.cv_movie);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvShortDesc = itemView.findViewById(R.id.tv_short_desc);
        }
    }
}
