package com.example.parking.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parking.R;
import com.example.parking.apiService.AppApiService;
import com.example.parking.databinding.FragmentPricingPageBinding;
import com.example.parking.databinding.FragmentUserPageBinding;
import com.example.parking.model.VehicleType;
import com.example.parking.utils.DataHolder;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class navigation_pricing extends Fragment {

    private VehicleType type;
    private ArrayList<VehicleType> types;
    private FragmentPricingPageBinding binding;
    private AppApiService apiService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService = new AppApiService();

//        BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
//        navBar.setVisibility(View.GONE);

        if (getArguments() != null) {
            types = getArguments().getParcelableArrayList("passType");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                getLayoutInflater(), R.layout.fragment_pricing_page, null, false
        );
        View root = binding.getRoot();

        find(1);

        if(DataHolder.getInstance().getLoginUser()==null){
            setLoginButton(binding);
        }else{
            setLogoutButton(binding);
        }

        binding.btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find(1);
            }
        });

        binding.btnBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find(2);
            }
        });

        binding.btnTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find(3);
            }
        });

        return root;
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
//        navBar.setVisibility(View.VISIBLE);
//    }

    private void setPriceText(VehicleType t){
        DecimalFormat formatter = new DecimalFormat("#,###");
        binding.tvPriceHour.setText(formatter.format(t.getPricePerHour())+" VND");
        binding.tvPriceDay.setText(formatter.format(t.getPricePerDay())+" VND");
        binding.tvPriceWeek.setText(formatter.format(t.getPricePerWeek())+" VND");
        binding.tvPriceMonth.setText(formatter.format(t.getPricePerMonth())+" VND");
        binding.tvPriceYear.setText(formatter.format(t.getPricePerYear())+" VND");
    }

    private void find(int id){
        for(VehicleType t : types){
            if(t.getId()==id){
                setPriceText(t);
            }
        }
    }

    private void setLoginButton(FragmentPricingPageBinding binding){
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

    private void setLogoutButton(FragmentPricingPageBinding binding){
        Drawable loginIcon = getContext().getResources().getDrawable(R.drawable.ic_baseline_logout_24);
        binding.btnLogInOut.setText("LOGOUT");
        binding.btnLogInOut.setCompoundDrawablesWithIntrinsicBounds(null, loginIcon, null, null);
        binding.btnLogInOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataHolder.setDataInstanceNull();
                Bundle bundle = new Bundle();
                Navigation.findNavController(v).navigate(R.id.navigation_home, bundle);
            }
        });
    }
}