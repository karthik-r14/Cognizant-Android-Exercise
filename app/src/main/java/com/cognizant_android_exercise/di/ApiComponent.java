package com.cognizant_android_exercise.di;

import com.cognizant_android_exercise.view.MainActivity;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {
    void inject(MainActivity mainActivity);
}
