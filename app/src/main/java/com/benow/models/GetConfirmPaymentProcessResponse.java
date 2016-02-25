package com.benow.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetConfirmPaymentProcessResponse {

    private PaymentInvoice paymentInvoice;
    private List<Account> accounts = new ArrayList<Account>();
    private String mpin;
    private SelectedAccount selectedAccount;
    private String mobileNumber;
    private Object referralCode;
    private String selectedPackateIds;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The paymentInvoice
     */
    public PaymentInvoice getPaymentInvoice() {
        return paymentInvoice;
    }

    /**
     * @param paymentInvoice The paymentInvoice
     */
    public void setPaymentInvoice(PaymentInvoice paymentInvoice) {
        this.paymentInvoice = paymentInvoice;
    }

    /**
     * @return The accounts
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * @param accounts The accounts
     */
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    /**
     * @return The mpin
     */
    public String getMpin() {
        return mpin;
    }

    /**
     * @param mpin The mpin
     */
    public void setMpin(String mpin) {
        this.mpin = mpin;
    }

    /**
     * @return The selectedAccount
     */
    public SelectedAccount getSelectedAccount() {
        return selectedAccount;
    }

    /**
     * @param selectedAccount The selectedAccount
     */
    public void setSelectedAccount(SelectedAccount selectedAccount) {
        this.selectedAccount = selectedAccount;
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
     * @return The referralCode
     */
    public Object getReferralCode() {
        return referralCode;
    }

    /**
     * @param referralCode The referralCode
     */
    public void setReferralCode(Object referralCode) {
        this.referralCode = referralCode;
    }

    /**
     * @return The selectedPackateIds
     */
    public String getSelectedPackateIds() {
        return selectedPackateIds;
    }

    /**
     * @param selectedPackateIds The selectedPackateIds
     */
    public void setSelectedPackateIds(String selectedPackateIds) {
        this.selectedPackateIds = selectedPackateIds;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}