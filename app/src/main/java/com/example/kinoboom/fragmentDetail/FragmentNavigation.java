package com.example.kinoboom.fragmentDetail;

import com.example.kinoboom.modal.Film;


public interface FragmentNavigation {

    interface View {
        void atachPresenter(Presenter presenter);
    }

    interface Presenter {
        void onFilmClicked(DetailFragment fragment, Film film);
    }
}
