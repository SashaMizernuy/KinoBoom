package com.example.kinoboom.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.kinoboom.R;
import com.example.kinoboom.recyclerAdapter.RecyclerAdapter;
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
        setRecycler(mainActivityBinding.recViewSongs);



    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof FilmViewModal) {
            RecyclerAdapter filmAdapter = (RecyclerAdapter) mainActivityBinding.recViewSongs.getAdapter();
            FilmViewModal filmVM = (FilmViewModal) observable;
            filmAdapter.setFilmList(filmVM.getFilmList());

        }
    }

    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }

    private void initBinding(){
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        filmViewModal = new FilmViewModal(this);
        mainActivityBinding.setFilmViewModal(filmViewModal);
    }

    private void setRecycler(RecyclerView listfilm){
        RecyclerAdapter filmAdapter = new RecyclerAdapter();
        listfilm.setAdapter(filmAdapter);
        listfilm.setLayoutManager(new LinearLayoutManager(this));
        listfilm.setHasFixedSize(true);

    }
}
