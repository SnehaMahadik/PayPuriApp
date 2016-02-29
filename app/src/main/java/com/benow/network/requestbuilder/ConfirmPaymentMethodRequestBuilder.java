package com.benow.network.requestbuilder;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.benow.fragments.ConfirmPaymentMethodFragment;
import com.benow.fragments.QuickPayContactsFragment;
import com.benow.models.AppUser;
import com.benow.models.GetConfirmPaymentProcessResponse;
import com.benow.network.Constants;
import com.benow.network.CustomVolleyRequestQueue;
import com.benow.network.GsonRequest;


/**
 * Created by swapnil13494 on 2/24/2016.
 */
public class ConfirmPaymentMethodRequestBuilder {

    private RequestQueue mQueue;
    private String METHOD_GET_BANK_ACCOUNTS = "";
    private String METHOD_PAYMENT_PROCESS = "ecomm/processPayments";

    public  ConfirmPaymentMethodRequestBuilder(Context context){
        mQueue = CustomVolleyRequestQueue.getInstance(context)
                .getRequestQueue();

    }

    public void getBankUserAccounts(ConfirmPaymentMethodFragment confirmPaymentMethodFragment){
        GsonRequest gsonRequest = new GsonRequest(Request.Method.POST, Constants.BASE_URL + METHOD_GET_BANK_ACCOUNTS, AppUser.class, null,confirmPaymentMethodFragment, confirmPaymentMethodFragment);
        mQueue.add(gsonRequest);

    }

    public void getPaymentConfirmationData(ConfirmPaymentMethodFragment confirmPaymentMethodFragment){
        GsonRequest gsonRequest = new GsonRequest(Request.Method.POST, Constants.BASE_URL + METHOD_PAYMENT_PROCESS, GetConfirmPaymentProcessResponse.class, null,confirmPaymentMethodFragment, confirmPaymentMethodFragment);
        mQueue.add(gsonRequest);
    }


}
