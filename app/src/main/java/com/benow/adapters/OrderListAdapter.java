package com.benow.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.benow.R;
import com.benow.fragments.OrderListFragment;
import com.benow.interfaces.OrderListClickListener;
import com.benow.models.Order;
import com.benow.utils.CircularImageview;

import java.util.List;

/**
 * Created by swapnil on 1/29/2016.
 */
public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.CustomOrderView> {

    private Context mContext;
    private List<Order> mOrders;
    private OrderListClickListener orderListClickListener;

    public OrderListAdapter(Context context, List<Order> orders){
        this.mContext = context;
        this.mOrders = orders;
    }

    @Override
    public CustomOrderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_orderlist_inbox, null);
        CustomOrderView viewHolder = new CustomOrderView(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomOrderView holder, int position) {
        Order order = mOrders.get(position);
        holder.tvOrderNo.setText(order.getOrderId());
        holder.tvOrderPrice.setText(mContext.getResources().getString(R.string.symbol_rupee) + " " + order.getOrderAmount());
        holder.textViewpurchasedate.setText("Order Date : "+order.getOrderDate());
       // holder.textViewdeliverydate.setText("ETA: "+order.getDeliveryDate());
        holder.textViewdeliverydate.setText("ETA: 2 Days");
        CircularImageview.makeCircularImage(holder.ivLogo, mContext,"http://www.nextbigwhat.com/wp-content/uploads/2013/09/Flipkart-Native-APp.png");
     //   Picasso.with(mContext).load("http://static.dnaindia.com/sites/default/files/2015/09/22/378260-flipkart-twitter.png").resize(150, 150).placeholder(R.mipmap.ic_launcher).into(holder.ivLogo);
    }



    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    public class CustomOrderView extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected ImageView ivLogo;
        protected TextView tvOrderNo,textViewpurchasedate,textViewdeliverydate;
        protected TextView tvOrderPrice;
        protected RelativeLayout rlRowOrderList;

        public CustomOrderView(View itemView) {
            super(itemView);
            this.ivLogo = (ImageView) itemView.findViewById(R.id.ivLogo);
            this.tvOrderNo = (TextView) itemView.findViewById(R.id.tvOrderNo);
            this.textViewpurchasedate = (TextView) itemView.findViewById(R.id.textViewpurchasedate);
            this.textViewdeliverydate = (TextView) itemView.findViewById(R.id.textViewdeliverydate);
            this.tvOrderPrice = (TextView) itemView.findViewById(R.id.tvOrderPrice);
            this.rlRowOrderList = (RelativeLayout) itemView.findViewById(R.id.rlRowOrderList);
            rlRowOrderList.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            orderListClickListener.onOrderListClick(getAdapterPosition());
        }
    }

    public void setOnOrderListClickListener(OrderListFragment orderListFragment){
        try {
            orderListClickListener = orderListFragment;
        } catch (ClassCastException e) {
            throw new ClassCastException(mContext.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
}
