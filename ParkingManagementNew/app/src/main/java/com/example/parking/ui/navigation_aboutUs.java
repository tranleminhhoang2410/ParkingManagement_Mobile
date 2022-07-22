package com.example.parking.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.parking.R;
import com.example.parking.databinding.FragmentAboutUsBinding;
import com.example.parking.databinding.FragmentCheckOutBinding;
import com.example.parking.utils.DataHolder;

public class navigation_aboutUs extends Fragment {

    private FragmentAboutUsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAboutUsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        if(DataHolder.getInstance().getLoginUser()==null){
            setLoginButton(binding);
        }else{
            setLogoutButton(binding);
        }
        return root;
    }

    private void setLoginButton(FragmentAboutUsBinding binding){
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

    private void setLogoutButton(FragmentAboutUsBinding binding){
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