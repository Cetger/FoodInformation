package com.example.count.foodinformation;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Model.AdminDTO;
import Model.BarcodeDTO;
import Model.GroupProductDTO2;
import Model.LanguagesClass;
import Model.ProductDTO;
import Model.ProductDTO2;
import Model.ProductDTO3;
import Model.UserDTO;
import Model.UserDTO2;
import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.count.foodinformation.R.layout.support_simple_spinner_dropdown_item;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAllProduct extends Fragment {


    public FragmentAllProduct() {
        // Required empty public constructor
    }

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> arrayAdapter2;
    ArrayList languageList;
    List<String> list;
    Service service;
    View dialogView;
    ListView spinner;
    AlertDialog alertDialog;
    int chosen = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_all_product, container, false);
        listView = view.findViewById(R.id.listviewproduct);
        service = Common.GetService();

        dialogView = inflater.inflate(R.layout.language_custom,null);
        arrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_list_item_1);
        arrayAdapter2 = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_list_item_1);
        list = new ArrayList<>();
        languageList = new ArrayList<>();
        FillListView();
        arrayAdapter= new ArrayAdapter<>(getContext(), support_simple_spinner_dropdown_item);
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(dialogView);
        spinner = dialogView.findViewById(R.id.listviewLanguage);
        alertDialog=alertDialogBuilder.create();
        spinner.setOnItemClickListener((adapterView, view12, i, l) -> {
        Bundle bundle = new Bundle();
        fragment_edit_content fragment_edit_content = new fragment_edit_content();
        bundle.putString("BARCODE", list.get(chosen));
        bundle.putString("LANGUAGE",languageList.get(i).toString());
        fragment_edit_content.setArguments(bundle);
        setFragment(fragment_edit_content);
            alertDialog.hide();
        });
        listView.setOnItemClickListener((adapterView, view1, i, l) -> new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText(getString(R.string.War))
                .setContentText(getString(R.string.SelTheOp))
                .setCancelText(getString(R.string.Edit))
                .setConfirmText(getString(R.string.Delete))
                .showCancelButton(true)
                .setConfirmClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.cancel();
                    DeleteProduct(list.get(i));
                }).setCancelClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.cancel();
                    Edit(i);
                })
                .show());
        return view;
    }

    private void Edit(int i2)
    {
        service.GetLanguageListOfProductByBarcodeId(new BarcodeDTO(list.get(i2).toString())).enqueue(new Callback<LanguagesClass>() {
            @Override
            public void onResponse(Call<LanguagesClass> call, Response<LanguagesClass> response) {
                if(response.isSuccessful())
                {
                    arrayAdapter2.clear();
                    languageList.clear();
                    if(response.body().getResult().length>1) {
                        for (int i = 0; i < response.body().getResult().length; i++) {
                            arrayAdapter2.add(response.body().getResult()[i].getLanguageName());
                            languageList.add(response.body().getResult()[i].getLanguageCode());
                        }
                        spinner.setAdapter(arrayAdapter2);
                        chosen=i2;
                        alertDialog.show();
                    }
                    else if(response.body().getResult().length== 1)
                    {
                        Bundle bundle = new Bundle();
                        fragment_edit_content fragment_edit_content = new fragment_edit_content();
                        bundle.putString("BARCODE", list.get(i2));
                        bundle.putString("LANGUAGE",response.body().getResult()[0].getLanguageCode());
                        fragment_edit_content.setArguments(bundle);
                        setFragment(fragment_edit_content);
                    }
                    else if(response.body().getResult().length== 0)
                    {
                        /*Bundle bundle = new Bundle();
                        fragment_edit_content fragmentShowInfo = new fragment_edit_content();
                        bundle.putString("BARCODE", list.get(i2));
                        bundle.putString("LANGUAGE","1");
                        fragmentShowInfo.setArguments(bundle);
                        setFragment(fragmentShowInfo);*/
                        Toast.makeText(getContext(), getString(R.string.nocontent), Toast.LENGTH_SHORT).show();
                    }

                }
            }
            @Override
            public void onFailure(Call<LanguagesClass> call, Throwable t) {
                Toast.makeText(getContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });

    }

    void DeleteProduct(String barcode)
    {
        service.DeleteProduct(barcode).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(getContext(),getString(R.string.productdeletedsuccessfully),Toast.LENGTH_SHORT).show();
                    FillListView();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
    void FillListView()
    {
        arrayAdapter.clear();
        list.clear();
        service.AllProducts().enqueue(new Callback<ProductDTO3>() {
            @Override
            public void onResponse(Call<ProductDTO3> call, Response<ProductDTO3> response) {
                if(response.isSuccessful())
                {
                    for(int i = 0 ; i<response.body().getResult().length;i++)
                    {
                        arrayAdapter.add(response.body().getResult()[i].getProductName());
                        list.add(response.body().getResult()[i].getBarcodeId());
                    }
                    listView.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onFailure(Call<ProductDTO3> call, Throwable t) {

            }
        });
    }
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
