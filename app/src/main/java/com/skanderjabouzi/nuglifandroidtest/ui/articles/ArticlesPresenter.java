package com.skanderjabouzi.nuglifandroidtest.ui.articles;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skanderjabouzi.nuglifandroidtest.app.Configs;
import com.skanderjabouzi.nuglifandroidtest.app.NuglifApplication;
import com.skanderjabouzi.nuglifandroidtest.helper.AsyncTaskHelper;
import com.skanderjabouzi.nuglifandroidtest.location.LocationWrapper;
import com.skanderjabouzi.nuglifandroidtest.model.ArticlesItem;
import com.skanderjabouzi.nuglifandroidtest.model.MyLocation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Date;
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

    @Override
    public void saveLocation(SharedPreferences.Editor editor, Context context, double latitude, double longitude) {
        Date date = new Date(System.currentTimeMillis());
        editor.putFloat("latitude", (float) latitude);
        editor.putFloat("longitude", (float) longitude);
        editor.putFloat("time", (float) date.getTime());
        editor.commit();
    }

    @Override
    public void getSavedLocation(MyLocation myLocation, SharedPreferences pref) {
        myLocation.setLatitude(pref.getFloat("latitude", 0));
        myLocation.setLongitude(pref.getFloat("longitude", 0));
        myLocation.setTime(pref.getLong("time", 0));

    }

    public void getLocation(MyLocation myLocation) {
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
//                articlesView.showLocationInfo(locationWrapper.getLat(), locationWrapper.getLong(), Configs.LOWEST_LATITUDE_CANADA);
                articlesView.showLocationInfo(myLocation);
            }
        });
    }
}
