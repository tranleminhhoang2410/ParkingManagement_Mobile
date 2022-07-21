package com.example.parking.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.parking.R;
import com.example.parking.apiService.AppApiService;
import com.example.parking.databinding.FragmentCheckInBinding;
import com.example.parking.databinding.FragmentCheckOutBinding;
import com.example.parking.model.Invoice;
import com.example.parking.model.VehicleType;
import com.example.parking.utils.DataHolder;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class navigation_check_out extends Fragment {

    String lotId;
    int typeId;

    private FragmentCheckOutBinding binding;
    private Invoice checkOutInvoice;
    private AppApiService apiService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lotId = getArguments().getString("LotId");
            typeId = getArguments().getInt("TypeId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCheckOutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if(DataHolder.getInstance().getLoginUser()==null){
            setLoginButton(binding);
        }else{
            setLogoutButton(binding);
        }

        apiService = new AppApiService();

        apiService.GetCheckOutInvoice(lotId).enqueue(new Callback<Invoice>() {
            @Override
            public void onResponse(Call<Invoice> call, Response<Invoice> response) {
                if(response.isSuccessful()){
                    checkOutInvoice = response.body();
                    Log.d("DEBUG", "Success");
                    setUI(typeId, lotId, checkOutInvoice, binding);
                }else{
                    Log.d("DEBUG", "Fail");

                }
            }

            @Override
            public void onFailure(Call<Invoice> call, Throwable t) {
                Log.d("DEBUG", "Failure: "+t.getMessage());
            }
        });

        binding.btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                apiService.CheckOut(checkOutInvoice).enqueue(new Callback<Invoice>() {
                    @Override
                    public void onResponse(Call<Invoice> call, Response<Invoice> response) {
                        if(response.isSuccessful()){
                            Log.d("DEBUG", "Success");
                            Toast.makeText(getContext(), "Vehicle "+checkOutInvoice.vehicleId+" check out success!", Toast.LENGTH_LONG).show();
                            Bundle bundle = new Bundle();
                            Navigation.findNavController(v).navigate(R.id.navigation_parking_lots, bundle);
                        }else{
                            Toast.makeText(getContext(), "Something wrong!!!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Invoice> call, Throwable t) {
                        Toast.makeText(getContext(), "Vehicle check out Fail!" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        binding.btnToPricing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService.getAllTypes().subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ArrayList<VehicleType>>() {

                            @Override
                            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull ArrayList<VehicleType> vehicleTypes) {
                                ArrayList<VehicleType> types = vehicleTypes;
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("passType", types);
                                Navigation.findNavController(v).navigate(R.id.navigation_pricing, bundle);
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                Log.d("DEBUG", "Fail: "+e.getMessage());
                            }
                        });
            }
        });

        return root;
    }

    private void setLoginButton(FragmentCheckOutBinding binding){
        Drawable loginIcon = getContext().getResources().getDrawable(R.drawable.ic_baseline_input_24);
        binding.btnLogInOut.setText("LOGIN");
        binding.btnLogInOut.setCompoundDrawablesWithIntrinsicBounds(null, loginIcon, null, null);
        binding.btnLogInOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("passMessage", "message");
                Navigation.findNavController(v).navigate(R.id.navigation_login, bundle);
            }
        });
    }

    private void setLogoutButton(FragmentCheckOutBinding binding){
        Drawable loginIcon = getContext().getResources().getDrawable(R.drawable.ic_baseline_logout_24);
        binding.btnLogInOut.setText("LOGOUT");
        binding.btnLogInOut.setCompoundDrawablesWithIntrinsicBounds(null, loginIcon, null, null);
        binding.btnLogInOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setUI(int typeId, String lotId, Invoice invoice, FragmentCheckOutBinding binding){
        DecimalFormat formatter = new DecimalFormat("#,###");
        binding.ivLotId.setText(lotId);
        switch (typeId){
            case 1:
                binding.ivTypeImage.setImageResource(R.drawable.ic_car);
                break;
            case 2:
                binding.ivTypeImage.setImageResource(R.drawable.ic_bus);
                break;
            case 3:
                binding.ivTypeImage.setImageResource(R.drawable.ic_truck);
                break;
        }
        binding.ivVehicleId.setText(invoice.getVehicleId());
        binding.ivCheckInTime.setText(invoice.getCheckinTime().split("T")[0] +"\n" + invoice.getCheckinTime().split("T")[1]);
        binding.ivCheckOutTime.setText(invoice.getCheckoutTime().split("T")[0] +"\n" + invoice.getCheckoutTime().split("T")[1]);
        binding.ivTotalPaid.setText(formatter.format(invoice.getTotalPaid())+" VND");
    }


}