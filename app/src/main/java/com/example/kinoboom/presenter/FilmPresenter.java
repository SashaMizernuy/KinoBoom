package com.example.kinoboom.presenter;

import com.example.kinoboom.fragmentDetail.DetailFragment;
import com.example.kinoboom.fragmentDetail.FragmentNavigation;
import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;
import com.example.kinoboom.viewModal.FilmViewModal;


public class FilmPresenter implements FilmListContract.Presenter, FragmentNavigation.Presenter {
    private final FilmViewModal filmViewModal;
    private final FilmListContract.View view;

    public FilmPresenter(FilmViewModal filmViewModal, FilmListContract.View view) {
        this.filmViewModal = filmViewModal;
        this.view = view;
    }

    @Override
    public void onViewCreated() {
        view.progressBarVisible();
        filmViewModal.getCallData(new FilmViewModal.CallbackInterface() {
            @Override
            public void accept(FilmModal filmModal) {
                view.addData(filmModal);
                view.displayingAdapter(filmModal);
                view.progressBarGone();
            }

            @Override
            public void error(String error) {
                view.error(error);
                view.progressBarGone();
            }
        });
    }

    @Override
    public void onFilmClicked(DetailFragment fragment, Film film) {
        view.progressBarVisible();
        view.aboutFilmFragment(fragment,film);
        view.detachAboutFragment();
        view.progressBarGone();
    }

    @Override
    public void onFilmLongClicked(Film film,int position) {
        view.progressBarVisible();
        view.deleteItemDialog(film,position);
        view.progressBarGone();
    }
}