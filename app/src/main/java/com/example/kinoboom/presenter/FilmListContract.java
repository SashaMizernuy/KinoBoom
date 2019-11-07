package com.example.kinoboom.presenter;

import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;


public interface FilmListContract {

    interface View{
        void progressBarVisible();

        void progressBarGone();

        void getDataListAccept(FilmModal filmModal);

        void error(String error);

        void getAlertDialog(Film film);

        void createFragment(Film film);

        void addData(FilmModal filmModal);
    }

    interface Presenter{
        void getDataList();

        void startFragment(Film film);

        void deleteItem(Film film);
    }
}
