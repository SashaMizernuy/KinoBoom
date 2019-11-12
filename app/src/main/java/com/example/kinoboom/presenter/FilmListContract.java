package com.example.kinoboom.presenter;

import com.example.kinoboom.fragmentDetail.DetailFragment;
import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;


public interface FilmListContract {

    interface View{
        void progressBarVisible();

        void progressBarGone();

        void displayingAdapter(FilmModal filmModal);

        void error(String error);

        void deleteItemDialog(Film film,int position);

        void aboutFilmFragment(DetailFragment fragment, Film film);

        void detachAboutFragment();

        void addData(FilmModal filmModal);
    }

    interface Presenter{
        void onViewCreated();

        void onFilmLongClicked(Film film,int position);
    }
}
