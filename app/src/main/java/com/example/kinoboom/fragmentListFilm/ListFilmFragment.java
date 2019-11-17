package com.example.kinoboom.fragmentListFilm;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.kinoboom.R;
import com.example.kinoboom.viewModal.FilmViewModal;
import javax.inject.Inject;
import butterknife.ButterKnife;

import static com.example.kinoboom.app.AppController.getAppComponent;


public class ListFilmFragment extends Fragment implements ListFilmContract.View {

    @Inject
    public FilmViewModal filmViewModal;

    private ListFilmPresenter listFilmPresenter;

    public ListFilmFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_film, container, false);
        ButterKnife.bind(this, v);
        getAppComponent().inject(this);
        initView();
        return v;
    }

    public void initView() {
        listFilmPresenter = new ListFilmPresenter(filmViewModal,this);

    }

    @Override
    public void initAdapter() {
        //initialize adapter
    }

    @Override
    public void listFilm() {
        //getListFilm
    }
}
