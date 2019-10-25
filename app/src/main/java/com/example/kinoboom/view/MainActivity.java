package com.example.kinoboom.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import android.os.Bundle;

import com.example.kinoboom.R;
import com.example.kinoboom.viewModal.FilmViewModal;
import com.example.kinoboom.databinding.ActivityMainBinding;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private FilmViewModal filmViewModal;
    private ActivityMainBinding mainActivityBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBinding();
        setUpObserver(filmViewModal);
        filmViewModal.getFilm();



    }

    @Override
    public void update(Observable observable, Object o) {

    }

    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }

    private void initBinding(){
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main); //layoutu set ediyor.
        filmViewModal = new FilmViewModal(this);
        mainActivityBinding.setFilmViewModal(filmViewModal);
    }
}
