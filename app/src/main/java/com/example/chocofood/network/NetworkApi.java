package com.example.chocofood.network;

import com.example.chocofood.models.Banner;
import com.example.chocofood.models.Restaurant;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkApi {

    @GET("restaurants/")
    Observable<List<Restaurant>> getRestaurantsByFilter(
            @Query("sort_by") String filter,
            @Query("limit") int limit,
            @Query("offset") int offset);

    @GET("ads/banner/main-slider/?image_size=xxhdpi")
    Observable<List<Banner>> getBanner();
}
