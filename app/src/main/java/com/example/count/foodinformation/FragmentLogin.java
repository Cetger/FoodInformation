package com.example.count.foodinformation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import Model.Parameter;
import Model.PostResponse;
import Model.Values;
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
    public FragmentLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_login, container, false);
        service = Common.GetService();
        txID = view.findViewById(R.id.txID);
        txPW = view.findViewById(R.id.txPW);
        view.findViewById(R.id.btnLogin).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogin)
        {
            service.getResponse(new Parameter("adwada")).enqueue(new Callback<PostResponse>() {
                @Override
                public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                    if(response.isSuccessful())
                    {

                    }
                }

                @Override
                public void onFailure(Call<PostResponse> call, Throwable t) {

                }
            });
           /* service.postBarcodeNumber("111111").enqueue(new Callback<Values>() {
                @Override
                public void onResponse(Call<Values> call, Response<Values> response)
                {
                   if(response.isSuccessful())
                    {
                        txID.setText(response.body().getname());
                        txPW.setText(response.body().getsurname()[0]);
                    }
                }

                @Override
                public void onFailure(Call<Values> call, Throwable t)
               {
                    Log.e("ERROR",t.getMessage());
               }
           });*/
        }
    }
}
