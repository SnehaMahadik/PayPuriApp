package com.benow.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class GetAllOrdersResponse implements Parcelable {

    private ArrayList<Order> orders = new ArrayList<Order>();

    /**
     * @return The orders
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * @param orders The orders
     */
    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    protected GetAllOrdersResponse(Parcel in) {
        if (in.readByte() == 0x01) {
            orders = new ArrayList<Order>();
            in.readList(orders, Order.class.getClassLoader());
        } else {
            orders = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (orders == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(orders);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<GetAllOrdersResponse> CREATOR = new Parcelable.Creator<GetAllOrdersResponse>() {
        @Override
        public GetAllOrdersResponse createFromParcel(Parcel in) {
            return new GetAllOrdersResponse(in);
        }

        @Override
        public GetAllOrdersResponse[] newArray(int size) {
            return new GetAllOrdersResponse[size];
        }
    };
}