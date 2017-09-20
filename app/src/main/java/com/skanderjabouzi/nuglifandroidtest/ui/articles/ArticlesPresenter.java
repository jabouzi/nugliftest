package com.skanderjabouzi.nuglifandroidtest.ui.articles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.provider.Settings;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
    Context context;

    public ArticlesPresenter(Context context) {
        this.context = context;
        locationWrapper = new LocationWrapper(context);
    }

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
    public MyLocation getSavedLocation(MyLocation myLocation, SharedPreferences pref) {
        myLocation.setLatitude((double) pref.getFloat("latitude", 0));
        myLocation.setLongitude((double) pref.getFloat("longitude", 0));
        myLocation.setTime((double) pref.getFloat("time", 0));
        return myLocation;
    }



    public void getLocation(final MyLocation myLocation) {
        if (myAsyncTask != null) {
            cancelTask();
        }
        locationWrapper = new LocationWrapper(context);
        myAsyncTask = new AsyncTaskHelper();
        myAsyncTask.execute(new AsyncTaskHelper.DoSomething() {
            @Override
            public void doItInBackground() {
                Looper.prepare();
                while(locationWrapper.gotLocation() == false){
                }
            }

            @Override
            public void doItPostExecute() {
                myLocation.setLatitude(locationWrapper.getLat());
                myLocation.setLongitude(locationWrapper.getLong());
                articlesView.showLocationInfo(myLocation);
            }
        });
    }

    public boolean checkLocation(SharedPreferences pref) {
        if (pref.contains("latitude")) {
            return true;
        }

        return false;
    }

    public void checkLocationEnabled(Context context) {
        if (!locationWrapper.checkLocationEnabled(context)) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
        }
    }

    @Override
    public void cancelTask() {
        myAsyncTask = null;
    }
}
