package com.example.chocofood.data.network;

import com.example.chocofood.data.entity.BannerEntity;
import com.example.chocofood.data.entity.CategoryEntity;
import com.example.chocofood.data.entity.RestaurantEntity;
import com.example.chocofood.domain.model.Restaurant;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkApi {

    @GET(NetworkConstants.GET_RESTAURANTS)
    Observable<List<RestaurantEntity>> getRestaurants();

    @GET(NetworkConstants.GET_RESTAURANTS)
    Observable<List<RestaurantEntity>> getRestaurantsByFilter(
            @Query("sort_by") String filter,
            @Query("food_type") String foodType,
            @Query("only_favourites") String favoriteMode,
            @Query("limit") int limit,
            @Query("offset") int offset);

    @GET(NetworkConstants.GET_BANNER)
    Observable<List<BannerEntity>> getBanners();

    @GET(NetworkConstants.GET_CATEGORIES)
    Observable<List<CategoryEntity>> getCategories();
}
