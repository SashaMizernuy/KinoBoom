package com.example.kinoboom.view;

public interface MainContract {

    interface View{
        void startListFragment();
    }

    interface Presenter{
       void fragmentLaunched();
    }
}
