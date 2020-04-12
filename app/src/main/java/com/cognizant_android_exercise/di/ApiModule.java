package com.cognizant_android_exercise.di;

import com.cognizant_android_exercise.rest.NewsItemApiService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    public static final String BASE_URL = "https://dl.dropboxusercontent.com/";

    @Provides
    public NewsItemApiService providesNewsItemApiService() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsItemApiService.class);
    }
}
