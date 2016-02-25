package com.benow.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;


import com.benow.R;
import com.benow.adapters.OrderDetailsAdapter;
import com.benow.models.GetAllOrdersResponse;
import com.benow.models.OrderItemDetail;
import com.benow.utils.CircularImageview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by swapnil.
 *
 * Fragment shows order details
 */
public class OrderDetailsFragment extends Fragment {

    OrderDetailsAdapter orderDetailsAdapter;
    public static ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, ArrayList<OrderItemDetail>> listDataChild;
    Context context;
    HashMap<String, ArrayList<OrderItemDetail>> map;
    List<OrderItemDetail> ll;
    int position;
    private ArrayList<OrderItemDetail> items;
    private String mOrderId;
    TextView mTextviewOrderId;
    ImageView mImageViewVendor;
    public GetAllOrdersResponse getAllOrdersResponse;


    public OrderDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_details, container, false);
        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.elvPackages);
        mTextviewOrderId= (TextView) view.findViewById(R.id.tvOrderNo);
        mImageViewVendor= (ImageView) view.findViewById(R.id.ivLogo);
        CircularImageview.makeCircularImage(mImageViewVendor, getContext());


        // preparing list data
        prepareListData();

        orderDetailsAdapter = new OrderDetailsAdapter(context, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(orderDetailsAdapter);

        return view;
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {


        Bundle args = getArguments();
        if (args != null) {
            position = args.getInt("POSITION");
            mOrderId=args.getString("ORDERID");
            getAllOrdersResponse= args.getParcelable("ORDERS");
        }
        mTextviewOrderId.setText("order Id :"+mOrderId);
        items = new ArrayList<OrderItemDetail>();
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<>();
        map = new HashMap<>();

        // Adding headers
        for (int i = 0; i < getAllOrdersResponse.getOrders().get(position).getOrderItemDetails().size(); i++) {
            if (!listDataHeader.contains(getAllOrdersResponse.getOrders().get(position).getOrderItemDetails().get(i).getPacketId())) {
                listDataHeader.add(getAllOrdersResponse.getOrders().get(position).getOrderItemDetails().get(i).getPacketId());

            }
        }

//Add Childs
            /*for (int j = 0; j < OrderListFragment.getAllOrdersResponse.getOrders().get(position).getOrderItemDetails().size(); j++)
            {


                for (int i = 0; i < listDataHeader.size(); i++) {
                    if (OrderListFragment.getAllOrdersResponse.getOrders().get(position).getOrderItemDetails().get(j).getPacketId().equalsIgnoreCase(listDataHeader.get(i))) {
                        Log.i("OrderDetailsFragment", listDataHeader.get(i) + ":==:" + OrderListFragment.getAllOrdersResponse.getOrders().get(position).getOrderItemDetails().get(j).getPacketId());
                        Log.i("OrderDetailsFragment", OrderListFragment.getAllOrdersResponse.getOrders().get(position).getOrderItemDetails().get(j).getItemId());

                        items.add(OrderListFragment.getAllOrdersResponse.getOrders().get(position).getOrderItemDetails().get(j));
                        listDataChild.put(listDataHeader.get(i), OrderListFragment.getAllOrdersResponse.getOrders().get(position).getOrderItemDetails());

                    }

                }


            }*/

        for (int i = 0; i < listDataHeader.size(); i++) {
            ArrayList<OrderItemDetail> secondList = new ArrayList<OrderItemDetail>();

            for (OrderItemDetail a : getAllOrdersResponse.getOrders().get(position).getOrderItemDetails()) {
// or equalsIgnoreCase or whatever your conditon is
                if (a.getPacketId().equals(listDataHeader.get(i))) {

                    secondList.add(a);
                }
                listDataChild.put(listDataHeader.get(i), secondList);
            }
        }



            // map.put(listDataHeader.get(i), items);

        }

        // Adding child data
        /*List<String> top250 = new ArrayList<String>();
        top250.add("190045678");
        top250.add("190045679");


        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("19004523");
        nowShowing.add("21312123");
        nowShowing.add("34234444");*/


/*
        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);*/

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



