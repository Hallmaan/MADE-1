package com.example.dicoding_made_1.adapter;

import android.service.autofill.TextValueSanitizer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dicoding_made_1.R;
import com.example.dicoding_made_1.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {

    private List<Genre> genres = new ArrayList<>();

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public GenreAdapter(){}

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_genre, viewGroup, false);
        return new GenreViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder genreViewHolder, int i) {
        final Genre genre = getGenres().get(i);

        genreViewHolder.tvName.setText(genre.getName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class GenreViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
