package com.mvi.apiwithmvvm.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {
    private static final String TAG = PropertyUtils.class.getSimpleName();

    private static final String propertyName = "app.properties";
    private static Properties appProperties= null;

    public static void loadProperty(Context context) throws IOException {
        appProperties = new Properties();
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = assetManager.open(propertyName);
        appProperties.load(inputStream);
        Log.d(TAG, "Property file loaded...");
    }

    public static String getProperty(String key, Context context) {
        if (appProperties == null){
            try {
                loadProperty(context);
            }catch (IOException e){
                Log.e(TAG, "Failed to load property file: " + propertyName + " due to: " + e.getMessage());
                return null;
            }
        }
        return appProperties.getProperty(key);
    }

}
