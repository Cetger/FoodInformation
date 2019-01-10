package com.example.count.foodinformation;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;

import java.util.Objects;

import androidx.annotation.NonNull;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSearch extends Fragment {

    private static final int RC_PERMISSION = 10;
    private CodeScanner mCodeScanner;
    private boolean mPermissionGranted;
    public boolean getBarcode;
    public FragmentAdd fragmentAdd;
    public FragmentSearch() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getView() != null ? getView() : inflater.inflate(R.layout.fragment_fragment_search, container, false);
        mCodeScanner = new CodeScanner(Objects.requireNonNull(getContext()).getApplicationContext(), view.findViewById(R.id.scanner));
        mCodeScanner.setDecodeCallback(result ->  Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            if(getBarcode)
            {
                //fragmentAdd.txBarcode.setText("1111");
                //String a = fragmentAdd.txBarcode.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("BARCODE",result.getText());
             //   fragmentAdd.setArguments(bundle);
                setFragment(MainActivity.mainActivity.fragmentAdd);
            }
            else {
                ScanResultDialog dialog = new ScanResultDialog(getContext(), result);
                dialog.setOnDismissListener(d -> mCodeScanner.startPreview());
                dialog.show();
            }
        }));
        mCodeScanner.setErrorCallback(error -> Objects.requireNonNull(getActivity()).runOnUiThread(
                () -> Toast.makeText(getContext(), "Scanner Error", Toast.LENGTH_LONG).show()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Objects.requireNonNull(getContext()).checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                mPermissionGranted = false;
                requestPermissions(new String[] {Manifest.permission.CAMERA}, RC_PERMISSION);
            } else {
                mPermissionGranted = true;
            }
        } else {
            mPermissionGranted = true;
        }
        return view;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == RC_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mPermissionGranted = true;
                mCodeScanner.startPreview();
            } else {
                mPermissionGranted = false;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPermissionGranted) {
            mCodeScanner.startPreview();
        }
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction  =  getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
      //  fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
