package com.example.parking.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.parking.R;
import com.example.parking.UserPage;
import com.example.parking.apiService.AppApiService;
import com.example.parking.databinding.FragmentHomeBinding;
import com.example.parking.databinding.FragmentUserPageBinding;
import com.example.parking.model.User;
import com.example.parking.utils.DataHolder;
import com.example.parking.utils.Validate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class navigation_user_page extends Fragment {

    private User loggedUser;
    private FragmentUserPageBinding binding;
    private AppApiService apiService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        apiService = new AppApiService();

        loggedUser = DataHolder.getInstance().getLoginUser();
//        Log.d("DEBUG", "" + loggedUser.getName());

        if(loggedUser == null){
            binding.btnEdit.setText("Please login");
            binding.btnEdit.setEnabled(false);
            binding.btnEdit.setBackgroundColor(getResources().getColor(R.color.disable_button));
            binding.btnToVehicles.setText("");
            binding.btnToVehicles.setVisibility(View.GONE);
            setLoginButton(binding);
        }else{
            setLogoutButton(binding);
            setInfoText(loggedUser);

            binding.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String name = binding.txtName.getText().toString();
                    String email = binding.txtEmail.getText().toString();
                    String phone = binding.txtPhone.getText().toString();

                    if(name.isEmpty() || email.isEmpty() || phone.isEmpty()){
                        binding.tvError.setText("Register information must not be empty! Try again!");
                    }else if(!Validate.validateEmail(email)){
                        binding.tvError.setText("Wrong email format! Try again!");
                    }else if(!Validate.validatePhone(phone)){
                        binding.tvError.setText("Wrong phone format! Try (*** *** **** or *********)!");
                    }else{
                        User newInfo = new User(
                                loggedUser.getId(), name, email, phone, 0, 0, 0, "");
                        apiService.UpdateUser(newInfo).enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                if(response.isSuccessful()){
                                    Log.d("DEBUG", "Success");
                                    DataHolder.getInstance().setLoginUser(response.body());
                                    Toast.makeText(getContext(), "User information updated!!!", Toast.LENGTH_SHORT).show();
                                }else{
                                    binding.tvError.setText("Something wrong! Try again later!");
                                    Log.d("DEBUG", "Fail: " + response.errorBody());
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                Log.d("DEBUG", "Failure: " + t.getMessage());
                            }
                        });
                    }
                }
            });

            binding.btnToVehicles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    Navigation.findNavController(v).navigate(R.id.navigation_vehicle, bundle);
                }
            });

            binding.btnToInvoices.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    Navigation.findNavController(v).navigate(R.id.navigation_user_invoice, bundle);
                }
            });

        }

        return root;
    }

    private void setLoginButton(FragmentUserPageBinding binding){
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

    private void setLogoutButton(FragmentUserPageBinding binding){
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

    private void setInfoText(User user){
        binding.txtName.setText(user.getName());
        binding.txtEmail.setText(user.getEmail());
        binding.txtPhone.setText(user.getPhone());
    }
}