package com.cognizant_android_exercise.view;

import android.os.Bundle;
import android.view.View;

import com.cognizant_android_exercise.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.refresh_button)
    public void onRefreshButtonClick(View view) {

    }
}
