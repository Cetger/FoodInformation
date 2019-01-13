package com.example.count.foodinformation;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Model.ContentDTO;
import Model.LanguageAndProductDTO;
import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentShowInfo extends Fragment {
    private Service service ;
    private TextView txIngredients,txProductCategory,txProductName;
    public FragmentShowInfo() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getView() != null ? getView() : inflater.inflate(R.layout.fragment_show_info, container, false);
        service = Common.GetService();
        txIngredients = view.findViewById(R.id.txIngredients);
        txProductCategory = view.findViewById(R.id.txProductCategory);
        txProductName = view.findViewById(R.id.txProductName);
        service.GetProductContentByLanguageCode(new LanguageAndProductDTO(getArguments().getString("BARCODE"),MainActivity.Language)).enqueue(new Callback<ContentDTO>() {
            @Override
            public void onResponse(Call<ContentDTO> call, Response<ContentDTO> response) {
                if(response.isSuccessful())
                {
                    txIngredients.setText(response.body().getResult().Ingredients);
                    txProductCategory.setText(response.body().getResult().getProduct().getProductCategoryDTO().CategoryName);
                    txProductName.setText(response.body().getResult().getProduct().getProductName());
                }
            }
            @Override
            public void onFailure(Call<ContentDTO> call, Throwable t) {
                Toast.makeText(getContext(), "Failure on GetProductContentByLanguageCode", Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }

}
