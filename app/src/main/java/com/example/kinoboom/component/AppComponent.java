package com.example.kinoboom.component;

import com.example.kinoboom.view.MainActivity;
import com.example.kinoboom.request.FilmServiceModule;
import dagger.Component;
import javax.inject.Singleton;



@Component(modules = {FilmServiceModule.class})
@Singleton
public interface AppComponent {
    void inject(MainActivity mainActivity);
}