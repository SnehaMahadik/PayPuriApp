package com.benow.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.benow.R;
import com.benow.activity.MainActivity;
import com.benow.adapters.GridAdapter;
import com.benow.interfaces.DashboardFragmentListener;
import com.benow.models.GetAccountResponse;
import com.benow.models.GridItems;
import com.benow.utils.FlipAnimation;
import com.google.zxing.client.android.CaptureActivity;


import java.util.ArrayList;

/**
 *
 */
public class DashboardFragment extends Fragment implements AdapterView.OnItemClickListener,View.OnClickListener , Response.Listener, Response.ErrorListener{
    private ArrayList<GridItems> mGridItemsArrayList = null;
    private GridItems gridItems;
    private DashboardFragmentListener dashboardFragmentListener;
    int width = 0, height = 0;
    private LinearLayout mLLSwipe, mLLGridview, mLLACBalance;
    private TextView mTextViewSwipe, mTextViewAcBalance;
    private GridAdapter mGridAdapter;
    private Context mContext;
    private final int MY_PERMISSIONS_REQUEST_OPEN_CAMERA = 1;


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addGridItems();
        getScreenSize();

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        ((MainActivity) getActivity()).setActionBarTitle("Paypuri");

        mLLGridview = (LinearLayout) view.findViewById(R.id.llGridview);
        GridView gridView = (GridView) view.findViewById(R.id.gridview);

        mGridAdapter = new GridAdapter(getContext(), mGridItemsArrayList, width, height);
        gridView.setAdapter(mGridAdapter);
        gridView.setOnItemClickListener(this);

        mLLSwipe = (LinearLayout) view.findViewById(R.id.LLSwipe);
        //mLLACBalance= (LinearLayout) view.findViewById(R.id.LLACBalance);
        mLLSwipe.setOnClickListener(this);
        //mLLACBalance.setOnClickListener(this);

        mTextViewSwipe = (TextView) view.findViewById(R.id.textviewSwipeTo);
        mTextViewAcBalance = (TextView) view.findViewById(R.id.textviewACBalance);

       /* mTextViewSwipe.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            public void onSwipeTop() {
                Toast.makeText(getActivity(), "top", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {
                Toast.makeText(getActivity(), "right", Toast.LENGTH_SHORT).show();
                mTextViewSwipe.setText("Your A/C balance is: 500000/-");
            }

            public void onSwipeLeft() {
                Toast.makeText(getActivity(), "left", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeBottom() {
                Toast.makeText(getActivity(), "bottom", Toast.LENGTH_SHORT).show();
            }

        });*/

        return view;
    }

    private void getScreenSize() {

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        double dwidth = display.getWidth();
        double dheight = display.getHeight();

        //my EditText will be smaller than full screen (80%)
        double doublewidth = (dwidth / 4) * 2;
        double doubleheight = (dwidth / 10) * 4;
        width = (int) doublewidth;
        height = (int) doubleheight;
    }

    private void addGridItems() {

        Resources res = getResources();
        TypedArray arrayGridItemText = res.obtainTypedArray(R.array.arrayGridItemText);
        TypedArray arrayGridItemIcon = getResources().obtainTypedArray(R.array.arrayGridItemIcon);


        mGridItemsArrayList = new ArrayList<GridItems>();
        for (int j = 0; j < arrayGridItemText.length(); j++) {
            gridItems = new GridItems();
            gridItems.setGridItemLabel(arrayGridItemText.getString(j));
            gridItems.setGridItemImage(arrayGridItemIcon.getResourceId(j, 0));
            mGridItemsArrayList.add(gridItems);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        /*if (context instanceof DashboardFragmentListener) {
            dashboardFragmentListener = (DashboardFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dashboardFragmentListener = null;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.LLSwipe:
                startAnimation(v);

                break;
            case 1:
                break;
        }
    }

    private void startAnimation(View view) {
        /*view.animate()
                .translationX(500)
                .alpha(0.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mTextViewSwipe.setVisibility(View.GONE);
                        mTextViewAcBalance.setVisibility(View.VISIBLE);
                    }
                });*/


        FlipAnimation flipAnimation = new FlipAnimation(mTextViewSwipe, mTextViewAcBalance);

        if (mTextViewSwipe.getVisibility() == View.GONE) {
            flipAnimation.reverse();
        }
        mLLSwipe.startAnimation(flipAnimation);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        switch (position) {

            case 0:

                fragmentTransaction.replace(R.id.container, new InboxFragment());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();

                break;
            case 1:
                fragmentTransaction.replace(R.id.container, new QuickPayFragment());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
                break;

            case 2:
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    // Do something for marshmallow and above versions
                    requestPermmisions();
                }else {
                    launchBarcodeScanner();
                }
                break;


        }

    }
    @Override
    public void onResume() {
        super.onResume();
       postJson();

    }



    private void postJson() {


       /* StringRequest stringRequest = new StringRequest(Request.Method.POST, " http://192.168.2.176:8080/upiproxy/getAccountBalance/12340987",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                        Log.e("Volley Error ::", "Failed" + error.toString());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("USERNAME","ganesh");
                params.put("PASSWORD","ganesh");

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("Authorization", "Basic Z2FuZXNoOmdhbmVzaA==");
                return headers;
            }

        };

        stringRequest
                .setRetryPolicy(new DefaultRetryPolicy(Constants.REQUEST_TIMEOUT,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,  DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue1 = Volley.newRequestQueue(getActivity());
        requestQueue1.add(stringRequest);*/
    }




    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {
        if(response instanceof GetAccountResponse){
            GetAccountResponse getAccountResponse = (GetAccountResponse)response;
            // getAccountResponse.
        }

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
                            android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
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

    public void requestPermmisions(){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CAMERA)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_OPEN_CAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else{
            launchBarcodeScanner();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_OPEN_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    launchBarcodeScanner();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    requestPermmisions();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}