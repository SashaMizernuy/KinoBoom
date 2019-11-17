package com.example.kinoboom.presenter;


public class FilmPresenter implements FilmListContract.Presenter {
    private final FilmListContract.View view;

    public FilmPresenter(FilmListContract.View view) {
        this.view = view;
    }

    @Override
    public void onFragmentInit() {
        view.startListFragment();
    }
}