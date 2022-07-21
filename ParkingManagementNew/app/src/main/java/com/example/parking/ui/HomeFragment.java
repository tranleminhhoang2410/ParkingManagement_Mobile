package com.example.parking.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.parking.R;
import com.example.parking.apiService.AppApiService;
import com.example.parking.model.User;
import com.example.parking.model.VehicleType;
import com.example.parking.databinding.FragmentHomeBinding;
import com.example.parking.utils.DataHolder;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private VehicleType type;
    private User loggedUser;
    private ArrayList<VehicleType> types;
    private AppApiService apiService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        apiService = new AppApiService();

        loggedUser = DataHolder.getInstance().getLoginUser();

        if(loggedUser==null){
            setLoginButton(binding);
        }else{
            setLogoutButton(binding);
            binding.tvUser.setText("Hello "+loggedUser.getName());
        }

        binding.btnPricing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService.getAllTypes().subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ArrayList<VehicleType>>() {

                            @Override
                            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull ArrayList<VehicleType> vehicleTypes) {
                                types = vehicleTypes;
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

        binding.btnVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loggedUser == null){
                    Toast.makeText(getContext(), "Please login to see your vehicles", Toast.LENGTH_LONG).show();
                }else{
                    Bundle bundle = new Bundle();
                    Navigation.findNavController(v).navigate(R.id.navigation_vehicle, bundle);
                }
            }
        });

        binding.btnParkingLots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loggedUser == null){
                    Toast.makeText(getContext(), "Please login to see current parking lots", Toast.LENGTH_LONG).show();
                }else{
                    Bundle bundle = new Bundle();
                    Navigation.findNavController(v).navigate(R.id.navigation_parking_lots, bundle);
                }
            }
        });
        return root;
    }

    private void setLoginButton(FragmentHomeBinding binding){
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

    private void setLogoutButton(FragmentHomeBinding binding){
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