package com.example.kinoboom.fragmentListFilm;

public interface ListFilmContract {

    interface View {
        void progressBarVisible();
    }

    interface Presenter {
        void onResponse();
    }
}
