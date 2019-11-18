package com.example.kinoboom.fragmentListFilm;

import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;
import com.example.kinoboom.viewModal.FilmViewModal;

public class FilmListPresenter implements FilmListContract.Presenter {

    private final FilmViewModal filmViewModal;
    private final FilmListContract.View view;

    public FilmListPresenter(FilmViewModal filmViewModal, FilmListContract.View view) {
        this.filmViewModal = filmViewModal;
        this.view = view;
    }


    @Override
    public void getResponse() {
        view.progressBarVisible();
        filmViewModal.getCallData(new FilmViewModal.CallbackInterface() {
            @Override
            public void accept(FilmModal filmModal) {
                view.responseDataAdd(filmModal);
                view.listenerAdapter();
                view.initAdapter();
                view.progressBarGone();
            }

            @Override
            public void error(String error) {
                view.catchError(error);
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
        view.deleteItemDialog(film,position);
    }
}
