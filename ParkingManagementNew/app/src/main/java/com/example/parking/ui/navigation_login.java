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
import com.example.parking.databinding.FragmentHomeBinding;
import com.example.parking.databinding.FragmentLoginBinding;
import com.example.parking.model.Account;
import com.example.parking.model.User;
import com.example.parking.model.VehicleType;
import com.example.parking.utils.DataHolder;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class navigation_login extends Fragment {

    private AppApiService apiService;
    private User loggedUser;
    private FragmentLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.GONE);

        if (getArguments() != null) {
            String mess = getArguments().getString("passMessage");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        apiService = new AppApiService();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.txtLoginUsername.getText().toString();
                String password = binding.txtLoginPassword.getText().toString();

                apiService.Login(new Account(0, username, password, "", new User()))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Account>() {
                            @Override
                            public void onSuccess(@NonNull Account account) {
                                Log.d("DEBUG", "" + account.getUser().getName());
                                Bundle bundle = new Bundle();
                                DataHolder.getInstance().setLoginUser(account.getUser());
                                Navigation.findNavController(v).navigate(R.id.navigation_home, bundle);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                binding.tvLoginErr.setText("Username or Password is not correct! Try again");
                                Log.d("DEBUG", "Fail: " + e.getMessage());
                            }
                        });
            }
        });

        binding.btnToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Navigation.findNavController(v).navigate(R.id.navigation_signup, bundle);
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