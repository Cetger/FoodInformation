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
    private TextView txtStatus;
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
        txtStatus = view.findViewById(R.id.txtStatus);
        view.findViewById(R.id.btnLogin).setOnClickListener(this);
        view.findViewById(R.id.btnGotoCreate).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogin)
        {
            service.Login(new LoginClass(txID.getText().toString(),txPW.getText().toString())).enqueue(new Callback<LoginClass>() {
                @Override
                public void onResponse(Call<LoginClass> call, Response<LoginClass> response) {
                    if(response.isSuccessful())
                    {
                        FragmentProfile fragmentProfile = new FragmentProfile();
                        Bundle bundle = new Bundle();
                       // bundle.putString("Name",response.body().getUsernameOrEmail());
                        setFragment(fragmentProfile);
                    }
                    else
                    {
                        Gson gson = new Gson();
                        try {
                            txtStatus.setTextColor(Color.RED);
                            CreateUserClass r = gson.fromJson(response.errorBody().string(), CreateUserClass.class);
                            if(r != null)
                                txtStatus.setText(r.getMessage());
                            else
                                txtStatus.setText("Unknown Error");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginClass> call, Throwable t) {
                    txtStatus.setText("Fail");
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
        fragmentTransaction.commit();
    }

}
