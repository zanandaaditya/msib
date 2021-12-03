package com.zananda.movieapp.ui.film;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zananda.movieapp.R;
import com.zananda.movieapp.adapter.MovieAdapter;
import com.zananda.movieapp.api.ApiConfig;
import com.zananda.movieapp.api.ApiService;
import com.zananda.movieapp.model.MovieRootModel;
import com.zananda.movieapp.model.ResultsItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmFragment extends Fragment {
    private final String TAG = "debug";
    private MovieAdapter movieAdapter;
    private List<ResultsItem> resultsItems;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_film, container, false);
        recyclerView = root.findViewById(R.id.rv_movie);
        getData();

        return root;
    }

    private void getData() {
        ApiService apiService = ApiConfig.getApiService();
        apiService.ambilData().enqueue(new Callback<MovieRootModel>() {
            @Override
            public void onResponse(Call<MovieRootModel> call, Response<MovieRootModel> response) {
                Log.d(TAG, "onResponse: " + response.body());

                if (response.isSuccessful()){
                    resultsItems = new ArrayList<>();
                    resultsItems = response.body().getResults();
                    movieAdapter = new MovieAdapter(getActivity(), resultsItems);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(movieAdapter);
                } else {
                    Toast.makeText(getActivity(), "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieRootModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Periksa koneksi internet anda" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}