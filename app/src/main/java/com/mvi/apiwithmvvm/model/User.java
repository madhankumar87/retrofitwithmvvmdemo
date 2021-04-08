package com.mvi.apiwithmvvm.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    @SerializedName("name")
    private String Name;

    @SerializedName("email")
    private String Email;

    @SerializedName("phone")
    private String Phone;
}
