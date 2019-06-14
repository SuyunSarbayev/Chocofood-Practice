package com.example.chocofood.domain.usecase;

import com.example.chocofood.domain.repository.RestaurantRepository;
import com.example.chocofood.domain.model.Restaurant;

import java.util.List;

import io.reactivex.Observable;

public class GetRestaurantListUseCase extends UseCase<List<Restaurant>, Void> {

    private final RestaurantRepository mRestaurantRepository;

    public GetRestaurantListUseCase(RestaurantRepository restaurantRepository) {
        mRestaurantRepository = restaurantRepository;
    }

    @Override
    Observable<List<Restaurant>> createObservable(Void aVoid) {
        return mRestaurantRepository.getRestaurants();
    }
}
