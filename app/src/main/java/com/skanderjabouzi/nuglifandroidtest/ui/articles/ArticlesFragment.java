package com.skanderjabouzi.nuglifandroidtest.ui.articles;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skanderjabouzi.nuglifandroidtest.R;
import com.skanderjabouzi.nuglifandroidtest.helper.ListHelper;
import com.skanderjabouzi.nuglifandroidtest.location.LocationStateReceiver;
import com.skanderjabouzi.nuglifandroidtest.model.ArticlesItem;
import com.skanderjabouzi.nuglifandroidtest.ui.article.ArticleActivity;
import com.skanderjabouzi.nuglifandroidtest.ui.article.ArticleFragment;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ArticlesFragment extends Fragment  implements ArticlesView{

    private ArticlesAdapter adapter;
    private InputStream inputStream;
    private ArticlesPresenter articlesPresenter;
    private LocationStateReceiver locationStateReceiver;
    private ListHelper listHelper;
    List<ArticlesItem> list;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private final String KEY_RECYCLER_STATE = "recycler_state";

    private ProgressBar progressBar;
    private TextView textInfo;
    private RecyclerView recycler_view;
    private RelativeLayout relativeLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setRetainInstance(true);
        View view = inflater.inflate(R.layout.fragment_articles_list, container, false);
        intViews(view);
        hideArticles();
        locationStateReceiver = new LocationStateReceiver(getActivity());
        locationStateReceiver.setObserver(this);
        articlesPresenter = new ArticlesPresenter();
        articlesPresenter.setView(this);
        articlesPresenter.getLocation();
        adapter = new ArticlesAdapter();
        inputStream =  getResources().openRawResource(R.raw.articles);
        list = articlesPresenter.getArticles(inputStream);
        adapter.setArticlesList(list);
        adapter.setView(this);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listHelper = new ListHelper();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        List<ArticlesItem> _list = new ArrayList<ArticlesItem>(list);
        switch (item.getItemId()) {

            case R.id.date:
                listHelper.sortByDate(_list);
                adapter.setArticlesList(_list);
                adapter.notifyDataSetChanged();
                showSnackBar();
                return true;
            case R.id.channel:
                listHelper.sortByChannel(_list);
                adapter.setArticlesList(_list);
                adapter.notifyDataSetChanged();
                showSnackBar();
                return true;
        }
        return false;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showArticles() { recycler_view.setAlpha(1); }

    @Override
    public void hideArticles() { recycler_view.setAlpha(0); }

    @Override
    public void showErrorMessage() {
        textInfo.setText(R.string.ArticlesListError);
    }

    @Override
    public void showLocationErrorMessage() {
        textInfo.setText(R.string.LocationItemError);
    }

    @Override
    public void showLocationInfo(double latitude, double longitude, double minlat) {
        if (latitude >= minlat) {
            textInfo.setText(getResources().getString(R.string.yourPosition, String.valueOf(latitude), String.valueOf(longitude)));
            showArticles();
        } else {
            textInfo.setText(getResources().getString(R.string.error_locationNotSupported));
            hideArticles();
        }
    }

    @Override
    public void launchArticleDetail(ArticlesItem articlesItem) {
        ArticleActivity.launch(getActivity(), articlesItem);
    }

    @Override
    public boolean isWideScreen() {
        return ArticlesActivity.isTwoPane;
    }

    public void showSecondPane(ArticlesItem articlesItem) {
        ArticleFragment fragmentItem = ArticleFragment.newInstance(articlesItem);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flDetailContainer, fragmentItem);
        ft.commit();
    }

    @Override
    public void setLocationResut(String state) {
        if (state.equals("false")) {
                requestPermissions(
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        }
    }

    private void intViews(final View view) {
        progressBar = (ProgressBar) view.findViewById(R.id.activity_articles_progressBar);
        textInfo = (TextView) view.findViewById(R.id.textInfo);
        textInfo.setText(getResources().getString(R.string.pleaseWait));
        recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.rlayourt);
        FloatingActionButton FAB = (FloatingActionButton) view.findViewById(R.id.button_addc);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    private void showSnackBar() {
        Snackbar snackbar = Snackbar.make(relativeLayout, "", Snackbar.LENGTH_LONG)
        .setAction("Restore", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setArticlesList(list);
                adapter.notifyDataSetChanged();
            }
        });
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }
}
