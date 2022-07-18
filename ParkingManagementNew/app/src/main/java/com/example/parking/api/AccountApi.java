package com.example.parking.api;

import com.example.parking.model.Account;
import com.example.parking.model.VehicleType;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccountApi {
    @POST("api/Account/Login")
    Single<Account> Login(@Body Account account);
}
