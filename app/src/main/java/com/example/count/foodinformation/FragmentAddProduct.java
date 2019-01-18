package com.example.count.foodinformation;


import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.renderscript.Element;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import Model.CategoryNameDTO;
import Model.ProductDTO;
import Remote.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static android.provider.MediaStore.EXTRA_SIZE_LIMIT;
import static com.example.count.foodinformation.MainActivity.Categories;
import static com.example.count.foodinformation.MainActivity.arrayList;
import static com.example.count.foodinformation.MainActivity.mainActivity;
import static com.example.count.foodinformation.R.layout.support_simple_spinner_dropdown_item;
public class FragmentAddProduct extends Fragment {


    private ContentValues values;

    public FragmentAddProduct() {
        // Required empty public constructor
    }
    private Service service ;
    EditText txBarcode,txProductName;
    Button btnNext;
    Spinner categorySpinner;
    Bitmap bitmap[];
    ImageView imageView[],imgScan;
    Uri imageUri;
    int IMGNumber = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getView() != null ? getView() :  inflater.inflate(R.layout.fragment_add_product, container, false);
        txBarcode = view.findViewById(R.id.txtBarcode2);
        btnNext = view.findViewById(R.id.btnNext);
        imageView = new ImageView[3];
        imageView[0] = view.findViewById(R.id.IMG1);
        imageView[1] = view.findViewById(R.id.IMG2);
        imageView[2] = view.findViewById(R.id.IMG3);
        imgScan = view.findViewById(R.id.imgScan);
        imageView[0].setOnClickListener(view14 -> TakePhoto(0));
        imageView[1].setOnClickListener(view13 -> TakePhoto(1));
        imageView[2].setOnClickListener(view12 -> TakePhoto(2));
        service = Common.GetService();
        bitmap = new Bitmap[3];
        btnNext.setOnClickListener(view1 -> {
            if(!txBarcode.getText().toString().equals("") && !txProductName.getText().toString().equals(""))
            {
                String A  = String.valueOf(MainActivity.Categories.get(categorySpinner.getSelectedItem().toString()));
                String Pic1=null,Pic2=null,Pic3=null;
                if(bitmap[0]!=null)
                    Pic1 = getStringFromBitmap(bitmap[0]);
                if(bitmap[1]!=null)
                    Pic2 = getStringFromBitmap(bitmap[1]);
                if(bitmap[2]!=null)
                    Pic3 = getStringFromBitmap(bitmap[2]);
                ProductDTO product = new ProductDTO(txBarcode.getText().toString(), txProductName.getText().toString(), 0,Pic1 , Pic2, Pic3, new CategoryNameDTO(A));
                service.CreateProduct(product)
                        .enqueue(new Callback<ProductDTO>() {
                            @Override
                            public void onResponse(Call<ProductDTO> call, Response<ProductDTO> response) {
                                if (response.isSuccessful()) {
                                    // Toast.makeText(getContext(), "CreateProduct Successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    String ErrMSG = MainActivity.GetErrorMessage(response);
                                    if(ErrMSG!=null)
                                        Toast.makeText(getContext(), MainActivity.GetErrorMessage(response), Toast.LENGTH_SHORT).show();
                                    else
                                        Toast.makeText(getContext(), "CreateProduct Unknown Error", Toast.LENGTH_SHORT).show();
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
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == (adapterView.getCount()-1))
                {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if(!Categories.isEmpty())
        {
            arrayList.add("Diğer");
            categorySpinner.setAdapter(new ArrayAdapter<>(getContext(), support_simple_spinner_dropdown_item, arrayList));
        }
        imgScan.setOnClickListener(view15 -> {
            Scanner scanner =  mainActivity.scanner;
            scanner.getBarcode = true;
            scanner.fragmentAdd = FragmentAddProduct.this;
            setFragment(scanner);
        });
        Bundle bundle = this.getArguments();
        if (bundle != null)
            new Thread(() -> getActivity().runOnUiThread(() -> txBarcode.setText( bundle.getString("BARCODE")))).start();

        return view;
    }
    String filename;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case 0:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        bitmap[IMGNumber] = MediaStore.Images.Media.getBitmap(
                                getActivity().getContentResolver(), imageUri);
                        imageView[IMGNumber].setImageBitmap(bitmap[IMGNumber]);
                        //imageurl = getRealPathFromURI(imageUri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        }
    }



    private void TakePhoto(int Nr) {
        values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        if (checkPermissionWRITE_EXTERNAL_STORAGE(getContext())) {
            imageUri = getActivity().getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(EXTRA_SIZE_LIMIT,5*1024*1024);
            intent.putExtra(MediaStore.EXTRA_SHOW_ACTION_ICONS,1);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, 0);
            IMGNumber = Nr;


        }
    }

    public static boolean checkPermissionWRITE_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                                    123);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }
    public static void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                (dialog, which) -> ActivityCompat.requestPermissions((Activity) context,
                        new String[] { permission },
                        123));
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    private String getStringFromBitmap(Bitmap bitmapPicture) {
        final int COMPRESSION_QUALITY = 10;
        String encodedImage;
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.JPEG, COMPRESSION_QUALITY, byteArrayBitmapStream);
        byte[] b = byteArrayBitmapStream.toByteArray();
        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }
    private Bitmap getBitmapFromString(String jsonString) {
        byte[] decodedString = Base64.decode(jsonString, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
}
