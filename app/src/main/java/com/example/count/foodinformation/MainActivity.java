package com.example.count.foodinformation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;

import Model.Values;
import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txName;
    private TextView txSurName;
    private Service service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txName = findViewById(R.id.txName);
        txSurName = findViewById(R.id.txtSurname);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.btnGet).setOnClickListener(this);
        findViewById(R.id.btnSend).setOnClickListener(this);

        service = Common.GetService();
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button)
            startActivity(new Intent(this, CodeScannerActivity.class));
        else if(view.getId() == R.id.btnGet)
        {


            service.getValues().enqueue(new Callback<Values>() {
                @Override
                public void onResponse(Call<Values> call, Response<Values> response) {
                    if(response.isSuccessful())
                    {
                        txName.setText(response.body().getname());
                        txSurName.setText(response.body().getsurname()[0]);
                    }

                }

                @Override
                public void onFailure(Call<Values> call, Throwable t) {
                    Log.e("ERROR",t.getMessage());
                }
            });
        }
        else if(view.getId() == R.id.btnSend)
        {

            service.postBarcodeNumber("111111").enqueue(new Callback<Values>() {
                @Override
                public void onResponse(Call<Values> call, Response<Values> response)
                {
                    if(response.isSuccessful())
                    {
                        txName.setText(response.body().getname());
                        txSurName.setText(response.body().getsurname()[0]);
                    }
                }

                @Override
                public void onFailure(Call<Values> call, Throwable t)
                {
                    Log.e("ERROR",t.getMessage());
                }
            });
        }
    }

}

