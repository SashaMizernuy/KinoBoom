package com.example.kinoboom.fragmentListFilm;

import com.example.kinoboom.modal.FilmModal;
import com.example.kinoboom.viewModal.FilmViewModal;

public class ListFilmPresenter implements ListFilmContract.Presenter {

    private final FilmViewModal filmViewModal;
    private final ListFilmContract.View view;

    public ListFilmPresenter(FilmViewModal filmViewModal,ListFilmContract.View view) {
        this.filmViewModal = filmViewModal;
        this.view = view;
    }


    @Override
    public void onResponse() {
        filmViewModal.getCallData(new FilmViewModal.CallbackInterface() {
            @Override
            public void accept(FilmModal filmModal) {
            }

            @Override
            public void error(String error) {
            }
        });
    }
}
