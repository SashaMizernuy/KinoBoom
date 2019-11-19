package com.example.kinoboom.fragmentListFilm;

import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;

public interface FilmListContract {

    interface View {
        void progressBarVisible();

        void responseDataAdd(FilmModal filmModal);

        void listenerAdapter();

        void initAdapter();

        void progressBarGone();

        void overviewFilm(Film film);

        void deleteItemDialog(Film film,int position);

        void showToast(String error);
    }

    interface Presenter {
        void filmListPresenterIsCreated();

        void onFilmClicked(Film film);

        void onFilmLongClicked(Film film,int position);
    }
}
