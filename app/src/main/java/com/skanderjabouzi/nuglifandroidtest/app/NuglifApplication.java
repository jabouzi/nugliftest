package com.skanderjabouzi.nuglifandroidtest.app;

import android.app.Application;
import android.content.Context;

public class NuglifApplication extends Application {
    private static NuglifApplication sApplication;
    public static NuglifApplication getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
