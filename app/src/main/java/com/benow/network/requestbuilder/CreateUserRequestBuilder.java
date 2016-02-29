package com.benow.network.requestbuilder;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.benow.fragments.AddwithIFSCFragment;
import com.benow.fragments.AddwithMMIDFragment;
import com.benow.fragments.QuickPayFragment;
import com.benow.models.CreateUser;
import com.benow.models.QuickPayContacts;
import com.benow.network.Constants;
import com.benow.network.CustomVolleyRequestQueue;
import com.benow.network.GsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sneha13498 on 2/27/2016.
 */
public class CreateUserRequestBuilder {
    private RequestQueue mQueue;


    public  CreateUserRequestBuilder(Context context)
    {
        mQueue = CustomVolleyRequestQueue.getInstance(context)
                .getRequestQueue();

    }


    public void getCreatedUser_IFSC(AddwithIFSCFragment mAddwithIFSCFragment, JSONObject stringStringMap) throws JSONException {

        // JSONObject jsonObj = new JSONObject(stringStringMap);
        // Log.i("jsonObj",jsonObj.toString());

        GsonRequest gsonRequest = new GsonRequest(Request.Method.POST, Constants.CREATE_USER_URL, CreateUser.class,stringStringMap,mAddwithIFSCFragment, mAddwithIFSCFragment);
        mQueue.add(gsonRequest);

    }
    public void getCreatedUser_MMID(AddwithMMIDFragment mAddwithMMIDFragment, JSONObject stringStringMap) throws JSONException {

        // JSONObject jsonObj = new JSONObject(stringStringMap);
        // Log.i("jsonObj",jsonObj.toString());

        GsonRequest gsonRequest = new GsonRequest(Request.Method.POST, Constants.CREATE_USER_URL, CreateUser.class,stringStringMap,mAddwithMMIDFragment, mAddwithMMIDFragment);
        mQueue.add(gsonRequest);

    }
}
