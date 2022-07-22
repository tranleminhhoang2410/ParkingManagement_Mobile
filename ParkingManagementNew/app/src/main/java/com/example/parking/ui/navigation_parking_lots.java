package com.example.parking.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parking.R;
import com.example.parking.adapter.ParkingLotAdapter;
import com.example.parking.adapter.VehicleAdapter;
import com.example.parking.apiService.AppApiService;
import com.example.parking.databinding.FragmentParkingLotsBinding;
import com.example.parking.databinding.FragmentVehicleBinding;
import com.example.parking.model.Lot;
import com.example.parking.model.User;
import com.example.parking.model.Vehicle;
import com.example.parking.utils.DataHolder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class navigation_parking_lots extends Fragment {

    private FragmentParkingLotsBinding binding;

    private ArrayList<Lot> lotsArrayList;
    private User loggedUser;
    private AppApiService apiService;
    private ParkingLotAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentParkingLotsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        apiService = new AppApiService();
        loggedUser = DataHolder.getInstance().getLoginUser();

        if(loggedUser==null){
            setLoginButton(binding);
        }else{
            setLogoutButton(binding);
        }

        binding.rvParkingLot.setLayoutManager(new GridLayoutManager(getContext(), 2));

        binding.areaTitle.setText("AREA A");
        setLotRecyclerView("A", binding);

        binding.btnAreaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.areaTitle.setText("AREA A");
                setLotRecyclerView("A", binding);
            }
        });
        binding.btnAreaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.areaTitle.setText("AREA B");
                setLotRecyclerView("B", binding);
            }
        });
        binding.btnAreaC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.areaTitle.setText("AREA C");
                setLotRecyclerView("C", binding);
            }
        });
        binding.btnAreaD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.areaTitle.setText("AREA D");
                setLotRecyclerView("D", binding);
            }
        });
        binding.btnAreaE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.areaTitle.setText("AREA E");
                setLotRecyclerView("E", binding);
            }
        });

        return root;
    }

    private void setLotRecyclerView(String area, FragmentParkingLotsBinding binding){
        lotsArrayList = new ArrayList<>();
        adapter = new ParkingLotAdapter(lotsArrayList);

        binding.rvParkingLot.setAdapter(adapter);

//        binding.checklistItemsLayout.removeAllViews();
        apiService.GetAllLots(area).enqueue(new Callback<ArrayList<Lot>>() {
            @Override
            public void onResponse(Call<ArrayList<Lot>> call, Response<ArrayList<Lot>> response) {
                if(response.isSuccessful()){
                    for (Lot lot: response.body()) {
                        Log.d("DEBUG", "" + lot.getArea()+lot.getPosition() + " " + lot.getStatus());
                        lotsArrayList.add(lot);
                        adapter.notifyDataSetChanged();
                    }
                }else{
                    Log.d("DEBUG", "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Lot>> call, Throwable t) {
                Log.d("DEBUG", "" + t.getMessage());
            }
        });
    }

    private void setLoginButton(FragmentParkingLotsBinding binding){
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

    private void setLogoutButton(FragmentParkingLotsBinding binding){
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