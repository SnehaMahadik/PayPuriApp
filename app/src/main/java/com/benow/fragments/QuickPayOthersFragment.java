package com.benow.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.benow.R;
import com.benow.activity.CreateNewUserActivity;
import com.benow.activity.MainActivity;
import com.benow.adapters.OrderListAdapter;
import com.benow.adapters.PeerContactsAdapter;
import com.benow.adapters.QuickPayContactsListAdapter;
import com.benow.interfaces.OrderListClickListener;
import com.benow.models.GetAllOrdersResponse;
import com.benow.models.OrderItemDetail;
import com.benow.models.QuickPayContacts;
import com.benow.models.QuickPayPhoneContact;
import com.benow.network.requestbuilder.OrderListRequestBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class QuickPayOthersFragment extends Fragment implements OrderListClickListener,View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressbar;
    private Context mContext;
    private ArrayList<String> listHeaderPackateId;
    private HashMap<String, List<OrderItemDetail>> listChildItems;
    public GetAllOrdersResponse getAllOrdersResponse;
    private FloatingActionButton fab;
    private Activity mActivity;
    private QuickPayContacts mQuickPayContacts;
    LinearLayout llIFSC,llMMID;
    private FragmentTransaction fragmentTransaction;
    private ArrayList<QuickPayPhoneContact> mContacts;


    public QuickPayOthersFragment()
    {
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
        View rootView = inflater.inflate(R.layout.fragment_quick_pay_others, container, false);

        // Initialize recycler view
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        llIFSC=(LinearLayout)rootView.findViewById(R.id.llIFSC);
        llMMID=(LinearLayout)rootView.findViewById(R.id.llMMID);

        llIFSC.setOnClickListener(this);
        llMMID.setOnClickListener(this);
        /*fab = (FloatingActionButton)rootView. findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, CreateNewUserActivity.class);
               startActivity(intent);
            }
        });*/
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(rootView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mProgressbar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        mProgressbar.setVisibility(View.VISIBLE);

        // Inflate the layout for this fragment
        return rootView;
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
    public void onResume() {
        super.onResume();
        mActivity = getActivity();
        Bundle args = getArguments();
        if (args != null) {
            mQuickPayContacts= args.getParcelable("CONTACT_DETAILS");
            mContacts= args.getParcelableArrayList("PHONE_CONTACTS");

            PeerContactsAdapter mPeerContactsAdapter = new PeerContactsAdapter(mContext, mQuickPayContacts.getPeerContacts());
    mPeerContactsAdapter.setOnOrderListClickListener(this);
            mRecyclerView.setAdapter(mPeerContactsAdapter);
            mProgressbar.setVisibility(View.GONE);
        }
    }


    @Override
    public void onOrderListClick(int position)
    {
        Bundle args = new Bundle();
        args.putInt("POSITION", position);
        args.putParcelableArrayList("PHONE_CONTACTS", mContacts);
        args.putParcelable("CONTACT_DETAILS", mQuickPayContacts);
        args.putString("PEER_CONTACTS", "peercontacts");


        PayPeerContactFragment mPayPeerContactFragment = new PayPeerContactFragment ();
        mPayPeerContactFragment.setArguments(args);

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.container, mPayPeerContactFragment);
        fragmentTransaction.addToBackStack("PayPeerContactFragment");
        fragmentTransaction.commit();

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
      //  Intent createUserIntent=new Intent(mContext,CreateNewUserActivity.class);
        switch (id)
        {
            case R.id.llIFSC:
                Changefragment(new AddwithIFSCFragment(),"IFSC");
                break;
            case R.id.llMMID:
                Changefragment(new AddwithMMIDFragment(),"MMID");
                break;
        }
    }

    private void Changefragment(Fragment fragment, String ifsc) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }



}
