package com.example.kinoboom.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.kinoboom.R;
import com.example.kinoboom.fragmentListFilm.ListFilmFragment;


public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter=new MainPresenter(this);
        presenter.onFragmentInit();
    }

    @Override
    public void startListFragment() {
        ListFilmFragment listFilmFragment = new ListFilmFragment();
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.listFragment,listFilmFragment).
                addToBackStack(null).
                commit();
    }
}