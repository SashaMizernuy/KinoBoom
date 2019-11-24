package com.example.kinoboom.fragmentListFilm;

import android.os.Bundle;

import com.example.kinoboom.modal.Film;

import java.util.List;


public interface FilmListContract {

    interface View {
        void progressBarVisible();

        void responseDataAdd(List<Film> responceList);

        void listenerAdapter();

        void initAdapter();

        void progressBarGone();

        void overviewFilm(Film film);

        void deleteItemDialog(Film film, int position);

        void showToast(String text);
    }

    interface Presenter {
        void onViewCreated();

        void onFilmClicked(Film film);

        void onFilmLongClicked(Film film, int position);
    }
}
