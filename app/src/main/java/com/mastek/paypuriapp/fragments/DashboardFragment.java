package com.mastek.paypuriapp.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mastek.paypuriapp.MainActivity;
import com.mastek.paypuriapp.R;
import com.mastek.paypuriapp.adapters.GridAdapter;
import com.mastek.paypuriapp.interfaces.DashboardFragmentListener;
import com.mastek.paypuriapp.interfaces.NetworkReceiver;
import com.mastek.paypuriapp.models.GridItems;
import com.mastek.paypuriapp.network.NetworkCall;
import com.mastek.paypuriapp.utils.FlipAnimation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class DashboardFragment extends Fragment implements AdapterView.OnItemClickListener,View.OnClickListener ,NetworkReceiver{
    private ArrayList<GridItems> mGridItemsArrayList = null;
    private GridItems gridItems;
    private DashboardFragmentListener dashboardFragmentListener;
    int width = 0, height = 0;
    private LinearLayout mLLSwipe, mLLGridview, mLLACBalance;
    private TextView mTextViewSwipe, mTextViewAcBalance;
    private GridAdapter mGridAdapter;
    private Context mContext;
    JSONObject jsonobject_one;
    private NetworkCall mNetworkCall;

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

        switch (position) {
            case 0:
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new InboxFragment());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();

                break;
            case 1:
                break;

        }

    }
    @Override
    public void onResume() {
        super.onResume();
       // postJson();
        makeNetworkCall();
        // Creating request and fetch Orders list

       /*StringRequest stringRequest = new StringRequest(Request.Method.POST, " http://192.168.2.176:8080/upiproxy/getAccountBalance/12340987",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue1 = Volley.newRequestQueue(getActivity());
        requestQueue1.add(stringRequest);*/

       /* StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.2.176:8080//appUsers",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                        Log.e("Volley Error ::", "Failed" + error.toString());
                    }
                }){


            *//*@Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("Authorization", "Basic Z2FuZXNoOmdhbmVzaA==");
                return headers;
            }*//*
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                *//*HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("Authorization", "Basic Z2FuZXNoOmdhbmVzaA==");
                // add headers <key,value>
                String credentials = "user"+":"+"password";
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(),
                        Base64.NO_WRAP);
                headers.put("Authorization", auth);*//*
                HashMap<String, String> params = new HashMap<String, String>();
                params.put(
                        "Authorization",
                        String.format("Basic %s", Base64.encodeToString(
                                String.format("%s:%s", "ganesh", "ganesh").getBytes(), Base64.DEFAULT)));
                return params;
            }
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
               *//*params.put("USERNAME","ganesh");
                params.put("PASSWORD","ganesh");*//*

                params.put("username","");
                params.put("password","");
                params.put("email","");
                params.put("mobileNumber","");
                params.put("parentUsername","parent");
                params.put("referralCode","");

                return params;
            };
        };


        RequestQueue requestQueue1 = Volley.newRequestQueue(getActivity());
        requestQueue1.add(stringRequest);*/
    }

    private void makeNetworkCall() {

        mNetworkCall.getJsonData("http://192.168.2.176:8080/upiproxy/getAccountBalance/12340987",1);


        /*StringRequest stringRequest = new StringRequest(Request.Method.POST, " http://192.168.2.176:8080/upiproxy/getAccountBalance/12340987",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue1 = Volley.newRequestQueue(getActivity());
        requestQueue1.add(stringRequest);*/
    }

    private void postJson() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username","sneha");
        params.put("password","anwha");
        params.put("email","dfgfd@dfg.com");
        params.put("mobileNumber","8775454545");
        params.put("parentUsername","parent");
        params.put("referralCode", "232");

        JsonObjectRequest req = new JsonObjectRequest("http://192.168.1.104:8080//appUsers" + Request.Method.POST, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error //: ", error.getCause());
            }

        }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headerMap = new HashMap<String, String>();

                String credentials = "ganesh" + ":" + "ganesh";
                String base64EncodedCredentials =
                        Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                headerMap.put("Authorization", "Basic " + base64EncodedCredentials);
                headerMap.put("Content-Type", "application/json; charset=utf-8");
                return headerMap;
            }
        };


        RequestQueue requestQueue1 = Volley.newRequestQueue(getActivity());

        requestQueue1.add(req);
    }

    @Override
    public <T> void onResponse(T obj, int tag) {
       // mNetworkCall.showToast(obj.toString());
    }

    @Override
    public void onError(int error_code, int tag, Object object) {

    }
}

   /* @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(mContext, "Failed"+error, Toast.LENGTH_SHORT).show();
       // Log.e("Volley Error ::","Failed"+error);
        //mProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void onResponse(Object response) {
        if (response instanceof GetAllTransactionHistory) {
            *//*getAllTransactionHistory = new GetAllTransactionHistory();
            getAllTransactionHistory = (GetAllTransactionHistory) response;
          *//*
            Toast.makeText(mContext, "Success+!", Toast.LENGTH_SHORT).show();

        }*/

             //dashboardFragmentListener.onGridItemClick();




