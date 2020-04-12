package com.cognizant_android_exercise.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cognizant_android_exercise.R;
import com.cognizant_android_exercise.adapter.RecyclerViewAdapter;
import com.cognizant_android_exercise.di.DaggerApiComponent;
import com.cognizant_android_exercise.model.NewsItem;
import com.cognizant_android_exercise.model.NewsItemResponse;
import com.cognizant_android_exercise.presenter.MainPresenter;
import com.cognizant_android_exercise.rest.NewsItemApiService;
import com.cognizant_android_exercise.util.InternetUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    public NewsItemApiService newsItemApiService;

    private MainPresenter presenter;
    private List<NewsItem> newsItemsForRecyclerView;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        newsItemsForRecyclerView = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        presenter = new MainPresenter(this);

        DaggerApiComponent.create().inject(this);
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
        Call<NewsItemResponse> call = newsItemApiService.getNewItems();
        call.enqueue(new Callback<NewsItemResponse>() {
            @Override
            public void onResponse(Call<NewsItemResponse> call, Response<NewsItemResponse> response) {
                newsItemsForRecyclerView = new ArrayList<>();
                List<NewsItem> newsItems = response.body().getNewsItems();
                String newsTitle = response.body().getTitle();
//                Toast.makeText(getApplicationContext(), newsTitle, Toast.LENGTH_LONG).show();
                setActionBarTitle(newsTitle);

                for (NewsItem newsItem : newsItems) {
                    //Toast used for printing API data to screen.
//                    Toast.makeText(getApplicationContext(), newsItem.getTitle() + ":" + newsItem.getDescription() + ":" + newsItem.getImageUrl(), Toast.LENGTH_LONG).show();
                    newsItemsForRecyclerView.add(new NewsItem(newsItem.getTitle(), newsItem.getDescription(), newsItem.getImageUrl()));
                }
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(newsItemsForRecyclerView, getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<NewsItemResponse> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Failed to load data from API", Toast.LENGTH_LONG).show();
                Log.e(TAG, throwable.toString());
            }
        });
    }

    private void setActionBarTitle(String newsTitle) {
        androidx.appcompat.app.ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle(newsTitle);
    }
}
