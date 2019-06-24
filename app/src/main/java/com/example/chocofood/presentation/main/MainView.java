package com.example.chocofood.presentation.main;

import com.example.chocofood.domain.model.Banner;
import com.example.chocofood.domain.model.Category;
import com.example.chocofood.domain.model.Restaurant;

import java.util.List;

public interface MainView {

    void initPresenter();

    void initRepositories();

    void initMappers();

    void initUseCases();

    void displayRestaurants(List<Restaurant> restaurantList);

    void displayBanners(List<Banner> bannerList);

    void displayCategories(List<Category> categoryList);

    void displayError(String errorMessage);
}
