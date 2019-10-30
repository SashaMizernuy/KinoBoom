package com.example.kinoboom.viewModal;


import android.content.Context;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableInt;
import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;
import com.example.kinoboom.app.AppController;
import com.example.kinoboom.request.FilmService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import javax.inject.Inject;
import es.dmoral.toasty.Toasty;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class FilmViewModal extends Observable {
    public ObservableInt progressBar;
    private Context context;
    public static List<Film> film;



    @Inject
    FilmService filmService;

    public FilmViewModal(@NonNull Context context) {
        this.context=context;
        progressBar = new ObservableInt(View.GONE);
        film=new ArrayList<>();

    }

    public void getFilm(){
        progressBar.set(View.VISIBLE);

        final AppController appController = AppController.create(context);

        appController.getAppComponent().inject(this);
        Disposable disposable = filmService.nowPlaying("3/movie/now_playing?api_key=330be44bd0d082bcdb4f061a051757cb")
                .subscribeOn(appController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable ->Toasty.error(context, "Error: "+throwable.getMessage(), Toast.LENGTH_LONG).show() )
                .subscribe(new Consumer<FilmModal>() {
                    @Override
                    public void accept(FilmModal filmModal) throws Exception {
                            Collections.sort(filmModal.getResults(), new Comparator<FilmModal.Result>() {
                                @Override
                                public int compare(FilmModal.Result result, FilmModal.Result t1) {
                                    return result.getTitle().compareTo(t1.getTitle());
                                }
                            });

                            for (int i = 0; i < filmModal.getResults().size(); i++) {
                                FilmModal.Result filmResult = filmModal.getResults().get(i);
                                film.add(new Film(filmResult.getPosterPath(), filmResult.getTitle(), filmResult.getPopularity(), filmResult.getReleaseDate(), filmResult.getOverview()));

                            }
                            setChanged();
                            notifyObservers();
                            progressBar.set(View.GONE);
                        }

                }, new Consumer<Throwable>() {
                    @Override public void accept(Throwable throwable) throws Exception {
                    }
                });

    }
    public List<Film> getFilmList() {
        return film;
    }
}



