package com.example.kinoboom.fragmentListFilm;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.kinoboom.R;
import com.example.kinoboom.recyclerAdapter.RecyclerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ListFilmFragment extends Fragment implements ListFilmContract.View {

    protected ListFilmContract.Presenter listFilmPresenter;

    @BindView(R.id.recViewSongs)
    RecyclerView adapter;

    private RecyclerAdapter recyclerAdapter;

    public ListFilmFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_film, container, false);
        ButterKnife.bind(this, v);
        listFilmPresenter = new ListFilmPresenter(this);
        listFilmPresenter.isAdapterInit();
        return v;
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
