package com.example.kinoboom.fragmentDetail;

public class DetailPresenter implements DeatailContract.Presenter {

    private final DeatailContract.View view;

    public DetailPresenter(DeatailContract.View view) {
        this.view = view;
    }

    @Override
    public void onViewCreated() {
        view.overviewFilm();
    }
}
