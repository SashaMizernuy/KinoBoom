package com.example.kinoboom.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kinoboom.R;
import com.example.kinoboom.fragmentDetail.DetailFragment;
import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;
import com.example.kinoboom.presenter.FilmListContract;
import com.example.kinoboom.presenter.FilmPresenter;
import com.example.kinoboom.recyclerAdapter.RecyclerAdapter;
import com.example.kinoboom.viewModal.FilmViewModal;
import static com.example.kinoboom.app.AppController.getAppComponent;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;


public class MainActivity extends AppCompatActivity implements FilmListContract.View {

    @Inject
    public FilmViewModal filmViewModal;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recViewSongs)
    RecyclerView adapter;

    private List<Film> filmList;
    private FilmPresenter presenter;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAppComponent().inject(this);
        initialView();
    }

    public void initialView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        filmList = new ArrayList<>();
        presenter = new FilmPresenter(filmViewModal, this);
        presenter.onViewCreated();
    }

    @Override
    public void progressBarVisible() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void progressBarGone() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayingAdapter(FilmModal filmModal) {
        recyclerAdapter = new RecyclerAdapter(filmList,
                (film)-> {
                        presenter.onFilmClicked(film);
                },
                (film,position)-> {
                        presenter.onFilmLongClicked(film,position);
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter.setLayoutManager(layoutManager);
        adapter.setAdapter(recyclerAdapter);
    }

    @Override
    public void addData(FilmModal filmModal) {
        for (int i = 0; i < filmModal.getResults().size(); i++) {
            FilmModal.Result filmResult = filmModal.getResults().get(i);
            filmList.add(new Film(filmResult.getPosterPath(),
                    filmResult.getTitle(),
                    filmResult.getPopularity(),
                    filmResult.getReleaseDate(),
                    filmResult.getOverview()));
        }
    }

    @Override
    public void error(String error) {
        Toasty.error(this, "Error: " + error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void aboutFilmFragment(Film film) {
        DetailFragment myObj = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("params", film.getOverview());
        myObj.setArguments(bundle);
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.listFragment, myObj).
                addToBackStack(null).
                commit();
    }

    @Override
    public void deleteItemDialog(Film film,int position) {
        new AlertDialog.Builder(this)
                .setTitle(film.title)
                .setMessage("Delete this item ?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    filmList.remove(film);
                    recyclerAdapter.notifyItemRemoved(position);
                })
                .setNegativeButton(android.R.string.no, (dialog, which) -> {
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}