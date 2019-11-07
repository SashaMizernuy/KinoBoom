package com.example.kinoboom.request;

import com.example.kinoboom.modal.FilmModal;
import io.reactivex.Observable;
import retrofit2.http.GET;


public interface FilmService {
    @GET("3/movie/now_playing?api_key=330be44bd0d082bcdb4f061a051757cb")
    Observable<FilmModal> nowPlaying();
}

