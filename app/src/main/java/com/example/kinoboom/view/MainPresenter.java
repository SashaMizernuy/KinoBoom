package com.example.kinoboom.view;

public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void mainPresenterIsCreated() {
        view.showFilmListFragment();
    }
}