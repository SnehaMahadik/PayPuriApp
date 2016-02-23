package com.mastek.paypuriapp.network.requestbuilder;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.mastek.paypuriapp.fragments.OrderListFragment;
import com.mastek.paypuriapp.models.GetAllOrdersResponse;
import com.mastek.paypuriapp.network.Constants;
import com.mastek.paypuriapp.network.CustomVolleyRequestQueue;
import com.mastek.paypuriapp.network.GsonRequestBuilder;

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
        gsonRequestBuilder = new GsonRequestBuilder<>(Constants.BASE_URL +METHOD_GET_ALL_ORDERS , GetAllOrdersResponse.class, null, orderListFragment,orderListFragment);
        mQueue.add(gsonRequestBuilder);
    }

}
