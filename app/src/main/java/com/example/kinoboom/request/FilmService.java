package com.example.kinoboom.request;

import com.example.kinoboom.modal.FilmModal;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface FilmService {
    @GET()
    Observable<FilmModal> nowPlaying(@Url String url);
}

