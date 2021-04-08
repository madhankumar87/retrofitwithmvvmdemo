package com.mvi.apiwithmvvm.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.mvi.apiwithmvvm.R;
import com.mvi.apiwithmvvm.adapter.UserAdapter;
import com.mvi.apiwithmvvm.model.User;
import com.mvi.apiwithmvvm.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;
    private MainViewModel mainViewModel;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefresh = findViewById(R.id.swiperefresh);
        recyclerView = findViewById(R.id.recyclerView);

        //mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainViewModel.class);
        getUserList();

        swipeRefresh.setOnRefreshListener(this::getUserList);
    }

    public void getUserList() {
        swipeRefresh.setRefreshing(true);
        mainViewModel.getAllUsers().observe(this, userList -> {
            swipeRefresh.setRefreshing(false);
            setRecyclerView(userList);
        });
    }

    private void setRecyclerView(List<User> userList) {
        userAdapter = new UserAdapter(userList);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdapter);
        userAdapter.notifyDataSetChanged();
    }
}