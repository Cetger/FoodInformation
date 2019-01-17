package com.example.count.foodinformation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
public class Scanner extends Fragment implements ZXingScannerView.ResultHandler  {

    private ZXingScannerView mScannerView;
    public static boolean getBarcode;
    public static FragmentAddProduct fragmentAdd;
    public Scanner() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mScannerView = new ZXingScannerView(getContext());
        return mScannerView;
    }
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }
    @Override
    public void handleResult(Result rawResult) {
        Log.v("test", rawResult.getText()); // Prints scan results
        Log.v("test", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)
            if (getBarcode) {
                getBarcode=false;
                Bundle bundle = new Bundle();
                bundle.putString("BARCODE", rawResult.getText());
                fragmentAdd.setArguments(bundle);
                setFragment(fragmentAdd);
                mScannerView.stopCamera();
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("BARCODE", rawResult.getText());
                FragmentShowInfo fragmentShowInfo = new FragmentShowInfo();
                fragmentShowInfo.setArguments(bundle);
                setFragment(fragmentShowInfo);
            }
        mScannerView.resumeCameraPreview(this);
    }
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction  =  getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
