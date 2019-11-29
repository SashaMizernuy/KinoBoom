package com.example.kinoboom.viewModal;

import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;
import com.example.kinoboom.request.FilmService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class FilmViewModal {

    private final FilmService filmService;
    List<Film> responceList;

    public FilmViewModal(FilmService filmService) {
        this.filmService = filmService;
    }

    public Disposable getCallData(CallbackInterface callback) {
        return filmService.callData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(filmModal -> {
                    sortData(filmModal);
                    getResult(filmModal);
                    callback.accept(responceList);
                }, throwable -> callback.error(throwable.getMessage()));
    }

    public void sortData(FilmModal filmModal) {
        Collections.sort(filmModal.getResults(), (result, t1) -> result.getTitle().compareTo(t1.getTitle()));
    }

    public void getResult(FilmModal filmModal) {
        responceList = new ArrayList<>();
        for (int i = 0; i < filmModal.getResults().size(); i++) {
            FilmModal.Result filmModalResult = filmModal.getResults().get(i);
            responceList.add(new Film(filmModalResult.getPosterPath(),
                    filmModalResult.getTitle(),
                    filmModalResult.getPopularity(),
                    filmModalResult.getReleaseDate(),
                    filmModalResult.getOverview()));
        }
    }

    public interface CallbackInterface {
        void accept(List<Film> responceResult);

        void error(String text);
    }
}