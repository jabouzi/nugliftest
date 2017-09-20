package com.skanderjabouzi.nuglifandroidtest.ui.articles;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.skanderjabouzi.nuglifandroidtest.R;



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
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.button_addc);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getBaseContext(), "FAB2", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}