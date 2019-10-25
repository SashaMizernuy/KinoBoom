package com.example.kinoboom.viewModal;


import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableInt;

import java.util.Observable;



public class FilmViewModal extends Observable {
    public ObservableInt progressBar;
    private Context context;

    public FilmViewModal(@NonNull Context context) {
        this.context=context;
        progressBar = new ObservableInt(View.GONE);

    }

    public void getFilm(){
        //Write method get data from API
        progressBar.set(View.VISIBLE);

    }

}



