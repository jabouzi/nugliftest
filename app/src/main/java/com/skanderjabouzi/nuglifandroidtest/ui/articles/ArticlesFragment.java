package com.skanderjabouzi.nuglifandroidtest.ui.articles;

import android.content.pm.PackageManager;
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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skanderjabouzi.nuglifandroidtest.R;
import com.skanderjabouzi.nuglifandroidtest.app.Configs;
import com.skanderjabouzi.nuglifandroidtest.app.NuglifApplication;
import com.skanderjabouzi.nuglifandroidtest.helper.ListHelper;
import com.skanderjabouzi.nuglifandroidtest.location.LocationStateReceiver;
import com.skanderjabouzi.nuglifandroidtest.model.ArticlesItem;
import com.skanderjabouzi.nuglifandroidtest.model.MyLocation;
import com.skanderjabouzi.nuglifandroidtest.ui.article.ArticleActivity;
import com.skanderjabouzi.nuglifandroidtest.ui.article.ArticleFragment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ArticlesFragment extends Fragment  implements ArticlesView{

    View view;
    private ArticlesAdapter adapter;
    private InputStream inputStream;
    private ArticlesPresenter articlesPresenter;
    private LocationStateReceiver locationStateReceiver;
    private ListHelper listHelper;
    private MyLocation myLocation;
    List<ArticlesItem> list;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    NuglifApplication app;
    private boolean articlesVisibles = false;

    private ProgressBar progressBar;
    private TextView textInfo;
    private RecyclerView recycler_view;
    private RelativeLayout relativeLayout;
    FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        app = NuglifApplication.getApplication();
        view = inflater.inflate(R.layout.fragment_articles_list, container, false);
        intViews(view);
        myLocation = new MyLocation();
        locationStateReceiver = new LocationStateReceiver(getActivity());
        locationStateReceiver.setObserver(this);
        articlesPresenter = new ArticlesPresenter(getActivity());
        articlesPresenter.setView(this);
        articlesPresenter.checkLocationEnabled(getActivity());
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
        if (isWideScreen()) {
            if (articlesVisibles == true) {
                showSecondPane(list.get(0));
            }
        }
        getLocation();
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
                if (isWideScreen()) {
                    showSecondPane(_list.get(0));
                }
                showSnackBar();
                return true;
            case R.id.channel:
                listHelper.sortByChannel(_list);
                adapter.setArticlesList(_list);
                adapter.notifyDataSetChanged();
                if (isWideScreen()) {
                    showSecondPane(_list.get(0));
                }
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
    public void showArticles() {
        recycler_view.setAlpha(1);
        articlesVisibles = true;
        if (isWideScreen()) {
            showSecondPane(list.get(0));
        }
    }

    @Override
    public void hideArticles() {
        recycler_view.setAlpha(0);
        articlesVisibles = false;
    }

    @Override
    public void showErrorMessage() {
        textInfo.setText(R.string.ArticlesListError);
    }

    @Override
    public void showLocationErrorMessage() {
        textInfo.setText(R.string.LocationItemError);
    }

    @Override
    public void showLocationInfo(MyLocation location) {
        myLocation = location;
        articlesPresenter.saveLocation(app.getEditor(), app.getApplicationContext(), myLocation.getLatitude(), myLocation.getLongitude());
        if (location.getLatitude() >= Configs.LOWEST_LATITUDE_CANADA) {
            textInfo.setText(getResources().getString(R.string.yourPosition, String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude())));
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
        TextView fakeview = (TextView) view.findViewById(R.id.fakeview);
        return (fakeview != null);
    }

    public void showSecondPane(ArticlesItem articlesItem) {
        ArticleFragment fragmentItem = ArticleFragment.newInstance(articlesItem);
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.flDetailContainer, fragmentItem);
        ft.commit();
    }

    @Override
    public void setLocationResut(String state) {
        LocationStateReceiver receiver = new LocationStateReceiver(app.getApplicationContext());
        app.getApplicationContext().unregisterReceiver(receiver);
        if (state.equals("false")) {
                articlesPresenter.cancelTask();
                requestPermissions(
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION : {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    showLocationErrorMessage();
                }
                return;
            }
        }
    }


    private void intViews(final View view) {
        progressBar = (ProgressBar) view.findViewById(R.id.activity_articles_progressBar);
        textInfo = (TextView) view.findViewById(R.id.textInfo);
        textInfo.setText(getResources().getString(R.string.pleaseWait));
        recycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.rlayourt);

        if (!isWideScreen()) {
            fab = (FloatingActionButton) view.findViewById(R.id.button_addc);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "FAB1", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void showSnackBar() {
        Snackbar snackbar = Snackbar.make(relativeLayout, "", Snackbar.LENGTH_LONG)
        .setAction("Restore", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setArticlesList(list);
                adapter.notifyDataSetChanged();
                if (isWideScreen()) {
                    showSecondPane(list.get(0));
                }
            }
        });
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    private void getLocation() {
        if (articlesPresenter.checkLocation(app.getPref())) {
            myLocation = articlesPresenter.getSavedLocation(myLocation, app.getPref());
            showLocationInfo(myLocation);
            showArticles();
        } else {
            hideArticles();
            articlesPresenter.getLocation(myLocation);
        }
    }
}
