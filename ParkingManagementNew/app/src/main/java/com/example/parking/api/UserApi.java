package com.example.parking.api;

import com.example.parking.model.Account;
import com.example.parking.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserApi {
    @PUT("api/User/Update2")
    Call<User> update(@Body User user);
}
