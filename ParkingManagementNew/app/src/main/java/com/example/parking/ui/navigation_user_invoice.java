package com.example.parking.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.parking.R;
import com.example.parking.adapter.InvoiceAdapter;
import com.example.parking.apiService.AppApiService;
import com.example.parking.databinding.FragmentNavigationUserInvoiceBinding;
import com.example.parking.model.Invoice;
import com.example.parking.model.Vehicle;
import com.example.parking.utils.DataHolder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class navigation_user_invoice extends Fragment {

    private FragmentNavigationUserInvoiceBinding binding;
    private ArrayList<String> VehicleIdList;
    private ArrayList<Invoice> invoiceArrayList;
    private AppApiService apiService;
    private InvoiceAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNavigationUserInvoiceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if(DataHolder.getInstance().getLoginUser()==null){
            setLoginButton(binding);
        }else{
            setLogoutButton(binding);
        }

        VehicleIdList = new ArrayList<>();

        apiService = new AppApiService();

        ArrayAdapter adapter = new ArrayAdapter(root.getContext(),
                android.R.layout.simple_spinner_item, VehicleIdList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spVehicleId.setAdapter(adapter);

        apiService.GetUserVehicleList(DataHolder.getInstance().getLoginUser().getId()).enqueue(new Callback<ArrayList<Vehicle>>() {
            @Override
            public void onResponse(Call<ArrayList<Vehicle>> call, Response<ArrayList<Vehicle>> response) {
                if(response.isSuccessful()){
                    for(Vehicle v : response.body()){
                        Log.d("DEBUG", "" + v.getId());
                        VehicleIdList.add(v.getId());
                        adapter.notifyDataSetChanged();
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
                setInvoiceRecyclerView(binding, selectedId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return root;
    }

    private void setInvoiceRecyclerView(FragmentNavigationUserInvoiceBinding binding, String vehicleId){
        invoiceArrayList = new ArrayList<>();
        adapter = new InvoiceAdapter(invoiceArrayList);
        binding.rvInvoice.setAdapter(adapter);

        apiService.VehicleInvoices(vehicleId).enqueue(new Callback<ArrayList<Invoice>>() {
            @Override
            public void onResponse(Call<ArrayList<Invoice>> call, Response<ArrayList<Invoice>> response) {
                if(response.isSuccessful()){
                    for (Invoice i : response.body()){
                        invoiceArrayList.add(i);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Invoice>> call, Throwable t) {

            }
        });
    }

    private void setLoginButton(FragmentNavigationUserInvoiceBinding binding){
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

    private void setLogoutButton(FragmentNavigationUserInvoiceBinding binding){
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