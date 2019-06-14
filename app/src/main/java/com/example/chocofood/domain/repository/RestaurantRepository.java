package com.example.chocofood.domain.repository;

import com.example.chocofood.domain.model.Restaurant;
import com.example.chocofood.domain.model.RestaurantFilteredRequest;

import java.util.List;

import io.reactivex.Observable;

public interface RestaurantRepository {

    Observable<List<Restaurant>> getRestaurants();

    Observable<List<Restaurant>> getRestaurantsByFilter(RestaurantFilteredRequest restaurantFilteredRequest);
}
