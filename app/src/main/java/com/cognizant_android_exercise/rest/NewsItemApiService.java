package com.cognizant_android_exercise.rest;

import com.cognizant_android_exercise.model.NewsItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsItemApiService {
    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<NewsItemResponse> getNewItems();
}
