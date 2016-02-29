package com.benow.network.requestbuilder;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.benow.fragments.MpinFragment;
import com.benow.fragments.PayPeerContactFragment;
import com.benow.fragments.QuickPayFragment;
import com.benow.models.PeerPayResponse;
import com.benow.models.QuickPayContacts;
import com.benow.network.Constants;
import com.benow.network.CustomVolleyRequestQueue;
import com.benow.network.GsonRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sneha13498 on 2/28/2016.
 */
public class PeerContactPaymentRequestBuilder {

    private RequestQueue mQueue;


    public  PeerContactPaymentRequestBuilder(Context context){
        mQueue = CustomVolleyRequestQueue.getInstance(context).getRequestQueue();

    }

    public void getPeerPaymentResponse(MpinFragment mpinFragment, JSONObject stringStringMap) throws JSONException {

        // JSONObject jsonObj = new JSONObject(stringStringMap);
        // Log.i("jsonObj",jsonObj.toString());

        GsonRequest gsonRequest = new GsonRequest(Request.Method.POST, Constants.BASE_URL+Constants.PAY_TO_PEER_CONTACT_URL, PeerPayResponse.class,stringStringMap,mpinFragment, mpinFragment);
        mQueue.add(gsonRequest);

    }
}
