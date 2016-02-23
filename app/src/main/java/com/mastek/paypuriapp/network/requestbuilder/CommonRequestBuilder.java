package com.mastek.paypuriapp.network.requestbuilder;

import android.app.Fragment;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.mastek.paypuriapp.MainActivity;
import com.mastek.paypuriapp.fragments.AllTransactionHistoryFragment;
import com.mastek.paypuriapp.fragments.DashboardFragment;
import com.mastek.paypuriapp.models.GetAccountBalance;
import com.mastek.paypuriapp.models.GetAllTransactionHistory;
import com.mastek.paypuriapp.network.Constants;
import com.mastek.paypuriapp.network.CustomVolleyRequestQueue;
import com.mastek.paypuriapp.network.GsonRequestBuilder;

/**
 * Created by sneha13498 on 2/22/2016.
 */
public class CommonRequestBuilder {
    private GsonRequestBuilder gsonRequestBuilder;





    private RequestQueue mQueue;

    public CommonRequestBuilder(Context context){
        mQueue = CustomVolleyRequestQueue.getInstance(context).getRequestQueue();

    }


    // Get all orders from web services and return a result to order list fragment
    /*public void getUserAccountBalanceDetails(DashboardFragment dashboardFragment) {
        gsonRequestBuilder = new GsonRequestBuilder<>(Constants.ACCOUNT_BALANCE , GetAccountBalance.class, null, dashboardFragment,dashboardFragment);
        mQueue.add(gsonRequestBuilder);
    }*/
}

