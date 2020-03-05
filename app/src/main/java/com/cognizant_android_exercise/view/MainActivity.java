package com.cognizant_android_exercise.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cognizant_android_exercise.R;
import com.cognizant_android_exercise.presenter.MainPresenter;
import com.cognizant_android_exercise.util.InternetUtil;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter(this);
    }

    @OnClick(R.id.refresh_button)
    public void onRefreshButtonClick(View view) {
        InternetUtil internetUtil = new InternetUtil(getApplicationContext());
        internetUtil.connectivityAvailable();

        presenter.onRefreshButtonClick(internetUtil.isInternetConnectivity());
    }

    @Override
    public void showNoInternetToastMessage() {
        Toast.makeText(getApplicationContext(), R.string.no_internet_toast_msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadDataFromRestService() {

    }
}
