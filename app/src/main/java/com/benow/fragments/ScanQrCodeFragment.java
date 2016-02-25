package com.benow.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.benow.R;
import com.google.zxing.client.android.CaptureActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScanQrCodeFragment extends Fragment {

    private Button btnScan;


    public ScanQrCodeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scan_qr_code, container, false);
        btnScan = (Button) view.findViewById(R.id.btnScanQr);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), CaptureActivity.class);
                intent1.setPackage("com.google.zxing.client.android");
                intent1.putExtra("SCAN_MODE", "QR_CODE_MODE");
                startActivityForResult(intent1, 0);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

//        Log.e("onActivityResult", requestCode + "   " + resultCode + " " + data.toString());

        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");
                String format = data.getStringExtra("SCAN_RESULT_FORMAT");
                Log.e("Scan data", (contents.replace("res", "")).trim());
                final String result =(contents.replace("res","")).trim();
                if(result.contains("vendor")){
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ConfirmPaymentMethodFragment newFragment = new ConfirmPaymentMethodFragment();
                            newFragment.setArguments(getOrderData(result));
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.container, newFragment);
                            transaction.addToBackStack("ScanQrCodeFragment");
                            transaction.commitAllowingStateLoss();
                        }
                    }, 1000);
                }else{
                    launchBarcodeScanner();
                    Toast.makeText(getActivity(), "Please scan valid QR code.", Toast.LENGTH_SHORT).show();
                }


//                splitQRText(contents);
            }
        }else{
            launchBarcodeScanner();
            Toast.makeText(getActivity(), "Please scan valid QR code.", Toast.LENGTH_SHORT).show();
        }
//        if (requestCode == 1234 && resultCode == RESULT_OK) {
//            String voice_text = data.getStringArrayListExtra(
//                    RecognizerIntent.EXTRA_RESULTS).get(0);
//            Toast.makeText(getApplicationContext(), voice_text,
//                    Toast.LENGTH_LONG).show();
//
//        }
//
//        if (requestCode == 0) {
//            if (resultCode == RESULT_OK) {
//                String contents = data.getStringExtra("SCAN_RESULT");
//                String format = data.getStringExtra("SCAN_RESULT_FORMAT");
//                Log.e("Scan data", contents + "   " + format);
//                splitQRText(contents);
//            } else if (resultCode == RESULT_CANCELED) {
//                // Handle cancel
//                Log.i("App", "Scan unsuccessful");
//
//                Bundle args = new Bundle();
//                args.putString("Menu", "You pressed done button.");
//                Fragment detail = new DashboardFragment();
//                detail.setArguments(args);
//                FragmentManager fragmentManager = getFragmentManager();
//
//                fragmentManager.popBackStack("MobileFragment", 0);
//
//                fragmentManager.beginTransaction()
//                        .replace(R.id.content_frame, detail).commit();
//            }
//        }
    }

    public Bundle getOrderData(String result){
        Bundle bundle = new Bundle();

        String [] token = result.split("-");
        for(int i=0; i<token.length; i++){
            String data[] = token[i].split(":");
            bundle.putString(data[0], data[1]);
        }
        return bundle;
    }

    public void launchBarcodeScanner() {
        Intent intent1 = new Intent(getActivity(), CaptureActivity.class);
        intent1.setPackage("com.google.zxing.client.android");
        intent1.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent1, 0);
    }

}
