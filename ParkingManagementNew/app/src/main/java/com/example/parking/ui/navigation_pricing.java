package com.example.parking.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parking.R;
import com.example.parking.databinding.FragmentPricingPageBinding;

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
}