package ru.anatoli.addressbook.models;

import com.google.gson.annotations.Expose;

public class UserData {
    @Expose
    private String userName;
    @Expose
    private String password;

    //Getters
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    //Setters
    public UserData withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserData withPassword(String password) {
        this.password = password;
        return this;
    }
}
