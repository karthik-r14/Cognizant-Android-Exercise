package com.cognizant_android_exercise.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cognizant_android_exercise.R;
import com.cognizant_android_exercise.adapter.RecyclerViewAdapter;
import com.cognizant_android_exercise.model.NewsItem;
import com.cognizant_android_exercise.model.NewsItemResponse;
import com.cognizant_android_exercise.presenter.MainPresenter;
import com.cognizant_android_exercise.rest.NewsItemApiService;
import com.cognizant_android_exercise.util.InternetUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MainView {
    private static final String JSON_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json";
    private static final String BASE_URL = "https://dl.dropboxusercontent.com/";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MainPresenter presenter;
    private List<NewsItem> newsItemsForRecyclerView;
    private static Retrofit retrofit = null;
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
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        final NewsItemApiService newsItemApiService = retrofit.create(NewsItemApiService.class);
        Call<NewsItemResponse> call = newsItemApiService.getNewItems();
        call.enqueue(new Callback<NewsItemResponse>() {
            @Override
            public void onResponse(Call<NewsItemResponse> call, Response<NewsItemResponse> response) {
                newsItemsForRecyclerView = new ArrayList<>();
                List<NewsItem> newsItems = response.body().getNewsItems();

                for (NewsItem newsItem : newsItems) {
                    //Toast used for printing API data to screen.
//                    Toast.makeText(getApplicationContext(), newsItem.getTitle() + ":" + newsItem.getDescription() + ":" + newsItem.getImageUrl(), Toast.LENGTH_LONG).show();
                    newsItemsForRecyclerView.add(new NewsItem(newsItem.getTitle(), newsItem.getDescription(), newsItem.getImageUrl()));
                }
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(newsItemsForRecyclerView);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<NewsItemResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }
}
