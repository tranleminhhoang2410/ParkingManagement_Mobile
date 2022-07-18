package com.example.parking.apiService;

import com.example.parking.api.AccountApi;
import com.example.parking.model.Account;
import com.example.parking.model.VehicleType;
import com.example.parking.api.VehicleTypeApi;

import java.util.ArrayList;
import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppApiService {
//    private static final String BASE_URL = "https://10.0.2.2:7297";
    private static final String BASE_URL = "http://10.0.2.2:5297/";

    private VehicleTypeApi Api_vehicleType;
    private AccountApi Api_Account;

    public AppApiService() {
        Api_vehicleType = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(VehicleTypeApi.class);

        Api_Account = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(AccountApi.class);
    }

    public Single<VehicleType> getType(int id){
        return Api_vehicleType.getType(id);
    }
    public Single<ArrayList<VehicleType>> getAllTypes() {return  Api_vehicleType.getAllTypes();}

    public Single<Account> Login(Account account){return Api_Account.Login(account);}
}
