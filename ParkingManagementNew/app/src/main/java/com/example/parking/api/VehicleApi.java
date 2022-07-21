package com.example.parking.api;

import com.example.parking.model.Invoice;
import com.example.parking.model.Vehicle;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VehicleApi{
    @GET("api/Vehicle/Get/UserVehicle/{id}")
    Call<ArrayList<Vehicle>> getVehicleList(@Path(value = "id") int id);

    @GET("api/Vehicle/Get/Vehicle/{id}")
    Call<Vehicle> getVehicle(@Path(value = "id") String id);

    @GET("api/Vehicle/CheckOut/{lotId}")
    Call<Invoice> GetCheckOutInvoice(@Path(value = "lotId") String id);

    @POST("api/Vehicle/m_AddVehicle")
    Call<Vehicle> AddVehicle(
            @Query("userID") int userId,
            @Query("vehicleId") String vehicleId,
            @Query("typeId") int typeId
    );

    @POST("api/Vehicle/CheckIn2")
    Call<Invoice> CheckIn(@Query("vehicleId") String vehicleId,
                          @Query("slotId") String lotId);

    @POST("api/Vehicle/CheckOut2")
    Call<Invoice> CheckOut(@Body Invoice invoice);
}
