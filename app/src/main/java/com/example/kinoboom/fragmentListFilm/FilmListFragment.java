package com.example.kinoboom.fragmentListFilm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kinoboom.R;
import com.example.kinoboom.fragmentDetail.DetailFragment;
import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;
import com.example.kinoboom.recyclerAdapter.RecyclerAdapter;
import com.example.kinoboom.viewModal.FilmViewModal;

import org.parceler.Parcels;

import static com.example.kinoboom.app.AppController.getAppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FilmListFragment extends Fragment implements FilmListContract.View {

    @Inject
    public FilmViewModal filmViewModal;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.listOfFilm)
    RecyclerView recyclerView;

    private View view;
    private FilmListPresenter presenter;
    private List<Film> listFilm;
    private RecyclerAdapter recyclerAdapter;

    public FilmListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_list_film, container, false);
            ButterKnife.bind(this, view);
            getAppComponent().inject(this);
            checkSavedState(savedInstanceState);
            initFilmListPresenter();
        }
        return view;
    }

    public void checkSavedState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            listFilm = Parcels.unwrap(savedInstanceState.getParcelable("FilmList"));
        } else {
            listFilm = new ArrayList<>();
        }
    }

    public void initFilmListPresenter() {
        presenter = new FilmListPresenter(filmViewModal, this);
        presenter.onViewCreated();
    }

    @Override
    public void progressBarVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void responseDataAdd(FilmModal filmModal) {
        for (int i = 0; i < filmModal.getResults().size(); i++) {
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
                (film) -> {
                    presenter.onFilmClicked(film);
                },
                (film, position) -> {
                    presenter.onFilmLongClicked(film, position);
                });
    }

    @Override
    public void initAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void progressBarGone() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable("FilmList", Parcels.wrap(listFilm));
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void overviewFilm(Film film) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("overview", film.getOverview());
        detailFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.listFragment, detailFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void deleteItemDialog(Film film, int position) {
        new AlertDialog.Builder(getActivity())
                .setTitle(film.title)
                .setMessage("Delete this item ?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    listFilm.remove(film);
                    recyclerAdapter.notifyItemRemoved(position);
                })
                .setNegativeButton(android.R.string.no, (dialog, which) -> {
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void showToast(String error) {
        Toasty.error(getActivity(), "Error: " + error.substring(error.lastIndexOf(":")),
                Toast.LENGTH_LONG).show();
    }
}
