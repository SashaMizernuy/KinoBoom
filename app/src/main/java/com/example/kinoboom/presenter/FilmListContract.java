package com.example.kinoboom.presenter;

public interface FilmListContract {

    interface View{
        void startListFragment();
    }

    interface Presenter{
       void onFragmentInit();
    }
}
