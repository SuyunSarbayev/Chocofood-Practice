package com.example.chocofood.domain.usecase;

import com.example.chocofood.domain.model.Restaurant;
import com.example.chocofood.domain.model.RestaurantFilteredRequest;
import com.example.chocofood.domain.repository.RestaurantRepository;

import java.util.List;

import io.reactivex.Observable;

public class GetRestaurantListByFilterUseCase extends UseCase<List<Restaurant>, RestaurantFilteredRequest> {

    private final RestaurantRepository mRestaurantRepository;

    public GetRestaurantListByFilterUseCase(RestaurantRepository restaurantRepository) {
        mRestaurantRepository = restaurantRepository;
    }

    @Override
    Observable<List<Restaurant>> createObservable(RestaurantFilteredRequest restaurantFilteredRequest) {
        return mRestaurantRepository.getRestaurantsByFilter(restaurantFilteredRequest);
    }
}
