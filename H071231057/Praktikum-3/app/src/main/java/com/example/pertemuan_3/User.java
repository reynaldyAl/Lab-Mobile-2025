package com.example.pertemuan_3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    private String username;
    private String name;
    private String followers;
    private String following;
    private String career;
    private String bio;
    private String link;
    private int profileImage;

    public User(String username, String name, String followers, String following, String career, String bio, String link, int profileImage) {
        this.username = username;
        this.name = name;
        this.followers = followers;
        this.following = following;
        this.career = career;
        this.bio = bio;
        this.link = link;
        this.profileImage = profileImage;
    }

    protected User(Parcel in) {
        username = in.readString();
        name = in.readString();
        followers = in.readString();
        following = in.readString();
        career = in.readString();
        bio = in.readString();
        link = in.readString();
        profileImage = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(followers);
        dest.writeString(following);
        dest.writeString(career);
        dest.writeString(bio);
        dest.writeString(link);
        dest.writeInt(profileImage);
    }
}
