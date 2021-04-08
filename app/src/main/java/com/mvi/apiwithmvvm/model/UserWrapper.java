package com.mvi.apiwithmvvm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWrapper {

    @SerializedName("data")
    private List<User> user;

    @SerializedName("error")
    private Boolean mError;

    @SerializedName("message")
    private String mMessage;

    @SerializedName("status")
    private String mStatus;
}
