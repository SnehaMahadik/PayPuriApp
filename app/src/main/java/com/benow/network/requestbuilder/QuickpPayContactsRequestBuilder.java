package com.benow.network.requestbuilder;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.benow.fragments.QuickPayContactsFragment;
import com.benow.fragments.QuickPayFragment;
import com.benow.models.GetConfirmPaymentProcessResponse;
import com.benow.models.QuickPayContacts;
import com.benow.network.Constants;
import com.benow.network.CustomVolleyRequestQueue;
import com.benow.network.GsonRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by sneha13498 on 2/25/2016.
 */
public class QuickpPayContactsRequestBuilder
{


    private RequestQueue mQueue;


    public  QuickpPayContactsRequestBuilder(Context context){
        mQueue = CustomVolleyRequestQueue.getInstance(context).getRequestQueue();

    }

    public void getQuickPayContacts(QuickPayFragment quickPayFragment, JSONObject stringStringMap) throws JSONException {

       // JSONObject jsonObj = new JSONObject(stringStringMap);
       // Log.i("jsonObj",jsonObj.toString());

        GsonRequest gsonRequest = new GsonRequest(Request.Method.POST, Constants.QUICKPAY_CONTACTS_URL, QuickPayContacts.class,stringStringMap,quickPayFragment, quickPayFragment);
        mQueue.add(gsonRequest);

    }
}
