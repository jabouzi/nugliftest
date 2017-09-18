package com.skanderjabouzi.nuglifandroidtest.ui.article;

import com.skanderjabouzi.nuglifandroidtest.app.Configs;
import com.skanderjabouzi.nuglifandroidtest.model.ArticleItem;
import com.skanderjabouzi.nuglifandroidtest.network.NetworkApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ArticlePresenter implements ArticlePresenterInterface {
    private ArticleView articleView;

    @Override
    public void setView(ArticleView view) {
        this.articleView = view;
    }

    @Override
    public void getArticleDetail(String articleId) {
        articleView.showLoading();

        Converter.Factory converter = GsonConverterFactory.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Configs.BASE_URL)
                .addConverterFactory(converter)
                .build();

        NetworkApi networkApi = retrofit.create(NetworkApi.class);

        networkApi.getArticleItem(articleId).enqueue(new Callback<ArticleItem>() {
            @Override
            public void onResponse(Call<ArticleItem> call, Response<ArticleItem> response) {

                if (response.code() != 200) {
                    articleView.showErrorMessage();
                } else {
                    ArticleItem articleItem = response.body();
                    articleView.showArticle(articleItem);
                }
                articleView.hideLoading();
            }

            @Override
            public void onFailure(Call<ArticleItem> call, Throwable t) {
                articleView.showErrorMessage();
                articleView.hideLoading();
            }
        });

    }
}
