package com.benow.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class QuickPayContacts implements Parcelable {

    private List<String> mobileNumbersUsingApp = new ArrayList<String>();
    private List<PeerContact> peerContacts = new ArrayList<PeerContact>();

    /**
     * @return The mobileNumbersUsingApp
     */
    public List<String> getMobileNumbersUsingApp() {
        return mobileNumbersUsingApp;
    }

    /**
     * @param mobileNumbersUsingApp The mobileNumbersUsingApp
     */
    public void setMobileNumbersUsingApp(List<String> mobileNumbersUsingApp) {
        this.mobileNumbersUsingApp = mobileNumbersUsingApp;
    }

    /**
     * @return The peerContacts
     */
    public List<PeerContact> getPeerContacts() {
        return peerContacts;
    }

    /**
     * @param peerContacts The peerContacts
     */
    public void setPeerContacts(List<PeerContact> peerContacts) {
        this.peerContacts = peerContacts;
    }


    protected QuickPayContacts(Parcel in) {
        if (in.readByte() == 0x01) {
            mobileNumbersUsingApp = new ArrayList<String>();
            in.readList(mobileNumbersUsingApp, Object.class.getClassLoader());
        } else {
            mobileNumbersUsingApp = null;
        }
        if (in.readByte() == 0x01) {
            peerContacts = new ArrayList<PeerContact>();
            in.readList(peerContacts, PeerContact.class.getClassLoader());
        } else {
            peerContacts = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mobileNumbersUsingApp == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(mobileNumbersUsingApp);
        }
        if (peerContacts == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(peerContacts);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<QuickPayContacts> CREATOR = new Parcelable.Creator<QuickPayContacts>() {
        @Override
        public QuickPayContacts createFromParcel(Parcel in) {
            return new QuickPayContacts(in);
        }

        @Override
        public QuickPayContacts[] newArray(int size) {
            return new QuickPayContacts[size];
        }
    };
}