package com.example.kinoboom.view;

public interface MainContract {

    interface View{
        void showFilmListFragment();
    }

    interface Presenter{
       void onViewCreated();
    }
}
