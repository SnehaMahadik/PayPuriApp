package com.benow.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sneha13498 on 2/20/2016.
 */
public class Embedded {




    private List<PaymentHistory> paymentHistory = new ArrayList<PaymentHistory>();

    /**
     *
     * @return
     * The paymentHistory
     */
    public List<PaymentHistory> getPaymentHistory() {
        return paymentHistory;
    }

    /**
     *
     * @param paymentHistory
     * The paymentHistory
     */
    public void setPaymentHistory(List<PaymentHistory> paymentHistory) {
        this.paymentHistory = paymentHistory;
    }

}
