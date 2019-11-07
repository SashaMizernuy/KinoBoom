package com.example.kinoboom.viewModal;

import com.example.kinoboom.modal.FilmModal;
import com.example.kinoboom.request.FilmService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.Collections;


public class FilmViewModal  {

    private final FilmService filmService;

    public FilmViewModal(FilmService filmService) {
        this.filmService = filmService;
    }

    public interface CallbackInterface {
        void accept(FilmModal filmModal);
        void error(String error);
    }

    public Disposable nowPlaying(CallbackInterface callback) {
        return filmService.nowPlaying()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable ->callback.error(throwable.getMessage()) )
                .subscribe(filmModal -> {
                    sortData(filmModal);
                    callback.accept(filmModal);
                });
    }

    public void sortData(FilmModal filmModal) {
        Collections.sort(filmModal.getResults(), (result, t1) -> result.getTitle().compareTo(t1.getTitle()));
    }
}