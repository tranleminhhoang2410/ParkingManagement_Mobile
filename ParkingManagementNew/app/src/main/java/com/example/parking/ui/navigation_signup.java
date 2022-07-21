package com.example.parking.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parking.R;
import com.example.parking.apiService.AppApiService;
import com.example.parking.databinding.FragmentSignupBinding;
import com.example.parking.model.Account;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class navigation_signup extends Fragment {

    private FragmentSignupBinding binding;
    private AppApiService apiService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.GONE);

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        apiService = new AppApiService();

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean canRegister = true;
                String username = binding.txtRegisterUsername.getText().toString();
                String password = binding.txtRegisterPassword.getText().toString();
                String rePassword = binding.txtConfirmRegisterPassword.getText().toString();

                if(username.isEmpty() || password.isEmpty() || rePassword.isEmpty()){
                    binding.tvReError.setText("Register information must not be empty! Try again!");
                }else if(!password.equals(rePassword)){
                    binding.tvReError.setText("Password doesn't match confirm password! Try again! ");
                }else{
                    apiService.FindByUsername(username).enqueue(new Callback<Account>() {
                        @Override
                        public void onResponse(Call<Account> call, Response<Account> response) {
                            if(response.isSuccessful()){
                                if(response.body()!=null){
                                    binding.tvReError.setText("Username existed! Try again! "+ response.body());
                                }else{
                                    apiService.Register(username, password, rePassword).enqueue(new Callback<Account>() {
                                        @Override
                                        public void onResponse(Call<Account> call, Response<Account> response) {
                                            if(response.isSuccessful()) {
                                                Log.d("DEBUG", "Success");
                                                Bundle bundle = new Bundle();
                                                Navigation.findNavController(v).navigate(R.id.navigation_login, bundle);
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<Account> call, Throwable t) {
                                            Log.d("DEBUG", "Fail: " + t);
                                        }
                                    });
                                }
                            }else{

                            }
                        }
                        @Override
                        public void onFailure(Call<Account> call, Throwable t) {
                            Log.d("DEBUG", "Fail: " + t);
                        }
                    });
                }
            }
        });

        binding.btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Navigation.findNavController(v).navigate(R.id.navigation_login, bundle);
            }
        });

        return root;
    }

    @Override
    public void onStop() {
        super.onStop();
        BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.VISIBLE);
    }
}