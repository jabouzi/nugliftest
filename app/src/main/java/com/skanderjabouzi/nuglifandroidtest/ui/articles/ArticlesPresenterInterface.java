package com.skanderjabouzi.nuglifandroidtest.ui.articles;

import com.skanderjabouzi.nuglifandroidtest.model.ArticlesItem;

import java.io.InputStream;
import java.util.List;

public interface ArticlesPresenterInterface {
    void setView(ArticlesView view);

    List<ArticlesItem> getArticles(InputStream inputStream);
}
