package com.example.kinoboom.app;

import android.app.Application;
import android.content.Context;

import com.example.kinoboom.component.AppComponent;
import com.example.kinoboom.component.DaggerAppComponent;
import com.example.kinoboom.request.FilmService;
import com.example.kinoboom.request.FilmServiceModule;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class AppController extends Application {
    private FilmService filmService; //retrofit things
    private Scheduler scheduler; //Rx object
    private static AppComponent appComponent;

    private static AppController get(Context context){
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context) {
        return AppController.get(context);
    }


    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }


    public void setUserService(FilmService filmService) {
        this.filmService = filmService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildAppComponent();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public AppComponent buildAppComponent() {
        return DaggerAppComponent.builder().filmServiceModule(new FilmServiceModule()).build();
    }

}
