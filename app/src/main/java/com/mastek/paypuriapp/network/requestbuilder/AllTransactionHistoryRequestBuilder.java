package com.mastek.paypuriapp.network.requestbuilder;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.mastek.paypuriapp.fragments.AllTransactionHistoryFragment;
import com.mastek.paypuriapp.models.GetAllTransactionHistory;
import com.mastek.paypuriapp.network.Constants;
import com.mastek.paypuriapp.network.CustomVolleyRequestQueue;
import com.mastek.paypuriapp.network.GsonRequestBuilder;

/**
 * Created by sneha13498 on 2/20/2016.
 */
public class AllTransactionHistoryRequestBuilder {
    private GsonRequestBuilder gsonRequestBuilder;

    //    private String METHOD_GET_ALL_ORDERS = "getAllOrders";
   // private String METHOD_GET_ALL_TRANSACTIONHISTORY = "getAllOrdersForUser/ganesh";
    private RequestQueue mQueue;

    public AllTransactionHistoryRequestBuilder(Context context){
        mQueue = CustomVolleyRequestQueue.getInstance(context)
                .getRequestQueue();
    }

    // Get all orders from web services and return a result to order list fragment
    public void getAllTransactionHistory(AllTransactionHistoryFragment allTransactionHistoryFragment) {
        gsonRequestBuilder = new GsonRequestBuilder<>(Constants.All_TRANSACTIONHISTORY_URL , GetAllTransactionHistory.class, null, allTransactionHistoryFragment,allTransactionHistoryFragment);
        mQueue.add(gsonRequestBuilder);
    }
}
