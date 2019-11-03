package com.example.kinoboom.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.kinoboom.R;
import com.example.kinoboom.fragmentDetail.DetailFragment;
import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;
import com.example.kinoboom.presenter.Presenter;
import com.example.kinoboom.presenter.PresenterInterface;
import com.example.kinoboom.recyclerAdapter.RecyclerAdapter;
import com.example.kinoboom.viewModal.FilmViewModal;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;

import static com.example.kinoboom.app.AppController.getAppComponent;


public class MainActivity extends AppCompatActivity implements PresenterInterface {


    @Inject
    public FilmViewModal filmViewModal;

    private ProgressBar progressBar;
    private RecyclerView adapter;
    private List<Film> filmList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAppComponent().inject(this);
        initialView();

        Presenter presenter=new Presenter(filmViewModal,this);
        presenter.getDataList();
    }

    public void initialView(){
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progressBar);
        adapter=findViewById(R.id.recViewSongs);
        filmList=new ArrayList<>();

    }


    @Override
    public void progressBarVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void progressBarGone() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void getDataListAccept(FilmModal filmModal) {
        addData(filmModal);
        RecyclerAdapter recyclerAdapter=new RecyclerAdapter(this,filmList,
                new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(Film film) {
                Log.i("Script","OVERVIEW="+film.getOverview()) ;
                onClickToFragments(film);
            }
        });

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        adapter.setLayoutManager(layoutManager);
        adapter.setAdapter(recyclerAdapter);


    }

    public void onClickToFragments(Film film){
                DetailFragment myObj = new DetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("params", film.getOverview());
                // set MyFragment Arguments
                myObj.setArguments(bundle);
                this.getSupportFragmentManager().beginTransaction().replace(R.id.listFragment,myObj).addToBackStack(null).commit();
    }


    public void addData(FilmModal filmModal){
        for (int i = 0; i < filmModal.getResults().size(); i++) {
            FilmModal.Result filmResult = filmModal.getResults().get(i);
            filmList.add(new Film(filmResult.getPosterPath(), filmResult.getTitle(), filmResult.getPopularity(), filmResult.getReleaseDate(), filmResult.getOverview()));
        }
    }


    @Override
    public void error(String error) {
        Toasty.error(this, "Error: "+error, Toast.LENGTH_LONG).show();
    }

}
