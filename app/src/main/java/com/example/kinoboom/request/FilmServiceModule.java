package com.example.kinoboom.request;


import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class FilmServiceModule {

    @Provides
    @Singleton
    public FilmService filmService(Retrofit retrofit) {
        return retrofit.create(FilmService.class);
    }


    @Provides
    @Singleton
    public Retrofit retrofit(GsonConverterFactory gsonConverterFactory) {

        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public GsonConverterFactory gsonConverterFactory() {
        return GsonConverterFactory.create();
    }

}
