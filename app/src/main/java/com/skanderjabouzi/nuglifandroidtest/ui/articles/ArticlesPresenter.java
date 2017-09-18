package com.skanderjabouzi.nuglifandroidtest.ui.articles;

import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skanderjabouzi.nuglifandroidtest.app.Configs;
import com.skanderjabouzi.nuglifandroidtest.app.NuglifApplication;
import com.skanderjabouzi.nuglifandroidtest.helper.AsyncTaskHelper;
import com.skanderjabouzi.nuglifandroidtest.location.LocationWrapper;
import com.skanderjabouzi.nuglifandroidtest.model.ArticlesItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class ArticlesPresenter implements ArticlesPresenterInterface {
    private ArticlesView articlesView;
    AsyncTaskHelper myAsyncTask;
    LocationWrapper locationWrapper;

    @Override
    public void setView(ArticlesView view) {
        articlesView = view;
    }

    @Override
    public List<ArticlesItem> getArticles(InputStream inputStream) {
        articlesView.showLoading();

        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            Type listType = new TypeToken<List<ArticlesItem>>() {
            }.getType();
            articlesView.hideLoading();
            return new Gson().fromJson(reader, listType);
        } catch (final Exception e) {
            articlesView.showErrorMessage();
            throw new RuntimeException(e);
        }
    }

    public void getLocation() {
        myAsyncTask = new AsyncTaskHelper();
        NuglifApplication app = NuglifApplication.getApplication();
        locationWrapper = new LocationWrapper(app.getApplicationContext());
        myAsyncTask.execute(new AsyncTaskHelper.DoSomething() {
            @Override
            public void doItInBackground() {
                Looper.prepare();
                while(locationWrapper.gotLocation() == false){
                }
            }

            @Override
            public void doItPostExecute() {
                articlesView.showLocationInfo(locationWrapper.getLat(), locationWrapper.getLong(), Configs.LOWEST_LATITUDE_CANADA);
            }
        });
    }
}
