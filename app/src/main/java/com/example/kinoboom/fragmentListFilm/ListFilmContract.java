package com.example.kinoboom.fragmentListFilm;

public interface ListFilmContract {

    interface View {
        void initAdapter();

        void listFilm();
    }

    interface Presenter {
        void isAdapterInit();

        void isShowListFilm();
    }
}
