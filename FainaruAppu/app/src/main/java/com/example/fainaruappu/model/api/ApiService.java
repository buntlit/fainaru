package com.example.fainaruappu.model.api;

import com.example.fainaruappu.model.entity.Photo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api")
    Observable<Photo> getPhoto(@Query("key") String key);
}
