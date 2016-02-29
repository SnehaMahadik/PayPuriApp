package com.benow.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.benow.R;
import com.benow.fragments.OrderListFragment;
import com.benow.fragments.QuickPayContactsFragment;
import com.benow.models.PeerContact;
import com.benow.models.QuickPayPhoneContact;
import com.google.android.gms.plus.model.people.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sneha13498 on 2/26/2016.
 */
public class QuickPayContactsListAdapter extends RecyclerView.Adapter<QuickPayContactsListAdapter.ContactsListView>  {

    private Context mContext;
    private List<String> mMobileNumbersUsingApp;
    //private ContactClickListener orderListClickListener;
    private ArrayList<QuickPayPhoneContact> mContacts;
    String imnage;
    Cursor phonesCursor;
    Person.Urls urls;
    private QuickPayContactsFragment orderListClickListener;


    public QuickPayContactsListAdapter(Context mContext, List<String> mobileNumbersUsingApp, ArrayList<QuickPayPhoneContact> mContacts) {
        this.mContext = mContext;
        this.mContacts=mContacts;
        this.mMobileNumbersUsingApp =mobileNumbersUsingApp;
    }




    @Override
    public QuickPayContactsListAdapter.ContactsListView onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_listitem, null);
        ContactsListView viewHolder = new ContactsListView(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QuickPayContactsListAdapter.ContactsListView holder, int position) {
       // QuickPayContacts mQuickPayContacts = mContacts.get(position);

        if (mMobileNumbersUsingApp.contains(mContacts.get(position).getMobileNo()))
        {
            holder.mTextViewContactName.setText(mContacts.get(position).getContactName()+"  yes");
            holder.mTextViewContactNo.setText(mContacts.get(position).getMobileNo());


           // holder.imageViewContactPic.setImageBitmap(pic());
            //CircularImageview.makeCircularImage(holder.imageViewContactPic, mContext, (mContacts.get(position).getMobileNo()));

            holder.imageViewStatus.setVisibility(View.VISIBLE);
        }
        else {

            holder.mTextViewContactName.setText(mContacts.get(position).getContactName());
            holder.mTextViewContactNo.setText(mContacts.get(position).getMobileNo());

            holder.imageViewStatus.setVisibility(View.GONE);
        }



     //   CircularImageview.makeCircularImage(holder.imageViewContactPic, mContext);

    }



    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public void setOnOrderListClickListener(QuickPayContactsFragment mQuickPayContactsFragment){
        try {
            orderListClickListener = mQuickPayContactsFragment;
        } catch (ClassCastException e)
        {
            throw new ClassCastException(mContext.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    public class ContactsListView extends RecyclerView.ViewHolder {
        protected ImageView imageViewContactPic,imageViewStatus;
        protected TextView mTextViewContactName,mTextViewContactNo;
        protected RelativeLayout rlRowOrderList;



        public ContactsListView(final View itemView) {
            super(itemView);
            this.imageViewContactPic = (ImageView) itemView.findViewById(R.id.imageViewContactPic);
            this.imageViewStatus=(ImageView) itemView.findViewById(R.id.imageViewStatus);
            this.mTextViewContactName = (TextView) itemView.findViewById(R.id.textViewContactName);
            this.mTextViewContactNo = (TextView) itemView.findViewById(R.id.textViewMobileNo);

            this.rlRowOrderList = (RelativeLayout) itemView.findViewById(R.id.rlRowOrderList);

            rlRowOrderList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    orderListClickListener.onOrderListClick(getAdapterPosition());
                }
            });
        }

    }
}
