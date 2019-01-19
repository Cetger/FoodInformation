package com.example.count.foodinformation;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Model.BarcodeDTO;
import Model.LanguagesClass;
import Model.SearchByNameDTO;
import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.count.foodinformation.R.layout.support_simple_spinner_dropdown_item;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSearch extends Fragment {


    public FragmentSearch() {
        // Required empty public constructor
    }

    TextView gönderilecek;
    EditText searchEdit;
    ListView listView;
    ArrayAdapter<String> veriAdaptoru;
    List<String> languageList;
    ArrayAdapter<CharSequence> arrayAdapter;
    private Service service ;
    ArrayList BarcodeList;
    boolean first =false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_search, container, false);
        //gönderilecek=view.findViewById(R.id.gonderilecekveri);
        searchEdit=view.findViewById(R.id.searchedittext);
        listView= view.findViewById(R.id.itemlist);
        languageList = new ArrayList<>();
        arrayAdapter= new ArrayAdapter<CharSequence>(getContext(),support_simple_spinner_dropdown_item);
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(getContext());
        View dialogView=inflater.inflate(R.layout.language_custom,null);
        alertDialogBuilder.setView(dialogView);
        ListView spinner = dialogView.findViewById(R.id.listviewLanguage);
        AlertDialog alertDialog=alertDialogBuilder.create();
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                FragmentShowInfo fragmentShowInfo = new FragmentShowInfo();
                bundle.putString("BARCODE",BarcodeList.get(i).toString());
                bundle.putString("LANGUAGE",languageList.get(i).toString());
                fragmentShowInfo.setArguments(bundle);
                setFragment(fragmentShowInfo);
                alertDialog.hide();
            }
        });
        listView.setOnItemClickListener((adapterView, view1, i, l) -> {
            service.GetLanguageListOfProductByBarcodeId(new BarcodeDTO(BarcodeList.get(i).toString())).enqueue(new Callback<LanguagesClass>() {
                @Override
                public void onResponse(Call<LanguagesClass> call, Response<LanguagesClass> response) {
                    if(response.isSuccessful())
                    {
                        arrayAdapter.clear();
                        languageList.clear();
                        if(response.body().getResult().length>1) {
                            for (int i = 0; i < response.body().getResult().length; i++) {
                                arrayAdapter.add(response.body().getResult()[i].getLanguageName());
                                languageList.add(response.body().getResult()[i].getLanguageCode());
                            }

                            first=true;
                            spinner.setAdapter(arrayAdapter);
                            alertDialog.show();
                        }
                        else if(response.body().getResult().length== 1)
                        {
                            Bundle bundle = new Bundle();
                            FragmentShowInfo fragmentShowInfo = new FragmentShowInfo();
                            bundle.putString("BARCODE",BarcodeList.get(i).toString());
                            bundle.putString("LANGUAGE",response.body().getResult()[0].getLanguageCode());
                            fragmentShowInfo.setArguments(bundle);
                            setFragment(fragmentShowInfo);
                        }
                        else if(response.body().getResult().length== 0)
                        {
                            Bundle bundle = new Bundle();
                            FragmentShowInfo fragmentShowInfo = new FragmentShowInfo();
                            bundle.putString("BARCODE",BarcodeList.get(i).toString());
                            bundle.putString("LANGUAGE","1");
                            fragmentShowInfo.setArguments(bundle);
                            setFragment(fragmentShowInfo);
                        }

                    }
                }

                @Override
                public void onFailure(Call<LanguagesClass> call, Throwable t) {
                    Toast.makeText(getContext(),"Failure",Toast.LENGTH_SHORT).show();
                }
            });
        });
        ArrayList list = new ArrayList();
        BarcodeList = new ArrayList();
        service = Common.GetService();
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()>2)
                {
                    service.SearchProductByName(String.valueOf(charSequence)).enqueue(new Callback<SearchByNameDTO>() {
                        @Override
                        public void onResponse(Call<SearchByNameDTO> call, Response<SearchByNameDTO> response) {
                            if(response.isSuccessful())
                            {
                                list.clear();
                                for(int i = 0 ; i < response.body().getResult().length;i++)
                                {
                                    list.add(response.body().getResult()[i].getProductName());
                                    BarcodeList.add(response.body().getResult()[i].getBarcodeId());
                                }
                                veriAdaptoru= new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1,list);
                                listView.setAdapter(veriAdaptoru);

                            }
                            else
                            {
                                list.clear();
                                if(veriAdaptoru!= null)
                                veriAdaptoru.clear();
                                BarcodeList.clear();

                            }
                        }
                        @Override
                        public void onFailure(Call<SearchByNameDTO> call, Throwable t)
                        {
                            list.clear();
                            BarcodeList.clear();
                            if(veriAdaptoru!= null)
                            veriAdaptoru.clear();
                        }
                    });

                }
                else
                {
                    list.clear();
                    if(veriAdaptoru!= null)
                    if(veriAdaptoru!= null)
                    veriAdaptoru.clear();

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
