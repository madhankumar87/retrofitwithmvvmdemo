package com.mvi.apiwithmvvm.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import lombok.Getter;

/**
 * Yaman: Custom application class
 * Referencing:
 * https://guides.codepath.com/android/Understanding-the-Android-Application-Class
 */
public class AppApplication extends Application {
    @Getter
    private static Context appContext;

    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    @Override
    public void onCreate() {
        super.onCreate();
        // Required initialization logic here!
        appContext = getApplicationContext();
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
