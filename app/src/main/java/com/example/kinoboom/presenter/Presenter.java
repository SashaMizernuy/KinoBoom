package com.example.kinoboom.presenter;


import com.example.kinoboom.modal.FilmModal;
import com.example.kinoboom.viewModal.FilmViewModal;


public class Presenter {

    private final FilmViewModal filmViewModal;

    private final PresenterInterface presenterInterface;


    public Presenter(FilmViewModal filmViewModal, PresenterInterface presenterInterface) {
        this.filmViewModal=filmViewModal;
        this.presenterInterface=presenterInterface;
    }


    public void getDataList(){
        presenterInterface.progressBarVisible();
        filmViewModal.nowPlaying(new FilmViewModal.CallbackInterface() {
            @Override
            public void accept(FilmModal filmModal) {

                presenterInterface.progressBarGone();

            }

            @Override
            public void error(String error) {
                presenterInterface.progressBarGone();
                presenterInterface.error(error);

            }
        });
    }
}
