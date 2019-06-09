package com.example.chocofood.models;

import com.google.gson.annotations.SerializedName;

public class Restaurant {

    @SerializedName("id")
    private int mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("card_image_url")
    private String mCardImageUrl;

    @SerializedName("cuisines")
    private String mCuisines;

    public Restaurant() {
    }

    public Restaurant(int id, String title, String cardImageUrl, String cuisines) {
        mId = id;
        mTitle = title;
        mCardImageUrl = cardImageUrl;
        mCuisines = cuisines;
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
}
