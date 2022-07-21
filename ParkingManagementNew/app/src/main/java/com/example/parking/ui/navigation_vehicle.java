package com.example.parking.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.parking.R;
import com.example.parking.adapter.VehicleAdapter;
import com.example.parking.apiService.AppApiService;
import com.example.parking.databinding.FragmentHomeBinding;
import com.example.parking.databinding.FragmentLoginBinding;
import com.example.parking.databinding.FragmentVehicleBinding;
import com.example.parking.model.User;
import com.example.parking.model.Vehicle;
import com.example.parking.utils.DataHolder;
import com.example.parking.utils.Validate;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class navigation_vehicle extends Fragment {

    private FragmentVehicleBinding binding;

    private ArrayList<Vehicle> vehicleArrayList;
    private User loggedUser;
    private AppApiService apiService;
    private VehicleAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVehicleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        apiService = new AppApiService();
        loggedUser = DataHolder.getInstance().getLoginUser();

        if(loggedUser==null){
            setLoginButton(binding);
        }else{
            setLogoutButton(binding);
        }

        vehicleArrayList = new ArrayList<>();
        adapter = new VehicleAdapter(vehicleArrayList);

        binding.rvVehicle.setAdapter(adapter);

        final int[] typeId = {0};
        final boolean[] typedChosen = {false};

        binding.rgVehicleType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_car:
                        typedChosen[0] = true;
                        Log.d("DEBUG", "choose 1");
                        typeId[0] =1;
                        break;
                    case R.id.rb_bus:
                        typedChosen[0] = true;
                        Log.d("DEBUG", "choose 2");
                        typeId[0] =2;
                        break;
                    case R.id.rb_truck:
                        typedChosen[0] = true;
                        Log.d("DEBUG", "choose 3");
                        typeId[0] =3;
                        break;
                }
            }
        });

        binding.btnAddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vehicleId = binding.tvNewId.getText().toString();
                if(vehicleId.isEmpty() || typedChosen[0] == false){
                    binding.tvError.setText("Input field must not be empty! Try again!");
                }else if(!Validate.validateVehicleId(vehicleId)){
                    binding.tvError.setText("Vehicle id is wrong format! Ex: A11-111111 or A11 111111");
                }else{
                    apiService.GetVehicle(vehicleId).enqueue(new Callback<Vehicle>() {
                        @Override
                        public void onResponse(Call<Vehicle> call, Response<Vehicle> response) {
                            if(response.isSuccessful()){
                                if(response.body()!=null){
                                    binding.tvError.setText("Vehicle existed! Try again!");
                                }else {
                                    apiService.AddNewVehicle(loggedUser.getId(), vehicleId, typeId[0]).enqueue(new Callback<Vehicle>() {
                                        @Override
                                        public void onResponse(Call<Vehicle> call, Response<Vehicle> response) {
                                            if(response.isSuccessful()) {
                                                Log.d("DEBUG", "Success");
                                                Toast.makeText(getContext(), "New vehicle added!!!", Toast.LENGTH_SHORT).show();
                                                setVehicleRecycleView(binding);
                                                binding.tvNewId.setText("");
                                                binding.rbCar.setChecked(false);
                                                binding.rbBus.setChecked(false);
                                                binding.rbTruck.setChecked(false);
                                            }else{
                                                Log.d("DEBUG", "Fail: " + response.message());
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Vehicle> call, Throwable t) {
                                            Log.d("DEBUG", "Failure: " + t);
                                        }
                                    });
                                }
                            }else {

                            }
                        }
                        @Override
                        public void onFailure(Call<Vehicle> call, Throwable t) {

                        }
                    });
                }
            }
        });

        binding.btnToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Navigation.findNavController(v).navigate(R.id.navigation_user_page, bundle);
            }
        });


        setVehicleRecycleView(binding);

        return root;
    }

    private void setVehicleRecycleView(FragmentVehicleBinding binding){
        vehicleArrayList = new ArrayList<>();
        adapter = new VehicleAdapter(vehicleArrayList);

        binding.rvVehicle.setAdapter(adapter);
        apiService.GetUserVehicleList(loggedUser.getId()).enqueue(new Callback<ArrayList<Vehicle>>() {
            @Override
            public void onResponse(Call<ArrayList<Vehicle>> call, Response<ArrayList<Vehicle>> response) {
                if(response.isSuccessful()){
                    for (Vehicle vehicle: response.body()) {
                        Log.d("DEBUG", "" + vehicle.getId());
                        vehicleArrayList.add(vehicle);
                        adapter.notifyDataSetChanged();
                    }
                }else{
                    Log.d("DEBUG", "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Vehicle>> call, Throwable t) {
                Log.d("DEBUG", "" + t.getMessage());
            }
        });
    }

    private void setLoginButton(FragmentVehicleBinding binding){
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

    private void setLogoutButton(FragmentVehicleBinding binding){
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