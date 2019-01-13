package com.example.count.foodinformation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import Components.MultiSpinner;
import Model.BarcodeDTO;
import Model.LanguagesClass;
import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddContent extends Fragment {

    private List<String> list = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private List<String> SelectedLanguages = new ArrayList<>();
    private Service service;
    private LinearLayout layout;
    private Button btnNext;
    private EditText Ingredients[] ,Warnings[],CookingTips[],Recommendations[],Details[];
    int SelectedCount;

    public FragmentAddContent() {
        // Required empty public constructor
    }
    public MultiSpinner multiSpinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getView() != null ? getView() :  inflater.inflate(R.layout.fragment_fragment_add_content, container, false);
        service = Common.GetService();
        layout = view.findViewById(R.id.Layout_Content);
        String BARCODE = this.getArguments().getString("BARCODE");
        btnNext = view.findViewById(R.id.btnNext2);

        service.GetLanguageListOfProductByBarcodeId(new BarcodeDTO(BARCODE)).enqueue(new Callback<LanguagesClass>() {
            @Override
            public void onResponse(Call<LanguagesClass> call, Response<LanguagesClass> response) {
                if(response.isSuccessful())
                {
                    AtomicBoolean found = new AtomicBoolean(false);
                    for(int i = 0 ; i<response.body().getResult().length;i++)
                    {
                        list.add(response.body().getResult()[i].getLanguageName());
                    }
                    multiSpinner = view.findViewById(R.id.LanguageMS);
                    List<String> llist2 = new ArrayList<>(MainActivity.llist);
                    List<String> lclist2 = new ArrayList<>(MainActivity.LanguageCodes);
                    if(!list.isEmpty())
                    {
                        for(int i = 0;i<list.size();i++)
                        {
                            llist2.remove(list.get(i));
                            lclist2.remove(list.get(i));
                        }
                    }

                    multiSpinner.setItems(llist2, "Languages", (boolean[] selected) -> {
                    layout.removeAllViews();
                    layout.addView(multiSpinner);
                    layout.addView(btnNext);
                    SelectedCount= 0;
                        for (int i = 0;i<selected.length;i++)
                        {
                            if(selected[i]){
                                SelectedCount++;
                                SelectedLanguages.add(lclist2.get(i));
                            }

                        }
                    Ingredients = new EditText[SelectedCount];
                    Warnings = new EditText[SelectedCount];
                    CookingTips = new EditText[SelectedCount];
                    Recommendations = new EditText[SelectedCount];
                    Details = new EditText[SelectedCount];
                    int ItemCount = 0;
                    for (int i = 0;i<selected.length;i++)
                    {

                        if(selected[i])
                        {

                            if(!found.get())
                            {
                                TextView textView = new TextView(getContext());
                                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                                textView.setText("Ingredients : ");
                                layout.addView(textView);
                                found.set(true);

                            }
                            Ingredients[ItemCount] = new EditText(getContext());
                            Ingredients[ItemCount].setEms(10);
                            Ingredients[ItemCount].setHint(lclist2.get(i).toUpperCase()+":");
                            Ingredients[ItemCount].setInputType(InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE);
                            Ingredients[ItemCount].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                            layout.addView(Ingredients[ItemCount]);
                            ItemCount++;
                        }
                    }
                        ItemCount = 0;
                        found.set(false);
                    for (int i = 0;i<selected.length;i++)
                    {

                        if(selected[i])
                        {
                            if(!found.get())
                            {
                                TextView textView2 = new TextView(getContext());
                                textView2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                                textView2.setText("Warnings : ");
                                layout.addView(textView2);
                                found.set(true);
                            }
                            Warnings[ItemCount] = new EditText(getContext());
                            Warnings[ItemCount].setEms(10);
                            Warnings[ItemCount].setHint(lclist2.get(i).toUpperCase()+":");
                            Warnings[ItemCount].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                            layout.addView(Warnings[ItemCount]);
                            ItemCount++;
                        }
                    }
                        found.set(false);

                        ItemCount = 0;
                    for (int i = 0;i<selected.length;i++)
                    {

                        if(selected[i])
                        {
                            if(!found.get())
                            {
                                TextView textView3 = new TextView(getContext());
                                textView3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                                textView3.setText("Cooking Tips : ");
                                layout.addView(textView3);
                                found.set(true);

                            }
                            CookingTips[ItemCount] = new EditText(getContext());
                            CookingTips[ItemCount].setEms(10);
                            CookingTips[ItemCount].setHint(lclist2.get(i).toUpperCase()+":");
                            CookingTips[ItemCount].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                            layout.addView(CookingTips[ItemCount]);
                            ItemCount++;
                        }
                    }
                        found.set(false);

                        ItemCount = 0;
                    for (int i = 0;i<selected.length;i++)
                    {

                        if(selected[i])
                        {
                            if(!found.get())
                            {
                                TextView textView4 = new TextView(getContext());
                                textView4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                                textView4.setText("Recommendations : ");
                                layout.addView(textView4);
                                found.set(true);
                            }
                            Recommendations[ItemCount] = new EditText(getContext());
                            Recommendations[ItemCount] .setEms(10);
                            Recommendations[ItemCount] .setHint(lclist2.get(i).toUpperCase()+":");
                            Recommendations[ItemCount] .setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                            layout.addView(Recommendations[ItemCount] );
                            ItemCount++;
                        }
                    }

                        ItemCount = 0;
                    found.set(false);
                    for (int i = 0;i<selected.length;i++)
                    {

                        if(selected[i])
                        {
                            if(!found.get())
                            {
                                TextView textView5 = new TextView(getContext());
                                textView5.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                                textView5.setText("Details : ");
                                layout.addView(textView5);
                                found.set(true);
                            }
                            Details[ItemCount] = new EditText(getContext());
                            Details[ItemCount].setEms(10);
                            Details[ItemCount].setHint(lclist2.get(i).toUpperCase()+":");
                            Details[ItemCount].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                            layout.addView(Details[ItemCount]);
                            ItemCount++;
                        }
                    }
                    });
                }
            }

            @Override
            public void onFailure(Call<LanguagesClass> call, Throwable t) {

            }
        });
        btnNext.setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
            bundle.putInt("Lenght Count",SelectedCount);
            for(int i = 0 ; i<SelectedCount;i++)
            {
                bundle.putString("Language"+String.valueOf(i),SelectedLanguages.get(i));
                bundle.putString("Ingredients"+String.valueOf(i),Ingredients[i].getText().toString());
                bundle.putString("Warnings"+String.valueOf(i),Warnings[i].getText().toString());
                bundle.putString("Cooking Tips"+String.valueOf(i),CookingTips[i].getText().toString());
                bundle.putString("Recommendations"+String.valueOf(i),Recommendations[i].getText().toString());
                bundle.putString("Details"+String.valueOf(i),Details[i].getText().toString());
            }
            FragmentAddNF fragmentAddNF = new FragmentAddNF();
            bundle.putString("BARCODE",getArguments().getString("BARCODE"));
            fragmentAddNF.setArguments(bundle);
            setFragment(fragmentAddNF);
        });
        return view;
    }
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction  =  getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        //  fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
