package com.example.tuprak40.models;

public class Book {
    private String id;
    private String title;
    private String author;
    private int publishYear;
    private String blurb;
    private String coverImage;
    private boolean isLiked;
    private String genre;
    private float rating;
    private String review;

    public Book(String id, String title, String author, int publishYear,
                String blurb, String coverImage, String genre, float rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.blurb = blurb;
        this.coverImage = coverImage;
        this.isLiked = false;
        this.genre = genre;
        this.rating = rating;
        this.review = "";
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublishYear() { return publishYear; }
    public String getBlurb() { return blurb; }
    public String getCoverImage() { return coverImage; }
    public boolean isLiked() { return isLiked; }
    public String getGenre() { return genre; }
    public float getRating() { return rating; }
    public String getReview() { return review; }

    public void setLiked(boolean liked) { isLiked = liked; }
    public void setReview(String review) { this.review = review; }
}