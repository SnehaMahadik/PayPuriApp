package com.benow.models;

public class BillingAddress {

    private String id;
    private String addrLine1;
    private String addrLine2;
    private String addrLine3;
    private String city;
    private String state;
    private String postCode;
    private String country;
    private String addressShortName;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The addrLine1
     */
    public String getAddrLine1() {
        return addrLine1;
    }

    /**
     * @param addrLine1 The addrLine1
     */
    public void setAddrLine1(String addrLine1) {
        this.addrLine1 = addrLine1;
    }

    /**
     * @return The addrLine2
     */
    public String getAddrLine2() {
        return addrLine2;
    }

    /**
     * @param addrLine2 The addrLine2
     */
    public void setAddrLine2(String addrLine2) {
        this.addrLine2 = addrLine2;
    }

    /**
     * @return The addrLine3
     */
    public String getAddrLine3() {
        return addrLine3;
    }

    /**
     * @param addrLine3 The addrLine3
     */
    public void setAddrLine3(String addrLine3) {
        this.addrLine3 = addrLine3;
    }

    /**
     * @return The city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return The state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return The postCode
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * @param postCode The postCode
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * @return The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return The addressShortName
     */
    public String getAddressShortName() {
        return addressShortName;
    }

    /**
     * @param addressShortName The addressShortName
     */
    public void setAddressShortName(String addressShortName) {
        this.addressShortName = addressShortName;
    }

}