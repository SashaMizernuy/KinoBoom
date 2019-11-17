package com.example.kinoboom.fragmentListFilm;

import com.example.kinoboom.modal.FilmModal;

public interface ListFilmContract {

    interface View {
        void progressBarVisible();

        void responseDataAdd(FilmModal filmModal);
    }

    interface Presenter {
        void onResponse();
    }
}
