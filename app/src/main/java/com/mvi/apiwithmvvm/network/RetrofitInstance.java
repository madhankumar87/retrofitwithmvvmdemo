package com.mvi.apiwithmvvm.network;

import android.content.Context;

import com.mvi.apiwithmvvm.application.AppApplication;
import com.mvi.apiwithmvvm.util.PropertyUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit = null;

    public static RestApiService getApiService() {
        if (retrofit == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);


            long readTimeout = 60L, writeTimeout = 60L, connectTimeout = 60L;

            Context context = AppApplication.getAppContext();
            String baseUrl = PropertyUtils.getProperty("url", context);

            String read = PropertyUtils.getProperty("readTimeout", context);
            if (read != null)
                readTimeout = Long.parseLong(read);
            String write = PropertyUtils.getProperty("writeTimeout", context);
            if (write != null)
                writeTimeout = Long.parseLong(write);
            String connect = PropertyUtils.getProperty("connectTimeout", context);
            if (connect != null)
                connectTimeout = Long.parseLong(connect);


            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(readTimeout, TimeUnit.SECONDS)
                    .connectTimeout(writeTimeout, TimeUnit.SECONDS)
                    .writeTimeout(connectTimeout, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build();


            assert baseUrl != null;
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit.create(RestApiService.class);
    }
}
