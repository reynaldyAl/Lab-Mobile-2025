package com.example.pertemuan_3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Story implements Parcelable {
    private User user;
    private String title;
    private String time;
    private int StoryImage;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public int getStoryImage() {
        return StoryImage;
    }

    public void setStoryImage(int storyImage) {
        StoryImage = storyImage;
    }

    public Story(User user, String title, String time, int storyImage) {
        this.user = user;
        this.title = title;
        this.time = time;
        StoryImage = storyImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.title);
        dest.writeString(this.time);
        dest.writeInt(this.StoryImage);
    }

    protected Story(Parcel in) {
        user = in.readParcelable(User.class.getClassLoader());
        title = in.readString();
        time = in.readString();
        StoryImage = in.readInt();
    }

    public static final Creator<Story> CREATOR = new Creator<Story>() {
        @Override
        public Story createFromParcel(Parcel in) {
            return new Story(in);
        }

        @Override
        public Story[] newArray(int size) {
            return new Story[size];
        }
    };
}
