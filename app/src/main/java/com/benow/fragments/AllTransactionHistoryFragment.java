package com.benow.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.benow.models.GetAllTransactionHistory;
import com.benow.models.OrderItemDetail;
import com.benow.network.requestbuilder.AllTransactionHistoryRequestBuilder;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AllTransactionHistoryFragment extends Fragment implements Response.Listener, Response.ErrorListener {
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressbar;
    private Context mContext;
    private ArrayList<String> listHeaderPackateId;
    private HashMap<String, List<OrderItemDetail>> listChildItems;
    public static GetAllTransactionHistory getAllTransactionHistory;


    public AllTransactionHistoryFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Initialize recycler view
        View rootView = inflater.inflate(R.layout.fragment_all_transaction_history, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mProgressbar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        mProgressbar.setVisibility(View.VISIBLE);

        // Inflate the layout for this fragment

     //   AllTransactionHistoryRequestBuilder allTransactionHistoryRequestBuilder = new AllTransactionHistoryRequestBuilder(mContext);
     //   allTransactionHistoryRequestBuilder.getAllTransactionHistory(this);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Creating request and fetch Orders list
        AllTransactionHistoryRequestBuilder allTransactionHistoryRequestBuilder = new AllTransactionHistoryRequestBuilder(mContext);
        allTransactionHistoryRequestBuilder.getAllTransactionHistory(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(mContext, "Failed to fetch GetAllTransactionHistory!", Toast.LENGTH_SHORT).show();
        mProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void onResponse(Object response) {
        if(response  instanceof GetAllTransactionHistory){
            getAllTransactionHistory=new GetAllTransactionHistory();
            getAllTransactionHistory = (GetAllTransactionHistory) response;
            Toast.makeText(mContext, "Success+!"+((GetAllTransactionHistory) response).get_embedded().getPaymentHistory().get(0).getMerchantCode(), Toast.LENGTH_SHORT).show();
           /* if(getAllTransactionHistory.getAllTransactionHistories().size() > 0) {
                OrderListAdapter orderListAdapter = new OrderListAdapter(mContext, getAllTransactionHistory.getOrders());
                orderListAdapter.setOnOrderListClickListener(this);
                mRecyclerView.setAdapter(orderListAdapter);
                mProgressbar.setVisibility(View.GONE);


            }*/
        }
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
