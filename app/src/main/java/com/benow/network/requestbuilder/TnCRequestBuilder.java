package com.benow.network.requestbuilder;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.benow.fragments.QuickPayContactsFragment;
import com.benow.models.QuickPayContacts;
import com.benow.network.Constants;
import com.benow.network.CustomVolleyRequestQueue;
import com.benow.network.GsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sneha13498 on 2/26/2016.
 */
public class TnCRequestBuilder
{

    private RequestQueue mQueue;


    public  TnCRequestBuilder(Context context)
    {
        mQueue = CustomVolleyRequestQueue.getInstance(context).getRequestQueue();

    }

    public void getTnCURL(QuickPayContactsFragment quickPayContactsFragment, JSONObject stringStringMap) throws JSONException {


        GsonRequest gsonRequest = new GsonRequest(Request.Method.POST, Constants.QUICKPAY_CONTACTS_URL, QuickPayContacts.class,stringStringMap,quickPayContactsFragment, quickPayContactsFragment);
        mQueue.add(gsonRequest);

    }
}
