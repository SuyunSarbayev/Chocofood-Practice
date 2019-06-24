package com.example.chocofood.data.entity;

import com.google.gson.annotations.SerializedName;

public class RestaurantEntity {

    @SerializedName("id")
    private int mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("card_image_url")
    private String mCardImageUrl;

    @SerializedName("cuisines")
    private String mCuisines;

    @SerializedName("logo")
    private String mLogoUrl;

    @SerializedName("rating")
    private Double mRating;

    @SerializedName("delivery_time")
    private String mDeliveryTime;

    @SerializedName("promotions")
    private int mIsPromoted;

    @SerializedName("cost_min")
    private int mMinimumCost;

    public RestaurantEntity() {
    }

    public RestaurantEntity(int id, String title, String cardImageUrl, String cuisines,
                            String logoUrl, Double rating, String deliveryTime, int isPromoted, int minimumCost) {
        mId = id;
        mTitle = title;
        mCardImageUrl = cardImageUrl;
        mCuisines = cuisines;
        mLogoUrl = logoUrl;
        mRating = rating;
        mDeliveryTime = deliveryTime;
        mIsPromoted = isPromoted;
        mMinimumCost = minimumCost;
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

    public int getMinimumCost() {
        return mMinimumCost;
    }

    public void setMinimumCost(int minimumCost) {
        mMinimumCost = minimumCost;
    }
}
