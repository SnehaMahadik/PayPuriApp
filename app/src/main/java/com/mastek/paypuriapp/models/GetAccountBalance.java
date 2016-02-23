package com.mastek.paypuriapp.models;

/**
 * Created by sneha13498 on 2/22/2016.
 */
public class GetAccountBalance {
    public String getAccountRefNumber() {
        return accountRefNumber;
    }

    public void setAccountRefNumber(String accountRefNumber) {
        this.accountRefNumber = accountRefNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    private String accountRefNumber;
    private double accountBalance;
}
