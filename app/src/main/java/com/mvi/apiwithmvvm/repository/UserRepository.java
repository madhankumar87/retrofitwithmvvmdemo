package com.mvi.apiwithmvvm.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.mvi.apiwithmvvm.model.User;
import com.mvi.apiwithmvvm.model.UserWrapper;
import com.mvi.apiwithmvvm.network.RestApiService;
import com.mvi.apiwithmvvm.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private ArrayList<User> users = new ArrayList<>();
    private MutableLiveData<List<User>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public UserRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<User>> getMutableLiveData(){

        RestApiService apiService = RetrofitInstance.getApiService();
        Call<UserWrapper> call = apiService.getUserList();

        call.enqueue(new Callback<UserWrapper>() {
            @Override
            public void onResponse(@NonNull Call<UserWrapper> call, @NonNull Response<UserWrapper> response) {
                UserWrapper userWrapper = response.body();
                if (userWrapper != null && userWrapper.getUser() != null){
                    users = (ArrayList<User>) userWrapper.getUser();
                    mutableLiveData.setValue(users);
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserWrapper> call, @NonNull Throwable t) {
                Log.d("ListSize"," - > Error    "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }



}
