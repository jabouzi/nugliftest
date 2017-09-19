package com.skanderjabouzi.nuglifandroidtest.ui.articles;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skanderjabouzi.nuglifandroidtest.R;
import com.skanderjabouzi.nuglifandroidtest.model.ArticlesItem;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesViewHolder> {

    private List<ArticlesItem> items;
    private ArticlesView articlesView;

    public ArticlesAdapter() {
        super();
    }

    public void setArticlesList(List<ArticlesItem> items) {
        this.items = items;
    }

    public void setView(ArticlesView view) {
        articlesView = view;
    }

    @Override
    public ArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ArticlesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ArticlesViewHolder holder, int position) {
        final ArticlesItem item = items.get(position);
        Log.e("LIST1", java.util.Arrays.asList(item.getId()).toString());
        holder.title.setText("[" + item.getChannelName() + "] " + item.getTitle());

        holder.getContainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (articlesView.isWideScreen()) {
                    articlesView.showSecondPane(item);
                } else{
                    articlesView.launchArticleDetail(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
