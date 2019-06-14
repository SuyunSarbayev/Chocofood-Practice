package com.example.chocofood.presentation.main;


import com.example.chocofood.domain.model.Banner;
import com.example.chocofood.domain.model.Category;
import com.example.chocofood.domain.model.Restaurant;
import com.example.chocofood.domain.model.RestaurantFilteredRequest;
import com.example.chocofood.domain.usecase.GetBannerListUseCase;
import com.example.chocofood.domain.usecase.GetCategoryListUseCase;
import com.example.chocofood.domain.usecase.GetRestaurantListByFilterUseCase;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class MainPresenter {

    private MainView mMainView;

    private final GetRestaurantListByFilterUseCase mGetRestaurantListByFilterUseCase;

    private final GetBannerListUseCase mGetBannerListUseCase;

    private final GetCategoryListUseCase mGetCategoryListUseCase;

    public MainPresenter(GetRestaurantListByFilterUseCase getRestaurantListByFilterUseCase,
                         GetBannerListUseCase getBannerListUseCase,
                         GetCategoryListUseCase getCategoryListUseCase) {
        mGetRestaurantListByFilterUseCase = getRestaurantListByFilterUseCase;
        mGetBannerListUseCase = getBannerListUseCase;
        mGetCategoryListUseCase = getCategoryListUseCase;
    }

    public void setMainView(MainView mainView) {
        mMainView = mainView;
    }

    public void getRestaurantListByFilter(RestaurantFilteredRequest restaurantFilteredRequest) {
        mGetRestaurantListByFilterUseCase.execute(getRestaurantListObserver(), restaurantFilteredRequest);
    }

    public void getBannerList() {
        mGetBannerListUseCase.execute(getBannerListObserver(), null);
    }

    public void getCategoryList() {
        mGetCategoryListUseCase.execute(getCategoryListObserver(), null);
    }

    private DisposableObserver<List<Restaurant>> getRestaurantListObserver() {
        return new DisposableObserver<List<Restaurant>>() {
            @Override
            public void onNext(List<Restaurant> restaurants) {
                mMainView.displayRestaurants(restaurants);
            }

            @Override
            public void onError(Throwable e) {
                mMainView.displayError(e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }

    private DisposableObserver<List<Banner>> getBannerListObserver() {
        return new DisposableObserver<List<Banner>>() {
            @Override
            public void onNext(List<Banner> banners) {
                mMainView.displayBanners(banners);
            }

            @Override
            public void onError(Throwable e) {
                mMainView.displayError(e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }

    private DisposableObserver<List<Category>> getCategoryListObserver() {
        return new DisposableObserver<List<Category>>() {
            @Override
            public void onNext(List<Category> categories) {
                mMainView.displayCategories(categories);
            }

            @Override
            public void onError(Throwable e) {
                mMainView.displayError(e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }
}
