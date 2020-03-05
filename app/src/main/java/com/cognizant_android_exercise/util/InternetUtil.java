package com.cognizant_android_exercise.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class InternetUtil {
    private boolean internetConnectivity;
    private Context context;

    public InternetUtil(Context context) {
        this.context = context;
    }

    public void connectivityAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        internetConnectivity = activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public boolean isInternetConnectivity() {
        return internetConnectivity;
    }
}
