package com.example.chocofood.domain.model;

public class RestaurantFilteredRequest {

    private String mFilter;

    private String mFoodType;

    private String mFavoriteMode;

    private int mLimit;

    private int mOffset;

    public RestaurantFilteredRequest() {
    }

    public RestaurantFilteredRequest(String filter, String foodType, String favoriteMode, int limit, int offset) {
        mFilter = filter;
        mFoodType = foodType;
        mFavoriteMode = favoriteMode;
        mLimit = limit;
        mOffset = offset;
    }

    public String getFilter() {
        return mFilter;
    }

    public void setFilter(String filter) {
        mFilter = filter;
    }

    public String getFoodType() {
        return mFoodType;
    }

    public void setFoodType(String foodType) {
        mFoodType = foodType;
    }

    public String getFavoriteMode() {
        return mFavoriteMode;
    }

    public void setFavoriteMode(String favoriteMode) {
        mFavoriteMode = favoriteMode;
    }

    public int getLimit() {
        return mLimit;
    }

    public void setLimit(int limit) {
        mLimit = limit;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int offset) {
        mOffset = offset;
    }
}
