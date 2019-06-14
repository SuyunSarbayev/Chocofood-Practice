package com.example.chocofood.data.repository;

import com.example.chocofood.data.mapper.RestaurantMapper;
import com.example.chocofood.data.entity.RestaurantEntity;
import com.example.chocofood.data.network.NetworkClient;
import com.example.chocofood.domain.model.Restaurant;
import com.example.chocofood.domain.model.RestaurantFilteredRequest;
import com.example.chocofood.domain.repository.RestaurantRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class RestaurantDataRepository implements RestaurantRepository {

    private final RestaurantMapper mRestaurantMapper;

    public RestaurantDataRepository(RestaurantMapper restaurantMapper) {
        mRestaurantMapper = restaurantMapper;
    }

    @Override
    public Observable<List<Restaurant>> getRestaurants() {
        return NetworkClient.getInstance()
                .getNetworkApi()
                .getRestaurants()
                .map(new Function<List<RestaurantEntity>, List<Restaurant>>() {
                    @Override
                    public List<Restaurant> apply(List<RestaurantEntity> restaurantEntities) throws Exception {
                        return mRestaurantMapper.transformRestaurantList(restaurantEntities);
                    }
                });

    }

    @Override
    public Observable<List<Restaurant>> getRestaurantsByFilter(RestaurantFilteredRequest restaurantFilteredRequest) {
        return NetworkClient.getInstance()
                .getNetworkApi()
                .getRestaurantsByFilter(restaurantFilteredRequest.getFilter(),
                        restaurantFilteredRequest.getFoodType(),
                        restaurantFilteredRequest.getFavoriteMode(),
                        restaurantFilteredRequest.getLimit(),
                        restaurantFilteredRequest.getOffset())
                .map(new Function<List<RestaurantEntity>, List<Restaurant>>() {
                    @Override
                    public List<Restaurant> apply(List<RestaurantEntity> restaurantEntities) throws Exception {
                        return mRestaurantMapper.transformRestaurantList(restaurantEntities);
                    }
                });
    }
}
