package com.skanderjabouzi.nuglifandroidtest.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.skanderjabouzi.nuglifandroidtest.location.LocationWrapper;

public class NuglifApplication extends Application {
    private static NuglifApplication sApplication;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
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
        pref = getContext().getSharedPreferences("locationPref", 0);
        editor = pref.edit();
    }

    public SharedPreferences getPref() {
        return pref;
    }

    public SharedPreferences.Editor  getEditor() {
        return editor;
    }
}
