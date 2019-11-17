package com.example.kinoboom.fragmentListFilm;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.example.kinoboom.R;
import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;
import com.example.kinoboom.recyclerAdapter.RecyclerAdapter;
import com.example.kinoboom.viewModal.FilmViewModal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.example.kinoboom.app.AppController.getAppComponent;


public class ListFilmFragment extends Fragment implements ListFilmContract.View {

    @Inject
    public FilmViewModal filmViewModal;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private ListFilmPresenter listFilmPresenter;
    private List<Film> listFilm;
    private RecyclerAdapter recyclerAdapter;

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
        listFilmPresenter.onResponse();
        listFilm = new ArrayList<>();
    }


    @Override
    public void progressBarVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void responseDataAdd(FilmModal filmModal) {
        for (int i = 0;i < filmModal.getResults().size();i++) {
            FilmModal.Result filmModalResult = filmModal.getResults().get(i);
            listFilm.add(new Film(filmModalResult.getPosterPath(),
                    filmModalResult.getTitle(),
                    filmModalResult.getPopularity(),
                    filmModalResult.getReleaseDate(),
                    filmModalResult.getOverview()));
        }
    }

    @Override
    public void listenerAdapter() {
        recyclerAdapter = new RecyclerAdapter(listFilm,
                (film)-> {
                    listFilmPresenter.onFilmClicked(film);
                },
                (film, position) -> {
                    listFilmPresenter.onFilmLongClicked(film,position);
                });
    }


}
