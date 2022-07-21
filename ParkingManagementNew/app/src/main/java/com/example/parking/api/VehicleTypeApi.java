package com.example.parking.api;

import com.example.parking.model.VehicleType;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VehicleTypeApi {
    @GET("api/VehicleType/Get/{id}")
    Single<VehicleType> getType(@Path(value = "id") int id);

    @GET("api/VehicleType/GetAll")
    Single<ArrayList<VehicleType>> getAllTypes();
}
