package com.example.kinoboom.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import com.example.kinoboom.R;
import com.example.kinoboom.fragmentDetail.DetailFragment;
import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;
import com.example.kinoboom.presenter.FilmListContract;
import com.example.kinoboom.presenter.FilmPresenter;
import com.example.kinoboom.recyclerAdapter.RecyclerAdapter;
import com.example.kinoboom.viewModal.FilmViewModal;
import static com.example.kinoboom.app.AppController.getAppComponent;
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
    private Context context;

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
        presenter.getDataList();
        context = this;
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
    public void getDataListAccept(FilmModal filmModal) {
        recyclerAdapter = new RecyclerAdapter(this, filmList,
                new OnItemClickListener(),
                new OnLongClickListener());
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

    class OnItemClickListener implements RecyclerAdapter.OnItemClickListener {
        @Override
        public void onClick(Film film) {
            DetailFragment myObj = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("params", film.getOverview());
            myObj.setArguments(bundle);
            presenter.onClickView(film, myObj);
        }
    }

    @Override
    public void startFragments(DetailFragment myObj) {
        this.getSupportFragmentManager().
                beginTransaction().
                replace(R.id.listFragment, myObj).
                addToBackStack(null).
                commit();
    }

    class OnLongClickListener implements RecyclerAdapter.OnItemLongClickListener {
        @Override
        public void onLongClick(Film film) {
            new AlertDialog.Builder(context)
                    .setTitle(film.title)
                    .setMessage("Delete this item ?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.onLongClickView(film);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    @Override
    public void getAlertDialog(Film film) {
        filmList.remove(film);
        recyclerAdapter.notifyDataSetChanged();
    }
}