package com.example.count.foodinformation;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import Model.CreateUserClass;
import Model.LoginClass;
import Model.UserDTO;
import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment implements View.OnClickListener {

    private Service service;
    private EditText txID,txPW;
    private TextView txStatus;
    public static int UserID;
    FragmentTransaction fragmentTransaction;
    public FragmentLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_login, container, false);
        fragmentTransaction  =  getActivity().getSupportFragmentManager().beginTransaction();
        service = Common.GetService();
        txID = view.findViewById(R.id.txID);
        txPW = view.findViewById(R.id.txPW);
        txStatus = view.findViewById(R.id.txStatus);
        view.findViewById(R.id.btnLogin).setOnClickListener(this);
        view.findViewById(R.id.btnGotoCreate).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogin)
        {
            txStatus.setText("");
            LoginClass loginClass;
            if(txID.getText().toString().contains("@"))
                loginClass =  new LoginClass(txPW.getText().toString(),null,txID.getText().toString());
            else
                loginClass =  new LoginClass(txPW.getText().toString(),txID.getText().toString(),null);
            service.Login(loginClass).enqueue(new Callback<LoginClass>() {
                @Override
                public void onResponse(Call<LoginClass> call, Response<LoginClass> response) {
                    if(response.isSuccessful())
                    {
                        String UserName = response.body().getResult().getUsername();
                        service.GetUserDetailByUsername(new UserDTO(UserName)).enqueue(new Callback<UserDTO>() {
                            @Override
                            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                                if(response.isSuccessful())
                                {
                                    UserID = response.body().getResult().Id;
                                }
                            }

                            @Override
                            public void onFailure(Call<UserDTO> call, Throwable t) {

                            }
                        });
                       // UserID = response.body().getCreatedUserId();
                        FragmentProfile fragmentProfile = new FragmentProfile();
                       // bundle.putString("Name",response.body().getUsernameOrEmail());
                        setFragment(fragmentProfile);
                    }
                    else
                    {
                        Gson gson = new Gson();
                        try {
                            txStatus.setTextColor(Color.RED);
                            txStatus.setVisibility(View.VISIBLE);
                            CreateUserClass r = gson.fromJson(response.errorBody().string(), CreateUserClass.class);
                            if(r != null)
                                txStatus.setText(MainActivity.Errors.get(r.getMessage()));
                            else
                                txStatus.setText("Unknown Error");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginClass> call, Throwable t) {
                    txStatus.setText("Fail");
                }
            });

        }
        else if(view.getId() == R.id.btnGotoCreate)
        {
            setFragment(new FragmentCreateUser());
        }
    }
    private void setFragment(Fragment fragment)
    {

        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
