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

//    @Provides
//    @Singleton
//    OkHttpClient provideHttpClient() {
//        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
//        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
//
//        OkHttpClient.Builder client = new OkHttpClient.Builder();
//        client.addInterceptor(interceptor);
//        return client.build();
//    }


    @Provides
    @Singleton
    public Retrofit retrofit(GsonConverterFactory gsonConverterFactory) {
//        Retrofit retrofit = new Retrofit.Builder().baseUrl()
//                .addConverterFactory(gsonConverterFactory)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        return retrofit.create(NewsService.class);

        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(gsonConverterFactory)
                //.client(provideHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public GsonConverterFactory gsonConverterFactory() {
        return GsonConverterFactory.create();
    }

}
