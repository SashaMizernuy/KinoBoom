package com.example.kinoboom.presenter;

import com.example.kinoboom.modal.FilmModal;

public interface PresenterInterface {

    void progressBarVisible();
    void progressBarGone();
    void error(String error);
}
