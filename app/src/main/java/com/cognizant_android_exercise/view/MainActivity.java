package com.cognizant_android_exercise.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cognizant_android_exercise.R;
import com.cognizant_android_exercise.adapter.RecyclerViewAdapter;
import com.cognizant_android_exercise.model.NewsItem;
import com.cognizant_android_exercise.presenter.MainPresenter;
import com.cognizant_android_exercise.util.InternetUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {
    private static final String JSON_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MainPresenter presenter;
    private List<NewsItem> newsItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        newsItems = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

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
        loadData();
    }

    private void loadData() {
        newsItems.add(new NewsItem("Title 1", "Description1", "Image url1"));
        newsItems.add(new NewsItem("Title 2", "Description2", "Image url2"));
        newsItems.add(new NewsItem("Title 3", "Description3", "Image url3"));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(newsItems);
        recyclerView.setAdapter(adapter);
    }
}
