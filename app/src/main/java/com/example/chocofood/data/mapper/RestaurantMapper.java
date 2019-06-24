package com.example.chocofood.data.mapper;

import com.example.chocofood.data.entity.RestaurantEntity;
import com.example.chocofood.domain.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantMapper {

    public RestaurantMapper() {
    }

    public Restaurant transformRestaurant(RestaurantEntity restaurantEntity) {
        Restaurant restaurant = null;

        if (restaurantEntity != null) {
            restaurant = new Restaurant(restaurantEntity.getId(), restaurantEntity.getTitle(),
                    restaurantEntity.getCardImageUrl(), restaurantEntity.getCuisines(),
                    restaurantEntity.getLogoUrl(), restaurantEntity.getRating(),
                    restaurantEntity.getDeliveryTime(), restaurantEntity.getIsPromoted(),
                    restaurantEntity.getMinimumCost());

        }
        return restaurant;
    }

    public List<Restaurant> transformRestaurantList(List<RestaurantEntity> restaurantEntityList) {
        List<Restaurant> restaurantList = new ArrayList<>();

        for (RestaurantEntity restaurantEntity : restaurantEntityList) {
            if (restaurantEntity != null)
                restaurantList.add(transformRestaurant(restaurantEntity));
        }
        return restaurantList;
    }
}
