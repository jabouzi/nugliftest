package com.skanderjabouzi.nuglifandroidtest.ui.articles;

import com.skanderjabouzi.nuglifandroidtest.model.ArticlesItem;
import com.skanderjabouzi.nuglifandroidtest.model.MyLocation;

public interface ArticlesView {
    void showLoading();

    void hideLoading();

    void showArticles();

    void hideArticles();

    void showErrorMessage();

    void showLocationErrorMessage();

    void showLocationInfo(MyLocation location);

    void launchArticleDetail(ArticlesItem articlesItem);

    boolean isWideScreen();

    void showSecondPane(ArticlesItem articlesItem);

    void setLocationResut(String state);
}
