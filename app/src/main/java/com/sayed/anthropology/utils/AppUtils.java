package com.sayed.anthropology.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class AppUtils extends Application {
    private SharedPreferences sharedPreferences;
    public static final String MY_PREFERENCE_KEY = "anthropologyAppKey";
    public static final String LAST_PAGE_VIEWED_PDF = "lastPageViewedKey";
    static AppUtils instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        sharedPreferences = getSharedPreferences(MY_PREFERENCE_KEY, Context.MODE_PRIVATE);

    }

    public static AppUtils getInstance() {
        return instance;
    }

    public void saveLastPageViewed(int lastPage) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(LAST_PAGE_VIEWED_PDF, lastPage);
        editor.apply();
    }

    public int getLastPageViewed() {
        if (sharedPreferences.contains(LAST_PAGE_VIEWED_PDF)) {
            return sharedPreferences.getInt(LAST_PAGE_VIEWED_PDF, 1);
        } else {
            return 1;
        }
    }
}
