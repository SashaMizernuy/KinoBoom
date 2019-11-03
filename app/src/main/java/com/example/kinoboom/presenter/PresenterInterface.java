package com.example.kinoboom.presenter;

import com.example.kinoboom.fragmentDetail.DetailFragment;
import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;


public interface PresenterInterface {

    void progressBarVisible();
    void progressBarGone();
    void getDataListAccept(FilmModal filmModal);
    void error(String error);
    void onClickView(Film film);
    void onLongClickView(Film film);
    void getAlertDialog(Film film);
    void startFragments(DetailFragment myObj);
    void addData(FilmModal filmModal);
    void setDataResult(FilmModal.Result filmResult);
}
