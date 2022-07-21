package com.example.parking.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.parking.R;
import com.example.parking.apiService.AppApiService;
import com.example.parking.databinding.FragmentCheckInBinding;
import com.example.parking.databinding.FragmentParkingLotsBinding;
import com.example.parking.model.Invoice;
import com.example.parking.model.Vehicle;
import com.example.parking.model.VehicleType;
import com.example.parking.utils.DataHolder;

import java.lang.reflect.Type;
import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class navigation_check_in extends Fragment {

    String lotId;
    int typeId;

    private FragmentCheckInBinding binding;
    private ArrayList<String> VehicleIdList;
    private AppApiService apiService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lotId = getArguments().getString("LotId");
            typeId = getArguments().getInt("TypeId");
            Log.d("DEBUG", "" + lotId + " " + typeId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCheckInBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if(DataHolder.getInstance().getLoginUser()==null){
            setLoginButton(binding);
        }else{
            setLogoutButton(binding);
        }

        apiService = new AppApiService();

        VehicleIdList = new ArrayList<>();

        ArrayAdapter adapter = new ArrayAdapter(root.getContext(),
                android.R.layout.simple_spinner_item, VehicleIdList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spVehicleId.setAdapter(adapter);

        apiService.GetUserVehicleList(DataHolder.getInstance().getLoginUser().getId()).enqueue(new Callback<ArrayList<Vehicle>>() {
            @Override
            public void onResponse(Call<ArrayList<Vehicle>> call, Response<ArrayList<Vehicle>> response) {
                if(response.isSuccessful()){
                    for(Vehicle v : response.body()){
                        if(v.getVehicleTypeId() == typeId && v.getParking()==false){
                            Log.d("DEBUG", "" + v.getId());
                            VehicleIdList.add(v.getId());
                            adapter.notifyDataSetChanged();
                        }
                    }
                }else{
                    Log.d("DEBUG", "Fail:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Vehicle>> call, Throwable t) {
                Log.d("DEBUG", "Failure:" + t.getMessage());
            }
        });

        binding.spVehicleId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedId= parent.getItemAtPosition(position).toString();
                binding.tvSelectedVehicleId.setText(selectedId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vehicleID = binding.tvSelectedVehicleId.getText().toString();
                if(vehicleID.isEmpty()){
                    Toast.makeText(getContext(), "Please choose your vehicle", Toast.LENGTH_SHORT).show();
                }else{
                    apiService.CheckIn(vehicleID, lotId).enqueue(new Callback<Invoice>() {
                        @Override
                        public void onResponse(Call<Invoice> call, Response<Invoice> response) {
                            if(response.isSuccessful()){
                                Log.d("DEBUG", "Success");
                                Toast.makeText(getContext(), "Vehicle "+vehicleID+" check in success!", Toast.LENGTH_LONG).show();
                                Bundle bundle = new Bundle();
                                Navigation.findNavController(v).navigate(R.id.navigation_parking_lots, bundle);
                            }else{
                                Log.d("DEBUG", "Something wrong!!! "+response.errorBody());
                                Toast.makeText(getContext(), "Something wrong!!!", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Invoice> call, Throwable t) {
                            Toast.makeText(getContext(), "Vehicle: "+vehicleID+" check in Fail!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
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

        setUI(typeId, lotId, binding);

        return root;
    }

    private void setUI(int typeId, String lotId, FragmentCheckInBinding binding){
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
    }

    private void setLoginButton(FragmentCheckInBinding binding){
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

    private void setLogoutButton(FragmentCheckInBinding binding){
        Drawable loginIcon = getContext().getResources().getDrawable(R.drawable.ic_baseline_logout_24);
        binding.btnLogInOut.setText("LOGOUT");
        binding.btnLogInOut.setCompoundDrawablesWithIntrinsicBounds(null, loginIcon, null, null);
        binding.btnLogInOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}