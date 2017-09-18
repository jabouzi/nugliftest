package com.skanderjabouzi.nuglifandroidtest.ui.article;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.skanderjabouzi.nuglifandroidtest.R;
import com.skanderjabouzi.nuglifandroidtest.model.ArticleItem;
import com.skanderjabouzi.nuglifandroidtest.model.ArticlesItem;

public class ArticleFragment extends Fragment implements ArticleView{
    private ArticlesItem item;
    ArticlePresenter articlePresenter;
    ProgressBar progressBar;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        item = (ArticlesItem) getArguments().getSerializable("item");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_article_detail, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.activity_article_progressBar);
        articlePresenter = new ArticlePresenter();
        articlePresenter.setView(this);
        articlePresenter.getArticleDetail(item.getId());

        return view;
    }

    public static ArticleFragment newInstance(ArticlesItem item) {
        ArticleFragment fragmentDemo = new ArticleFragment();
        Bundle args = new Bundle();
        args.putSerializable("item", item);
        fragmentDemo.setArguments(args);
        return fragmentDemo;
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
    public void showArticle(ArticleItem  article) {
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView lead = (TextView) view.findViewById(R.id.lead);
        TextView byLine = (TextView) view.findViewById(R.id.byLine);
        TextView info = (TextView) view.findViewById(R.id.info);
        title.setText(article.getTitle());
        lead.setText(article.getLead());
        byLine.setText(article.getByLine());
        info.setText(article.getClassName() + " - " + article.getType() + " - " + article.getOrganisation());
    }

    @Override
    public void showErrorMessage() {
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(R.string.ArticleItemError);
    }
}
