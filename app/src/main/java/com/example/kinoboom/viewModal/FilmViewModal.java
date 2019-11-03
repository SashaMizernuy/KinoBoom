package com.example.kinoboom.viewModal;

import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;
import com.example.kinoboom.request.FilmService;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class FilmViewModal  {

    String URL="3/movie/now_playing?api_key=330be44bd0d082bcdb4f061a051757cb";

    private final FilmService filmService;
    private List<Film> filmList;


    public FilmViewModal(FilmService filmService){
        this.filmService=filmService;

    }

    public interface CallbackInterface{
        void accept(FilmModal filmModal);
        void error(String error);
    }


    public Disposable nowPlaying(CallbackInterface callback){
        return filmService.nowPlaying(URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable ->callback.error(throwable.getMessage()) )
                .subscribe(new Consumer<FilmModal>() {
                    @Override
                    public void accept(FilmModal filmModal) throws Exception {
                        sortData(filmModal);
                        callback.accept(filmModal);
                    }
                });
    }

    
    public void sortData(FilmModal filmModal){
        Collections.sort(filmModal.getResults(), new Comparator<FilmModal.Result>() {
            @Override
            public int compare(FilmModal.Result result, FilmModal.Result t1) {
                return result.getTitle().compareTo(t1.getTitle());
            }
        });
    }
}



