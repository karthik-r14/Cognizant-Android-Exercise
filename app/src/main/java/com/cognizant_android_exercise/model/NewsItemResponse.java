package com.cognizant_android_exercise.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsItemResponse {
    @SerializedName("rows")
    private List<NewsItem> newsItems;

    @SerializedName("title")
    private String title;

    public String getTitle() {
        return title;
    }

    public List<NewsItem> getNewsItems() {
        return newsItems;
    }

    public void setNewsItems(List<NewsItem> newsItems) {
        this.newsItems = newsItems;
    }
}
