package com.benow.models;

import java.util.HashMap;
import java.util.Map;


public class GetAccountResponse {

    private String accountRefNumber;
    private Double accountBalance;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The accountRefNumber
     */
    public String getAccountRefNumber() {
        return accountRefNumber;
    }

    /**
     * @param accountRefNumber The accountRefNumber
     */
    public void setAccountRefNumber(String accountRefNumber) {
        this.accountRefNumber = accountRefNumber;
    }

    /**
     * @return The accountBalance
     */
    public Double getAccountBalance() {
        return accountBalance;
    }

    /**
     * @param accountBalance The accountBalance
     */
    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}