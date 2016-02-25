package com.benow.network.requestbuilder;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.benow.fragments.DashboardFragment;
import com.benow.models.GetAccountResponse;
import com.benow.network.Constants;
import com.benow.network.CustomVolleyRequestQueue;
import com.benow.network.GsonRequest;
import com.benow.network.GsonRequestBuilder;


/**
 * Created by sneha13498 on 2/24/2016.
 */
public class DashboardRequestBuilder {

    private GsonRequestBuilder gsonRequestBuilder;
    private GsonRequest mGsonRequest;

    private RequestQueue mQueue;

    public DashboardRequestBuilder(Context context){
        mQueue = CustomVolleyRequestQueue.getInstance(context)
                .getRequestQueue();
    }

    // Get all orders from web services and return a result to order list fragment
    public void getAccountBalance(DashboardFragment dashboardFragment) {

        GsonRequest gsonRequest = new GsonRequest(Request.Method.POST, Constants.ACCOUNT_BALANCE, GetAccountResponse.class, null,dashboardFragment, dashboardFragment);

        mQueue.add(gsonRequest);



    }
}
