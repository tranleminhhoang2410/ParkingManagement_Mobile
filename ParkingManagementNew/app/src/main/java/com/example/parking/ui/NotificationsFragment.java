package com.example.parking.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.parking.R;
import com.example.parking.adapter.InvoiceAdapter;
import com.example.parking.apiService.AppApiService;
import com.example.parking.databinding.FragmentNotificationsBinding;
import com.example.parking.model.Invoice;
import com.example.parking.utils.DataHolder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private ArrayList<Invoice> invoiceArrayList;
    private InvoiceAdapter adapter;
    private AppApiService apiService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        if(DataHolder.getInstance().getLoginUser()==null){
            setLoginButton(binding);
            binding.tvNoNotification.setText("Login to see your parking notifications");
        }else{
            setLogoutButton(binding);

            apiService = new AppApiService();

            invoiceArrayList = new ArrayList<>();
            adapter = new InvoiceAdapter(invoiceArrayList);
            binding.rvNotification.setAdapter(adapter);

            apiService.GetUserParkingInvoice(DataHolder.getInstance().getLoginUser().getId()).enqueue(new Callback<ArrayList<Invoice>>() {
                @Override
                public void onResponse(Call<ArrayList<Invoice>> call, Response<ArrayList<Invoice>> response) {
                    if(response.isSuccessful()){
                        if(response.body().size()!=0){
                            for (Invoice i : response.body()){
                                invoiceArrayList.add(i);
                                adapter.notifyDataSetChanged();
                            }
                        }else{
                            binding.tvNoNotification.setText("No parking notifications found!");
                        }

                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Invoice>> call, Throwable t) {

                }
            });
        }



        return root;
    }
    private void setLoginButton(FragmentNotificationsBinding binding){
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

    private void setLogoutButton(FragmentNotificationsBinding binding){
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