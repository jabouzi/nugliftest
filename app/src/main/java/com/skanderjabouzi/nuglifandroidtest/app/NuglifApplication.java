package com.skanderjabouzi.nuglifandroidtest.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skanderjabouzi.nuglifandroidtest.R;
import com.skanderjabouzi.nuglifandroidtest.model.ArticlesItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NuglifApplication extends Application {
    private static NuglifApplication sApplication;
    private List<ArticlesItem> items;
    private InputStream inputStream;

    public static NuglifApplication getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;items = new ArrayList<>();
        if (items.isEmpty()) {
            items = getArticlesList();
        }
    }

    private List<ArticlesItem> getArticlesList() {
        inputStream =  getResources().openRawResource(R.raw.articles);
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            Type listType = new TypeToken<List<ArticlesItem>>() {
            }.getType();
            return new Gson().fromJson(reader, listType);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ArticlesItem> getItems() {
        return items;
    }
}
