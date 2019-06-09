package com.example.chocofood.main;

import android.util.Log;

import com.example.chocofood.models.Banner;
import com.example.chocofood.models.Restaurant;
import com.example.chocofood.network.NetworkClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    private MainView mMainView;

    private final static String TAG = "MainPresenter";

    public MainPresenter(MainView mainView) {
        mMainView = mainView;
    }

    public void getRestaurantsByFilter(String filter, int limit, int offset) {
        getRestaurantsObservable(filter, limit, offset).subscribeWith(getRestaurantsObserver());
    }

    public void getBanner() {
        getBannerObservable().subscribeWith(getBannerObserver());
    }

    private Observable<List<Restaurant>> getRestaurantsObservable(String filter, int limit, int offset) {
        return NetworkClient.getInstance()
                .getNetworkApi()
                .getRestaurantsByFilter(filter, limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Observable<List<Banner>> getBannerObservable() {
        return NetworkClient.getInstance()
                .getNetworkApi()
                .getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<List<Restaurant>> getRestaurantsObserver() {
        return new DisposableObserver<List<Restaurant>>() {
            @Override
            public void onNext(List<Restaurant> restaurantList) {
                Log.d(TAG,"onNext");
                mMainView.showRestaurants(restaurantList);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError" + e);
                mMainView.showError("Unable to fetch data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
            }
        };
    }

    private DisposableObserver<List<Banner>> getBannerObserver() {
        return new DisposableObserver<List<Banner>>() {
            @Override
            public void onNext(List<Banner> banners) {
                Log.d(TAG,"onSuccess");
                mMainView.showBanner(banners.get(0));
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"onError" + e);
                mMainView.showError("Unable to fetch data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
            }
        };
    }

}
