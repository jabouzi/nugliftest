package com.skanderjabouzi.nuglifandroidtest.network;

import  com.skanderjabouzi.nuglifandroidtest.model.ArticleItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkApi {
    @GET("/article/detail/{articleId}?v=3.2&os=android")
    Call<ArticleItem> getArticleItem(@Path("articleId") String articleId);
}


