package com.example.parking.api;

import com.example.parking.model.Account;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AccountApi {
    @POST("api/Account/Login")
    Single<Account> Login(@Body Account account);

    @GET("api/Account/FindAccount/{username}")
    Call<Account> FindByUsername(@Path(value = "username") String username);

    @POST("api/ParkingManagement/SignUp")
    Call<Account> Register(
            @Query("username") String username,
            @Query("password") String password,
            @Query("ConfirmPassword") String confirmPassword
    );
}
