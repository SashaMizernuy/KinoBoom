package com.example.kinoboom.fragmentListFilm;

import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;

public interface ListFilmContract {

    interface View {
        void progressBarVisible();

        void responseDataAdd(FilmModal filmModal);

        void listenerAdapter();
    }

    interface Presenter {
        void onResponse();

        void onFilmClicked(Film film);

        void onFilmLongClicked(Film film,int position);
    }
}
