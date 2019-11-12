package com.example.kinoboom.fragmentDetail;


public class DetailPresenter implements DeatailFragmentContract.Presenter {

    private final DeatailFragmentContract.View view;

    public DetailPresenter(DeatailFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void onFilmClicked() {
        view.overviewFilm();
    }
}
