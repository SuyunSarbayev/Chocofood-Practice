package com.example.chocofood.main;

import com.example.chocofood.models.Banner;
import com.example.chocofood.models.Restaurant;

import java.util.List;

public interface MainView {

    void showRestaurants(List<Restaurant> restaurantList);

    void showBanner(Banner banner);

    void showError(String errorMessage);
}
