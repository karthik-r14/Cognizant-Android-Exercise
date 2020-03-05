package com.cognizant_android_exercise.model;

import com.google.gson.annotations.SerializedName;

public class NewsItem {
    @SerializedName("title")
    String title;
    @SerializedName("description")
    String description;
    @SerializedName("imageHref")
    String imageUrl;

    public NewsItem(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}