package com.example.count.foodinformation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import static com.example.count.foodinformation.MainActivity.Categories;
import static com.example.count.foodinformation.MainActivity.arrayList;
import static com.example.count.foodinformation.R.layout.support_simple_spinner_dropdown_item;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAdd extends Fragment {

    private Spinner categorySpinner;

    public FragmentAdd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_add, container, false);
        categorySpinner = view.findViewById(R.id.categorySpinner);
        if(!Categories.isEmpty())
        {
            categorySpinner.setAdapter(new ArrayAdapter<CharSequence>(getContext(),support_simple_spinner_dropdown_item,arrayList));
        }
        return view;
    }

}
