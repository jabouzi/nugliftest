package com.skanderjabouzi.nuglifandroidtest.ui.articles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.support.v4.app.FragmentTransaction;

import com.skanderjabouzi.nuglifandroidtest.R;
import com.skanderjabouzi.nuglifandroidtest.app.NuglifApplication;
import com.skanderjabouzi.nuglifandroidtest.model.ArticlesItem;
import com.skanderjabouzi.nuglifandroidtest.ui.article.ArticleFragment;

import java.io.InputStream;
import java.util.List;


public class ArticlesActivity extends AppCompatActivity{

    public static boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_list);
        determinePaneLayout();
    }

    private void determinePaneLayout() {
        FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.flDetailContainer);
        if (fragmentItemDetail != null) {
            isTwoPane = true;
        }
    }

}