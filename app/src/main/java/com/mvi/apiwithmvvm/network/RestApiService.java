package com.mvi.apiwithmvvm.network;

import com.mvi.apiwithmvvm.model.UserWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiService {

    @GET("mvvmapplication")
    Call<UserWrapper> getUserList();
}
