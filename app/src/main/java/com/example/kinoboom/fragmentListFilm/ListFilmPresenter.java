package com.example.kinoboom.fragmentListFilm;


public class ListFilmPresenter implements ListFilmContract.Presenter {

    private final ListFilmContract.View view;

    public ListFilmPresenter(ListFilmContract.View view) {
        this.view = view;
    }

    @Override
    public void isAdapterInit() {
        view.initAdapter();
    }

    @Override
    public void isShowListFilm() {

    }
}
