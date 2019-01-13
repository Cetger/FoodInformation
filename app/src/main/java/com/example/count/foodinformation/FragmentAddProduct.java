package com.example.count.foodinformation;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import Model.CategoryNameDTO;
import Model.ProductDTO;
import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.count.foodinformation.MainActivity.Categories;
import static com.example.count.foodinformation.MainActivity.arrayList;
import static com.example.count.foodinformation.R.layout.support_simple_spinner_dropdown_item;
public class FragmentAddProduct extends Fragment {


    public FragmentAddProduct() {
        // Required empty public constructor
    }
    private Service service ;
    EditText txBarcode,txProductName;
    Button btnNext;
    Spinner categorySpinner;
    Bitmap bitmap[];
    ImageView imageView[];
    int IMGNumber = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getView() != null ? getView() :  inflater.inflate(R.layout.fragment_add_product, container, false);
        txBarcode = view.findViewById(R.id.txtBarcode2);
        btnNext = view.findViewById(R.id.btnNext);
        imageView = new ImageView[3];
        imageView[0] = view.findViewById(R.id.imProduct1);
        imageView[1] = view.findViewById(R.id.imProduct2);
        imageView[2] = view.findViewById(R.id.imProduct3);
        service = Common.GetService();
        bitmap = new Bitmap[3];
        btnNext.setOnClickListener(view1 -> {
            if(!txBarcode.getText().toString().equals("") && !txProductName.getText().toString().equals(""))
            {
                String A  = String.valueOf(MainActivity.Categories.get(categorySpinner.getSelectedItem().toString()));
                ProductDTO product = new ProductDTO(txBarcode.getText().toString(), txProductName.getText().toString(), 0, null, null, null, new CategoryNameDTO(A));
                service.CreateProduct(product)
                        .enqueue(new Callback<ProductDTO>() {
                            @Override
                            public void onResponse(Call<ProductDTO> call, Response<ProductDTO> response) {
                                if (response.isSuccessful()) {
                                    // Toast.makeText(getContext(), "CreateProduct Successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), MainActivity.GetErrorMessage(response), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ProductDTO> call, Throwable t) {
                                Toast.makeText(getContext(), "CreateProduct Failure", Toast.LENGTH_SHORT).show();
                            }
                        });
                FragmentAddContent fragmentAddContent = new FragmentAddContent();
                Bundle bundle = new Bundle();
                bundle.putString("BARCODE",txBarcode.getText().toString());
                fragmentAddContent.setArguments(bundle);
                setFragment(fragmentAddContent);
            }

        });
        txProductName = view.findViewById(R.id.txProductName2);
        categorySpinner = view.findViewById(R.id.categorySpinner2);
        if(!Categories.isEmpty())
        {
            categorySpinner.setAdapter(new ArrayAdapter<>(getContext(), support_simple_spinner_dropdown_item, arrayList));
        }
        Bundle bundle = this.getArguments();
        if (bundle != null)
            new Thread(() -> getActivity().runOnUiThread(() -> txBarcode.setText( bundle.getString("BARCODE")))).start();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap[IMGNumber] = (Bitmap) data.getExtras().get("data");
        imageView[IMGNumber].setImageBitmap(bitmap[IMGNumber]);
    }

    private void TakePhoto(int Nr)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        IMGNumber = Nr;
        startActivityForResult(intent,0);
    }
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
