package com.benow.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benow.R;
import com.benow.models.OrderItemDetail;


/**
 *  Created by swapnil
 *
 *  Adapter to add items on the Order Items
 */
public class OrderDetailsAdapter extends BaseExpandableListAdapter implements CompoundButton.OnCheckedChangeListener {

    private Context mContext;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, ArrayList<OrderItemDetail>> _listDataChild;
    OrderItemDetail orderItemDetail;

    public OrderDetailsAdapter(Context context, List<String> listDataHeader,
                               HashMap<String, ArrayList<OrderItemDetail>> listChildData) {
        this.mContext = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;

    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,boolean isLastChild, View convertView, ViewGroup parent) {


        ChildViewHolder childViewHolder;

        if (convertView == null) {


            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_child_order_package_item, null);

            childViewHolder = new ChildViewHolder();

            childViewHolder.mItemId = (TextView) convertView.findViewById(R.id.textViewItemDetails);
            childViewHolder.mItemPrice= (TextView) convertView.findViewById(R.id.textViewItemPrice);
            childViewHolder.mItemImage= (ImageView) convertView.findViewById(R.id.imageviewItem);
            childViewHolder.mChildCheckbox= (CheckBox) convertView.findViewById(R.id.checkboxChildItem);

            childViewHolder.mChildCheckbox.setOnCheckedChangeListener(this);

            convertView.setTag(childViewHolder);

        }


    else

    {

        childViewHolder = (ChildViewHolder) convertView.getTag();

    }
       orderItemDetail = _listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition);
        // assign values if the object is not null

        if(orderItemDetail != null)
        {
            if (orderItemDetail.getPacketId().equalsIgnoreCase("Unknown")||orderItemDetail.getPacketId().equalsIgnoreCase(""))
        {
            childViewHolder.mChildCheckbox.setVisibility(View.VISIBLE);

        }
            else {
                childViewHolder.mChildCheckbox.setVisibility(View.GONE);
            }
            childViewHolder.mItemId.setText(orderItemDetail.getItemDescription());
            childViewHolder.mItemPrice.setText(String.valueOf(orderItemDetail.getItemAmount()));
        }






    return convertView;
}
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) {

        GroupViewHolder groupViewHolder;

        if (convertView == null) {


            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_group_order_package, null);

            groupViewHolder = new GroupViewHolder();

            groupViewHolder.mPacketId = (TextView) convertView.findViewById(R.id.textViewPacketId);
            groupViewHolder.mGroupCheckbox= (CheckBox) convertView.findViewById(R.id.checkboxGroupItem);
           groupViewHolder.mLLGroup = (LinearLayout) convertView.findViewById(R.id.LLGroup);
            groupViewHolder.mtextViewAmountPayable= (TextView) convertView.findViewById(R.id.textViewAmountPayable);
            groupViewHolder.mtextViewExpectedDeliverydate=(TextView) convertView.findViewById(R.id.textViewExpectedDeliveryDate);

            groupViewHolder.mGroupCheckbox.setOnCheckedChangeListener(this);
            convertView.setTag(groupViewHolder);

        }
        else
        {
            groupViewHolder = (GroupViewHolder) convertView.getTag();

        }
        String mGroupPackageId = (String) getGroup(groupPosition);
        orderItemDetail = _listDataChild.get(_listDataHeader.get(groupPosition)).get(0);

        if(mGroupPackageId != null)
        {
           if (!isExpanded)
           {

               groupViewHolder.mtextViewExpectedDeliverydate.setVisibility(View.GONE);
           }else {

               groupViewHolder.mtextViewExpectedDeliverydate.setVisibility(View.VISIBLE);
               groupViewHolder.mtextViewExpectedDeliverydate.setText("EDD : " + orderItemDetail.getItemDeliveryDate());
           }


            if (mGroupPackageId.equalsIgnoreCase("Unknown")||mGroupPackageId.equalsIgnoreCase(""))
            {
                groupViewHolder.mGroupCheckbox.setVisibility(View.GONE);


            }
            else {
                groupViewHolder.mGroupCheckbox.setVisibility(View.VISIBLE);
            }


            groupViewHolder.mPacketId.setText(mGroupPackageId);
            groupViewHolder.mtextViewAmountPayable.setText(mContext.getResources().getString(R.string.symbol_rupee)+orderItemDetail.getPacakgeItemsAmount());

        }

        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(isChecked)
        {
            //selectedIds.add(recIdArr.get(reportslistview.getPositionForView(cBox)));
           // orderItemDetail.getPacakgeItemsAmount();
            //System.out.println("position "+reportslistview.getPositionForView(cBox));
           // Toast.makeText(mContext, _listDataChild.get(OrderDetailsFragment.expListView.getPositionForView(buttonView))+""+isChecked, Toast.LENGTH_SHORT).show();
        }
        else
        {
           // selectedIds.remove(recIdArr.get(reportslistview.getPositionForView(cBox)));
        }



    }



    public final class ChildViewHolder {

        TextView mItemId,mItemPrice;
        ImageView mItemImage;

        CheckBox mChildCheckbox;
    }
    public final class GroupViewHolder {

        TextView mPacketId,mtextViewAmountPayable,mtextViewExpectedDeliverydate;

        CheckBox mGroupCheckbox;
        LinearLayout mLLGroup;

    }

}