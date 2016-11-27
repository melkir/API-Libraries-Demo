package com.melkir.googlesamplesdemo.model;

import android.graphics.drawable.Drawable;

public class Module {
    private String mTitle, mDescription, mLink, mCategory;
    private Drawable mPicture;

    public Module(String title, String description, String link, String category, Drawable picture) {
        this.mTitle = title;
        this.mDescription = description;
        this.mLink = link;
        this.mCategory = category;
        this.mPicture = picture;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String mLink) {
        this.mLink = mLink;
    }

    public Drawable getPicture() {
        return mPicture;
    }

    public void setPicture(Drawable mPicture) {
        this.mPicture = mPicture;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }

}
