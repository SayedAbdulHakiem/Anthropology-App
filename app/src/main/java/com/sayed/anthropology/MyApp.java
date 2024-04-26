package com.sayed.anthropology;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    private static Context context;
    MyApp instance;

    public static Context getAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();

    }
}
