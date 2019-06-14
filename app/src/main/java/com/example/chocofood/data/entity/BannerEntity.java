package com.example.chocofood.data.entity;

import com.google.gson.annotations.SerializedName;

public class BannerEntity {

    @SerializedName("id")
    private int mId;

    @SerializedName("banner")
    private String mImageUrl;

    @SerializedName("title")
    private String mTitle;

    public BannerEntity() {
    }

    public BannerEntity(int id, String imageUrl, String title) {
        mId = id;
        mImageUrl = imageUrl;
        mTitle = title;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
