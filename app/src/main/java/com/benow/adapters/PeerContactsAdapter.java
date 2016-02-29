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
import com.benow.models.PeerContact;
import com.benow.models.QuickPayPhoneContact;
import com.benow.utils.CircularImageview;
import com.google.android.gms.plus.model.people.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sneha13498 on 2/26/2016.
 */
public class PeerContactsAdapter extends RecyclerView.Adapter<PeerContactsAdapter.ContactsListView>  {

    private Context mContext;
    private List<String> mMobileNumbersUsingApp;
    //private ContactClickListener orderListClickListener;
    private ArrayList<QuickPayPhoneContact> mContacts;
    String imnage;
    Cursor phonesCursor;
    Person.Urls urls;
    private List<PeerContact> mPeerContactList;
    private String mBankImageUrl,mMobileImageUrl;


    public PeerContactsAdapter(Context mContext, List<String> mobileNumbersUsingApp, ArrayList<QuickPayPhoneContact> mContacts) {

    }

    public PeerContactsAdapter(Context mContext, List<PeerContact> peerContacts) {
        this.mContext = mContext;
        this.mPeerContactList=peerContacts;
    }


    @Override
    public PeerContactsAdapter.ContactsListView onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.peercontact_item, null);
        ContactsListView viewHolder = new ContactsListView(view);

        mBankImageUrl="https://en.wikipedia.org/wiki/File:SunTrust_Bank,_Gaithersburg,_Maryland,_August_25,_2015.jpg";
                mMobileImageUrl="http://icons.iconarchive.com/icons/pelfusion/long-shadow-media/256/Mobile-Tablet-icon.png";
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactsListView holder, int position) {
        if (mPeerContactList.get(position).getContactType().equalsIgnoreCase("Mobile"))
        {
            holder.mTextViewContactName.setText(mPeerContactList.get(position).getContactName());
            holder.mTextViewMobileBank.setText(mPeerContactList.get(position).getMobileNumber());
            holder.imageViewStatus.setImageResource(R.drawable.ic_stay_primary_portrait_black_24dp);


        }
        else {
            holder.mTextViewContactName.setText(mPeerContactList.get(position).getContactName());
            holder.mTextViewMobileBank.setText(mPeerContactList.get(position).getAccountNumber());
            holder.imageViewStatus.setImageResource(R.drawable.ic_domain_black_24dp);

        }


    }





    @Override
    public int getItemCount() {
        return mPeerContactList.size();
    }



    public class ContactsListView extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        protected ImageView imageViewStatus;
        protected TextView mTextViewContactName,mTextViewMobileBank;
        protected RelativeLayout rlRowOrderList;



        public ContactsListView(View itemView) {
            super(itemView);
            this.imageViewStatus=(ImageView) itemView.findViewById(R.id.imageViewContactPic);
            this.mTextViewContactName = (TextView) itemView.findViewById(R.id.textViewContactName);
            this.mTextViewMobileBank = (TextView) itemView.findViewById(R.id.textViewMobileBank);

            this.rlRowOrderList = (RelativeLayout) itemView.findViewById(R.id.rlRowOrderList);
            rlRowOrderList.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // orderListClickListener.onOrderListClick(getAdapterPosition());
        }
    }
}
