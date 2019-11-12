package com.example.kinoboom.fragmentDetail;

public interface DeatailFragmentContract {

    interface View {
       void overviewFilm();
    }

    interface Presenter {
        void onFilmClicked();
    }
}
