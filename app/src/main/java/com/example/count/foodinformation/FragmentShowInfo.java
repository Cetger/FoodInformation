package com.example.count.foodinformation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatRatingBar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Bitmap;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.util.ArrayList;
import java.util.List;

import Model.ContentDTO;
import Model.LanguageAndProductDTO;
import Remote.Service;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentShowInfo extends Fragment {
    private Service service;
    private TextView txIngredients, txDetails, txProductName;
    PieChartView pieChartView;
    ArrayList<CommentList> commentlistesi;
    ListView listView;
    ImageView IMG1,IMG2,IMG3,IMGMain;
    AppCompatRatingBar ratingBar;
    private static CommentAdapter adapter;
    private TextView comments;


    public FragmentShowInfo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getView() != null ? getView() : inflater.inflate(R.layout.fragment_show_info, container, false);
        service = Common.GetService();
        txIngredients = view.findViewById(R.id.txIngredients);
        //  txProductCategory = view.findViewById(R.id.txProductCategory);
        txProductName = view.findViewById(R.id.txProductName);
        txDetails = view.findViewById(R.id.Details);
        comments = view.findViewById(R.id.commentsbaslık);
        listView=view.findViewById(R.id.listcomment);
        IMG1=view.findViewById(R.id.IMGShow1);
        IMG2=view.findViewById(R.id.IMGShow2);
        IMG3=view.findViewById(R.id.IMGShow3);
        IMGMain=view.findViewById(R.id.IMGShowMain);

        IMG1.setOnClickListener(view13 -> IMGMain.setImageDrawable(IMG1.getDrawable()));
        IMG2.setOnClickListener(view12 -> IMGMain.setImageDrawable(IMG2.getDrawable()));
        IMG3.setOnClickListener(view1 -> IMGMain.setImageDrawable(IMG3.getDrawable()));
        ratingBar = view.findViewById(R.id.Rate);


        comments.setOnClickListener(v -> {
            Intent goster=new Intent(getContext(),commetshow.class);
            startActivity(goster);
        });

        pieChartView = view.findViewById(R.id.chart);

        List<SliceValue> pieData = new ArrayList<>();
        PieChartData pieChartData;
        pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartView.setPieChartData(pieChartData);
        assert getArguments() != null;
        String barcode = getArguments().getString("BARCODE");
        service.GetProductContentByLanguageCode(new LanguageAndProductDTO(barcode, MainActivity.Language)).enqueue(new Callback<ContentDTO>() {
            @Override
            public void onResponse(@NonNull Call<ContentDTO> call, @NonNull Response<ContentDTO> response) {
                if (response.isSuccessful())  {
                    assert response.body() != null;
                    Bitmap  bitmap;
                    if(response.body().getResult().getProduct().getFirstImage()!= null){
                         bitmap = getBitmapFromString(response.body().getResult().getProduct().getFirstImage());
                        IMG1.setImageBitmap(bitmap);
                        IMGMain.setImageBitmap(bitmap);
                    }
                    if(response.body().getResult().getProduct().getSecondImage()!= null){
                        bitmap = getBitmapFromString(response.body().getResult().getProduct().getSecondImage());
                        IMG2.setImageBitmap(bitmap);
                    }
                    if(response.body().getResult().getProduct().getThirdImage()!= null){
                        bitmap = getBitmapFromString(response.body().getResult().getProduct().getThirdImage());
                        IMG3.setImageBitmap(bitmap);
                    }
                    txDetails.setText(response.body().getResult().getDetails());
                    ratingBar.setRating(Float.valueOf(response.body().getResult().getAverageVote()));
                    pieData.add(new SliceValue(Float.valueOf(response.body().getResult().getNutritionFact().getEnergy()), Color.parseColor("#d32f2f")).setLabel("Energy"));
                    pieData.add(new SliceValue(Float.valueOf(response.body().getResult().getNutritionFact().getFat()), Color.parseColor("#f57c00")).setLabel("Fat"));
                    pieData.add(new SliceValue(Float.valueOf(response.body().getResult().getNutritionFact().getSaturatedFattyAcids()), Color.parseColor("#d4e157")).setLabel("SaturatedFattyAcids"));
                    pieData.add(new SliceValue(Float.valueOf(response.body().getResult().getNutritionFact().getTransFattyAcids()), Color.parseColor("#d32f2f")).setLabel("TransFattyAcids"));
                    pieData.add(new SliceValue(Float.valueOf(response.body().getResult().getNutritionFact().getCarbohydrate()), Color.parseColor("#f57c00")).setLabel("Carbohydrate"));
                    pieData.add(new SliceValue(Float.valueOf(response.body().getResult().getNutritionFact().getFiber()), Color.parseColor("#d4e157")).setLabel("Fiber"));
                    pieData.add(new SliceValue(Float.valueOf(response.body().getResult().getNutritionFact().getProtein()), Color.parseColor("#d32f2f")).setLabel("Protein"));
                    pieData.add(new SliceValue(Float.valueOf(response.body().getResult().getNutritionFact().getSalt()), Color.parseColor("#f57c00")).setLabel("Salt"));
                    txIngredients.setText(response.body().getResult().getIngredients());
                    txProductName.setText(response.body().getResult().getProduct().getProductName() );//+ System.lineSeparator()+response.body().getResult().getProduct().getProductCategoryDTO().getCategoryName());
                    commentlistesi=new ArrayList<>();
                    for(int i = 0 ; i<response.body().getResult().getComments().size();i++)
                        commentlistesi.add(new CommentList("Huseyin",response.body().getComments().get(i).getUserComment(),2.0f));
                    adapter=new CommentAdapter(view.getContext(),commentlistesi);
                    listView.setAdapter(adapter);

                }
                else
                {
                    new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Product Couldn't Found")
                            .setContentText("Do you wanna add product ?")
                            .setCancelText("No")
                            .setConfirmText("Yes")
                            .showCancelButton(true)
                            .setConfirmClickListener(sDialog -> {
                                Bundle bundle = new Bundle();
                                bundle.putString("BARCODE",barcode);
                                FragmentAddProduct fragmentAddContent = new FragmentAddProduct();
                                fragmentAddContent.setArguments(bundle);
                                sDialog.cancel();
                                setFragment(fragmentAddContent);
                            })
                            .setCancelClickListener(sweetAlertDialog -> {
                                getFragmentManager().popBackStack();
                                sweetAlertDialog.cancel();
                            }).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ContentDTO> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Failure on GetProductContentByLanguageCode", Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }
    private Bitmap getBitmapFromString(String jsonString) {
        /*
         * This Function converts the String back to Bitmap
         * */
        byte[] decodedString = Base64.decode(jsonString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
