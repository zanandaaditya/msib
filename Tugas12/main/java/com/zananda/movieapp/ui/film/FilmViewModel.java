package com.zananda.movieapp.ui.film;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FilmViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FilmViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is film fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}