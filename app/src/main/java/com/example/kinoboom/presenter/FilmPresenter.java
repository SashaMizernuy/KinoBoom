package com.example.kinoboom.presenter;

import com.example.kinoboom.fragmentDetail.DetailFragment;
import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;
import com.example.kinoboom.viewModal.FilmViewModal;


public class FilmPresenter implements FilmListContract.Presenter {
    private final FilmViewModal filmViewModal;
    private final FilmListContract.View view;

    public FilmPresenter(FilmViewModal filmViewModal, FilmListContract.View view) {
        this.filmViewModal = filmViewModal;
        this.view = view;
    }

    @Override
    public void getDataList() {
        view.progressBarVisible();
        filmViewModal.nowPlaying(new FilmViewModal.CallbackInterface() {
            @Override
            public void accept(FilmModal filmModal) {
                view.addData(filmModal);
                view.getDataListAccept(filmModal);
                view.progressBarGone();
            }
            @Override
            public void error(String error) {
                view.error(error);
                view.progressBarGone();
            }
        });
    }

    @Override
    public void onClickView(Film film, DetailFragment myObj) {
        view.progressBarVisible();
        view.startFragments(myObj);
        view.progressBarGone();
    }

    @Override
    public void onLongClickView(Film film) {
        view.progressBarVisible();
        view.getAlertDialog(film);
        view.progressBarGone();
    }
}