package com.skanderjabouzi.nuglifandroidtest.helper;

import android.os.AsyncTask;

public class AsyncTaskHelper {

    public interface DoSomething {
        void doItInBackground();
        void doItPostExecute();
    }

    public AsyncTask execute(final DoSomething callback) {
        return new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {

                callback.doItInBackground();
                return null;
            }

            @Override
            protected void onPostExecute(Object result) {
                callback.doItPostExecute();
            }

        }.execute();
    }
}
