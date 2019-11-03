package com.example.kinoboom.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.example.kinoboom.fragmentDetail.DetailFragment;
import com.example.kinoboom.modal.Film;
import com.example.kinoboom.modal.FilmModal;
import com.example.kinoboom.viewModal.FilmViewModal;


public class Presenter {

    private final FilmViewModal filmViewModal;
    private final PresenterInterface presenterInterface;
    private  final Context context;


    public Presenter(FilmViewModal filmViewModal, PresenterInterface presenterInterface,Context context) {
        this.filmViewModal=filmViewModal;
        this.presenterInterface=presenterInterface;
        this.context=context;
    }


    public void getDataList(){
        presenterInterface.progressBarVisible();
        filmViewModal.nowPlaying(new FilmViewModal.CallbackInterface() {
            @Override
            public void accept(FilmModal filmModal) {
                presenterInterface.getDataListAccept(filmModal);
                presenterInterface.progressBarGone();
            }
            @Override
            public void error(String error) {
                presenterInterface.error(error);
                presenterInterface.progressBarGone();


            }
        });
    }

    public void onClick(Film film){
        DetailFragment myObj = new DetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("params", film.getOverview());
                myObj.setArguments(bundle);
                presenterInterface.startFragments(myObj);
    }

    public void onLongClick(Film film){
        new AlertDialog.Builder(context)
                .setTitle(film.title)
                .setMessage("Delete this item ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        presenterInterface.getAlertDialog(film);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    public void addData(FilmModal filmModal){
        for (int i = 0; i < filmModal.getResults().size(); i++) {
            FilmModal.Result filmResult = filmModal.getResults().get(i);
            presenterInterface.setDataResult(filmResult);
        }
    }
}
