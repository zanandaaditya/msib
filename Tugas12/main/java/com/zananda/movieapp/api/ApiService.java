package com.zananda.movieapp.api;

import com.zananda.movieapp.model.MovieRootModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("movie/now_playing?api_key=a6c51851cb033d1c045c18dbaca5eea3")
    Call<MovieRootModel> ambilData();

}
