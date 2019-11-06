package com.example.kinoboom.presenter;

import com.example.kinoboom.fragmentDetail.DetailFragment;
import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;


public interface FilmListContract {

    interface View{
        void progressBarVisible();

        void progressBarGone();

        void getDataListAccept(FilmModal filmModal);

        void error(String error);

        void getAlertDialog(Film film);

        void startFragments(DetailFragment myObj);

        void addData(FilmModal filmModal);
    }

    interface Presenter{
        void getDataList();

        void onClickView(Film film,DetailFragment myObj);

        void onLongClickView(Film film);
    }
}
