package com.example.kinoboom.fragmentListFilm;

import com.example.kinoboom.modal.Film;
import com.example.kinoboom.viewModal.FilmViewModal;

import java.util.List;


public class FilmListPresenter implements FilmListContract.Presenter {

    private final FilmViewModal filmViewModal;
    private final FilmListContract.View view;

    public FilmListPresenter(FilmViewModal filmViewModal, FilmListContract.View view) {
        this.filmViewModal = filmViewModal;
        this.view = view;
    }

    @Override
    public void onViewCreated() {
        view.progressBarVisible();
        filmViewModal.getCallData(new FilmViewModal.CallbackInterface() {
            @Override
            public void accept(List<Film> responceList) {
                view.responseDataAdd(responceList);
                view.listenerAdapter();
                view.initAdapter();
                view.progressBarGone();
            }

            @Override
            public void error(String text) {
                view.showToast(text);
                view.progressBarGone();
            }
        });
    }

    @Override
    public void onFilmClicked(Film film) {
        view.overviewFilm(film);
    }

    @Override
    public void onFilmLongClicked(Film film, int position) {
        view.deleteItemDialog(film, position);
    }
}
