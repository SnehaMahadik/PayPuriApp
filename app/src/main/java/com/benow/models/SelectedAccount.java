package com.benow.models;

public class SelectedAccount {

    private String accountRefNumber;
    private String maskedAccountNumber;
    private String ifsc;
    private String mmid;
    private String name;
    private Boolean aeba;

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
     * @return The maskedAccountNumber
     */
    public String getMaskedAccountNumber() {
        return maskedAccountNumber;
    }

    /**
     * @param maskedAccountNumber The maskedAccountNumber
     */
    public void setMaskedAccountNumber(String maskedAccountNumber) {
        this.maskedAccountNumber = maskedAccountNumber;
    }

    /**
     * @return The ifsc
     */
    public String getIfsc() {
        return ifsc;
    }

    /**
     * @param ifsc The ifsc
     */
    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
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
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The aeba
     */
    public Boolean getAeba() {
        return aeba;
    }

    /**
     * @param aeba The aeba
     */
    public void setAeba(Boolean aeba) {
        this.aeba = aeba;
    }


}