package com.example.count.foodinformation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView txIngredients, txProductCategory, txProductName;
    PieChartView pieChartView;
    ArrayList<CommentList> commentlistesi;
    ListView listView;
    private static CommentAdapter adapter;
    private TextView comments;


    public FragmentShowInfo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getView() != null ? getView() : inflater.inflate(R.layout.fragment_show_info, container, false);
        service = Common.GetService();
        txIngredients = view.findViewById(R.id.txIngredients);
        //  txProductCategory = view.findViewById(R.id.txProductCategory);
        txProductName = view.findViewById(R.id.txProductName);
        comments = view.findViewById(R.id.commentsbaslık);

        listView=view.findViewById(R.id.listcomment);
        commentlistesi=new ArrayList<>();
        commentlistesi.add(new CommentList("ali","veli",2.0f));
        commentlistesi.add(new CommentList("2","deneme",3.0f));
        commentlistesi.add(new CommentList("3","farklı yorum",4.0f));
        commentlistesi.add(new CommentList("4","ısrarcı",0.0f));

        adapter=new CommentAdapter(getContext(),commentlistesi);
        listView.setAdapter(adapter);




        comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goster=new Intent(getContext(),commetshow.class);
                startActivity(goster);
            }
        });






        pieChartView = view.findViewById(R.id.chart);

        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(10, Color.parseColor("#d32f2f")).setLabel("PRO: $10"));
        pieData.add(new SliceValue(20, Color.parseColor("#f57c00")).setLabel("KAR: $4"));
        pieData.add(new SliceValue(30, Color.parseColor("#d4e157")).setLabel("YAG: $18"));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartView.setPieChartData(pieChartData);

        service.GetProductContentByLanguageCode(new LanguageAndProductDTO(getArguments().getString("BARCODE"), MainActivity.Language)).enqueue(new Callback<ContentDTO>() {
            @Override
            public void onResponse(Call<ContentDTO> call, Response<ContentDTO> response) {
                if (response.isSuccessful()) {
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
