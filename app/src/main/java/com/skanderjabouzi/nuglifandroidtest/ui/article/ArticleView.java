package com.skanderjabouzi.nuglifandroidtest.ui.article;

import com.skanderjabouzi.nuglifandroidtest.model.ArticleItem;

public interface ArticleView {

    void showLoading();

    void hideLoading();

    void showArticle(ArticleItem article);

    void showErrorMessage();
}
