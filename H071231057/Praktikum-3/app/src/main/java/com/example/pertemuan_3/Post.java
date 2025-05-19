package com.example.pertemuan_3;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Post implements Parcelable {

    private User user;
    private String caption;
    private String time;
    private String like;
    private String comment;
    private String share;
    private int postImage;
    private Uri postImageUri;

    public Post(User user, String caption, String time, String like, String comment, String share, int postImage) {
        this.user = user;
        this.caption = caption;
        this.time = time;
        this.like = like;
        this.comment = comment;
        this.share = share;
        this.postImage = postImage;
    }

    public Post(User user, String caption, String time, Uri imageUri) {
        this.user = user;
        this.caption = caption;
        this.time = time;
        this.postImageUri = imageUri;
        this.like = "0";
        this.comment = "0";
        this.share = "0";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }
    public boolean isFromUser() {
        return postImageUri != null;
    }
    public Uri getPostImageUri() {
        return postImageUri;
    }

    public void setPostImageUri(Uri postImageUri) {
        this.postImageUri = postImageUri;
    }

    protected Post(Parcel in) {
        user = in.readParcelable(User.class.getClassLoader());
        caption = in.readString();
        time = in.readString();
        like = in.readString();
        comment = in.readString();
        share = in.readString();
        postImage = in.readInt();
        postImageUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(this.user, flags);
        dest.writeString(caption);
        dest.writeString(time);
        dest.writeString(like);
        dest.writeString(comment);
        dest.writeString(share);
        dest.writeInt(postImage);
        dest.writeParcelable(this.postImageUri, flags);
    }
}
