package com.example.chocofood.domain.model;

import com.google.gson.annotations.SerializedName;

public class Restaurant {

    private int mId;

    private String mTitle;

    private String mCardImageUrl;

    private String mCuisines;

    private String mLogoUrl;

    private Double mRating;

    private String mDeliveryTime;

    private int mIsPromoted;

    public Restaurant() {
    }

    public Restaurant(int id, String title, String cardImageUrl, String cuisines,
                      String logoUrl, Double rating, String deliveryTime, int isPromoted) {
        mId = id;
        mTitle = title;
        mCardImageUrl = cardImageUrl;
        mCuisines = cuisines;
        mLogoUrl = logoUrl;
        mRating = rating;
        mDeliveryTime = deliveryTime;
        mIsPromoted = isPromoted;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getCardImageUrl() {
        return mCardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        mCardImageUrl = cardImageUrl;
    }

    public String getCuisines() {
        return mCuisines;
    }

    public void setCuisines(String cuisines) {
        mCuisines = cuisines;
    }

    public String getLogoUrl() {
        return mLogoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        mLogoUrl = logoUrl;
    }

    public Double getRating() {
        return mRating;
    }

    public void setRating(Double rating) {
        mRating = rating;
    }

    public String getDeliveryTime() {
        return mDeliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        mDeliveryTime = deliveryTime;
    }

    public int getIsPromoted() {
        return mIsPromoted;
    }

    public void setIsPromoted(int isPromoted) {
        mIsPromoted = isPromoted;
    }
}
