package com.example.kinoboom.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.kinoboom.R;
import com.example.kinoboom.fragmentDetail.DetailFragment;
import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;
import com.example.kinoboom.presenter.Presenter;
import com.example.kinoboom.presenter.PresenterInterface;
import com.example.kinoboom.recyclerAdapter.RecyclerAdapter;
import com.example.kinoboom.viewModal.FilmViewModal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import static com.example.kinoboom.app.AppController.getAppComponent;


public class MainActivity extends AppCompatActivity implements PresenterInterface {



    @Inject
    public FilmViewModal filmViewModal;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recViewSongs )
    RecyclerView adapter;

    private List<Film> filmList;
    private Presenter presenter;
    private RecyclerAdapter recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAppComponent().inject(this);
        initialView();
        presenter = new Presenter(filmViewModal, this,this);
        presenter.getDataList();
    }

    public void initialView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        filmList = new ArrayList<>();
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
        addData(filmModal);
        recyclerAdapter = new RecyclerAdapter(this, filmList, new OnItemClickListener(), new OnLongClickListener());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter.setLayoutManager(layoutManager);
        adapter.setAdapter(recyclerAdapter);

    }

    @Override
    public void error(String error) {
        Toasty.error(this, "Error: " + error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void addData(FilmModal filmModal) {
        presenter.addData(filmModal);
    }

    @Override
    public void setDataResult(FilmModal.Result filmResult) {
        filmList.add(new Film(filmResult.getPosterPath(), filmResult.getTitle(), filmResult.getPopularity(), filmResult.getReleaseDate(), filmResult.getOverview()));
    }

    @Override
    public void onClickView(Film film) {
        presenter.onClick(film);
    }

    @Override
    public void startFragments(DetailFragment myObj) {
        this.getSupportFragmentManager().beginTransaction().replace(R.id.listFragment, myObj).addToBackStack(null).commit();
    }

    @Override
    public void onLongClickView(Film film) {
        presenter.onLongClick(film);
    }

    class OnItemClickListener implements RecyclerAdapter.OnItemClickListener {
        @Override
        public void onClick(Film film) {
            onClickView(film);
        }
    }

    class OnLongClickListener implements RecyclerAdapter.OnItemLongClickListener{
        @Override
        public void onLongClick(Film film) {
            onLongClickView(film);
        }
    }

    @Override
    public void getAlertDialog(Film film) {
            filmList.remove(film);
            recyclerAdapter.notifyDataSetChanged();
    }
}
