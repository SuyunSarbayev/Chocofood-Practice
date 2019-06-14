package com.example.chocofood.domain.model;

public class Category {

    private int mId;

    private String mTitle;

    private String mSlug;

    public Category() {
    }

    public Category(int id, String title, String slug) {
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
