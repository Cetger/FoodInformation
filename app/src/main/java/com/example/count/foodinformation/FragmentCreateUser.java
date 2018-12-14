package com.example.count.foodinformation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
    private EditText txtName,txtSurname,txtEmail,txtPassword,txtPassword2,txtUsername;
    private TextView txStatus;
    private ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_create_user, container, false);
        view.findViewById(R.id.btnCreateAccount).setOnClickListener(this);
        service = Common.GetService();
        txtName = view.findViewById(R.id.txtName);
        txtSurname = view.findViewById(R.id.txtSurName);
        txtUsername = view.findViewById(R.id.txtID);
        txtPassword = view.findViewById(R.id.txtPW);
        txtPassword2 = view.findViewById(R.id.txtPW2);
        txtEmail = view.findViewById(R.id.txtEmail);
        txStatus = view.findViewById(R.id.txStatusCreate);
        imageView = view.findViewById(R.id.imageView2);
        txtPassword2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(charSequence==txtPassword.getText())
            {
                imageView.setVisibility(View.VISIBLE);
            }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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
                        txStatus.setText("Success");
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
                public void onFailure(Call<CreateUserClass> call, Throwable t) {
                    txStatus.setText("Fail");
                }
            });
        }
    }

}
