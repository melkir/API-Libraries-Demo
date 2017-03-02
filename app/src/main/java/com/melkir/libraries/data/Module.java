package com.melkir.libraries.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.melkir.libraries.util.ActivityLauncher;

public class Module implements Parcelable, SortedListAdapter.ViewModel {
    private final String mTitle, mDescription, mLink, mAction;
    private final String[] mCategories;
    private final int mPictureRsc;
    private final int mId;

    public Module() {
        this.mId = 0;
        this.mTitle = "title";
        this.mDescription = "description";
        this.mCategories = new String[]{"test"};
        this.mPictureRsc = 0;
        this.mAction = null;
        this.mLink = null;
    }

    public Module(int id, String title, String description, String link, String action,
                  String[] categories, int pictureRsc) {
        this.mId = id;
        this.mTitle = title;
        this.mDescription = description;
        this.mLink = link;
        this.mAction = action;
        this.mCategories = categories;
        this.mPictureRsc = pictureRsc;
    }

    private Module(Parcel in) {
        mId = in.readInt();
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

    public int getId() {
        return mId;
    }

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
        parcel.writeInt(mId);
        parcel.writeString(mTitle);
        parcel.writeString(mDescription);
        parcel.writeString(mLink);
        parcel.writeString(mAction);
        parcel.writeStringArray(mCategories);
        parcel.writeInt(mPictureRsc);
    }

    public void onLaunchClick(View view, String action) {
        ActivityLauncher.start(view.getContext(), action);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return mId == module.mId && mTitle.equals(module.mTitle);
    }

    @Override
    public int hashCode() {
        int result = mTitle.hashCode();
        result = 31 * result + mId;
        return result;
    }

}
