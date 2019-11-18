package com.example.kinoboom.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
        Fragment fragmentTag =getSupportFragmentManager().findFragmentByTag("listFilmTag");
        if (fragmentTag==null) {
            getSupportFragmentManager().
                    beginTransaction().
                    add(R.id.listFragment, new ListFilmFragment(), "listFilmTag").
                    commit();
        }
    }
}