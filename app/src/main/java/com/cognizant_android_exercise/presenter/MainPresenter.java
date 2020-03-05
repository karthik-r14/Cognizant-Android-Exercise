package com.cognizant_android_exercise.presenter;

import com.cognizant_android_exercise.view.MainView;

public class MainPresenter {
    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void onRefreshButtonClick(boolean internetConnectivity) {
        if(!internetConnectivity) {
            view.showNoInternetToastMessage();
        }
    }
}
