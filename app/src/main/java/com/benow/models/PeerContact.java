package com.benow.models;


import android.os.Parcel;
import android.os.Parcelable;

public class PeerContact implements Parcelable {

    private Integer id;
    private String contactName;
    private String mobileNumber;
    private String mmid;
    private String accountNumber;
    private String ifscCode;
    private String contactType;
    private String appUserId;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName The contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return The mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber The mobileNumber
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * @return The mmid
     */
    public String getMmid() {
        return mmid;
    }

    /**
     * @param mmid The mmid
     */
    public void setMmid(String mmid) {
        this.mmid = mmid;
    }

    /**
     * @return The accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber The accountNumber
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return The ifscCode
     */
    public String getIfscCode() {
        return ifscCode;
    }

    /**
     * @param ifscCode The ifscCode
     */
    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    /**
     * @return The contactType
     */
    public String getContactType() {
        return contactType;
    }

    /**
     * @param contactType The contactType
     */
    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    /**
     * @return The appUserId
     */
    public String getAppUserId() {
        return appUserId;
    }

    /**
     * @param appUserId The appUserId
     */
    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }


    protected PeerContact(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        contactName = in.readString();
        mobileNumber = in.readString();
        mmid = in.readString();
        accountNumber = in.readString();
        ifscCode = in.readString();
        contactType = in.readString();
        appUserId = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(contactName);
        dest.writeString(mobileNumber);
        dest.writeString(mmid);
        dest.writeString(accountNumber);
        dest.writeString(ifscCode);
        dest.writeString(contactType);
        dest.writeString(appUserId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PeerContact> CREATOR = new Parcelable.Creator<PeerContact>() {
        @Override
        public PeerContact createFromParcel(Parcel in) {
            return new PeerContact(in);
        }

        @Override
        public PeerContact[] newArray(int size) {
            return new PeerContact[size];
        }
    };
}