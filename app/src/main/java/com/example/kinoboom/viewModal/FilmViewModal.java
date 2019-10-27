package com.example.kinoboom.viewModal;


import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableInt;

import com.example.kinoboom.Modal.FilmModal;
import com.example.kinoboom.app.AppController;
import com.example.kinoboom.request.FilmService;

import java.util.Observable;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class FilmViewModal extends Observable {
    public ObservableInt progressBar;
    private Context context;



    @Inject
    FilmService filmService;

    public FilmViewModal(@NonNull Context context) {
        this.context=context;
        progressBar = new ObservableInt(View.GONE);

    }

    public void getFilm(){
        //Write method get data from API
        progressBar.set(View.VISIBLE);

        final AppController appController = AppController.create(context);

        appController.getAppComponent().inject(this);
        Disposable disposable = filmService.nowPlaying("3/movie/now_playing?api_key=330be44bd0d082bcdb4f061a051757cb")
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FilmModal>() {
                    @Override
                    public void accept(FilmModal filmModal) throws Exception {
                       Toasty.success(context, "Success!"+filmModal.results.get(1), Toast.LENGTH_SHORT, true).show();
                    }


                }, new Consumer<Throwable>() {
                    @Override public void accept(Throwable throwable) throws Exception {
                       // progressBar.set(View.GONE);
                    }
                });



   }

}



