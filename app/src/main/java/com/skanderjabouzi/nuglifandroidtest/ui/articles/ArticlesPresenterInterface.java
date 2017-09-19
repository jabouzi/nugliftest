package com.skanderjabouzi.nuglifandroidtest.ui.articles;

import android.content.Context;
import android.content.SharedPreferences;

import com.skanderjabouzi.nuglifandroidtest.model.ArticlesItem;
import com.skanderjabouzi.nuglifandroidtest.model.MyLocation;

import java.io.InputStream;
import java.util.List;

public interface ArticlesPresenterInterface {
    void setView(ArticlesView view);

    List<ArticlesItem> getArticles(InputStream inputStream);

    void saveLocation(SharedPreferences.Editor editor, Context context, double latitude, double longitude);

    void getSavedLocation(MyLocation myLocation, SharedPreferences preferences);
}
