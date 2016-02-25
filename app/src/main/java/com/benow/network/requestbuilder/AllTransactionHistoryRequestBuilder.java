package com.benow.network.requestbuilder;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.benow.fragments.AllTransactionHistoryFragment;
import com.benow.models.GetAllTransactionHistory;
import com.benow.network.Constants;
import com.benow.network.CustomVolleyRequestQueue;
import com.benow.network.GsonRequestBuilder;


/**
 * Created by sneha13498 on 2/20/2016.
 */
public class AllTransactionHistoryRequestBuilder {
    private GsonRequestBuilder gsonRequestBuilder;


    private RequestQueue mQueue;

    public AllTransactionHistoryRequestBuilder(Context context){
        mQueue = CustomVolleyRequestQueue.getInstance(context)
                .getRequestQueue();
    }

    // Get all orders from web services and return a result to order list fragment
    public void getAllTransactionHistory(AllTransactionHistoryFragment allTransactionHistoryFragment) {
        gsonRequestBuilder = new GsonRequestBuilder<>(Constants.All_TRANSACTIONHISTORY_URL , Request.Method.GET,GetAllTransactionHistory.class, null,null, allTransactionHistoryFragment,allTransactionHistoryFragment);
        mQueue.add(gsonRequestBuilder);
    }
}
