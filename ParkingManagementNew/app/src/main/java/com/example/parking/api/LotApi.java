package com.example.parking.api;

import com.example.parking.model.Lot;
import com.example.parking.model.Vehicle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LotApi {
    @GET("api/Slot/GetArea/{area}")
    Call<ArrayList<Lot>> getAllLots(@Path(value = "area") String area);
}
