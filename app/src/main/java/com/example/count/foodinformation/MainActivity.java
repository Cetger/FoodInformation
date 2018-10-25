package com.example.count.foodinformation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;

import Model.Values;
import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private TextView txName;
//    private TextView txSurName;
    private Service service;
    private BottomNavigationView nav;
    private FrameLayout frameLayout;
    private FragmentAdd fragmentAdd;
    private FragmentLogin fragmentLogin;
    private FragmentSearch fragmentSearch;
    private FragmentProfile fragmentProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Connection connection = new Connection();
//        txName = findViewById(R.id.txName);
//        txSurName = findViewById(R.id.txtSurname);
//        findViewById(R.id.button).setOnClickListener(this);
//        findViewById(R.id.btnGet).setOnClickListener(this);
//        findViewById(R.id.btnSend).setOnClickListener(this);
        nav = findViewById(R.id.nav);
        fragmentAdd = new FragmentAdd();
        fragmentLogin = new FragmentLogin();
        fragmentSearch = new FragmentSearch();
        fragmentProfile = new FragmentProfile();

        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                findViewById(R.id.txWelcome).setVisibility(View.INVISIBLE);
                switch (menuItem.getItemId()) {
                    case R.id.nav_login:
                        nav.setItemBackgroundResource(R.color.colorPrimary);
                        if(!Common.Logon)
                            setFragment(fragmentLogin);
                        else
                            setFragment(fragmentProfile);
                        return true;
                    case R.id.nav_search:
                        nav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(fragmentSearch);
                        return true;
                    case R.id.nav_add:
                        nav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(fragmentAdd);
                        return true;
                    default:
                        return false;
                }
            }

        });
        frameLayout = findViewById(R.id.frame_layout);
        service = Common.GetService();
    }


    @Override
    public void onClick(View view) {
//        if(view.getId() == R.id.button)
//            startActivity(new Intent(this, CodeScannerActivity.class));
//        else if(view.getId() == R.id.btnGet)
//        {
//
//
//            service.getValues().enqueue(new Callback<Values>() {
//                @Override
//                public void onResponse(Call<Values> call, Response<Values> response) {
//                    if(response.isSuccessful())
//                    {
//                        txName.setText(response.body().getname());
//                        txSurName.setText(response.body().getsurname()[0]);
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<Values> call, Throwable t) {
//                    Log.e("ERROR",t.getMessage());
//                }
//            });
//        }
//        else if(view.getId() == R.id.btnSend)
//        {
//
//            service.postBarcodeNumber("111111").enqueue(new Callback<Values>() {
//                @Override
//                public void onResponse(Call<Values> call, Response<Values> response)
//                {
//                    if(response.isSuccessful())
//                    {
//                        txName.setText(response.body().getname());
//                        txSurName.setText(response.body().getsurname()[0]);
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Values> call, Throwable t)
//                {
//                    Log.e("ERROR",t.getMessage());
//                }
//            });
//        }
    }
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

}


