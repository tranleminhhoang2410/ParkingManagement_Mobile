package com.example.parking.utils;

import android.util.Log;

import com.example.parking.model.User;

public class DataHolder {
    private static DataHolder dataInstance = null;

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        if (dataInstance == null)
        {
            dataInstance = new DataHolder();
            Log.d("DEBUG", "Crate new instance");
        }
        return dataInstance;
    }

    private User loginUser;

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
        Log.d("DEBUG", "set user: name=" + loginUser.getName());
    }

    public static void setDataInstanceNull() {
        DataHolder.dataInstance = null;
    }
}
