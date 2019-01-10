package com.example.count.foodinformation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import Model.BarcodeDTO;
import Model.CategoryNameDTO;
import Model.ContentDTO;
import Model.ProductDTO;
import Model.ProductDTO2;
import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.count.foodinformation.MainActivity.Categories;
import static com.example.count.foodinformation.MainActivity.arrayList;
import static com.example.count.foodinformation.MainActivity.mainActivity;
import static com.example.count.foodinformation.R.layout.support_simple_spinner_dropdown_item;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAdd extends Fragment {

    private Spinner categorySpinner;
    private Service service ;
    public FragmentAdd() {
        // Required empty public constructor
    }
    public EditText txBarcode,txProductName,txContents,txRecommendations;
    RatingBar ratingBar;
    Button btnAdd,btnTest;
    ImageButton btnGetBarcode;
    public String a = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getView() != null ? getView() : inflater.inflate(R.layout.fragment_fragment_add, container, false);
        view.notifyAll();
                categorySpinner = view.findViewById(R.id.categorySpinner);
        service = Common.GetService();
        if(!Categories.isEmpty())
        {
            categorySpinner.setAdapter(new ArrayAdapter<>(getContext(), support_simple_spinner_dropdown_item, arrayList));
        }
        if(txBarcode== null)
        txBarcode = view.findViewById(R.id.txtBarcode);
        txProductName = view.findViewById(R.id.txtProductName);
        Toast.makeText(getContext(), txBarcode.getText().toString(), Toast.LENGTH_SHORT).show();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
           // String B = txBarcode.getText().toString();
           // String A = bundle.getString("BARCODE");
            txProductName.setText("122");
        }
        txRecommendations = view.findViewById(R.id.txRecommendations);
        txContents = view.findViewById(R.id.txContents);

        btnAdd = view.findViewById(R.id.btnAdd);
        btnTest = view.findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),txBarcode.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        btnGetBarcode = view.findViewById(R.id.btnGetBarcode);
        btnGetBarcode.setOnClickListener(view12 -> {
            FragmentSearch fragmentSearch =  mainActivity.fragmentSearch;
            fragmentSearch.getBarcode = true;
            setFragment(fragmentSearch);
        });

        btnAdd.setOnClickListener(view1 -> {
            String A  = String.valueOf(MainActivity.Categories.get(categorySpinner.getSelectedItem().toString()));
            ProductDTO product = new ProductDTO(txBarcode.getText().toString(),txProductName.getText().toString(),0,null,null,null,new CategoryNameDTO(A));
            service.CreateProduct(product)
                    .enqueue(new Callback<ProductDTO>() {
                        @Override
                        public void onResponse(Call<ProductDTO> call, Response<ProductDTO> response) {
                            if (response.isSuccessful())
                            {
                                Toast.makeText(getContext(), "CreateProduct Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), MainActivity.GetErrorMessage(response), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ProductDTO> call, Throwable t) {
                            Toast.makeText(getContext(), "CreateProduct Failure", Toast.LENGTH_SHORT).show();
                        }
                    });
            ContentDTO contentDTO = new ContentDTO(txContents.getText().toString(),txRecommendations.getText().toString());
            contentDTO.setProduct(new ProductDTO(txBarcode.getText().toString()));
            contentDTO.setCreatedUserId(FragmentLogin.UserID);
            service.CreateContentOfProduct(contentDTO).enqueue(new Callback<ContentDTO>() {
                @Override
                public void onResponse(Call<ContentDTO> call, Response<ContentDTO> response) {
                    if (response.isSuccessful())
                    {
                        Toast.makeText(getContext(), "CreateContentOfProduct Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), MainActivity.GetErrorMessage(response), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ContentDTO> call, Throwable t) {
                    Toast.makeText(getContext(), "CreateContentOfProduct Failure", Toast.LENGTH_SHORT).show();
                }
            });

        });
        txBarcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                txProductName.setEnabled(true);
                service.GetProductNameByBarcodeId(new BarcodeDTO(txBarcode.getText().toString())).enqueue(new Callback<ProductDTO>() {
                    @Override
                    public void onResponse(Call<ProductDTO> call, Response<ProductDTO> response) {
                        if(response.isSuccessful())
                        {
                            txProductName.setText(response.body().getResult().getProductName());
                            txProductName.setEnabled(false);
                        }
                     //   Toast.makeText(getContext(),"GetProductName Error",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ProductDTO> call, Throwable t) {
                        Toast.makeText(getContext(),"GetProductName Failure",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction  =  getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
