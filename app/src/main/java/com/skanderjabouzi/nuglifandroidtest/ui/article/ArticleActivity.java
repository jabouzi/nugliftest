package com.skanderjabouzi.nuglifandroidtest.ui.article;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.skanderjabouzi.nuglifandroidtest.R;
import com.skanderjabouzi.nuglifandroidtest.model.ArticlesItem;

public class ArticleActivity extends AppCompatActivity {
    ArticleFragment fragmentItemDetail;
    public static final String ITEM = "item";

    public static void launch(Context context, ArticlesItem item) {
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra(ITEM, item);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        ArticlesItem item = (ArticlesItem) getIntent().getSerializableExtra("item");

        if (savedInstanceState == null) {
            fragmentItemDetail = ArticleFragment.newInstance(item);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailContainer, fragmentItemDetail);
            ft.commit();
        }
    }
}
