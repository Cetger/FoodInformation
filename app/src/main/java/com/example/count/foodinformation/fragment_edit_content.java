package com.example.count.foodinformation;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Model.ContentDTO;
import Model.LanguageAndProductDTO;
import Model.LanguagesClass;
import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.count.foodinformation.R.layout.support_simple_spinner_dropdown_item;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_edit_content extends Fragment {
    Service service;

    public fragment_edit_content()
    {

    }
    public static ContentDTO contentDTO;
    EditText Ingredients,Warnings,CookingTips,Recommendations,VideoURL,Details,AverageVote,Energy,Fat,SaturatedFattyAcids,TransFattyAcids,Carbohydrate,Fiber,Protein,Salt;
    Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        String barcode = getArguments().getString("BARCODE");
        String language = getArguments().getString("LANGUAGE");
        service = Common.GetService();
        Ingredients = view.findViewById(R.id.txEIngredients);
        Warnings = view.findViewById(R.id.txEWarnings);
        CookingTips = view.findViewById(R.id.txECookingTips);
        Recommendations = view.findViewById(R.id.txERecommendations);
        VideoURL = view.findViewById(R.id.txEVideoURL);
        Details = view.findViewById(R.id.txEDetails);
        AverageVote = view.findViewById(R.id.txEAverageVote);
        Energy = view.findViewById(R.id.txEEnergy);
        Fat = view.findViewById(R.id.txEFat);
        SaturatedFattyAcids = view.findViewById(R.id.txESaturatedFattyAcids);
        TransFattyAcids = view.findViewById(R.id.txETransFattyAcids);
        Carbohydrate = view.findViewById(R.id.txECarbohydrate);
        Fiber = view.findViewById(R.id.txEFiber);
        Protein = view.findViewById(R.id.txEProtein);
        Salt= view.findViewById(R.id.txeSalt);
        button= view.findViewById(R.id.btnUpdateContent);
        button.setOnClickListener(view1 -> {
            contentDTO.setIngredients(Ingredients.getText().toString());
            contentDTO.setWarnings(Warnings.getText().toString());
            contentDTO.setIngredients(CookingTips.getText().toString());
            contentDTO.setIngredients(Recommendations.getText().toString());
            contentDTO.setIngredients(VideoURL.getText().toString());
            contentDTO.getNutritionFact().setEnergy(Energy.getText().toString());
            contentDTO.getNutritionFact().setFat(Fat.getText().toString());
            contentDTO.getNutritionFact().setSaturatedFattyAcids(SaturatedFattyAcids.getText().toString());
            contentDTO.getNutritionFact().setTransFattyAcids(TransFattyAcids.getText().toString());
            contentDTO.getNutritionFact().setCarbohydrate(Carbohydrate.getText().toString());
            contentDTO.getNutritionFact().setFiber(Fiber.getText().toString());
            contentDTO.getNutritionFact().setProtein(Protein.getText().toString());
            contentDTO.getNutritionFact().setSalt(Salt.getText().toString());
            contentDTO.setModifiedUserId(FragmentLogin.UserID);
            contentDTO.setLanguage(new LanguagesClass(language));
            service.UpdateContent(contentDTO).enqueue(new Callback<ContentDTO>() {
                @Override
                public void onResponse(Call<ContentDTO> call, Response<ContentDTO> response) {
                    if(response.isSuccessful())
                    {
                        Toast.makeText(getContext(),getString(R.string.ContentUpload),Toast.LENGTH_SHORT).show();
                        getFragmentManager().popBackStack();
                    }
                }

                @Override
                public void onFailure(Call<ContentDTO> call, Throwable t) {

                }
            });
        });
        service.GetProductContentByLanguageCode(new LanguageAndProductDTO(barcode,language)).enqueue(new Callback<ContentDTO>() {
            @Override
            public void onResponse(Call<ContentDTO> call, Response<ContentDTO> response) {
                if(response.isSuccessful())
                {
                    contentDTO = response.body().getResult();
                    Ingredients.setText(response.body().getResult().getIngredients());
                    Warnings.setText(response.body().getResult().getWarnings());
                    CookingTips.setText(response.body().getResult().getCookingTips());
                    Recommendations.setText(response.body().getResult().getRecommendations());
                    VideoURL.setText(response.body().getResult().getVideoURL());
                    Details.setText(response.body().getResult().getDetails());
                    Energy.setText(response.body().getResult().getNutritionFact().getEnergy());
                    Fat.setText(response.body().getResult().getNutritionFact().getFat());
                    SaturatedFattyAcids.setText(response.body().getResult().getNutritionFact().getSaturatedFattyAcids());
                    TransFattyAcids.setText(response.body().getResult().getNutritionFact().getTransFattyAcids());
                    Carbohydrate.setText(response.body().getResult().getNutritionFact().getCarbohydrate());
                    Fiber.setText(response.body().getResult().getNutritionFact().getFiber());
                    Protein.setText(response.body().getResult().getNutritionFact().getProtein());
                    Salt.setText(response.body().getResult().getNutritionFact().getSalt());
                }
            }
            @Override
            public void onFailure(Call<ContentDTO> call, Throwable t) {

            }
        });
        return view;
    }
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
