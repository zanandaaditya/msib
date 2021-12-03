package com.zananda.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zananda.movieapp.DetailActivity;
import com.zananda.movieapp.R;
import com.zananda.movieapp.model.ResultsItem;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<ResultsItem> resultsItems = new ArrayList<>();

    public MovieAdapter(Context context, List<ResultsItem> resultsItems) {
        this.context = context;
        this.resultsItems = resultsItems;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_list_movie, viewGroup, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder movieViewHolder, int i) {
        movieViewHolder.tvJudul.setText(resultsItems.get(i).getTitle());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + resultsItems.get(i).getBackdropPath()).override(512, 512)
                .error(R.drawable.ic_launcher_background)
                .into(movieViewHolder.ivBackdrop);

        movieViewHolder.cvKlik.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("backdrop", "https://image.tmdb.org/t/p/w500" + resultsItems.get(i).getBackdropPath());
            intent.putExtra("detail", resultsItems.get(i).getOverview());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return resultsItems.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivBackdrop;
        private TextView tvJudul;
        private CardView cvKlik;
        public MovieViewHolder(View itemView) {
            super(itemView);

            ivBackdrop = itemView.findViewById(R.id.iv_backdrop);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            cvKlik = itemView.findViewById(R.id.cv_klik);
        }
    }
}