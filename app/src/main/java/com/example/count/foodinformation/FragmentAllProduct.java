package com.example.count.foodinformation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import Model.AdminDTO;
import Model.UserDTO;
import Model.UserDTO2;
import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAllProduct extends Fragment {


    public FragmentAllProduct() {
        // Required empty public constructor
    }

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    List<String> list;
    Service service;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_fragment_all_product, container, false);

        return view;
    }
    void DeleteProduct(String username)
    {
        service.DeleteUserByUsername(new UserDTO(username,FragmentLogin.UserID)).enqueue(new Callback<AdminDTO>() {
            @Override
            public void onResponse(Call<AdminDTO> call, Response<AdminDTO> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(getContext(),"User successfully deleted",Toast.LENGTH_SHORT).show();
                    FillListView();
                }
            }
            @Override
            public void onFailure(Call<AdminDTO> call, Throwable t) {

            }
        });
    }
    void FillListView()
    {
        arrayAdapter.clear();
        list.clear();
        service.GetAllUsersOrderByName().enqueue(new Callback<UserDTO2>() {
            @Override
            public void onResponse(Call<UserDTO2> call, Response<UserDTO2> response){
                if(response.isSuccessful())
                {
                    for(int i = 0 ; i<response.body().getResult().length;i++)
                    {
                        arrayAdapter.add(response.body().getResult()[i].getName());
                        list.add(response.body().getResult()[i].getUsername());
                    }
                    listView.setAdapter(arrayAdapter);
                }
            }
            @Override
            public void onFailure(Call<UserDTO2> call, Throwable t) {

            }
        });
    }
}
