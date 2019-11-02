package com.example.kinoboom.viewModal;

import android.util.Log;
import com.example.kinoboom.modal.FilmModal;
import com.example.kinoboom.request.FilmService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class FilmViewModal  {

    private final FilmService filmService;


    public FilmViewModal(FilmService filmService){
        this.filmService=filmService;

    }

    public interface CallbackInterface{
        void accept(FilmModal filmModal);
        void error(String error);
    }


    public Disposable nowPlaying(CallbackInterface callback){
        return filmService.nowPlaying("3/movie/now_playing?api_key=330be44bd0d082bcdb4f061a051757cb")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable ->callback.error(throwable.getMessage()) )
                .subscribe(new Consumer<FilmModal>() {
                    @Override
                    public void accept(FilmModal filmModal) throws Exception {
                        callback.accept(filmModal);

                        Log.i("Script","WellDone");
                    }
                });
            }

}



