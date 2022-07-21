package com.example.parking.apiService;

import com.example.parking.api.AccountApi;
import com.example.parking.api.LotApi;
import com.example.parking.api.UserApi;
import com.example.parking.api.VehicleApi;
import com.example.parking.api.VehicleTypeApi;
import com.example.parking.model.Account;
import com.example.parking.model.Invoice;
import com.example.parking.model.Lot;
import com.example.parking.model.User;
import com.example.parking.model.Vehicle;
import com.example.parking.model.VehicleType;

import java.util.ArrayList;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppApiService {
//    private static final String BASE_URL = "https://10.0.2.2:7297";
    private static final String BASE_URL = "http://10.0.2.2:5297/";

    private VehicleTypeApi Api_vehicleType;
    private AccountApi Api_Account;
    private UserApi Api_User;
    private VehicleApi Api_Vehicle;
    private LotApi Api_Lot;

    public static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());
    public static Retrofit retrofit = builder.build();

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

        Api_User = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(UserApi.class);

        Api_Vehicle = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(VehicleApi.class);

        Api_Lot = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(LotApi.class);
    }

    public Single<VehicleType> getType(int id){
        return Api_vehicleType.getType(id);
    }
    public Single<ArrayList<VehicleType>> getAllTypes() {return  Api_vehicleType.getAllTypes();}

    public Single<Account> Login(Account account){return Api_Account.Login(account);}
    public Call<Account> FindByUsername(String username){return Api_Account.FindByUsername(username);}
    public Call<Account> Register(String username, String password, String confirm){return Api_Account.Register(username, password, confirm);}

    public Call<User> UpdateUser(User user){return Api_User.update(user);}

    public Call<ArrayList<Vehicle>> GetUserVehicleList(int id){return Api_Vehicle.getVehicleList(id);}
    public Call<Vehicle> AddNewVehicle(int userid, String vehicleId, int typeId){return Api_Vehicle.AddVehicle(userid, vehicleId, typeId);}
    public Call<Vehicle> GetVehicle(String id){return Api_Vehicle.getVehicle(id);}
    public Call<Invoice> CheckIn(String vehicleId, String lotId){return  Api_Vehicle.CheckIn(vehicleId, lotId);}
    public Call<Invoice> GetCheckOutInvoice(String lotId){return Api_Vehicle.GetCheckOutInvoice(lotId);}
    public Call<Invoice> CheckOut(Invoice invoice){return Api_Vehicle.CheckOut(invoice);}

    public Call<ArrayList<Lot>> GetAllLots(String area) {return Api_Lot.getAllLots(area);}

}
