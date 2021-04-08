package com.mvi.apiwithmvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mvi.apiwithmvvm.model.User;
import com.mvi.apiwithmvvm.repository.UserRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private UserRepository userRepository;


    public MainViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<List<User>> getAllUsers() {
        return userRepository.getMutableLiveData();
    }
}
