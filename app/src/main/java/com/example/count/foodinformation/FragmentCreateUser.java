package com.example.count.foodinformation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import Model.CreateUserClass;
import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class FragmentCreateUser extends Fragment implements View.OnClickListener {
    private Service service;
    private EditText txtName,txtSurname,txtEmail,txtPassword,txtUsername;
    private TextView txtStatus;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_create_user, container, false);
        view.findViewById(R.id.btnCreateAccount).setOnClickListener(this);
        service = Common.GetService();
        txtName = view.findViewById(R.id.txtName);
        txtSurname = view.findViewById(R.id.txtSurName);
        txtUsername = view.findViewById(R.id.txtID);
        txtPassword = view.findViewById(R.id.txtName);
        txtEmail = view.findViewById(R.id.txtName);
        txtStatus = view.findViewById(R.id.txtStatus);
        return view;
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnCreateAccount)
        {
            service.CreateUser(new CreateUserClass(txtName.getText().toString(),txtSurname.getText().toString(),txtEmail.getText().toString(),txtPassword.getText().toString(),txtUsername.getText().toString())).enqueue(new Callback<CreateUserClass>() {
                @Override
                public void onResponse(Call<CreateUserClass> call, Response<CreateUserClass> response)
                {
                    if(response.isSuccessful())
                    {
                        txtStatus.setText("Success");
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
                public void onFailure(Call<CreateUserClass> call, Throwable t) {
                    txtStatus.setText("Fail");
                }
            });
        }
    }

}
