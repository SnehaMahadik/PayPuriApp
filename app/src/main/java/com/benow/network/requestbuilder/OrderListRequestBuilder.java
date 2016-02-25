package com.benow.network.requestbuilder;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.benow.fragments.OrderListFragment;
import com.benow.models.GetAllTransactionHistory;
import com.benow.network.Constants;
import com.benow.network.CustomVolleyRequestQueue;
import com.benow.network.GsonRequestBuilder;


/**
 * Created by swapnil13494 on 1/29/2016.
 *
 * This class is for building request of Order list
 */
public class OrderListRequestBuilder {

    private GsonRequestBuilder gsonRequestBuilder;

//    private String METHOD_GET_ALL_ORDERS = "getAllOrders";
    private String METHOD_GET_ALL_ORDERS = "getAllOrdersForUser/ganesh";
    private RequestQueue mQueue;

    public OrderListRequestBuilder(Context context){
        mQueue = CustomVolleyRequestQueue.getInstance(context)
                .getRequestQueue();
    }

    // Get all orders from web services and return a result to order list fragment
    public void getAllOrders(OrderListFragment orderListFragment) {
        gsonRequestBuilder = new GsonRequestBuilder<>(Constants.BASE_URL , Request.Method.GET,GetAllTransactionHistory.class, null,null, orderListFragment,orderListFragment);

        mQueue.add(gsonRequestBuilder);
    }

}
