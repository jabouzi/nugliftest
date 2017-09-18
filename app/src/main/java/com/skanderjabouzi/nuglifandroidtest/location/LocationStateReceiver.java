package com.skanderjabouzi.nuglifandroidtest.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.skanderjabouzi.nuglifandroidtest.ui.articles.ArticlesView;

public class LocationStateReceiver extends BroadcastReceiver {

    private final Context context;
    private ArticlesView observer;

    public LocationStateReceiver(Context _context){
        String CUSTOM_ACTION = "com.skanderjabouzi.nuglifandroidtest.CONN_INTENT";
        Intent intent = new Intent();
        intent.setAction(CUSTOM_ACTION);
        context = _context;
        context.registerReceiver(this, new IntentFilter(intent.getAction()));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String conn_state = intent.getStringExtra("CONNSTATE");
        observer.setLocationResut(conn_state);
        context.unregisterReceiver(this);
    }

    public void setObserver(ArticlesView observer){
        this.observer = observer;
    }
}
