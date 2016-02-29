package com.benow.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sneha13498 on 2/26/2016.
 */
public class QuickPayPhoneContact implements Parcelable {
    private  String mContactName;
    private String mMobileNo;
    private Uri mImageUrl;

    public QuickPayPhoneContact() {

    }

    public String getContactName() {
        return mContactName;
    }

    public void setContactName(String mContactName) {
        this.mContactName = mContactName;
    }

    public String getMobileNo() {
        return mMobileNo;
    }

    public void setMobileNo(String mMobileNo) {
        this.mMobileNo = mMobileNo;
    }

    public Uri getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(Uri mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public QuickPayPhoneContact(Parcel in) {
        mContactName = in.readString();
        mMobileNo = in.readString();
        mImageUrl = (Uri) in.readValue(Uri.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mContactName);
        dest.writeString(mMobileNo);
        dest.writeValue(mImageUrl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<QuickPayPhoneContact> CREATOR = new Parcelable.Creator<QuickPayPhoneContact>() {
        @Override
        public QuickPayPhoneContact createFromParcel(Parcel in) {
            return new QuickPayPhoneContact(in);
        }

        @Override
        public QuickPayPhoneContact[] newArray(int size) {
            return new QuickPayPhoneContact[size];
        }
    };
}