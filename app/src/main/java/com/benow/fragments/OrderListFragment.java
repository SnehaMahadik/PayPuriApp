package com.benow.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.benow.R;
import com.benow.adapters.OrderListAdapter;
import com.benow.interfaces.OrderListClickListener;
import com.benow.models.GetAllOrdersResponse;
import com.benow.models.OrderItemDetail;
import com.benow.network.requestbuilder.OrderListRequestBuilder;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by swapnil 28/01/2016
 *
 * This fragment shows order lists
 */
public class OrderListFragment extends Fragment implements Response.Listener, Response.ErrorListener, OrderListClickListener {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressbar;
    private Context mContext;
    private ArrayList<String> listHeaderPackateId;
    private HashMap<String, List<OrderItemDetail>> listChildItems;
    public GetAllOrdersResponse getAllOrdersResponse;

    public OrderListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order_list, container, false);

        // Initialize recycler view
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mProgressbar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        mProgressbar.setVisibility(View.VISIBLE);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Creating request and fetch Orders list
        OrderListRequestBuilder orderListRequestBuilder = new OrderListRequestBuilder(mContext);
        orderListRequestBuilder.getAllOrders(this);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(mContext, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
        mProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void onResponse(Object response) {
        if(response  instanceof GetAllOrdersResponse){

            getAllOrdersResponse = (GetAllOrdersResponse) response;
            if(getAllOrdersResponse.getOrders().size() > 0) {
                OrderListAdapter orderListAdapter = new OrderListAdapter(mContext, getAllOrdersResponse.getOrders());
                orderListAdapter.setOnOrderListClickListener(this);
                mRecyclerView.setAdapter(orderListAdapter);
                mProgressbar.setVisibility(View.GONE);


            }
        }
    }



    @Override
    public void onOrderListClick(int position) {

        Bundle args = new Bundle();
        args.putInt("POSITION", position);
        args.putString("ORDERID", getAllOrdersResponse.getOrders().get(position).getOrderId());
        args.putParcelable("ORDERS",getAllOrdersResponse);
        OrderDetailsFragment orderDetailsFragment = new OrderDetailsFragment ();
        orderDetailsFragment.setArguments(args);

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.container, orderDetailsFragment);
        fragmentTransaction.addToBackStack("OrderDetailsFragment");
        fragmentTransaction.commit();
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_fragment, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_home:
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new DashboardFragment());
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
