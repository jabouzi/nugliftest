package com.skanderjabouzi.nuglifandroidtest.ui.articles;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skanderjabouzi.nuglifandroidtest.R;


public class ArticlesViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    private ViewGroup container;

    public ArticlesViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.title);
        container = (ViewGroup) itemView.findViewById(R.id.list_item_container);
    }

    public ViewGroup getContainer() {
        return container;
    }
}
