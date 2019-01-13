package com.example.count.foodinformation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import Model.ContentDTO;
import Model.LanguagesClass;
import Model.NutritionFacts;
import Model.ProductDTO;
import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddNF extends Fragment {

Button button;
EditText txEnergy,txFat,txCarbohydrate,txFiber,txProtein,txSalt,txSaturatedFattyAcids,txTransFattyAcids;
private Service service ;

    public FragmentAddNF() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getView() != null ? getView() :  inflater.inflate(R.layout.fragment_add_n, container, false);
        button = view.findViewById(R.id.btnCreateContent);
        service = Common.GetService();
        txEnergy = view.findViewById(R.id.Energy);
        txFat = view.findViewById(R.id.Fat);
        txCarbohydrate = view.findViewById(R.id.Carbohydrate);
        txFiber = view.findViewById(R.id.Fiber);
        txProtein = view.findViewById(R.id.Protein);
        txSalt = view.findViewById(R.id.Salt);
        txTransFattyAcids = view.findViewById(R.id.TransFattyAcids);
        txSaturatedFattyAcids = view.findViewById(R.id.SaturatedFattyAcids);
        button.setOnClickListener(view1 -> {
            int Count = getArguments().getInt("Lenght Count");
            for(int i = 0 ; i<Count;i++)
            {
                //String ingredients, NutritionFacts nutritionFact, ProductDTO product, String warnings, String cookingTips, String recommendations, String videoURL, LanguagesClass language, String details) {
                ProductDTO productDTO = new ProductDTO(getArguments().getString("BARCODE"));
                LanguagesClass languagesClass = new LanguagesClass(getArguments().getString("Language"+String.valueOf(i)));
                NutritionFacts nutritionFacts = new NutritionFacts(txEnergy.getText().toString(),txFat.getText().toString(),txSaturatedFattyAcids.getText().toString(),txTransFattyAcids.getText().toString(),txCarbohydrate.getText().toString(),txProtein.getText().toString(),txSalt.getText().toString());
                ContentDTO contentDTO = new ContentDTO(getArguments().getString("Ingredients"+String.valueOf(i)),nutritionFacts,productDTO,getArguments().getString("Warnings"+String.valueOf(i)),getArguments().getString("Cooking Tips"+String.valueOf(i)),getArguments().getString("Recommendations"+String.valueOf(i)),"",languagesClass,getArguments().getString("Details"+String.valueOf(i)));
                contentDTO.setCreatedUserId(FragmentLogin.UserID);
                service.CreateContentOfProduct(contentDTO).enqueue(new Callback<ContentDTO>() {
                    @Override
                    public void onResponse(Call<ContentDTO> call, Response<ContentDTO> response) {
                        if(response.isSuccessful())
                        {

                        }
                    }
                    @Override
                    public void onFailure(Call<ContentDTO> call, Throwable t)
                    {

                    }
                });
            }

        });
        return view;
    }

}
