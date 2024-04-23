package com.sayed.anthropology;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    MyApp instance;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();

    }

    public static Context getAppContext() {
        return context;
    }
}
