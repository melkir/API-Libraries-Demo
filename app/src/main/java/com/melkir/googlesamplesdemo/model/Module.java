package com.melkir.googlesamplesdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Module implements Parcelable {
    private String mTitle, mDescription, mLink, mAction;
    private String[] mCategories;
    private int mPictureRsc;

    public Module(String title, String description, String link, String action,
                  String[] categories, int pictureRsc) {
        this.mTitle = title;
        this.mDescription = description;
        this.mLink = link;
        this.mAction = action;
        this.mCategories = categories;
        this.mPictureRsc = pictureRsc;
    }

    private Module(Parcel in) {
        mTitle = in.readString();
        mDescription = in.readString();
        mLink = in.readString();
        mAction = in.readString();
        mCategories = in.createStringArray();
        mPictureRsc = in.readInt();
    }

    public static final Creator<Module> CREATOR = new Creator<Module>() {
        @Override
        public Module createFromParcel(Parcel in) {
            return new Module(in);
        }

        @Override
        public Module[] newArray(int size) {
            return new Module[size];
        }
    };

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getLink() {
        return mLink;
    }

    public String getAction() {
        return mAction;
    }

    public int getPicture() {
        return mPictureRsc;
    }

    public String[] getCategories() {
        return mCategories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mDescription);
        parcel.writeString(mLink);
        parcel.writeString(mAction);
        parcel.writeStringArray(mCategories);
        parcel.writeInt(mPictureRsc);
    }
}
