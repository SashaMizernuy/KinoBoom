package com.example.kinoboom.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.kinoboom.R;
import com.example.kinoboom.presenter.Presenter;
import com.example.kinoboom.presenter.PresenterInterface;
import com.example.kinoboom.viewModal.FilmViewModal;


import javax.inject.Inject;

import es.dmoral.toasty.Toasty;

import static com.example.kinoboom.app.AppController.getAppComponent;


public class MainActivity extends AppCompatActivity implements PresenterInterface {


    @Inject
    public FilmViewModal filmViewModal;

    ProgressBar progressBar;

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
    public void error(String error) {
        Toasty.error(this, "Error: "+error, Toast.LENGTH_LONG).show();
    }

}
