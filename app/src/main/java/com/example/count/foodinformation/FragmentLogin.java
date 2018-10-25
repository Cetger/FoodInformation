package com.example.count.foodinformation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
        view.findViewById(R.id.btnLogin).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogin)
        { 
            setFragment(new FragmentProfile());
           /* service.getResponse(new Parameter("adwada")).enqueue(new Callback<PostResponse>() {
                @Override
                public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                    if(response.isSuccessful())
                    {

                    }
                }

                @Override
                public void onFailure(Call<PostResponse> call, Throwable t) {

                }
            });*/
        }
    }
    private void setFragment(Fragment fragment)
    {

        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

}
