package com.example.kinoboom.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.kinoboom.R;
import com.example.kinoboom.fragmentListFilm.FilmListFragment;


public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMainPresenter();
    }

    public void initMainPresenter(){
        mainPresenter=new MainPresenter(this);
        mainPresenter.isMainPresenterCreated();
    }

    @Override
    public void showFilmListFragment() {
        Fragment fragmentTag =getSupportFragmentManager().findFragmentByTag("listFilmTag");
        if (fragmentTag==null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.listFragment, new FilmListFragment(), "listFilmTag")
                    .commit();
        }
    }
}