package com.example.count.foodinformation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Model.AdminDTO;
import Model.ModeratorDTO;
import Model.UserDTO;
import Model.UserDTO2;
import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAllUsers extends Fragment {
    public FragmentAllUsers() {
        // Required empty public constructor
    }
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    List<String>  list;
    List<Boolean>  listadmin;
    List<Boolean>  listmoderator;
    Service service;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_all_users, container, false);
        listView = view.findViewById(R.id.listviewuser);
        list = new ArrayList<>();
        listadmin = new ArrayList<>();
        listmoderator = new ArrayList<>();
        service = Common.GetService();
        listView.setOnItemClickListener((adapterView, view1, i, l) -> new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText(getString(R.string.War))
                .setContentText(getString(R.string.SelTheOp))
                .setCancelText(getString(R.string.Edit))
                .setConfirmText(getString(R.string.Delete))
                .showCancelButton(true)
                .setConfirmClickListener(sweetAlertDialog -> {
                sweetAlertDialog.cancel();
                Delete(i);
                }).setCancelClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.cancel();
                    Edit(i);
                })
                .show());
        arrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_list_item_1);
        FillListView();
        return view;
    }
    void Edit(int i)
    {
        String STR1,STR2;
        if(!listmoderator.get(i) && !listadmin.get(i))
        {
            STR1 = "Admin";
            STR2 = "Moderator";
        }
        else if (listmoderator.get(i))
        {
            STR1 = "Admin";
            STR2 = "User";
        }
        else
        {
            STR1 = "Moderator";
            STR2 = "User";
        }
        new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText(getString(R.string.War))
                .setContentText(getString(R.string.UseWillBe))
                .setCancelText(STR1)
                .setConfirmText(STR2)
                .showCancelButton(true)
                .setConfirmClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.cancel();
                    if(STR2=="User")
                        setNormalUser(list.get(i));
                    else if(STR2=="Admin")
                        setAdmin(list.get(i));
                    else if(STR2=="Moderator")
                        setModerator(list.get(i));
                })
                .setCancelClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.cancel();
                    if(STR1=="User")
                        setNormalUser(list.get(i));
                    else if(STR1=="Admin")
                        setAdmin(list.get(i));
                    else if(STR1=="Moderator")
                        setModerator(list.get(i));

                }).show();
    }
    void Delete(int i)
    {
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText(getString(R.string.War))
                .setContentText(getString(R.string.UseWilDel))
                .setCancelText("NO")
                .setConfirmText("YES")
                .showCancelButton(true)
                .setConfirmClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.cancel();
                    DeleteUser(list.get(i));
                }).show();
    }
    void DeleteUser(String username)
    {
        service.DeleteUserByUsername(new UserDTO(username,FragmentLogin.UserID)).enqueue(new Callback<AdminDTO>() {
            @Override
            public void onResponse(Call<AdminDTO> call, Response<AdminDTO> response) {
                if(response.isSuccessful())
                {
                    FillListView();
                }
            }
            @Override
            public void onFailure(Call<AdminDTO> call, Throwable t) {

            }
        });
    }
    void setAdmin(String username)
    {
        service.SetAdminByUsername(new UserDTO(username,FragmentLogin.UserID)).enqueue(new Callback<AdminDTO>() {
            @Override
            public void onResponse(Call<AdminDTO> call, Response<AdminDTO> response) {
                if(response.isSuccessful())
                {
                    FillListView();
                }
            }
            @Override
            public void onFailure(Call<AdminDTO> call, Throwable t) {

            }
        });
    }
    void setNormalUser(String username)
    {
        service.SetNormalUserByUsername(new UserDTO(username,FragmentLogin.UserID)).enqueue(new Callback<AdminDTO>() {
            @Override
            public void onResponse(Call<AdminDTO> call, Response<AdminDTO> response) {
                if(response.isSuccessful())
                {
                    FillListView();
                }
            }
            @Override
            public void onFailure(Call<AdminDTO> call, Throwable t) {

            }
        });
    }
    void setModerator(String username)
    {
        service.SetModeratorByUsername(new UserDTO(username,FragmentLogin.UserID)).enqueue(new Callback<ModeratorDTO>() {
            @Override
            public void onResponse(Call<ModeratorDTO> call, Response<ModeratorDTO> response) {
                if(response.isSuccessful())
                {
                    FillListView();
                }
            }
            @Override
            public void onFailure(Call<ModeratorDTO> call, Throwable t) {

            }
        });
    }
    void FillListView()
    {
        arrayAdapter.clear();
        list.clear();
        listadmin.clear();
        listmoderator.clear();
        service.GetAllUsersOrderByName().enqueue(new Callback<UserDTO2>() {
            @Override
            public void onResponse(Call<UserDTO2> call, Response<UserDTO2> response){
                if(response.isSuccessful())
                {
                    for(int i = 0 ; i<response.body().getResult().length;i++)
                    {
                        if(response.body().getResult()[i].isAdmin())
                            arrayAdapter.add(response.body().getResult()[i].getName() + " [ADMIN]");
                        else if(response.body().getResult()[i].isModerator())
                            arrayAdapter.add(response.body().getResult()[i].getName() + " [MODERATOR]");
                        else
                            arrayAdapter.add(response.body().getResult()[i].getName() + " [USER]");
                        list.add(response.body().getResult()[i].getUsername());
                        listadmin.add(response.body().getResult()[i].isAdmin());
                        listmoderator.add(response.body().getResult()[i].isModerator());
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
