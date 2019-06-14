package com.example.chocofood.data.entity;

import com.google.gson.annotations.SerializedName;

public class CategoryEntity {

    @SerializedName("id")
    private int mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("slug")
    private String mSlug;

    public CategoryEntity() {
    }

    public CategoryEntity(int id, String title, String slug) {
        mId = id;
        mTitle = title;
        mSlug = slug;
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

    public String getSlug() {
        return mSlug;
    }

    public void setSlug(String slug) {
        mSlug = slug;
    }
}
